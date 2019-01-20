CREATE TABLE t_user (
  id INT (12) NOT NULL auto_increment,
  user_name VARCHAR (60) NOT NULL ,
  sex INT (3) NOT NULL DEFAULT 1 CHECK (sex in (1, 2)),
  note VARCHAR (256) NOT NULL ,
  PRIMARY KEY (id)
)