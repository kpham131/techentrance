package com.techentrance.techentrance.controller;
import com.techentrance.techentrance.Utils;
import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.security.Security;
import com.techentrance.techentrance.service.SkillService;
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


public class UserControllerTest {

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



    private UserController userController;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(securityMock, userServiceMock, skillServiceMock);
    }

    @Test
    public void userSkills_user1_is_null() {

        when(userServiceMock.getUserById(any())).thenReturn(null);

        String response = userController.userSkills(httpServletRequestMock, httpServletResponseMock, UUID.randomUUID().toString(), modelMock);

        assertEquals("error", response);
    }

    @Test
    public void userSkills1_cookie_is_null() {

        when(userServiceMock.getUserById(any())).thenReturn(new User());

        when(securityMock.validateSession(httpServletRequestMock)).thenReturn(null);
        String response = userController.userSkills(httpServletRequestMock, httpServletResponseMock, UUID.randomUUID().toString(), modelMock);

        assertEquals("redirect:/login", response);
    }

    @Test
    public void userSkills1_is_not_null() {
        when(userServiceMock.getUserById(any())).thenReturn(new User());

        when(securityMock.validateSession(httpServletRequestMock)).thenReturn(new Cookie("1","2"));
        String response = userController.userSkills(httpServletRequestMock, httpServletResponseMock, UUID.randomUUID().toString(), modelMock);

        assertEquals("addskills", response);
    }

    @Test
    public void userSkills_user2_is_null() {
        when(userServiceMock.getUserById(any())).thenReturn(null);

        String response = userController.userSkills(null, UUID.randomUUID().toString());

        assertEquals("error", response);
    }

    @Test
    public void userSkills_user2_is_not_null() {
        when(userServiceMock.getUserById(any())).thenReturn(new User());
        UUID userId = UUID.randomUUID();
        String response = userController.userSkills(null, userId.toString());

        assertEquals("redirect:/users/"+userId+"/profile", response);
    }

    @Test
    public void newPersonalInfoGet_user_is_null() {
        when(userServiceMock.getUserById(any())).thenReturn(null);
        String response = userController.newPersonalInfoGet(httpServletResponseMock, UUID.randomUUID().toString());
        assertEquals("error", response);
    }

    @Test
    public void newPersonalInfoGet_user_is_not_null() {
        when(userServiceMock.getUserById(any())).thenReturn(new User());
        String response = userController.newPersonalInfoGet(httpServletResponseMock, UUID.randomUUID().toString());
        assertEquals("newPersonalInfo", response);
    }

    @Test
    public void newPersonalInfoPost_is_null() {
        when(userServiceMock.getUserById(any())).thenReturn(null);
        String response = userController.newPersonalInfoPost(UUID.randomUUID().toString(), userMock, modelMock);
        assertEquals("error", response);
    }

    @Test
    public void newPersonalInfoPost_is_not_null() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "firstName", "lastName", "phone", "gpa", "city", "state", "email", "pass", UUID.randomUUID());
        when(userServiceMock.getUserById(any())).thenReturn(user);
        String response = userController.newPersonalInfoPost(userId.toString(), user, modelMock);
        assertEquals("redirect:/users/"+ userId + "/skills", response);
    }

    @Test
    public void newPersonalInfoPost_userToFoundUser_is_null() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        when(userServiceMock.getUserById(any())).thenReturn(user);
        String response = userController.newPersonalInfoPost(userId.toString(), user, modelMock);
        assertEquals("redirect:/users/"+ userId + "/skills", response);
    }

    @Test
    public void editPersonalInfoGet_null_user() {
        UUID userId = UUID.randomUUID();
        when(userServiceMock.getUserById(any())).thenReturn(null);

        assertEquals("error", userController.editPersonalInfoGet(httpServletResponseMock, userId.toString(), null));

    }

    @Test
    public void editPersonalInfoGet_nonNull_user() {
        UUID userId = UUID.randomUUID();
        when(userServiceMock.getUserById(any())).thenReturn(userMock);

        assertEquals("editprofile", userController.editPersonalInfoGet(httpServletResponseMock, userId.toString(), modelMock));

    }

    @Test
    public void editPersonalInfoPost_null_user() {
        UUID userId = UUID.randomUUID();
        when(userServiceMock.getUserById(any())).thenReturn(null);
        assertEquals("error", userController.editPersonalInfoPost(userId.toString(), new User(),null));

    }

    @Test
    public void editPersonalInfoPost_nonNull_user() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "firstName", "lastName", "phone", "gpa", "city", "state", "email", "pass", UUID.randomUUID());
        when(userServiceMock.getUserById(any())).thenReturn(user);
        assertEquals("redirect:/users/"+ userId + "/profile", userController.editPersonalInfoPost(userId.toString(), new User(),null));
    }

    @Test
    public void profileView_null_user() {
        UUID userId = UUID.randomUUID();
        when(userServiceMock.getUserById(any())).thenReturn(null);
        assertEquals("error", userController.profileView(null, httpServletResponseMock, userId.toString(), modelMock));

    }

    @Test
    public void profileView_null_cookie() {
        UUID userId = UUID.randomUUID();
        when(userServiceMock.getUserById(any())).thenReturn(userMock);
        when(securityMock.validateSession(isNull())).thenReturn(null);

        assertEquals("redirect:/login", userController.profileView(null, httpServletResponseMock, userId.toString(), modelMock));



    }

    @Test
    public void profileView_nonNull_cookie() {
        UUID userId = UUID.randomUUID();
        when(userServiceMock.getUserById(any())).thenReturn(userMock);
        when(securityMock.validateSession(isNull())).thenReturn(new Cookie("test", "testValue"));
        when(skillServiceMock.getSkillsByUserId(any())).thenReturn(null);

        assertEquals("profile", userController.profileView(null, httpServletResponseMock, userId.toString(), modelMock));



    }



}