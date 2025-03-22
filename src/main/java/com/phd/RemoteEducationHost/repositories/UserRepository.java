package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User getUserById(int id);
    User getUserByEmail(String email);
    User getUserWithRolesById(int id);
    List<User> getAllUsers();
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
}
