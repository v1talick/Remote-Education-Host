
--
-- TOC entry 882 (class 1247 OID 25351)
-- Name: lesson_type_enum; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE lesson_type_enum AS ENUM (
    'Lecture',
    'Laboratory lesson',
    'Practical lesson'
);

CREATE TYPE public.science_degree_enum AS ENUM (
    'Associate of Science',
    'Bachelor of Science',
    'Master of Science',
    'Doctor of Philosophy',
    'Doctor of Science',
    'Professional Doctorate',
    'None'
);
--
-- TOC entry 231 (class 1259 OID 25388)
-- Name: answers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.answers (
    answer_id integer NOT NULL,
    task integer NOT NULL,
    student integer NOT NULL,
    grade integer,
    file_path text,
    task_delivery_time timestamp without time zone,
    CONSTRAINT answers_file_path_check CHECK ((file_path ~ '^/uploads/.*\.(txt|pdf|docs|jpg|png)$'::text)),
    CONSTRAINT answers_grade_check CHECK (((grade > 0) AND (grade < 101)))
);

--
-- TOC entry 230 (class 1259 OID 25387)
-- Name: answers_answer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.answers_answer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 3479 (class 0 OID 0)
-- Dependencies: 230
-- Name: answers_answer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

--ALTER SEQUENCE public.answers_answer_id_seq OWNED BY public.answers.answer_id;


--
-- TOC entry 226 (class 1259 OID 25327)
-- Name: classes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.classes (
    class_id integer NOT NULL,
    teacher integer NOT NULL,
    group_ integer NOT NULL,
    discipline integer NOT NULL,
    active boolean DEFAULT false NOT NULL,
    started date DEFAULT CURRENT_DATE
);



--
-- TOC entry 221 (class 1259 OID 25072)
-- Name: departments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.departments (
    department_id integer NOT NULL,
    department_name character varying(255) NOT NULL,
    description text,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);



--
-- TOC entry 220 (class 1259 OID 25071)
-- Name: departments_department_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.departments_department_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3480 (class 0 OID 0)
-- Dependencies: 220
-- Name: departments_department_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

--ALTER SEQUENCE public.departments_department_id_seq OWNED BY public.departments.department_id;


--
-- TOC entry 225 (class 1259 OID 25225)
-- Name: disciplines; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.disciplines (
    discipline_id integer NOT NULL,
    discipline_name character varying(255) NOT NULL,
    description character varying(255) NOT NULL
);


--
-- TOC entry 217 (class 1259 OID 25013)
-- Name: groups_; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.groups_ (
    group_id integer NOT NULL,
    specialty integer NOT NULL,
    group_name character varying(10) NOT NULL,
    creation_date date NOT NULL
);


--
-- TOC entry 216 (class 1259 OID 25012)
-- Name: groups__group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

--ALTER TABLE public.groups_ ALTER COLUMN group_id ADD GENERATED ALWAYS AS IDENTITY (
--    SEQUENCE NAME public.groups__group_id_seq
--    START WITH 1
--    INCREMENT BY 1
--    NO MINVALUE
--    NO MAXVALUE
--    CACHE 1
--);
ALTER TABLE groups_ ALTER COLUMN group_id BIGINT GENERATED ALWAYS AS IDENTITY;


--
-- TOC entry 227 (class 1259 OID 25357)
-- Name: lessons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lessons (
    lesson_id integer NOT NULL,
    week_day integer NOT NULL,
    lesson_number integer NOT NULL,
    class_ integer NOT NULL,
    lesson_type public.lesson_type_enum NOT NULL,
    lesson_link character varying(255),
    CONSTRAINT chk_lesson_link CHECK (((lesson_link)::text ~ '^(https?:\/\/)?([a-zA-Z0-9\-]+\.)+[a-zA-Z]{2,}(:\d+)?(\/[^\s]*)?$'::text)),
    CONSTRAINT lesson_numb_const CHECK (((lesson_number > 0) AND (lesson_number < 6))),
    CONSTRAINT week_day_const CHECK (((week_day > 0) AND (week_day < 6)))
);


--
-- TOC entry 223 (class 1259 OID 25172)
-- Name: profiles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profiles (
    profile_id integer NOT NULL,
    email character varying(255) NOT NULL,
    encrypted_password character varying(255) NOT NULL,
    firstname character varying(255) NOT NULL,
    lastname character varying(255) NOT NULL,
    creation_date date DEFAULT CURRENT_DATE NOT NULL,
    birthday_date date NOT NULL,
    CONSTRAINT profiles_email_check CHECK (((email)::text ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,}$'::text))
);

--
-- TOC entry 222 (class 1259 OID 25171)
-- Name: profile_profile_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.profile_profile_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 3481 (class 0 OID 0)
-- Dependencies: 222
-- Name: profile_profile_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--


--
-- TOC entry 224 (class 1259 OID 25210)
-- Name: profiles_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profiles_roles (
    profile integer NOT NULL,
    role_ integer NOT NULL
);



--
-- TOC entry 219 (class 1259 OID 25037)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    role_id integer NOT NULL,
    role_name character varying(50) NOT NULL
);



--
-- TOC entry 218 (class 1259 OID 25036)
-- Name: roles_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

--ALTER TABLE public.roles ALTER COLUMN role_id ADD GENERATED ALWAYS AS IDENTITY (
--    SEQUENCE NAME public.roles_role_id_seq
--    START WITH 1
--    INCREMENT BY 1
--    NO MINVALUE
--    NO MAXVALUE
--    CACHE 1
--);



--
-- TOC entry 215 (class 1259 OID 25005)
-- Name: specialties; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.specialties (
    specialty_id integer NOT NULL,
    specialty_name character varying(255) NOT NULL,
    department integer NOT NULL
);


--
-- TOC entry 214 (class 1259 OID 25004)
-- Name: specialties_specialty_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

--ALTER TABLE public.specialties ALTER COLUMN specialty_id ADD GENERATED ALWAYS AS IDENTITY (
--    SEQUENCE NAME public.specialties_specialty_id_seq
--    START WITH 1
--    INCREMENT BY 1
--    NO MINVALUE
--    NO MAXVALUE
--    CACHE 1
--);
ALTER TABLE specialties ALTER COLUMN specialty_id BIGINT GENERATED ALWAYS AS IDENTITY;


--
-- TOC entry 232 (class 1259 OID 25610)
-- Name: students; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.students (
    student_id integer NOT NULL,
    group_ integer NOT NULL
);


--
-- TOC entry 229 (class 1259 OID 25371)
-- Name: tasks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tasks (
    task_id integer NOT NULL,
    class_ integer NOT NULL,
    description text,
    file_path text,
    deadline date,
    CONSTRAINT tasks_file_path_check CHECK ((file_path ~ '^/uploads/.*\.(txt|pdf|docs|jpg|png)$'::text))
);


--
-- TOC entry 228 (class 1259 OID 25370)
-- Name: tasks_task_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tasks_task_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 3482 (class 0 OID 0)
-- Dependencies: 228
-- Name: tasks_task_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

--ALTER SEQUENCE public.tasks_task_id_seq OWNED BY public.tasks.task_id;


--
-- TOC entry 233 (class 1259 OID 25630)
-- Name: teachers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.teachers (
    teacher_id integer NOT NULL,
    department integer,
    science_degree public.science_degree_enum DEFAULT 'None'::public.science_degree_enum NOT NULL
);


--
-- TOC entry 3242 (class 2604 OID 25391)
-- Name: answers answer_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answers ALTER COLUMN answer_id SET DEFAULT nextval('public.answers_answer_id_seq'::regclass);


--
-- TOC entry 3235 (class 2604 OID 25075)
-- Name: departments department_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departments ALTER COLUMN department_id SET DEFAULT nextval('public.departments_department_id_seq'::regclass);


--
-- TOC entry 3237 (class 2604 OID 25175)
-- Name: profiles profile_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profiles ALTER COLUMN profile_id SET DEFAULT nextval('public.profile_profile_id_seq'::regclass);


--
-- TOC entry 3241 (class 2604 OID 25374)
-- Name: tasks task_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks ALTER COLUMN task_id SET DEFAULT nextval('public.tasks_task_id_seq'::regclass);


--
-- TOC entry 3471 (class 0 OID 25388)
-- Dependencies: 231
-- Data for Name: answers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.answers (answer_id, task, student, grade, file_path, task_delivery_time) FROM stdin;
\.


--
-- TOC entry 3466 (class 0 OID 25327)
-- Dependencies: 226
-- Data for Name: classes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.classes (class_id, teacher, group_, discipline, active, started) FROM stdin;
\.


--
-- TOC entry 3461 (class 0 OID 25072)
-- Dependencies: 221
-- Data for Name: departments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.departments (department_id, department_name, description, created_at) FROM stdin;
8	Computer Science	The department focuses on software development, algorithms, data science, and artificial intelligence.	2025-01-18 14:02:37.391934
9	Electrical Engineering	This department covers topics related to power systems, electronics, and signal processing.	2025-01-18 14:02:37.391934
10	Mechanical Engineering	The department specializes in robotics, thermodynamics, and mechanical systems design.	2025-01-18 14:02:37.391934
11	Mathematics	Focused on pure and applied mathematics, including algebra, calculus, and statistics.	2025-01-18 14:02:37.391934
12	Physics	Research and teaching related to classical mechanics, quantum physics, and astrophysics.	2025-01-18 14:02:37.391934
13	Biology	The department covers areas like genetics, microbiology, and ecology.	2025-01-18 14:02:37.391934
14	Chemistry	Focus on organic, inorganic, and physical chemistry for research and teaching.	2025-01-18 14:02:37.391934
15	Economics	The department emphasizes macroeconomics, microeconomics, and financial systems.	2025-01-18 14:02:37.391934
16	History	Dedicated to the study of historical events, cultures, and civilizations.	2025-01-18 14:02:37.391934
17	Psychology	Focus on understanding human behavior, mental processes, and therapy methods.	2025-01-18 14:02:37.391934
18	Business Administration	Covers management, entrepreneurship, and organizational behavior.	2025-01-18 14:02:37.391934
19	Architecture	Specializes in architectural design, urban planning, and construction.	2025-01-18 14:02:37.391934
20	Fine Arts	Focuses on visual arts, sculpture, and creative design.	2025-01-18 14:02:37.391934
21	Environmental Science	Dedicated to studying and solving environmental challenges.	2025-01-18 14:02:37.391934
22	Philosophy	Focus on ethics, logic, and the philosophy of science.	2025-01-18 14:02:37.391934
\.


--
-- TOC entry 3465 (class 0 OID 25225)
-- Dependencies: 225
-- Data for Name: disciplines; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.disciplines (discipline_id, discipline_name, description) FROM stdin;
\.


--
-- TOC entry 3457 (class 0 OID 25013)
-- Dependencies: 217
-- Data for Name: groups_; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.groups_ (group_id, specialty, group_name, creation_date) FROM stdin;
13	51	SE-101	2022-09-01
14	51	SE-102	2023-09-01
15	52	AI-201	2022-09-01
16	52	AI-202	2023-09-01
17	53	DS-301	2022-09-01
18	53	DS-302	2023-09-01
19	54	PS-401	2022-09-01
20	54	PS-402	2023-09-01
21	55	ES-501	2022-09-01
22	55	ES-502	2023-09-01
23	57	RB-601	2022-09-01
24	57	RB-602	2023-09-01
25	60	AM-701	2022-09-01
26	60	AM-702	2023-09-01
27	63	QP-801	2022-09-01
28	63	QP-802	2023-09-01
\.


--
-- TOC entry 3467 (class 0 OID 25357)
-- Dependencies: 227
-- Data for Name: lessons; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lessons (lesson_id, week_day, lesson_number, class_, lesson_type, lesson_link) FROM stdin;
\.


--
-- TOC entry 3463 (class 0 OID 25172)
-- Dependencies: 223
-- Data for Name: profiles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profiles (profile_id, email, encrypted_password, firstname, lastname, creation_date, birthday_date) FROM stdin;
2	john.doe@example.com	5f4dcc3b5aa765d61d8327deb882cf99	John	Doe	2023-09-15	1990-07-12
3	jane.smith@example.com	6cb75f652a9b52798eb6cf2201057c73	Jane	Smith	2023-08-20	1985-11-05
4	michael.brown@example.com	e99a18c428cb38d5f260853678922e03	Michael	Brown	2023-07-30	1992-03-19
5	sarah.johnson@example.com	098f6bcd4621d373cade4e832627b4f6	Sarah	Johnson	2023-06-10	1988-12-24
6	david.wilson@example.com	5d41402abc4b2a76b9719d911017c592	David	Wilson	2023-05-25	1995-05-15
7	emily.davis@example.com	d8578edf8458ce06fbc5bb76a58c5ca4	Emily	Davis	2023-04-12	1997-09-22
8	chris.taylor@example.com	827ccb0eea8a706c4c34a16891f84e7b	Chris	Taylor	2023-03-05	1991-01-09
9	patricia.anderson@example.com	d41d8cd98f00b204e9800998ecf8427e	Patricia	Anderson	2023-02-18	1983-04-17
10	robert.thomas@example.com	c4ca4238a0b923820dcc509a6f75849b	Robert	Thomas	2023-01-10	1987-08-30
11	linda.moore@example.com	eccbc87e4b5ce2fe28308fd9f2a7baf3	Linda	Moore	2022-12-01	1994-06-27
12	alice.johnson@university.com	e99a18c428cb38d5f260853678922e03	Alice	Johnson	2023-01-15	1980-03-25
13	bob.martin@university.com	098f6bcd4621d373cade4e832627b4f6	Bob	Martin	2023-02-10	1975-06-12
14	carol.clark@university.com	d8578edf8458ce06fbc5bb76a58c5ca4	Carol	Clark	2023-03-20	1983-11-05
15	david.hall@university.com	827ccb0eea8a706c4c34a16891f84e7b	David	Hall	2023-04-18	1985-09-15
16	emma.lewis@university.com	5d41402abc4b2a76b9719d911017c592	Emma	Lewis	2023-05-25	1990-07-10
\.


--
-- TOC entry 3464 (class 0 OID 25210)
-- Dependencies: 224
-- Data for Name: profiles_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profiles_roles (profile, role_) FROM stdin;
\.


--
-- TOC entry 3459 (class 0 OID 25037)
-- Dependencies: 219
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (role_id, role_name) FROM stdin;
1	admin
2	student
3	teacher
\.


--
-- TOC entry 3455 (class 0 OID 25005)
-- Dependencies: 215
-- Data for Name: specialties; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.specialties (specialty_id, specialty_name, department) FROM stdin;
51	Software Engineering	8
52	Artificial Intelligence	8
53	Data Science	8
54	Power Systems	9
55	Embedded Systems	9
56	Telecommunications	9
57	Robotics	10
58	Thermal Engineering	10
59	Automotive Engineering	10
60	Applied Mathematics	11
61	Statistics	11
62	Operations Research	11
63	Quantum Physics	12
64	Astrophysics	12
65	Nuclear Physics	12
\.


--
-- TOC entry 3472 (class 0 OID 25610)
-- Dependencies: 232
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.students (student_id, group_) FROM stdin;
2	13
3	14
4	14
5	15
6	15
7	16
8	16
9	17
10	17
11	13
\.


--
-- TOC entry 3469 (class 0 OID 25371)
-- Dependencies: 229
-- Data for Name: tasks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tasks (task_id, class_, description, file_path, deadline) FROM stdin;
\.


--
-- TOC entry 3473 (class 0 OID 25630)
-- Dependencies: 233
-- Data for Name: teachers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.teachers (teacher_id, department, science_degree) FROM stdin;
12	9	Master of Science
13	10	Doctor of Science
14	11	Bachelor of Science
15	12	Professional Doctorate
16	8	Doctor of Philosophy
\.


--
-- TOC entry 3483 (class 0 OID 0)
-- Dependencies: 230
-- Name: answers_answer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.answers_answer_id_seq', 1, false);


--
-- TOC entry 3484 (class 0 OID 0)
-- Dependencies: 220
-- Name: departments_department_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.departments_department_id_seq', 22, true);


--
-- TOC entry 3485 (class 0 OID 0)
-- Dependencies: 216
-- Name: groups__group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.groups__group_id_seq', 28, true);


--
-- TOC entry 3486 (class 0 OID 0)
-- Dependencies: 222
-- Name: profile_profile_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profile_profile_id_seq', 16, true);


--
-- TOC entry 3487 (class 0 OID 0)
-- Dependencies: 218
-- Name: roles_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_role_id_seq', 3, true);


--
-- TOC entry 3488 (class 0 OID 0)
-- Dependencies: 214
-- Name: specialties_specialty_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.specialties_specialty_id_seq', 65, true);


--
-- TOC entry 3489 (class 0 OID 0)
-- Dependencies: 228
-- Name: tasks_task_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tasks_task_id_seq', 1, false);


--
-- TOC entry 3288 (class 2606 OID 25401)
-- Name: answers answer_unique_const; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answers
    ADD CONSTRAINT answer_unique_const UNIQUE (task, student);


--
-- TOC entry 3290 (class 2606 OID 25399)
-- Name: answers answers_file_path_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answers
    ADD CONSTRAINT answers_file_path_key UNIQUE (file_path);


--
-- TOC entry 3292 (class 2606 OID 25397)
-- Name: answers answers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answers
    ADD CONSTRAINT answers_pkey PRIMARY KEY (answer_id);


--
-- TOC entry 3278 (class 2606 OID 25332)
-- Name: classes classes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classes
    ADD CONSTRAINT classes_pkey PRIMARY KEY (class_id);


--
-- TOC entry 3280 (class 2606 OID 25349)
-- Name: classes classes_unique_const; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classes
    ADD CONSTRAINT classes_unique_const UNIQUE (teacher, group_, discipline);


--
-- TOC entry 3262 (class 2606 OID 25079)
-- Name: departments departments_department_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departments
    ADD CONSTRAINT departments_department_name_key UNIQUE (department_name);


--
-- TOC entry 3264 (class 2606 OID 25077)
-- Name: departments departments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departments
    ADD CONSTRAINT departments_pkey PRIMARY KEY (department_id);


--
-- TOC entry 3274 (class 2606 OID 25231)
-- Name: disciplines discipline_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.disciplines
    ADD CONSTRAINT discipline_pk PRIMARY KEY (discipline_id);


--
-- TOC entry 3276 (class 2606 OID 25233)
-- Name: disciplines disciplines_discipline_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.disciplines
    ADD CONSTRAINT disciplines_discipline_name_key UNIQUE (discipline_name);


--
-- TOC entry 3254 (class 2606 OID 25017)
-- Name: groups_ groups__pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups_
    ADD CONSTRAINT groups__pkey PRIMARY KEY (group_id);


--
-- TOC entry 3266 (class 2606 OID 25539)
-- Name: profiles id_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT id_unique UNIQUE (profile_id);


--
-- TOC entry 3282 (class 2606 OID 25364)
-- Name: lessons lessons_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT lessons_pkey PRIMARY KEY (lesson_id);


--
-- TOC entry 3256 (class 2606 OID 25300)
-- Name: groups_ name_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups_
    ADD CONSTRAINT name_unique UNIQUE (group_name);


--
-- TOC entry 3268 (class 2606 OID 25183)
-- Name: profiles profile_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT profile_email_key UNIQUE (email);


--
-- TOC entry 3270 (class 2606 OID 25181)
-- Name: profiles profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT profile_pkey PRIMARY KEY (profile_id);


--
-- TOC entry 3272 (class 2606 OID 25214)
-- Name: profiles_roles profiles_roles_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profiles_roles
    ADD CONSTRAINT profiles_roles_pk PRIMARY KEY (profile, role_);


--
-- TOC entry 3258 (class 2606 OID 25041)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (role_id);


--
-- TOC entry 3260 (class 2606 OID 25043)
-- Name: roles roles_role_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_role_name_key UNIQUE (role_name);


--
-- TOC entry 3252 (class 2606 OID 25011)
-- Name: specialties specialties_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.specialties
    ADD CONSTRAINT specialties_pkey PRIMARY KEY (specialty_id);


--
-- TOC entry 3294 (class 2606 OID 25614)
-- Name: students students2_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT students2_pkey PRIMARY KEY (student_id);


--
-- TOC entry 3284 (class 2606 OID 25381)
-- Name: tasks tasks_file_path_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_file_path_key UNIQUE (file_path);


--
-- TOC entry 3286 (class 2606 OID 25379)
-- Name: tasks tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (task_id);


--
-- TOC entry 3296 (class 2606 OID 25635)
-- Name: teachers teachers2_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teachers
    ADD CONSTRAINT teachers2_pkey PRIMARY KEY (teacher_id);


--
-- TOC entry 3306 (class 2606 OID 25625)
-- Name: answers answers_student_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answers
    ADD CONSTRAINT answers_student_fkey FOREIGN KEY (student) REFERENCES public.students(student_id);


--
-- TOC entry 3307 (class 2606 OID 25402)
-- Name: answers answers_task_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answers
    ADD CONSTRAINT answers_task_fkey FOREIGN KEY (task) REFERENCES public.tasks(task_id);


--
-- TOC entry 3301 (class 2606 OID 25343)
-- Name: classes classes_discipline_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classes
    ADD CONSTRAINT classes_discipline_fkey FOREIGN KEY (discipline) REFERENCES public.disciplines(discipline_id);


--
-- TOC entry 3302 (class 2606 OID 25338)
-- Name: classes classes_group__fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classes
    ADD CONSTRAINT classes_group__fkey FOREIGN KEY (group_) REFERENCES public.groups_(group_id);


--
-- TOC entry 3303 (class 2606 OID 25646)
-- Name: classes classes_teacher_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classes
    ADD CONSTRAINT classes_teacher_fkey FOREIGN KEY (teacher) REFERENCES public.teachers(teacher_id);


--
-- TOC entry 3298 (class 2606 OID 25018)
-- Name: groups_ groups__specialty_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups_
    ADD CONSTRAINT groups__specialty_fkey FOREIGN KEY (specialty) REFERENCES public.specialties(specialty_id);


--
-- TOC entry 3304 (class 2606 OID 25365)
-- Name: lessons lessons_class__fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT lessons_class__fkey FOREIGN KEY (class_) REFERENCES public.classes(class_id);


--
-- TOC entry 3299 (class 2606 OID 25215)
-- Name: profiles_roles profiles_roles_profile_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profiles_roles
    ADD CONSTRAINT profiles_roles_profile_fkey FOREIGN KEY (profile) REFERENCES public.profiles(profile_id);


--
-- TOC entry 3300 (class 2606 OID 25220)
-- Name: profiles_roles profiles_roles_role__fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profiles_roles
    ADD CONSTRAINT profiles_roles_role__fkey FOREIGN KEY (role_) REFERENCES public.roles(role_id);


--
-- TOC entry 3297 (class 2606 OID 25439)
-- Name: specialties specialties_department_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.specialties
    ADD CONSTRAINT specialties_department_fkey FOREIGN KEY (department) REFERENCES public.departments(department_id);


--
-- TOC entry 3308 (class 2606 OID 25620)
-- Name: students students2_group__fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT students2_group__fkey FOREIGN KEY (group_) REFERENCES public.groups_(group_id);


--
-- TOC entry 3309 (class 2606 OID 25615)
-- Name: students students2_student_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT students2_student_id_fkey FOREIGN KEY (student_id) REFERENCES public.profiles(profile_id);


--
-- TOC entry 3305 (class 2606 OID 25382)
-- Name: tasks tasks_class__fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_class__fkey FOREIGN KEY (class_) REFERENCES public.classes(class_id);


--
-- TOC entry 3310 (class 2606 OID 25636)
-- Name: teachers teachers2_teacher_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teachers
    ADD CONSTRAINT teachers2_teacher_id_fkey FOREIGN KEY (teacher_id) REFERENCES public.profiles(profile_id);


--
-- TOC entry 3311 (class 2606 OID 25641)
-- Name: teachers teachers_department_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teachers
    ADD CONSTRAINT teachers_department_fkey FOREIGN KEY (department) REFERENCES public.departments(department_id);


-- Completed on 2025-02-09 16:12:32

--
-- PostgreSQL database dump complete
--

