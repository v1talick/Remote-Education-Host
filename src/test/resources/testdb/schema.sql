CREATE TYPE public.lesson_type_enum AS ENUM
    ('Lecture', 'Laboratory lesson', 'Practical lesson');

CREATE TYPE public.science_degree_enum AS ENUM
    ('Associate of Science', 'Bachelor of Science', 'Master of Science', 'Doctor of Philosophy', 'Doctor of Science', 'Professional Doctorate', 'None');

DROP TABLE IF EXISTS departments;
CREATE TABLE IF NOT EXISTS departments
(
    department_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    department_name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT departments_department_name_key UNIQUE (department_name)
);

DROP TABLE IF EXISTS specialties;
CREATE TABLE IF NOT EXISTS specialties
(
    specialty_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    specialty_name VARCHAR(255) NOT NULL,
    department BIGINT NOT NULL,
    CONSTRAINT specialties_department_fkey FOREIGN KEY (department) REFERENCES departments (department_id) ON DELETE NO ACTION
);

DROP TABLE IF EXISTS groups_;
CREATE TABLE IF NOT EXISTS groups_
(
    group_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    specialty BIGINT NOT NULL,
    group_name VARCHAR(10) NOT NULL,
    creation_date DATE NOT NULL,
    CONSTRAINT name_unique UNIQUE (group_name),
    CONSTRAINT groups_specialty_fkey FOREIGN KEY (specialty) REFERENCES specialties (specialty_id) ON DELETE NO ACTION
);

DROP TABLE IF EXISTS profiles;
CREATE TABLE IF NOT EXISTS profiles
(
    profile_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    encrypted_password VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    creation_date DATE DEFAULT CURRENT_DATE,
    birthday_date DATE NOT NULL,
    CONSTRAINT profile_email_key UNIQUE (email),
    CONSTRAINT profiles_email_check CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$')
);

DROP TABLE IF EXISTS students;
CREATE TABLE IF NOT EXISTS students
(
    student_id BIGINT NOT NULL PRIMARY KEY,
    group_ BIGINT NOT NULL,
    CONSTRAINT students_group_fkey FOREIGN KEY (group_) REFERENCES groups_ (group_id) ON DELETE NO ACTION,
    CONSTRAINT students_student_id_fkey FOREIGN KEY (student_id) REFERENCES profiles (profile_id) ON DELETE NO ACTION
);

DROP TABLE IF EXISTS teachers;
CREATE TABLE IF NOT EXISTS teachers
(
    teacher_id BIGINT NOT NULL PRIMARY KEY,
    department BIGINT,
    science_degree VARCHAR(255) NOT NULL DEFAULT 'None',
    CONSTRAINT teachers_teacher_id_fkey FOREIGN KEY (teacher_id) REFERENCES profiles (profile_id) ON DELETE NO ACTION,
    CONSTRAINT teachers_department_fkey FOREIGN KEY (department) REFERENCES departments (department_id) ON DELETE NO ACTION
);

DROP TABLE IF EXISTS disciplines;
CREATE TABLE IF NOT EXISTS disciplines
(
    discipline_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    discipline_name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    CONSTRAINT disciplines_discipline_name_key UNIQUE (discipline_name)
);

DROP TABLE IF EXISTS classes;
CREATE TABLE IF NOT EXISTS classes
(
    class_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    teacher BIGINT NOT NULL,
    group_ BIGINT NOT NULL,
    discipline BIGINT NOT NULL,
    active BOOLEAN NOT NULL DEFAULT false,
    started DATE DEFAULT CURRENT_DATE,
    CONSTRAINT classes_unique_const UNIQUE (teacher, group_, discipline),
    CONSTRAINT classes_discipline_fkey FOREIGN KEY (discipline) REFERENCES disciplines (discipline_id) ON DELETE NO ACTION,
    CONSTRAINT classes_group_fkey FOREIGN KEY (group_) REFERENCES groups_ (group_id) ON DELETE NO ACTION,
    CONSTRAINT classes_teacher_fkey FOREIGN KEY (teacher) REFERENCES teachers (teacher_id) ON DELETE NO ACTION
);

DROP TABLE IF EXISTS tasks;
CREATE TABLE IF NOT EXISTS tasks
(
    task_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    class_ BIGINT NOT NULL,
    description TEXT,
    file_path TEXT,
    deadline DATE,
    CONSTRAINT tasks_file_path_key UNIQUE (file_path),
    CONSTRAINT tasks_class_fkey FOREIGN KEY (class_) REFERENCES classes (class_id) ON DELETE NO ACTION,
    CONSTRAINT tasks_file_path_check CHECK (file_path ~ '^/uploads/.*\\.(txt|pdf|docs|jpg|png)$')
);

DROP TABLE IF EXISTS answers;
CREATE TABLE IF NOT EXISTS answers
(
    answer_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    task BIGINT NOT NULL,
    student BIGINT NOT NULL,
    grade INT,
    file_path TEXT,
    task_delivery_time TIMESTAMP,
    CONSTRAINT answer_unique_const UNIQUE (task, student),
    CONSTRAINT answers_file_path_key UNIQUE (file_path),
    CONSTRAINT answers_student_fkey FOREIGN KEY (student) REFERENCES students (student_id) ON DELETE NO ACTION,
    CONSTRAINT answers_task_fkey FOREIGN KEY (task) REFERENCES tasks (task_id) ON DELETE NO ACTION,
    CONSTRAINT answers_file_path_check CHECK (file_path ~ '^/uploads/.*\\.(txt|pdf|docs|jpg|png)$'),
    CONSTRAINT answers_grade_check CHECK (grade > 0 AND grade < 101)
);

DROP TABLE IF EXISTS lessons;

CREATE TABLE IF NOT EXISTS lessons
(
    lesson_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    week_day INT NOT NULL,
    lesson_number INT NOT NULL,
    class_ BIGINT NOT NULL,
    lesson_type lesson_type_enum NOT NULL, -- Changed from enum to VARCHAR
    lesson_link VARCHAR(255), -- Removed collate
    CONSTRAINT lessons_class_fkey FOREIGN KEY (class_) REFERENCES classes (class_id) ON DELETE NO ACTION,
    CONSTRAINT chk_lesson_link CHECK (lesson_link ~ '^(https?:\/\/)?([a-zA-Z0-9\-]+\.)+[a-zA-Z]{2,}(:\d+)?(\/[^\s]*)?$'),
    CONSTRAINT lesson_numb_const CHECK (lesson_number > 0 AND lesson_number < 6),
    CONSTRAINT week_day_const CHECK (week_day > 0 AND week_day < 6)
);

