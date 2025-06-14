-- Reset sequences for manual ID insertion
ALTER TABLE departments ALTER COLUMN department_id RESTART WITH 1;
ALTER TABLE specialties ALTER COLUMN specialty_id RESTART WITH 1;
ALTER TABLE groups_ ALTER COLUMN group_id RESTART WITH 1;
ALTER TABLE disciplines ALTER COLUMN discipline_id RESTART WITH 1;
ALTER TABLE classes ALTER COLUMN class_id RESTART WITH 1;
ALTER TABLE tasks ALTER COLUMN task_id RESTART WITH 1;
ALTER TABLE answers ALTER COLUMN answer_id RESTART WITH 1;
ALTER TABLE lessons ALTER COLUMN lesson_id RESTART WITH 1;

INSERT INTO departments (department_name, description) VALUES
('Computer Science', 'Focuses on programming, AI, and cybersecurity.'),
('Electrical Engineering', 'Covers electronics, power systems, and circuits.'),
('Business Administration', 'Teaches management, finance, and marketing.');

INSERT INTO specialties (specialty_name, department) VALUES
-- Computer Science Department (Assumed department_id = 1)
('Software Engineering', 1),
('Cybersecurity', 1),

-- Electrical Engineering Department (Assumed department_id = 2)
('Power Systems', 2),
('Telecommunications', 2),

-- Business Administration Department (Assumed department_id = 3)
('Finance', 3),
('Marketing', 3);

INSERT INTO groups_ (specialty, group_name, creation_date) VALUES
-- Computer Science Specialties
(1, 'CS-101', '2023-09-01'),
(2, 'CS-201', '2022-09-01'),

-- Electrical Engineering Specialties
(3, 'EE-101', '2023-09-01'),
(4, 'EE-201', '2022-09-01'),

-- Business Administration Specialties
(5, 'BA-101', '2023-09-01'),
(6, 'BA-201', '2022-09-01');
--insert into profiles (email, encrypted_password, firstname, lastname, creation_date, birthday_date) values ('email@mail.com', 'psswd', 'firstname', 'lastname', '2003-04-15', '2003-04-15');
INSERT INTO profiles (email, encrypted_password, firstname, lastname, creation_date, birthday_date)
VALUES ('alice.smith@example.com', 'hashed_password_1', 'Alice', 'Smith', CURRENT_DATE, '2003-04-15');

INSERT INTO profiles (email, encrypted_password, firstname, lastname, creation_date, birthday_date)
VALUES ('bob.johnson@example.com', 'hashed_password_2', 'Bob', 'Johnson',CURRENT_DATE, '2002-07-21');

INSERT INTO profiles (email, encrypted_password, firstname, lastname, creation_date, birthday_date)
VALUES ('carol.williams@example.com', 'hashed_password_3', 'Carol', 'Williams', CURRENT_DATE, '1990-05-10');

INSERT INTO profiles (email, encrypted_password, firstname, lastname, creation_date, birthday_date)
VALUES ('dave.miller@example.com', 'hashed_password_4', 'Dave', 'Miller',CURRENT_DATE, '1985-09-30');

INSERT INTO profiles (email, encrypted_password, firstname, lastname, creation_date, birthday_date)
VALUES ('arthur.johnson@example.com', 'hashed_password_5', 'Arthur', 'Johnson',CURRENT_DATE, '1983-04-05');

INSERT INTO profiles (email, encrypted_password, firstname, lastname, creation_date, birthday_date)
VALUES ('admin@example.com', 'hashed_password_6', 'Admin', 'Admin',CURRENT_DATE, '1983-04-05');

INSERT INTO students (student_id, group_) VALUES
(1, 1), -- Alice Smith in CS-101
(2, 3); -- Bob Johnson in EE-101

INSERT INTO teachers (teacher_id, department, science_degree) VALUES
(3, 1, 'Bachelor of Science'), -- Carol Williams in Computer Science
(4, 2, 'Master of Science'); -- Dave Miller in Electrical Engineering
--
INSERT INTO disciplines (discipline_name, description) VALUES
('Algorithms and Data Structures', 'Study of algorithms, complexity, and data structures.'),
('Digital Signal Processing', 'Processing of digital signals in electrical systems.'),
('Financial Management', 'Principles of corporate finance and investment.');
--
INSERT INTO classes (teacher, group_, discipline, active, started) VALUES
-- Computer Science Class (Carol Williams teaching CS-101)
(3, 1, 1, true, '2024-02-01'),

-- Electrical Engineering Class (Dave Miller teaching EE-101)
(4, 3, 2, false, '2024-02-01');

INSERT INTO tasks (class_, description,  deadline) VALUES
(1, 'Implement a sorting algorithm.', '2024-03-01'),
(2, 'Analyze a given digital signal.', '2024-03-05');

INSERT INTO answers (task, student, grade, task_delivery_time) VALUES
-- Alice Smith submits the Algorithms task
(2, 2, 85, '2024-02-20 14:30:00'),

-- Bob Johnson submits the Digital Signal Processing task
(1, 1, 90, '2024-02-21 15:45:00');

INSERT INTO lessons (week_day, lesson_number, class_, lesson_type, lesson_link) VALUES
-- Monday, 1st lesson: Algorithms class
(1, 1, 1, 'Laboratory lesson', 'https://university.edu/algorithms-class'),
(1, 2, 1, 'Laboratory lesson', 'https://university.edu/algorithms-class'),
(1, 3, 1, 'Laboratory lesson', 'https://university.edu/algorithms-class'),
(1, 4, 1, 'Laboratory lesson', 'https://university.edu/algorithms-class'),

-- Wednesday, 3rd lesson: DSP class
(3, 3, 2, 'Laboratory lesson', 'https://university.edu/dsp-lab'),
(3, 1, 2, 'Laboratory lesson', 'https://university.edu/dsp-lab'),
(3, 2, 2, 'Laboratory lesson', 'https://university.edu/dsp-lab');

INSERT INTO admins (admin_id) VALUES (6);