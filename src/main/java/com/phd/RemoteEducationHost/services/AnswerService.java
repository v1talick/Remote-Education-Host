package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.AnswerDTO;

import java.util.List;

public interface AnswerService {
    List<AnswerDTO> getAllAnswers();
    List<AnswerDTO> getAllAnswersByTaskId(int taskId);
    List<AnswerDTO> getAllAnswersByStudentId(int studentId);
    AnswerDTO getAnswerById(int id);
    AnswerDTO getAnswerByStudentIdAndTaskId(int studentId, int taskId);
    void createAnswer(AnswerDTO answerDTO);
    void updateAnswer(AnswerDTO answerDTO);
    void deleteAnswer(int id);
}
