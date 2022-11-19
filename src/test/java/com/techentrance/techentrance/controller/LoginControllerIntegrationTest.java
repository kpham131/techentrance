package com.techentrance.techentrance.controller;

import com.techentrance.techentrance.Utils;
import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.repo.UserRepository;
import com.techentrance.techentrance.security.Security;
import com.techentrance.techentrance.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class LoginControllerIntegrationTest {

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
    private User userMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private Model modelMock;

    private LoginController loginController;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        loginController = new LoginController(userServiceMock, securityMock);
    }

    @Test
    public void loginView_is_null() {
        when(httpServletRequestMock.getCookies()).thenReturn(null);
        String response = loginController.loginView(httpServletRequestMock, httpServletResponseMock);
        assertEquals("login", response);
    }

    @Test
    public void loginView_is_not_null() {
        when(httpServletRequestMock.getCookies()).thenReturn(new Cookie[]{new Cookie("SessionId","1"), new Cookie("2", "2")});
        when(userServiceMock.getUserBySessionId(UUID.randomUUID())).thenReturn(new User());
        String response = loginController.loginView(httpServletRequestMock, httpServletResponseMock);
        assertEquals("login", response);
    }

    @Test
    public void login_sessionID_is_null() {
        when(userServiceMock.getUserByEmail(Matchers.any())).thenReturn(null);
        String resposne = loginController.login(httpServletRequestMock, httpServletResponseMock, userMock, modelMock);
        assertEquals("redirect:/login", resposne);
    }

    @Test
    public void login_sessionID_is_not_null() {
        when(userServiceMock.getUserByEmail(Matchers.any())).thenReturn(userMock);
        String resposne = loginController.login(httpServletRequestMock, httpServletResponseMock, userMock, modelMock);
        assertEquals("redirect:/login", resposne);
    }

    @Test
    public void userRegistration_null() {
        when(userRepositoryMock.findByEmail(Matchers.any())).thenReturn(null);
        String response = loginController.userRegistration(httpServletResponseMock, userMock, modelMock);
        assertEquals("redirect:/users/null/personalInfo", response);

    }

    @Test
    public void userRegistration_not_null() {
        when(userRepositoryMock.findByEmail(Matchers.any())).thenReturn(new User());
        String response = loginController.userRegistration(httpServletResponseMock, userMock, modelMock);
        assertEquals("redirect:/users/null/personalInfo", response);

    }
}

