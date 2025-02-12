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

--INSERT INTO profiles (email, encrypted_password, firstname, lastname, birthday_date)
--VALUES ('alice.smith@example.com', 'hashed_password_1', 'Alice', 'Smith', '2003-04-15');
--
--INSERT INTO profiles (email, encrypted_password, firstname, lastname, birthday_date)
--VALUES ('bob.johnson@example.com', 'hashed_password_2', 'Bob', 'Johnson', '2002-07-21');
--
--INSERT INTO profiles (email, encrypted_password, firstname, lastname, birthday_date)
--VALUES ('carol.williams@example.com', 'hashed_password_3', 'Carol', 'Williams', '1990-05-10');
--
--INSERT INTO profiles (email, encrypted_password, firstname, lastname, birthday_date)
--VALUES ('dave.miller@example.com', 'hashed_password_4', 'Dave', 'Miller', '1985-09-30');
--
--
--INSERT INTO students (student_id, group_) VALUES
--(1, 1), -- Alice Smith in CS-101
--(2, 3); -- Bob Johnson in EE-101
--
--INSERT INTO teachers (teacher_id, department, science_degree) VALUES
--(3, 1, 'Bachelor of Science'), -- Carol Williams in Computer Science
--(4, 2, 'Master of Science'); -- Dave Miller in Electrical Engineering
--
--INSERT INTO disciplines (discipline_name, description) VALUES
--('Algorithms and Data Structures', 'Study of algorithms, complexity, and data structures.'),
--('Digital Signal Processing', 'Processing of digital signals in electrical systems.'),
--('Financial Management', 'Principles of corporate finance and investment.');
--
--INSERT INTO classes (teacher, group_, discipline, active, started) VALUES
---- Computer Science Class (Carol Williams teaching CS-101)
--(3, 1, 1, true, '2024-02-01'),
--
---- Electrical Engineering Class (Dave Miller teaching EE-101)
--(4, 3, 2, false, '2024-02-01');
--
--INSERT INTO tasks (class_, description, file_path, deadline) VALUES
--(1, 'Implement a sorting algorithm.', '/uploads/algorithms_hw1.pdf', '2024-03-01'),
--(2, 'Analyze a given digital signal.', '/uploads/dsp_lab1.txt', '2024-03-05');
--
--INSERT INTO answers (task, student, grade, file_path, task_delivery_time) VALUES
---- Alice Smith submits the Algorithms task
--(1, 1, 85, '/uploads/alice_sorting.pdf', '2024-02-20 14:30:00'),
--
---- Bob Johnson submits the Digital Signal Processing task
--(2, 2, 90, '/uploads/bob_dsp_analysis.txt', '2024-02-21 15:45:00');
--
--INSERT INTO lessons (week_day, lesson_number, class_, lesson_type, lesson_link) VALUES
---- Monday, 1st lesson: Algorithms class
--(1, 1, 1, 'Lecture', 'https://university.edu/algorithms-class'),
--
---- Wednesday, 3rd lesson: DSP class
--(3, 3, 2, 'Lab', 'https://university.edu/dsp-lab');
