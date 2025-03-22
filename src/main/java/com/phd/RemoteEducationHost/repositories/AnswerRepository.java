package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository {
    Answer getAnswerById(int id);
    List<Answer> getAnswersByStudentId(int studentId);
    List<Answer> getAnswersByTaskId(int taskId);
    List<Answer> getAllAnswers();
    void saveAnswer(Answer answer);
    void updateAnswer(Answer answer);
    void deleteAnswer(int answerId);
}
