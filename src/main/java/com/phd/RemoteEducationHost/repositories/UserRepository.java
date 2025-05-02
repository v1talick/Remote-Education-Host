package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.User;

import java.util.List;

public interface UserRepository {
    User getUserById(Integer id);

    User getUserByEmail(String email);

    User getUserWithRolesById(Integer id);

    User getUserByEmailWithRoles(String email);

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer userId);
}
