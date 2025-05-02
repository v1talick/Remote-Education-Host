package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.configuration.SystemTestConfiguration;
import com.phd.RemoteEducationHost.enteties.User;
import com.phd.RemoteEducationHost.enteties.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(SystemTestConfiguration.class)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void saveUserTest() {
        User user = new User(0, "testEmail@mail.com", "testSurname", "testEmail@mail.com", "testPassword", new Date(), new Date(), List.of());
        userRepository.saveUser(user);
        List<User> allUsers = userRepository.getAllUsers();
        assertEquals(7, allUsers.size());
        assertEquals("testEmail@mail.com", allUsers.get(allUsers.size()-1).getEmail());
    }

    @Test
    public void getUserByIdTest() {
        User user = userRepository.getUserById(1);
        assertEquals("hashed_password_1", user.getPassword());
    }

    @Test
    public void getUserWithRolesByIdTest() {
        User user = userRepository.getUserWithRolesById(1);
        assertEquals(List.of(Role.STUDENT), user.getRoles());
    }

    @Test
    public void updateUserTest() {
        User user = new User(2, "testEmail2@mail.com", "testSurname", "testEmail@mail.com", "testPassword", new Date(), new Date(), List.of());
        userRepository.updateUser(user);
        assertEquals(user.getEmail(), userRepository.getUserById(2).getEmail());
    }

    @Test
    public void deleteUserTest() {
//        userRepository.deleteUser(6);
//        assertFalse(userRepository.getUserById(5).isPresent());
//        assertEquals(5, userRepository.getAllUsers().size());
    }
}
