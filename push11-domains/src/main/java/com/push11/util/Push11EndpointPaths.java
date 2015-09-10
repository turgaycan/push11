package com.push11.util;

import org.apache.commons.lang3.StringUtils;

public class Push11EndpointPaths {

    private Push11EndpointPaths(){}

    //TODO turgay : Tüm end point path'lerini burada topla
    public static final String EMPTY = StringUtils.EMPTY;
    public static final String VERSION = "/{version}";
    public static final String APPLICATION = "/application";
    public static final String V_APPLICATION = VERSION +  "/application";
    public static final String NEW = "/new";
    public static final String TAG = "/tag";
    public static final String TAG_NEW = TAG + NEW;
    public static final String ID = "/{id}";
    public static final String COMPANY = "/company";
    public static final String V_COMPANY= VERSION + COMPANY;
    public static final String COMPANY_NEW = COMPANY + NEW;




}
