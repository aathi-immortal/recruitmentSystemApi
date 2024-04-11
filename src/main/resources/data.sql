INSERT INTO loginuser (USERNAME, EMAIL, PASSWORD, ISCOMPANY) VALUES
('aathi', 'aathi@gmail.com', '12345', false),
('John', 'john@example.com', '12345', TRUE),
('Mary', 'mary@example.com', '12345', TRUE),
('Michael', 'michael@example.com', '12345', TRUE),
('Jennifer', 'jennifer@example.com', '12345', TRUE),
('David', 'david@example.com', '12345', TRUE),
('Sarah', 'sarah@example.com', '12345', TRUE),
('Christopher', 'christopher@example.com', '12345', TRUE),
('Jessica', 'jessica@example.com', '12345', TRUE),
('Matthew', 'matthew@example.com', '12345', TRUE),
('Elizabeth', 'elizabeth@example.com', '12345', TRUE);
-- INSERT INTO loginuser (USERNAME, EMAIL, PASSWORD) VALUES
-- ('John', 'john@example.com', '12345');


-- SELECT * FROM loginuser;




-- Inserting a job with various details
INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id)
VALUES ('Software Engineer', 75000.00, 3, 'Java, Python, SQL', '30 days', 1);

-- Another job insertion
INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id)
VALUES ('Data Analyst', 60000.00, 2, 'R, Python, SQL', '60 days', 1);

-- Inserting a job with different skills
INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id)
VALUES ('Web Developer', 65000.00, 4, 'HTML, CSS, JavaScript', '45 days', 1);

-- Inserting a job with a different notice period
INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id)
VALUES ('UX Designer', 70000.00, 3, 'UI/UX Design, Prototyping', '90 days', 2);

-- Inserting a job with a different salary
INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id)
VALUES ('Network Engineer', 80000.00, 5, 'Cisco, Networking Protocols', '60 days', 2);

-- Inserting a job with a different year of skill
INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id)
VALUES ('Business Analyst', 70000.00, 3, 'Business Analysis, Data Analysis', '30 days', 2);

-- Inserting a job with a different notice period
INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id)
VALUES ('DevOps Engineer', 85000.00, 4, 'AWS, Docker, Kubernetes', '45 days', 3);

-- Inserting a job with a different salary
INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id)
VALUES ('Product Manager', 90000.00, 5, 'Product Management, Agile', '60 days', 4);

-- Inserting a job with different skills
INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id)
VALUES ('Quality Assurance Tester', 60000.00, 2, 'Testing, QA Automation', '30 days', 5);

-- Inserting a job with a different year of skill
INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id)
VALUES ('Systems Administrator', 75000.00, 4, 'Linux, Windows Server', '45 days', 5);

-- Inserting a job with a different notice period
INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id)
VALUES ('UI Designer', 65000.00, 3, 'UI Design, Adobe XD', '90 days', 6);

-- Inserting a job with a different salary
INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id)
VALUES ('Database Administrator', 80000.00, 5, 'SQL Server, Oracle', '60 days', 7);

-- Inserting a job with different skills
INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id)
VALUES ('Frontend Developer', 70000.00, 3, 'React, Vue.js, Angular', '30 days', 8);

-- Inserting a job with a different year of skill
INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id)
VALUES ('Backend Developer', 75000.00, 4, 'Node.js, Python, PHP', '45 days', 9);

-- Inserting a job with a different notice period
INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id)
VALUES ('IT Support Specialist', 60000.00, 2, 'Help Desk, Troubleshooting', '90 days', 10);
