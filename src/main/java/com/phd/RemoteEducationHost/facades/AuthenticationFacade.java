package com.phd.RemoteEducationHost.facades;

import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.security.responses.AuthResponse;

public interface AuthenticationFacade {
    AuthResponse register(UserCreationDTO userCreationDTO);

    AuthResponse login(UserCreationDTO userCreationDTO);
}
