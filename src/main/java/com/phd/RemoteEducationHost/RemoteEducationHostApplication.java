package com.phd.RemoteEducationHost;

import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Specialty;
import com.phd.RemoteEducationHost.mappers.GroupMapper;
import com.phd.RemoteEducationHost.repositories.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class RemoteEducationHostApplication {
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ClassRepository classRepository;
	public static void main(String[] args) {
		SpringApplication.run(RemoteEducationHostApplication.class, args);
	}
	@PostConstruct
	public void test() {
		try {
//			System.out.println(groupRepository.getGroupById(228));
//			studentRepository.getAllStudents().forEach(System.out::println);
			System.out.println(classRepository.getClassWithDetailsById(101));
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Group with id 228 not found");
		}
	}
}
