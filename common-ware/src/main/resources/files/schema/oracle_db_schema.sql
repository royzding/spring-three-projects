
--- create unique constraint with a condition.

--- DROP TABLE holiday_date;

CREATE TABLE holiday_date 
(	id 		INTEGER ALways as Identity primary key, 
	name 	VARCHAR2(50 CHAR), 
	email 	VARCHAR2(50 CHAR), 
	branch 	VARCHAR2(50 CHAR), 
	active  SMALLINT,   ----- 1 or 0
	PRIMARY KEY (id)
);


CREATE UNIQUE INDEX ON HOLIDAY_DATE (CASE WHEN ACTIVE = 1 THEN NAME || '.' || EMAIL END);
