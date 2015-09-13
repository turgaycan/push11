package com.push11.data.repository;

import com.google.common.collect.Lists;
import com.push11.data.repository.core.AbstractIntegration;
import com.push11.domain.Application;
import com.push11.domain.ApplicationTag;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ApplicationTagRepositoryTest extends AbstractIntegration{

    @Autowired
    private ApplicationTagRepository repository;

    @Test
    public void shouldFindApplicationTagsByApplication(){
        Application application1 = new Application("appId1");
        Application application2 = new Application("appId2");
        ApplicationTag applicationTag1 = new ApplicationTag(application1, Lists.newArrayList("tag11", "tag21"));
        ApplicationTag applicationTag2 = new ApplicationTag(application2, Lists.newArrayList("tag12", "tag22", "tag23"));
        ApplicationTag applicationTag3 = new ApplicationTag(application1, Lists.newArrayList("tag32", "tag22", "tag23"));

        repository.save(Lists.newArrayList(applicationTag1, applicationTag2, applicationTag3));

        final List<ApplicationTag> actualApplicationTags = repository.findApplicationTagsByApplication(application1);

        assertThat(actualApplicationTags.size(), is(2));
        assertThat(actualApplicationTags.get(0).getTagList(), hasItems("tag11", "tag21"));
        assertThat(actualApplicationTags.get(1).getTagList(), hasItems("tag32", "tag22", "tag23"));
    }

}