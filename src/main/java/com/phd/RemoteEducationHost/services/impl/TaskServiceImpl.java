package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.TaskDTO;
import com.phd.RemoteEducationHost.enteties.Task;
import com.phd.RemoteEducationHost.exceptions.InvalidArgumentException;
import com.phd.RemoteEducationHost.mappers.TaskMapper;
import com.phd.RemoteEducationHost.repositories.TaskRepository;
import com.phd.RemoteEducationHost.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private static final String TASKS_DIR = "src/main/resources/uploads/tasks/";
    private final TaskRepository taskRepository;

    @Override
    public void createTask(TaskDTO taskDTO) {
        Task task = TaskMapper.toEntity(taskDTO);
        try {
            taskRepository.saveTask(task);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentException("Invalid Task data " + e.getMessage());
        }
    }

    @Override
    public void updateTask(TaskDTO taskDTO) {
        Task task = TaskMapper.toEntity(taskDTO);
        try {
            taskRepository.updateTask(task);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentException("Invalid Task data " + e.getMessage());
        }
    }

    @Override
    public void deleteTask(Integer id) {
        try {
            taskRepository.deleteTask(id);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentException("Task with such id is used in other entities");
        }
    }

    @Override
    public TaskDTO getTaskById(Integer id) {
        try {
            return TaskMapper.toDTO(taskRepository.getTaskWithDetailsById(id));
        } catch (EmptyResultDataAccessException e) {
            throw new InvalidArgumentException("Task not found");
        }
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.getAllTasks().stream()
                .map(TaskMapper::toDTO)
                .toList();
    }

    @Override
    public List<TaskDTO> getTasksByClassId(Integer classId) {
        return taskRepository.getAllTasksByClassId(classId).stream()
                .map(TaskMapper::toDTO)
                .toList();
    }

    @Override
    public List<TaskDTO> getTasksByGroupId(Integer groupId) {
        return taskRepository.getAllTasksByGroupId(groupId).stream()
                .map(TaskMapper::toDTO)
                .toList();
    }
}
