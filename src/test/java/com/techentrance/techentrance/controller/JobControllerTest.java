package com.techentrance.techentrance.controller;

import com.techentrance.techentrance.Utils;
import com.techentrance.techentrance.model.Job;
import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.repo.UserJobRepository;
import com.techentrance.techentrance.security.Security;
import com.techentrance.techentrance.service.JobService;
import com.techentrance.techentrance.service.SkillService;
import com.techentrance.techentrance.service.UserJobService;
import com.techentrance.techentrance.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.springframework.ui.Model;

import static org.junit.Assert.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

public class JobControllerTest {
    @Mock
    private UserService userServiceMock;

    @Mock
    private Security securityMock;

    @Mock
    private SkillService skillServiceMock;

    @Mock
    private HttpServletResponse httpServletResponseMock;

    @Mock
    private Utils utilsMock;

    @Mock
    private HttpServletRequest httpServletRequestMock;

    @Mock
    private User userMock;

    @Mock
    private Model modelMock;

    @Mock
    private JobService jobService;

    @Mock
    private UserJobService userJobService;
    private JobController jobController;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        jobController = new JobController(jobService, userJobService, userServiceMock);
    }

    @Test
    public void jobDetailGet_null() {
        when(userServiceMock.getUserById(any())).thenReturn(null);
        String response = jobController.jobDetailGet(httpServletRequestMock, httpServletResponseMock, UUID.randomUUID().toString(), "", modelMock);
        assertEquals("error", response);
    }

    @Test
    public void jobDetailGet_not_null_saved() {
        Job j = new Job("", "", "", "", "", "");
        when(userServiceMock.getUserById(any())).thenReturn(new User());
        when(jobService.getJobById(any())).thenReturn(j);

        when(userJobService.isSaved(any(), any())).thenReturn(true);
        String response = jobController.jobDetailGet(httpServletRequestMock, httpServletResponseMock, UUID.randomUUID().toString(), "", modelMock);
        assertEquals("jobDetailSaved", response);
    }

    @Test
    public void jobDetailGet_not_null_not_saved() {
        Job j = new Job("", "", "", "", "", "");
        when(userServiceMock.getUserById(any())).thenReturn(new User());
        when(jobService.getJobById(any())).thenReturn(j);
        when(userJobService.isSaved(any(), any())).thenReturn(false);

        String response = jobController.jobDetailGet(httpServletRequestMock, httpServletResponseMock, UUID.randomUUID().toString(), "", modelMock);
        assertEquals("jobDetail", response);
    }
}
