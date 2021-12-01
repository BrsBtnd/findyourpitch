
create table users (
	user_id serial primary key, 
	user_first_name varchar(50),
	user_last_name varchar(50),
	user_age_varchar(50),
)

select distinct users.user_id, users.first_name, 
users.last_name, users.age, users.user_role
from users
inner join pitches 
on users.user_id = pitches.user_id

select distinct users.user_id, users.first_name, 
users.last_name, users.age, users.user_role
from users
inner join pitches 
on users.user_id = (
	select distinct user_id 
	from pitches where user_id = 1
)

select pitches.pitch_id, pitches.pitch_name, pitches.pitch_type, 
pitches.user_id, pitches.country, pitches.region, pitches.city
from pitches 
inner join users 
on pitches.user_id = users.user_id
