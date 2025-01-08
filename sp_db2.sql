CREATE OR REPLACE PROCEDURE PSAKPROD.CALCULATE_UNEARN_KBM()
LANGUAGE SQL
MODIFIES SQL DATA
BEGIN

    DECLARE cursor_done INT DEFAULT 0;

    -- Variables to store each column fetched from the cursor
    DECLARE c_INSURANCE_CONTRACT_ID VARCHAR(36);
    DECLARE c_INSURANCE_CONTRACT_ID_N VARCHAR(36);
    DECLARE c_ENDORSEMENT_TYPE_CD VARCHAR(2);
    DECLARE c_ENDORSEMENT_DT DATE;
    DECLARE c_BEGIN_COV_DT DATE;
    DECLARE c_END_COV_DT DATE;
    DECLARE c_PREM_AMT DECIMAL(30, 5);
    DECLARE c_PREM_AMT_N DECIMAL(30, 5);
    DECLARE c_IS_CHN_COV INT;
    DECLARE c_IS_SUBSTANTIAL INT;
    DECLARE c_RATIO DECIMAL(30, 5);
    DECLARE c_RESULT DECIMAL(30, 5);
    DECLARE c_ROW_NUM INT;

    -- Variables for conditional BEGIN_COV_DT and END_COV_DT handling
    DECLARE BEGIN_COV_DT_N DATE;
    DECLARE END_COV_DT_N DATE;
    
    DECLARE previous_result DECIMAL(30, 2) DEFAULT 0.0;
    DECLARE current_contract_id VARCHAR(36) DEFAULT '';

    -- Declare the cursor with the updated query
    DECLARE result_cursor CURSOR FOR
    
    SELECT 
        INSURANCE_CONTRACT_ID, 
        ENDORSEMENT_TYPE_CD, 
        ENDORSEMENT_DT, 
        BEGIN_COV_DT, 
        END_COV_DT, 
        PREM_AMT, 
        PREM_AMT_N, 
        IS_CHN_COV,
        IS_SUBSTANTIAL,
        ROW_NUM,
        CASE 
            WHEN num > 0 THEN INSURANCE_CONTRACT_ID || '/' || LPAD(num, 3, '0') 
            ELSE INSURANCE_CONTRACT_ID 
        END AS INSURANCE_CONTRACT_ID_N
    FROM (
        SELECT 
            INSURANCE_CONTRACT_ID, 
            ENDORSEMENT_TYPE_CD, 
            ENDORSEMENT_DT, 
            BEGIN_COV_DT, 
            END_COV_DT, 
            PREM_AMT, 
            PREM_AMT_N, 
            IS_CHN_COV,
            IS_SUBSTANTIAL,
            ROW_NUM,
            SUM(IS_SUBSTANTIAL) OVER (PARTITION BY INSURANCE_CONTRACT_ID, IS_SUBSTANTIAL ORDER BY ENDORSEMENT_DT) AS num
        FROM PSAKPROD.STG_OBJECT_KBM_TEMP
        ORDER BY INSURANCE_CONTRACT_ID, ENDORSEMENT_DT, IS_SUBSTANTIAL
    ) 
    ORDER BY INSURANCE_CONTRACT_ID, ENDORSEMENT_DT, IS_SUBSTANTIAL;

    -- Handler to set cursor_done to 1 when no more rows are found
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET cursor_done = 1;

    -- Open the cursor
    OPEN result_cursor;

    -- Loop to fetch each row from the cursor and calculate the result
    result_loop: LOOP
        -- Fetch the next row into the variables
        FETCH result_cursor INTO c_INSURANCE_CONTRACT_ID, c_ENDORSEMENT_TYPE_CD, c_ENDORSEMENT_DT, c_BEGIN_COV_DT, c_END_COV_DT, c_PREM_AMT, c_PREM_AMT_N, c_IS_CHN_COV, c_IS_SUBSTANTIAL, c_ROW_NUM, c_INSURANCE_CONTRACT_ID_N;

        -- Exit the loop if no more rows
        IF cursor_done = 1 THEN
            LEAVE result_loop;
        END IF;

        -- Reset previous_result and BEGIN_COV_DT_N, END_COV_DT_N if processing a new insurance contract
        IF current_contract_id <> c_INSURANCE_CONTRACT_ID THEN
            SET previous_result = 0.0;
            SET current_contract_id = c_INSURANCE_CONTRACT_ID;
            SET BEGIN_COV_DT_N = c_BEGIN_COV_DT;
            SET END_COV_DT_N = c_END_COV_DT;
        END IF;

        -- Logic to set or update BEGIN_COV_DT_N and END_COV_DT_N based on IS_SUBSTANTIAL
        IF c_IS_SUBSTANTIAL = 1 THEN
            -- Save current BEGIN_COV_DT and END_COV_DT to BEGIN_COV_DT_N and END_COV_DT_N
            SET BEGIN_COV_DT_N = c_BEGIN_COV_DT;
            SET END_COV_DT_N = c_END_COV_DT;
        ELSE
            -- Update c_BEGIN_COV_DT and c_END_COV_DT with the saved values BEGIN_COV_DT_N and END_COV_DT_N
            SET c_BEGIN_COV_DT = BEGIN_COV_DT_N;
            SET c_END_COV_DT = END_COV_DT_N;
        END IF;

        -- Calculate c_RATIO after setting BEGIN_COV_DT and END_COV_DT values
        IF DAYS(c_END_COV_DT) - DAYS(c_BEGIN_COV_DT) = 0 THEN
            SET c_RATIO = 0;
        ELSE
            SET c_RATIO = ROUND(CAST((DAYS(c_END_COV_DT) - DAYS(c_ENDORSEMENT_DT)) * 1.0 / (DAYS(c_END_COV_DT) - DAYS(c_BEGIN_COV_DT)) AS DOUBLE), 2);
        END IF;

        -- Calculate result based on updated Ratio and IS_CHN_COV conditions
        IF (c_RATIO > 0 AND c_RATIO < 1) AND c_IS_CHN_COV = 1 THEN
            SET c_RESULT = -1 * previous_result * c_RATIO;
        ELSEIF (c_RATIO = 1 OR c_RATIO <= 0) AND c_IS_CHN_COV = 1 THEN
            SET c_RESULT = c_PREM_AMT - previous_result;
        ELSE
            SET c_RESULT = c_PREM_AMT_N;
        END IF;

        -- Update the previous_result for the next iteration
        SET previous_result = c_RESULT;

        -- Insert the result into the permanent table with updated columns
        INSERT INTO PSAKPROD.CALCULATE_UNEARN_TEMP (
            INSURANCE_CONTRACT_ID, ENDORSEMENT_TYPE_CD, ENDORSEMENT_DT, BEGIN_COV_DT, END_COV_DT, PREM_AMT, PREM_AMT_N, RATIO, RESULT, INSURANCE_CONTRACT_ID_N, ROW_NUM
        ) VALUES (
            c_INSURANCE_CONTRACT_ID, c_ENDORSEMENT_TYPE_CD, c_ENDORSEMENT_DT, c_BEGIN_COV_DT, c_END_COV_DT, c_PREM_AMT, c_PREM_AMT_N, c_RATIO, c_RESULT, c_INSURANCE_CONTRACT_ID_N, c_ROW_NUM
        );
    END LOOP;

    -- Close the cursor after processing
    CLOSE result_cursor;

END;
