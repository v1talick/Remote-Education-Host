package com.phd.RemoteEducationHost;

import com.phd.RemoteEducationHost.DTOs.*;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.StudentCreationDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.enteties.enums.LessonType;
import com.phd.RemoteEducationHost.enteties.enums.ScienceDegree;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class TestObjectDTOsFactory {
    public static DepartmentDTO getDepartmentDTO() {
        return new DepartmentDTO(
                1,
                "Test Science",
                "Department of CS",
                new Date()
        );
    }

    public static SpecialtyDTO getSpecialtyDTO() {
        return new SpecialtyDTO(
                1,
                "Test Specialty",
                getDepartmentDTO()
        );
    }

    public static GroupDTO getGroupDTO() {
        return new GroupDTO(
                2,
                getSpecialtyDTO(),
                "Test Group",
                new Date()
        );
    }

    public static DisciplineDTO getDisciplineDTO() {
        return new DisciplineDTO(
                1,
                "Test Discipline",
                "Test Description");
    }

    public static TeacherDTO getTeacherDTO() {
        return new TeacherDTO(3,
                ScienceDegree.BACHELOR_OF_SCIENCE,
                getDepartmentDTO());
    }

    public static ClassDTO getClassDTO() {
        TeacherDTO teacherDTO = getTeacherDTO();
        return new ClassDTO(
                1,
                teacherDTO,
                getDisciplineDTO(),
                getGroupDTO(),
                true,
                new Date()
        );
    }

    public static UserCreationDTO getUserCreationDTO() {
        return new UserCreationDTO(
                "testUser@gmail.com",
                "testPassword123",
                "TestFirstName",
                "TestLastName",
                new Date(),
                new Date(),
                List.of()
        );
    }

    public static StudentDTO getStudentDTO() {
        return new StudentDTO(
                5,
                getUserCreationDTO().getEmail(),
                getUserCreationDTO().getFirstName(),
                getUserCreationDTO().getLastName(),
                getUserCreationDTO().getBirthdayDate(),
                getUserCreationDTO().getCreateAt(),
                getUserCreationDTO().getRoles(),
                getGroupDTO()
        );
    }

    public static StudentCreationDTO getCreationStudentDTO() {
        return new StudentCreationDTO(
                getUserCreationDTO().getEmail(),
                getUserCreationDTO().getPassword(),
                getUserCreationDTO().getFirstName(),
                getUserCreationDTO().getLastName(),
                getUserCreationDTO().getBirthdayDate(),
                getUserCreationDTO().getCreateAt(),
                getUserCreationDTO().getRoles(),
                getGroupDTO()
        );
    }

    public static TeacherDTO getTeacherDTOWithUser() {
        return new TeacherDTO(
                5,
                getUserCreationDTO().getEmail(),
                getUserCreationDTO().getFirstName(),
                getUserCreationDTO().getLastName(),
                getUserCreationDTO().getBirthdayDate(),
                getUserCreationDTO().getCreateAt(),
                getUserCreationDTO().getRoles(),
                ScienceDegree.BACHELOR_OF_SCIENCE,
                getDepartmentDTO()
        );
    }

    public static TaskDTO getTaskDTO() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(2);
        taskDTO.setAClass(getClassDTO());
        taskDTO.setDeadline(new Date());
        taskDTO.setDescription("test description text");

        return taskDTO;
    }

    public static AnswerDTO getAnswerDTO() {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(13);
        TaskDTO taskDTO = getTaskDTO();
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(1);
        ClassDTO classDTO = new ClassDTO();
        classDTO.setGroup(groupDTO);
        taskDTO.setAClass(classDTO);
        answerDTO.setTask(taskDTO);
        answerDTO.setGrade(100);
        StudentDTO studentDTO = getStudentDTO();
        studentDTO.setId(2);
        answerDTO.setStudent(studentDTO);
        answerDTO.setTaskDeliveryTime(LocalDate.now());

        return answerDTO;
    }

    public static LessonDTO getLessonDTO() {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setId(1);
        lessonDTO.setAClass(getClassDTO());
        lessonDTO.setLessonLink("https://test.edu/test");
        lessonDTO.setLessonType(LessonType.LECTURE);
        lessonDTO.setDayOfWeek(DayOfWeek.FRIDAY);
        lessonDTO.setLessonNumber(1);

        return lessonDTO;
    }
}
