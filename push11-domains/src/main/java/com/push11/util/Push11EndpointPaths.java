package com.push11.util;

import org.apache.commons.lang3.StringUtils;

public class Push11EndpointPaths {


    private Push11EndpointPaths() {
    }

    //TODO turgay : Tüm end point path'lerini burada topla
    public static final String EMPTY = StringUtils.EMPTY;
    public static final String VERSION = "/{version}";
    public static final String APPLICATION = "/application";
    public static final String V_APPLICATION = VERSION + "/application";
    public static final String NEW = "/new";
    public static final String ALL = "/all";
    public static final String NOTIFY = "/notify";
    public static final String SET_TAG = "/set/tag";
    public static final String ADD_TAG = "/add/tag";
    public static final String ID = "/{id}";
    public static final String COMPANY = "/company";
    public static final String V_COMPANY = VERSION + COMPANY;
    public static final String COMPANY_NEW = COMPANY + NEW;
    public static final String USER = "/user";
    public static final String V_USER = VERSION + USER;
    public static final String UPDATE = "/update";
    public static final String PUSH = "/push11";
    public static final String V_PUSH = VERSION + PUSH;
    public static final String ACTION = "/action";
    public static final String NEW_ACTION = ACTION + NEW;
    public static final String V_ACTION = VERSION + ACTION;
    public static final String EVENT = "/event";
    public static final String NAMES = "/names";
    public static final String VALUES = "/values";
    public static final String V_EVENT = VERSION + EVENT;

}
