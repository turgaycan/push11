package com.push11.data.service;

import com.push11.domain.ApplicationEvent;
import com.push11.data.repository.AppEventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationEventServiceTest {

    @InjectMocks
    private AppEventService service;

    @Mock
    private AppEventRepository appEventRepository;


    @Test
    public void shouldUpdateAppEvent(){
        ApplicationEvent applicationEvent = new ApplicationEvent();
    }

}
