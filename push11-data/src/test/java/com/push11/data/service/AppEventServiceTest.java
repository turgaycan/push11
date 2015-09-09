package com.push11.data.service;

import com.push11.data.repository.AppEventRepository;
import com.push11.domain.AppEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AppEventServiceTest {

    @InjectMocks
    private AppEventService service;

    @Mock
    private AppEventRepository appEventRepository;


    @Test
    public void shouldUpdateAppEvent(){
        AppEvent appEvent = new AppEvent();
    }

}
