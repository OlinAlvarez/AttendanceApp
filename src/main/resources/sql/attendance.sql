drop table if exists students;
drop table if exists courses;
drop table if exists attendance_record;
drop table if exists students_courses;

create table root.students(
    id integer auto_increment,
    name varchar(255),
    cin integer,

    primary key(id)
);

insert into students (name,cin)
    values ("Olin Alvarez",12345);
insert into students (name,cin)
    values ("Pranil Dahal",55555);
insert into students (name,cin)
    values ("Chris Ortiz",66666);
insert into students (name,cin)
    values ("Gio Garcia",77777);

create table root.courses(
    id integer auto_increment,
    name varchar(255),
    time varchar(255),
    units integer not null check(units between 1 and 4),
    location varchar(255),
    instructor varchar(255),

    primary key(id)
);    
insert into courses(name,time,units,location,instructor)
    values("CS3220","MW 9:00-10:00pm",3,"ET A220","Michael Hsu");
insert into courses(name,time,units,location,instructor)
    values("PHIL 3690","MWF 10:00-11:00AM",2,"KH 200A", "Bruce Atta");
create table students_courses(
    student_id integer references students(id),
    course_id integer references courses(id),
    foreign key(student_id) references students(id),
    foreign key(course_id) references courses(id)
);  
insert into students_courses(student_id,course_id)
    values(1,1);
insert into students_courses(student_id,course_id)
    values(2,1);
insert into students_courses(student_id,course_id)
    values(3,1);
insert into students_courses(student_id,course_id)
    values(1,2);
insert into students_courses(student_id,course_id)
    values(4,2);
insert into students_courses(student_id,course_id)
    values(3,2);

create table attendance(
    id integer auto_increment, 
    student_id integer references students(id),
    course_id integer references courses(id),
    activity varchar(255),
    present varchar(255),

    primary key(id),
    foreign key(student_id) references students(id),
    foreign key(course_id) references courses(id)
);
insert into attendance(student_id,course_id,activity,present)
    values(1,1,"Lab 1","present");
insert into attendance(student_id,course_id,activity,present)
    values(2,1,"Lab 1","present");
insert into attendance(student_id,course_id,activity,present)
    values(3,1,"Lab 1","present");
insert into attendance(student_id,course_id,activity,present)
    values(1,1,"Lab 2","present");
insert into attendance(student_id,course_id,activity,present)
    values(2,1,"Lab 2","present");
insert into attendance(student_id,course_id,activity,present)
    values(3,1,"Lab 2","present");
insert into attendance(student_id,course_id,activity,present)
values(1,1,"Activity","present");
insert into attendance(student_id,course_id,activity,present)
values(2,1,"Activity","present");
insert into attendance(student_id,course_id,activity,present)
values(3,1,"Activity","absent");
insert into attendance(student_id,course_id,activity,present)
    values(3,2,"Lecture 1", "present");
insert into attendance(student_id,course_id,activity,present)
    values(4,2,"Lecture 1", "present");
insert into attendance(student_id,course_id,activity,present)
    values(3,2,"Lecture 2", "present");
insert into attendance(student_id,course_id,activity,present)
    values(4,2,"Lecture 2", "present");
insert into attendance(student_id,course_id,activity,present)
    values(3,2,"Lecture 3", "present");
insert into attendance(student_id,course_id,activity,present)
    values(4,2,"Lecture 3", "present");
insert into attendance(student_id,course_id,activity,present)
    values(3,2,"Midterm Exam", "present");
insert into attendance(student_id,course_id,activity,present)
    values(4,2,"Midterm Exam", "present");
insert into attendance(student_id,course_id,activity,present)
    values(3,2,"Lecture 4", "present");
insert into attendance(student_id,course_id,activity,present)
    values(4,2,"Lecture 4", "present");
