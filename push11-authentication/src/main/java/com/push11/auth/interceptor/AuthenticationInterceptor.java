package com.push11.auth.interceptor;

import com.push11.auth.service.VersionService;
import com.push11.util.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private VersionService versionService;

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {

        String versionId = req.getRequestURI();


        versionService.validateAndFindVersion(versionId);


        String auth = req.getHeader("Authorization");

        Authorization authorization = Authorization.generateFromString(auth);
        //TODO turgay : TokenAuth gelecek.. moduller arası bu sınıf kullanılabilir
        if (authorization == null || authorization.isNotAuthorizationValid()) {
//            res.sendError(HttpStatus.UNAUTHORIZED.value());
            return true;
        }
        return true;

    }

}