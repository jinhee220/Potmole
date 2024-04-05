BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, potholes;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE potholes (
	pothole_id SERIAL, 
	user_id int NOT NULL,
	latitude decimal(13, 5) NOT NULL,
	longitude decimal(13, 5) NOT NULL,
	current_status varchar(20) NOT NULL,
	reported_date varchar(20) NOT NULL,
	inspected_date varchar(20),
	repaired_date varchar(20),
	CONSTRAINT PK_potholes PRIMARY KEY (pothole_id),
	CONSTRAINT FK_users_potholes FOREIGN KEY (user_id) REFERENCES users (user_id)
	
	
);



COMMIT TRANSACTION;
