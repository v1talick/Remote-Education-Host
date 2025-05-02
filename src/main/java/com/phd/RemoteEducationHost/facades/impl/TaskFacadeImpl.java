package com.phd.RemoteEducationHost.facades.impl;

import com.phd.RemoteEducationHost.DTOs.TaskDTO;
import com.phd.RemoteEducationHost.enteties.User;
import com.phd.RemoteEducationHost.enteties.enums.Role;
import com.phd.RemoteEducationHost.exceptions.DataNotFoundException;
import com.phd.RemoteEducationHost.exceptions.InvalidArgumentException;
import com.phd.RemoteEducationHost.facades.TaskFacade;
import com.phd.RemoteEducationHost.services.StudentService;
import com.phd.RemoteEducationHost.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskFacadeImpl implements TaskFacade {
    private final TaskService taskService;

    private final StudentService studentService;

    private static final String TASKS_DIR = "src/main/resources/uploads/tasks/";

    @Override
    public void createTask(MultipartFile file, TaskDTO taskDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getRoles().contains(Role.ADMIN) || !user.getId().equals(taskDTO.getAClass().getTeacher().getId())) {
            throw new AccessDeniedException("User not allowed to create task");
        }

        String filePath = saveTasksToFileSystem(file);
        taskDTO.setFilePath(filePath);
        taskService.createTask(taskDTO);
    }

    @Override
    public void updateTask(MultipartFile file, TaskDTO taskDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getRoles().contains(Role.ADMIN) || !user.getId().equals(taskDTO.getAClass().getTeacher().getId())) {
            throw new AccessDeniedException("User not allowed to create task");
        }

        String filePath = saveTasksToFileSystem(file);
        taskDTO.setFilePath(filePath);
        taskService.updateTask(taskDTO);
    }

    @Override
    public void deleteTask(Integer id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TaskDTO task = taskService.getTaskById(id);
        if (!user.getRoles().contains(Role.ADMIN) || !user.getId().equals(task.getAClass().getTeacher().getId())) {
            throw new AccessDeniedException("User not allowed to delete task");
        }

        taskService.deleteTask(id);
    }

    @Override
    public TaskDTO getTaskById(Integer id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TaskDTO task = taskService.getTaskById(id);

        boolean isStudentOfThisGroup = (user.getRoles().contains(Role.STUDENT) && !studentService.getStudentById(user.getId())
                .getGroupDTO().getId().equals(task.getAClass().getGroup().getId()));
        boolean isTeacherOfThisClass = user.getId().equals(task.getAClass().getTeacher().getId());
        if (!user.getRoles().contains(Role.ADMIN) && !isStudentOfThisGroup && !isTeacherOfThisClass) {
            throw new AccessDeniedException("User not allowed to get task");
        }

        return task;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getRoles().contains(Role.ADMIN)) {
            throw new AccessDeniedException("User not allowed to get task");
        }

        return taskService.getAllTasks();
    }

    @Override
    public List<TaskDTO> getTasksByGroupId(Integer groupId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getRoles().isEmpty()) {
            throw new AccessDeniedException("User not allowed to get task");
        }
        List<TaskDTO> tasks = taskService.getTasksByGroupId(groupId);
        if (tasks.isEmpty()) {
            throw new DataNotFoundException("Tasks not found");
        }
        TaskDTO task = tasks.get(0);

        boolean isStudentOfThisGroup = (user.getRoles().contains(Role.STUDENT) && !studentService.getStudentById(user.getId())
                .getGroupDTO().getId().equals(task.getAClass().getGroup().getId()));
        boolean isTeacherOfThisClass = user.getId().equals(task.getAClass().getTeacher().getId());
        if (!user.getRoles().contains(Role.ADMIN) && !isStudentOfThisGroup && !isTeacherOfThisClass) {
            throw new AccessDeniedException("User not allowed to get task");
        }

        return tasks;
    }

    @Override
    public List<TaskDTO> getTasksByClassId(Integer classId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getRoles().isEmpty()) {
            throw new AccessDeniedException("User not allowed to get task");
        }
        List<TaskDTO> tasks = taskService.getTasksByClassId(classId);
        if (tasks.isEmpty()) {
            throw new DataNotFoundException("Tasks not found");
        }
        TaskDTO task = tasks.get(0);

        boolean isStudentOfThisGroup = (user.getRoles().contains(Role.STUDENT) && !studentService.getStudentById(user.getId())
                .getGroupDTO().getId().equals(task.getAClass().getGroup().getId()));
        boolean isTeacherOfThisClass = user.getId().equals(task.getAClass().getTeacher().getId());
        if (!user.getRoles().contains(Role.ADMIN) && !isStudentOfThisGroup && !isTeacherOfThisClass) {
            throw new AccessDeniedException("User not allowed to get task");
        }

        return tasks;
    }


    private String saveTasksToFileSystem(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null; // No file to save
        }
        try {
            // Ensure the directory exists
            Files.createDirectories(Paths.get(TASKS_DIR));

            // Check file type
            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null || !isAllowedFileType(originalFileName)) {
                throw new InvalidArgumentException("Unsupported file type. Allowed types are: txt, pdf, docx, jpg, png.");
            }

            // Unique file name
            String fileName = System.currentTimeMillis() + "_" + originalFileName;

            Path filePath = Paths.get(TASKS_DIR + fileName);
            Files.write(filePath, file.getBytes());

            return "/uploads/tasks/" + fileName; // Return relative path (to be stored in DB)

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    private boolean isAllowedFileType(String fileName) {
        String lowerCaseFileName = fileName.toLowerCase();
        return lowerCaseFileName.endsWith(".txt") ||
                lowerCaseFileName.endsWith(".pdf") ||
                lowerCaseFileName.endsWith(".docx") ||
                lowerCaseFileName.endsWith(".jpg") ||
                lowerCaseFileName.endsWith(".png");
    }
}
