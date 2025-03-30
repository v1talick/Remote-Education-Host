package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository {
    Answer getAnswerById(Integer id);
    List<Answer> getAnswersByStudentId(Integer studentId);
    List<Answer> getAnswersByTaskId(Integer taskId);
    List<Answer> getAllAnswers();
    void saveAnswer(Answer answer);
    void updateAnswer(Answer answer);
    void deleteAnswer(Integer answerId);
}
