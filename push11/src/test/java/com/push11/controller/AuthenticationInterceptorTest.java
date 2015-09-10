package com.push11.controller;

import com.push11.interceptor.AuthenticationInterceptor;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationInterceptorTest {

    @InjectMocks
    private AuthenticationInterceptor interceptor;

    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpServletResponse response = new MockHttpServletResponse();
    private ArgumentCaptor<MockHttpServletResponse> captor = ArgumentCaptor.forClass(MockHttpServletResponse.class);


    @Test
    public void shouldReturnFalseIfAuthIsNull() throws Exception {
        request.addHeader("Authorization", "");
        boolean handle = interceptor.preHandle(request, response, null);
        assertFalse(handle);
    }

    @Test
    public void shouldReturnFalseIfAuthIsNotValid() throws Exception {
        request.addHeader("Authorization", "123,123,aa");
        boolean handle = interceptor.preHandle(request, response, null);
        assertFalse(handle);
    }

    @Ignore
    @Test
    public void shouldReturnTrueIfAuthIsValid() throws Exception {
        request.addHeader("Authorization", "123,123,123");
        boolean handle = interceptor.preHandle(request, response, null);
        assertTrue(handle);
    }

}