package com.push11.data.controller;

import com.push11.data.service.UserService;
import com.push11.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDataControllerTest {

    @InjectMocks
    private UserDataController controller;

    @Mock
    private UserService userService;

    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpServletResponse response = new MockHttpServletResponse();

    @Test
    public void shouldRegisterUser(){
        User user = new User("id");
        controller.registerUser(user);

        ArgumentCaptor<User> captorUser = ArgumentCaptor.forClass(User.class);
        verify(userService).saveEntity(captorUser.capture());

        assertThat(captorUser.getValue(), is(user));
    }

    @Test
    public void shouldGetUser(){
        User user = new User();
        when(userService.findById("id")).thenReturn(user);

        User actualUser = controller.getUser("id");
        assertThat(actualUser, is(user));
    }
}