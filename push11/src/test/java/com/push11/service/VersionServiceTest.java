package com.push11.service;

import com.push11.exception.custom.ErrorCode;
import com.push11.exception.custom.Push11VersionException;
import com.push11.model.version.Version;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


@RunWith(MockitoJUnitRunner.class)
public class VersionServiceTest {

    @InjectMocks
    private VersionService service;

    @Before
    public void init() throws Exception {
        service.afterPropertiesSet();
    }

    @Test
    public void shouldThrowPush11VersionExceptionIfVersionNotValid() {
        try {
            service.findVersion("v2.33");
            fail();
        } catch (Push11VersionException pve) {
            final ErrorCode errorCode = pve.getErrorCode();
            assertThat(errorCode.getCode(), is(2001));
            assertThat(errorCode.getType(), is("version"));
            assertThat(errorCode.getMessage(), is("Version is not valid"));
        }
    }

    @Test
    public void shouldThrowPush11VersionExceptionIfVersionNotFound() {
        try {
            service.findVersion("v3.1");
            fail();
        } catch (Push11VersionException pve) {
            final ErrorCode errorCode = pve.getErrorCode();
            assertThat(errorCode.getCode(), is(2002));
            assertThat(errorCode.getType(), is("version"));
            assertThat(errorCode.getMessage(), is("Version is not found"));
        }
    }

    @Test
    public void shouldThrowPush11VersionExceptionIfVersionNotSupported() {
        try {
            service.findVersion("v0.9");
            fail();
        } catch (Push11VersionException pve) {
            final ErrorCode errorCode = pve.getErrorCode();
            assertThat(errorCode.getCode(), is(2000));
            assertThat(errorCode.getType(), is("version"));
            assertThat(errorCode.getMessage(), is("Version not supported any more.."));
        }
    }

    @Test
    public void shouldFindAndGetVersion() {
        Version version = service.findVersion("v3.0");

        assertThat(version.getVersion(), is("v3.0"));
        assertThat(version.getVersionNumber(), is(3.0d));
        assertThat(version.isSupported(), is(true));
    }

}