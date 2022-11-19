package com.techentrance.techentrance.controller;

import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.repo.UserRepository;
import com.techentrance.techentrance.security.Security;
import com.techentrance.techentrance.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

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
    private UserService userService;

    private Security security = new Security(new UserService(userRepository));
    private HomeController homeController = new HomeController(security);

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void homeTest1() {
        when(httpServletRequest.getCookies()).thenReturn(null);
        String response = homeController.home(httpServletRequest, httpServletResponse);
        assertEquals("redirect:/login", response);
    }

    @Test
    public void homeTest2() {
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{ new Cookie("name1", "v1"), new Cookie("SessionId", UUID.randomUUID().toString())});
        when(userService.getUserBySessionId(UUID.randomUUID())).thenReturn(null);
        //when(userRepository.findBySessionId(UUID.randomUUID())).thenReturn(new User());
        String response = homeController.home(httpServletRequest, httpServletResponse);
        assertEquals("index", response);
    }

}


