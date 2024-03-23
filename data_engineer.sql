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
