package com.techentrance.techentrance.controller;


import com.techentrance.techentrance.MainGET;
import com.techentrance.techentrance.model.Job;
import com.techentrance.techentrance.model.Skill;
import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.security.Security;
import com.techentrance.techentrance.service.JobService;
import com.techentrance.techentrance.service.SkillService;
import com.techentrance.techentrance.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class HomeControllerTest {

    @Mock
    private Security securityMock;

    @Mock
    private User userMock;

    @Mock
    private HttpServletResponse httpServletResponseMock;

    @Mock
    private UserService userServiceMock;

    @Mock
    private SkillService skillServiceMock;

    @Mock
    private JobService jobServiceMock;

    private HomeController homeController;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        homeController = new HomeController(securityMock, userServiceMock, skillServiceMock, jobServiceMock);
    }

    @Test
    public void home_cookie_not_null() {
        // Arrange
        UUID sessionId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        when(securityMock.validateSession(any())).thenReturn(new Cookie("sessionId", sessionId.toString()));
        when(userServiceMock.getUserBySessionId(any())).thenReturn(userMock);
        when(userMock.getId()).thenReturn(userId);

        // ACT
        String response = homeController.home(null, httpServletResponseMock);

        // ASSERT
        assertEquals("redirect:/users/"+userId, response);

    }

    @Test
    public void home_cookie_is_null() {
        // Arrange
        when(securityMock.validateSession(any())).thenReturn(null);

        // ACT
        String response = homeController.home(null, httpServletResponseMock);

        // ASSERT
        assertEquals("redirect:/login", response);

    }

    @Test
    public void homeUser_cookie_is_null() {
        // Arrange
        when(securityMock.validateSession(any())).thenReturn(null);

        // ACT
        String response = homeController.homeUser(null, httpServletResponseMock, "userId", null);

        // ASSERT
        assertEquals("redirect:/login", response);
    }

    @Test
    public void homeUser_cookie_is_not_null() {
        // Arrange
        when(securityMock.validateSession(any())).thenReturn(new Cookie("sessionId", java.util.UUID.randomUUID().toString()));
        List<Skill> list = new ArrayList<>();
        //Skill s = new Skill()
        list.add(new Skill("Java"));
        when(skillServiceMock.getSkillsByUserId(UUID.randomUUID())).thenReturn(list);

        when(MainGET.getJobsWithSkills(list)).thenReturn(new ArrayList<Job>());
        // ACT
        String response = homeController.homeUser(null, httpServletResponseMock, UUID.randomUUID().toString(), null);

        // ASSERT
        assertEquals("index", response);
    }

    @Test
    public void about_null() {
        when(securityMock.validateSession(any())).thenReturn(null);
        String response = homeController.about(null, httpServletResponseMock, UUID.randomUUID().toString(), null);
        assertEquals("redirect:/login", response);
    }
    @Test
    public void about_not_null() {
        when(securityMock.validateSession(any())).thenReturn(new Cookie("name", "12"));
        String response = homeController.about(null, httpServletResponseMock, UUID.randomUUID().toString(), null);
        assertEquals("about", response);
    }
}
