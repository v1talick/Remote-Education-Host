package com.phd.RemoteEducationHost.facades.impl;

import com.phd.RemoteEducationHost.DTOs.AnswerDTO;
import com.phd.RemoteEducationHost.DTOs.StudentDTO;
import com.phd.RemoteEducationHost.DTOs.TaskDTO;
import com.phd.RemoteEducationHost.enteties.User;
import com.phd.RemoteEducationHost.enteties.enums.Role;
import com.phd.RemoteEducationHost.exceptions.DataNotFoundException;
import com.phd.RemoteEducationHost.exceptions.InvalidArgumentException;
import com.phd.RemoteEducationHost.facades.AnswerFacade;
import com.phd.RemoteEducationHost.services.AnswerService;
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
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerFacadeImpl implements AnswerFacade {
    private final AnswerService answerService;

    private final StudentService studentService;

    private final TaskService taskService;

    private static final String ANSWER_DIR = "src/main/resources/uploads/answers/";

    @Override
    public List<AnswerDTO> getAllAnswers() {
        return List.of();
    }

    @Override
    public List<AnswerDTO> getAllAnswersByTaskId(int taskId) {
        List<AnswerDTO> answers = answerService.getAllAnswersByTaskId(taskId);
        if(answers.isEmpty()) {
            throw new DataNotFoundException("No answers found for task with id " + taskId);
        }
        AnswerDTO answerDTO = answers.get(0);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getRoles().contains(Role.ADMIN) && !user.getId().equals(answerDTO.getTask().getAClass().getTeacher().getId())) {
            throw new AccessDeniedException("User not allowed to see answer");
        }
        return answers;
    }

    @Override
    public List<AnswerDTO> getAllAnswersByStudentId(int studentId) {
        List<AnswerDTO> answers = answerService.getAllAnswersByStudentId(studentId);
        if(answers.isEmpty()) {
            throw new DataNotFoundException("No answers found for student with id " + studentId);
        }
        AnswerDTO answerDTO = answers.get(0);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getRoles().contains(Role.ADMIN) && !user.getId().equals(answerDTO.getTask().getAClass().getTeacher().getId()) &&
                !user.getId().equals(answerDTO.getStudent().getId())) {
            throw new AccessDeniedException("User not allowed to see answer");
        }

        return answers;
    }

    @Override
    public AnswerDTO getAnswerById(int id) {
        AnswerDTO answerDTO = answerService.getAnswerById(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getRoles().contains(Role.ADMIN) && !user.getId().equals(answerDTO.getTask().getAClass().getTeacher().getId()) &&
                !user.getId().equals(answerDTO.getStudent().getId())) {
            throw new AccessDeniedException("User not allowed to see answer");
        }

        return answerDTO;
    }

    @Override
    public AnswerDTO getAnswerByStudentIdAndTaskId(int studentId, int taskId) {
        AnswerDTO answerDTO = answerService.getAnswerByStudentIdAndTaskId(studentId, taskId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getRoles().contains(Role.ADMIN) && !user.getId().equals(answerDTO.getTask().getAClass().getTeacher().getId()) &&
                !user.getId().equals(answerDTO.getStudent().getId())) {
            throw new AccessDeniedException("User not allowed to see answer");
        }

        return answerDTO;
    }

    @Override
    public void createAnswer(AnswerDTO answerDTO, MultipartFile file) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StudentDTO student = studentService.getStudentById(answerDTO.getStudent().getId());
        boolean isUserStudent = user.getId().equals(answerDTO.getStudent().getId()) && user.getRoles().contains(Role.STUDENT);
        TaskDTO task = taskService.getTaskById(answerDTO.getTask().getId());
        boolean isStudentOfThisGroup = student.getGroupDTO().getId().equals(task.getAClass().getGroup().getId());
        if(!isStudentOfThisGroup) {
            throw new AccessDeniedException("This task is not assigned to this student`s group");
        }
        if (!user.getRoles().contains(Role.ADMIN) && !user.getId().equals(task.getAClass().getTeacher().getId()) && !isUserStudent) {
            throw new AccessDeniedException("User not allowed to create answer");
        }
        String filePath = saveAnswerToFileSystem(file);
        answerDTO.setFilePath(filePath);
        answerDTO.setTaskDeliveryTime(LocalDate.now());

        answerService.createAnswer(answerDTO);
    }

    @Override
    public void updateAnswer(AnswerDTO answerDTO, MultipartFile file) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StudentDTO student = studentService.getStudentById(answerDTO.getStudent().getId());
        boolean isUserStudent = user.getId().equals(answerDTO.getStudent().getId()) && user.getRoles().contains(Role.STUDENT);
        TaskDTO task = taskService.getTaskById(answerDTO.getTask().getId());
        boolean isStudentOfThisGroup = student.getGroupDTO().getId().equals(task.getAClass().getGroup().getId());
        if(!isStudentOfThisGroup) {
            throw new AccessDeniedException("This task is not assigned to this student`s group");
        }
        if ((!user.getRoles().contains(Role.ADMIN) && !user.getId().equals(task.getAClass().getTeacher().getId())) && !isUserStudent) {
            throw new AccessDeniedException("User not allowed to see answer");
        }
        String filePath = saveAnswerToFileSystem(file);
        answerDTO.setFilePath(filePath);

        answerService.updateAnswer(answerDTO);
    }

    @Override
    public void deleteAnswer(int id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AnswerDTO answerDTO = answerService.getAnswerById(id);
        if (!user.getRoles().contains(Role.ADMIN) && !user.getId().equals(answerDTO.getTask().getAClass().getTeacher().getId()) &&
                !user.getId().equals(answerDTO.getStudent().getId())) {
            throw new AccessDeniedException("User not allowed to delete answer");
        }

        answerService.deleteAnswer(id);
    }

    private String saveAnswerToFileSystem(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null; // No file to save
        }
        try {
            // Ensure the directory exists
            Files.createDirectories(Paths.get(ANSWER_DIR));

            // Check file type
            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null || !isAllowedFileType(originalFileName)) {
                throw new InvalidArgumentException("Unsupported file type. Allowed types are: txt, pdf, docx, jpg, png.");
            }

            // Unique file name
            String fileName = System.currentTimeMillis() + "_" + originalFileName;

            Path filePath = Paths.get(ANSWER_DIR + fileName);
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
