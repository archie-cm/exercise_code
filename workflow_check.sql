--cek node workflow/decision flow
select * from workflow.flows f 
where flh_id = '337'

select * from workflow.flows_nodes fn
where fln_flh_id = '337'

select * from workflow.flows_edges fe
where fle_flh_id = '337'

----

QUE = rule condition 
select * from public.rule
where rul_id = '3199';
select * from version_crud.rule_crud rc 
where rul_id_ori  = '3199';

XTR = predefined database / predefine webservice

database
select * from interface.interface_module im 
where imo_id = '736'

select * from interface.interface_module_db 
where db_header_id = '736'

select * from interface.interface_module_db_dtl
where db_header_id  = '736'

webservice 
select * from interface.interface_module im 
where imo_id = '766'

select * from interface.interface_module_wsv 
where imw_header_id  = '766'

D = decision flow 

select * from workflow.flows f 
where flh_id = '333'

select * from workflow.flows_nodes fn
where fln_flh_id = '333'

select * from workflow.flows_edges fe
where fle_flh_id = '333'

RLX = rule parameter update / statement
select * from public.rule
where rul_id = '3199';
select * from version_crud.rule_crud rc 
where rul_id_ori  = '3199';

ADF = advance form / component form
ADFCOLL21
select * from workflow.form_component_header fch 
where fch_code = 'ADFCOLL01'

select * from workflow.form_component_detail fcd 
where fcd_code_hdr = 'ADFCOLL01'


angka aja = form generator
select * from forms.form_master fm 
where fms_id = '405'

--- cara cek posisi workflow
---1. dapatkan informasi rsh_id terlebih dahulu

select * from public.rule_set_history rsh 
order by rsh_id desc

---2. cek process_log
select * from workflow.process_log pl 
where pol_app_id = '158160'
order by pol_id asc
