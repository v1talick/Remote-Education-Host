package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.AnswerDTO;
import com.phd.RemoteEducationHost.exceptions.DataNotFoundException;
import com.phd.RemoteEducationHost.exceptions.InvalidArgumentException;
import com.phd.RemoteEducationHost.mappers.AnswerMapper;
import com.phd.RemoteEducationHost.repositories.AnswerRepository;
import com.phd.RemoteEducationHost.services.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    @Override
    public List<AnswerDTO> getAllAnswers() {
        return answerRepository.getAllAnswers().stream().map(AnswerMapper::mapToDto).toList();
    }

    @Override
    public List<AnswerDTO> getAllAnswersByTaskId(int taskId) {
        return answerRepository.getAnswersByTaskId(taskId).stream().map(AnswerMapper::mapToDto).toList();
    }

    @Override
    public List<AnswerDTO> getAllAnswersByStudentId(int studentId) {
        return answerRepository.getAnswersByStudentId(studentId).stream().map(AnswerMapper::mapToDto).toList();
    }

    @Override
    public AnswerDTO getAnswerById(int id) {
        try {
            return AnswerMapper.mapToDto(answerRepository.getAnswerById(id));
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("Answer with id " + id + " not found");
        }
    }

    @Override
    public AnswerDTO getAnswerByStudentIdAndTaskId(int studentId, int taskId) {
        try {
            return AnswerMapper.mapToDto(answerRepository.getAnswerByStudentIdAndTaskId(studentId, taskId));
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("Answer with task id %d and student id %d not found".formatted(
                    taskId, studentId));
        }
    }

    @Override
    public void createAnswer(AnswerDTO answerDTO) {
        try {
            answerRepository.saveAnswer(AnswerMapper.mapToEntity(answerDTO));
        } catch (Exception e) { //TODO: replace with specific exception
            throw new InvalidArgumentException("Error while saving answer: " + e.getMessage());
        }
    }

    @Override
    public void updateAnswer(AnswerDTO answerDTO) {
        try {
            answerRepository.updateAnswer(AnswerMapper.mapToEntity(answerDTO));
        } catch (Exception e) {
            throw new InvalidArgumentException("Error while updating answer: " + e.getMessage());
        }
    }

    @Override
    public void deleteAnswer(int id) {
        try {
            answerRepository.deleteAnswer(id);
        } catch (Exception e) {
            throw new InvalidArgumentException("Error while deleting answer: " + e.getMessage());
        }
    }
}
