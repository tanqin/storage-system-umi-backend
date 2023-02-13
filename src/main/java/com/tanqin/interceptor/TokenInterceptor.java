package com.tanqin.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.tanqin.common.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private HttpServletResponse httpServletResponse;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        String token = request.getHeader("Authorization");
        if (token != null) {
            boolean flag = JwtUtil.verify(token);
            if (flag) {
                System.out.println("认证成功，通过拦截器！");
                return true;
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            httpServletResponse.setStatus(401);
            JSONObject json = new JSONObject();
            json.put("msg", "用户验签已失效，请重新登录！");
            json.put("code", 401);
            response.getWriter().append(json.toJSONString());
            System.out.println("认证失败，未通过拦截器！");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;
    }
}
