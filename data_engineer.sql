-- FIRST_VALUE & LAST_VALUE
SELECT username, posts,
  LAST_VALUE(posts) OVER (
    PARTITION BY username
    ORDER BY posts
    RANGE BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING
  ) as 'most_post'
FROM social_media;

-- result
-- username	posts	most_post
-- aliaabhatt	5	25
-- aliaabhatt	7	25
-- aliaabhatt	9	25
-- aliaabhatt	9	25
-- aliaabhatt	9	25
-- aliaabhatt	13	25
-- aliaabhatt	14	25
-- aliaabhatt	25	25

-- LAG
select
  artist,
  week,
  streams_millions,
  streams_millions - LAG(streams_millions, 1,streams_millions) OVER (
    ORDER BY week
  ) streams_millions_change,
  chart_position,
  LAG(chart_position, 1, chart_position) OVER (
    PARTITION BY artist
    ORDER BY week
  ) - chart_position as 'chart_position_change'
from streams
where artist='Lady Gaga';

-- result
-- artist	week	streams_millions	streams_millions_change	chart_position	chart_position_change
-- Lady Gaga	1	15.4	0.0	106	0
-- Lady Gaga	2	15.2	-0.2	112	-6
-- Lady Gaga	3	16.6	1.4	98	14
-- Lady Gaga	4	21.0	4.4	75	23
-- Lady Gaga	5	64.0	43.0	10	65
-- Lady Gaga	6	36.0	-28.0	24	-14
-- Lady Gaga	7	30.5	-5.5	36	-12
-- Lady Gaga	8	27.0	-3.5	47	-11
