package com.techentrance.techentrance.service;


import com.techentrance.techentrance.Utils;
import com.techentrance.techentrance.controller.LoginController;
import com.techentrance.techentrance.model.Job;
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


public class UserJobServiceTest {

    @Mock
    private UserJobRepository userJobRepositoryMock;

    @Mock
    private User userMock;

    @Mock
    private Job jobMock;
    private UserJobService userJobService;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        userJobService = new UserJobService(userJobRepositoryMock);
    }

    @Test
    public void IsSaved_null() {
        when(userJobRepositoryMock.findById(any(),any())).thenReturn(new UUID(1,1));

        boolean result = userJobService.isSaved(userMock, jobMock);
        assertTrue(result);
    }

    @Test
    public void IsSaved_not_null() {
        when(userJobRepositoryMock.findById(any(),any())).thenReturn(null);
        boolean result = userJobService.isSaved(userMock, jobMock);
        assertFalse(result);
    }
}
