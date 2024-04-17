CREATE Table loginuser
(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ISCOMPANY BOOLEAN,
    USERNAME VARCHAR(40),
    EMAIL VARCHAR(40) ,
    PASSWORD VARCHAR(40)
    

);

CREATE TABLE job (
    id INT AUTO_INCREMENT PRIMARY KEY,
    jobName VARCHAR(100),
    jobSalary DECIMAL(10, 2), -- Assuming job salary is in decimal format
    yearOfSkill INT,
    skill VARCHAR(100),
    noticePeriod VARCHAR(50),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES loginuser(ID)
);


-- SELECT * from loginuser;


CREATE Table user_job(
    id INT Auto_Increment PRIMARY KEY,
    user_id INT,
    job_id INT,
    FOREIGN KEY (user_id) REFERENCES loginuser(ID),
    FOREIGN KEY (job_id) REFERENCES job(ID)
);

CREATE TABLE resume (
    id INT AUTO_INCREMENT PRIMARY KEY,
    file_data BLOB NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES loginuser(ID)

);
