DROP ALL OBJECTS;
CREATE TABLE vaccine
	(id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(250) NOT NULL,
	type VARCHAR(250) NOT NULL,
	storage_temperature INTEGER NOT NULL,
	age_limit_min SMALLINT NOT NULL,
	age_limit_max SMALLINT NOT NULL,
	shots_needed TINYINT NOT NULL,
	next_shot_id TINYINT NOT NULL,
	days_until_next_shot SMALLINT NOT NULL,
	fully_vaccinated_time_period SMALLINT NOT NULL,
	applicable BOOLEAN NOT NULL,
	applicable_for_pregnant BOOLEAN NOT NULL,
	applicable_for_chronic BOOLEAN NOT NULL);