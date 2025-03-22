package com.phd.RemoteEducationHost;

import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Specialty;
import com.phd.RemoteEducationHost.enteties.User;
import com.phd.RemoteEducationHost.mappers.GroupMapper;
import com.phd.RemoteEducationHost.repositories.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class RemoteEducationHostApplication {
	public static void main(String[] args) {
		SpringApplication.run(RemoteEducationHostApplication.class, args);
	}

}
