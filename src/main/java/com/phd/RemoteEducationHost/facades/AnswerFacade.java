package com.phd.RemoteEducationHost.facades;

import com.phd.RemoteEducationHost.DTOs.AnswerDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnswerFacade {
    List<AnswerDTO> getAllAnswers();
    List<AnswerDTO> getAllAnswersByTaskId(int taskId);
    List<AnswerDTO> getAllAnswersByStudentId(int studentId);
    AnswerDTO getAnswerById(int id);
    void createAnswer(AnswerDTO answerDTO, MultipartFile file);
    void updateAnswer(AnswerDTO answerDTO, MultipartFile file);
    void deleteAnswer(int id);
}
