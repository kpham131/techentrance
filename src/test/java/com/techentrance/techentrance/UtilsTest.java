package com.techentrance.techentrance.security;

import com.techentrance.techentrance.Utils;
import com.techentrance.techentrance.controller.LoginController;
import com.techentrance.techentrance.model.User;
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

import java.beans.Transient;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class SecurityTest {

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
    private Model modelMock;

    private Utils util;
    private String cookieStringMock;
    private Cookie[] cookieArrayMock;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        util = new Utils();
    }

    @Test
    public void authenticate_request_null() {
        when(httpServletRequestMock.getCookies(Matchers.any())).thenReturn(null);
        Object response = Utils.getCookie(httpServletRequestMock, cookieStringMock);

        assertNull(response);
    }

    @Test
    public void authenticate_request_not_null() {
        when(httpServletRequestMock.getCookies(Matchers.any())).thenReturn(cookieArrayMock);


        Object response = Utils.getCookie(httpServletRequestMock, cookieStringMock);

        assertNotNull(response);
    }

    @Test
    public void authenticate_null_cookie_name() {
        when(httpServeletRequestMock.getCookie(Matcher.any())).thenReturn(cookieArrayMock);

        Object responce = Utils.getCookie(httpServletRequestMock, null);

        assertNull(responce);


    }

    
}
