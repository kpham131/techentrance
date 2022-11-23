package com.techentrance.techentrance.controller;

import com.techentrance.techentrance.repo.SkillRepository;
import com.techentrance.techentrance.repo.UserRepository;
import com.techentrance.techentrance.repo.UserSkillRepository;
import com.techentrance.techentrance.security.Security;
import com.techentrance.techentrance.service.JobService;
import com.techentrance.techentrance.service.SkillService;
import com.techentrance.techentrance.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class HomeControllerIntegrationTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private SkillService skillServiceMock;

    @Mock
    private UserSkillRepository userSkillRepository;

    @Mock
    private JobService jobServiceMock;

    private UserService userService = new UserService(userRepository, skillRepository, userSkillRepository);


    private Security security = new Security(userService);
    private HomeController homeController = new HomeController(security, userService, skillServiceMock, jobServiceMock);

    @Test
    public void homeTest1() {
        //homeController = new HomeController(security);
        //Cookie cookie = security.validateSession(httpServletRequest);
        when(httpServletRequest.getCookies()).thenReturn(null);
        String response = homeController.home(httpServletRequest, httpServletResponse);
        assertEquals("redirect:/login", response);

    }
}