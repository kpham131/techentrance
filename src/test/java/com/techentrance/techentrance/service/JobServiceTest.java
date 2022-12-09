package com.techentrance.techentrance.service;

import com.techentrance.techentrance.Utils;
import com.techentrance.techentrance.controller.LoginController;
import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.repo.*;
import com.techentrance.techentrance.security.Security;
import com.techentrance.techentrance.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class JobServiceTest {
    @Mock
    private UserService userServiceMock;

    @Mock
    private Security securityMock;

    @Mock
    private HttpServletResponse httpServletResponseMock;

    @Mock
    private Utils utilsMock;

    @Mock
    private HttpServletRequest httpServletRequestMock;

    @Mock
    private User userMock1;

    @Mock
    private User userMock2;

    @Mock
    private Model modelMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private SkillRepository skillRepositoryMock;

    @Mock
    private UserSkillRepository userSkillRepositoryMock;

    @Mock
    private JobRepository jobRepositoryMock;

    private JobService jobService;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        jobService = new JobService(jobRepositoryMock);
    }

    @Test
    public void SaveJob() {
        Object result = jobService.saveJob(any());
        assertNull(result);
    }

    @Test
    public void SaveJobs() {
        Object result = jobService.saveJobs(any());
        assertNotNull(result);
    }

    @Test
    public void GetJobById() {
        Object result = jobService.getJobById(any());
        assertNull(result);
    }

    @Test
    public void GetJobsByUserId() {
        Object result = jobService.getJobsByUserId(any());
        assertNotNull(result);
    }
}
