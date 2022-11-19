package com.techentrance.techentrance.security;

import com.techentrance.techentrance.Utils;
import com.techentrance.techentrance.controller.LoginController;
import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.repo.UserRepository;
import com.techentrance.techentrance.security.Security;
import com.techentrance.techentrance.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
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


public class SecurityIntegrationTest {

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

    private Security security;

    @Mock
    private UserRepository userRepositoryMock;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        security = new Security(userServiceMock);
    }

    @Test
    public void authenticate_null() {
        when(userRepositoryMock.findByEmail(Matchers.any())).thenReturn(null);

        Object response = security.authenticate(new User());

        assertNull(response);
    }

    @Test
    public void validsession() {
        when(userRepositoryMock.findBySessionId(UUID.randomUUID())).thenReturn(null);

        Object response = security.validateSession(httpServletRequestMock);

        assertNull(response);
    }


}

