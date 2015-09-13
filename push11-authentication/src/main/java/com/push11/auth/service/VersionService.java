package com.push11.auth.service;

import com.push11.exception.custom.ErrorCode;
import com.push11.exception.custom.Push11VersionException;
import com.push11.model.version.Version;
import com.push11.validator.Push11RegexValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VersionService implements InitializingBean {

    private static final String VERSION_REGEX = "[v][0-9].[0-9]$";
    private static final String BACKSLASH_REGEX = "/";

    private Map<String, Version> versions = new HashMap<>();
    private Version latestVersion = new Version("v3.0", 3.0, true);

    @Override
    public void afterPropertiesSet() throws Exception {
        versions.put("", latestVersion);//latest version
        versions.put("v3.0", new Version("v3.0", 3.0, true));
        versions.put("v2.4", new Version("v2.4", 2.4, true));
        versions.put("v2.3", new Version("v2.3", 2.3, true));
        versions.put("v1.9", new Version("v1.9", 1.9, true));
        versions.put("v0.9", new Version("v0.9", 0.9, false));//not supported version
    }

    public Version validateAndFindVersion(String uri) {
        String versionId = versionIdFromUri(uri);

        boolean isNotValidVersion = new Push11RegexValidator().isNotValid(VERSION_REGEX, versionId);

        if (isNotValidVersion) {
            throw new Push11VersionException(ErrorCode.VERSION_NOT_VALID);
        }

        Version version = versions.get(versionId);

        if (version == null) {
            throw new Push11VersionException(ErrorCode.VERSION_NOT_FOUND);
        }

        if (version.isNotSupported()) {
            throw new Push11VersionException(ErrorCode.VERSION_NOT_SUPPORTED);
        }

        return version;
    }

    private String versionIdFromUri(String uri) {
        String[] matches = new Push11RegexValidator().matches(BACKSLASH_REGEX, uri);
        return matches == null ? StringUtils.EMPTY : matches[0];
    }


}
