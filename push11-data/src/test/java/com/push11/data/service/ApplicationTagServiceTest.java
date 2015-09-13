package com.push11.data.service;

import com.google.common.collect.Lists;
import com.push11.data.repository.ApplicationTagRepository;
import com.push11.domain.Application;
import com.push11.domain.ApplicationTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ApplicationTagServiceTest {

    @InjectMocks
    private ApplicationTagService service;

    @Mock
    private ApplicationTagRepository applicationTagRepository;

    @Test
    public void shouldFindApplicationTagsByApplication(){
        Application application = new Application("appId");
        ArrayList<ApplicationTag> applicationTags = Lists.newArrayList(new ApplicationTag());
        when(applicationTagRepository.findApplicationTagsByApplication(application)).thenReturn(applicationTags);

        final List<ApplicationTag> actualApplicationTagsByApplications = service.findApplicationTagsByApplication(application);

        assertThat(actualApplicationTagsByApplications.size(), is(1));
        assertThat(actualApplicationTagsByApplications, is(applicationTags));
    }
}