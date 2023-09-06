CREATE TABLE "job" (
    "job_id" bigint PRIMARY KEY AUTO_INCREMENT,
    "name" varchar(100)
);
CREATE TABLE "user" (
   "id" bigint PRIMARY KEY AUTO_INCREMENT,
   "first_name" varchar(100),
   "last_name" varchar(100)
);
