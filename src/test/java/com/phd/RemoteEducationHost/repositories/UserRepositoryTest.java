package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.configuration.SystemTestConfiguration;
import com.phd.RemoteEducationHost.enteties.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(SystemTestConfiguration.class)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void saveUserTest(){
        User user = new User(0, "testEmail@mail.com", "testSurname", "testEmail@mail.com", "testPassword", new Date(), new Date());
        userRepository.saveUser(user);
        assertEquals(6, userRepository.getAllUsers().size());
        assertEquals("testEmail@mail.com", userRepository.getUserById(6).get().getEmail());
    }
    @Test
    public void getUserByIdTest() {
        User user = userRepository.getUserById(1).get();
        assertEquals("hashed_password_1", user.getPassword());
    }
    @Test
    public void updateUserTest(){
        User user = new User(2, "testEmail2@mail.com", "testSurname", "testEmail@mail.com", "testPassword", new Date(), new Date());
        userRepository.updateUser(user);
        assertEquals(user.getEmail(), userRepository.getUserById(2).get().getEmail());
    }
    @Test
    public void deleteUserTest(){
        userRepository.deleteUser(5);
        assertFalse(userRepository.getUserById(5).isPresent());
        assertEquals(5, userRepository.getAllUsers().size());
    }
}
