package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.AnswerDTO;
import com.phd.RemoteEducationHost.enteties.Answer;

public class AnswerMapper {
    public static Answer mapToEntity(AnswerDTO answerDTO) {
        Answer answer = new Answer();
        if (answerDTO == null) {
            return answer;
        }
        answer.setId(answerDTO.getId());
        answer.setGrade(answerDTO.getGrade());
        answer.setStudent(StudentMapper.studentDTOToStudent(answerDTO.getStudent()));
        answer.setFilePath(answerDTO.getFilePath());
        answer.setTask(TaskMapper.toEntity(answerDTO.getTask()));
        answer.setTaskDeliveryTime(answerDTO.getTaskDeliveryTime());

        return answer;
    }

    public static AnswerDTO mapToDto(Answer entity) {
        AnswerDTO DTO = new AnswerDTO();
        if (entity == null) {
            return DTO;
        }
        DTO.setId(entity.getId());
        DTO.setGrade(entity.getGrade());
        DTO.setStudent(StudentMapper.studentToStudentDTO(entity.getStudent()));
        DTO.setFilePath(entity.getFilePath());
        DTO.setTask(TaskMapper.toDTO(entity.getTask()));
        DTO.setTaskDeliveryTime(entity.getTaskDeliveryTime());

        return DTO;
    }
}
