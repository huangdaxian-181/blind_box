package com.example.demo.interceptor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.user.UserInfo;
import com.example.demo.tools.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录拦截器
 *
 * @author 搬砖的码农
 * @date 2023/03/21
 * @email
 **/
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = "";

        token = request.getHeader("Authorization");

        if (token == "" || token == null) {
            PrintWriter writer = null;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            try {
                writer = response.getWriter();

                JSONObject json = new JSONObject();

                json.put("code", 1003);

                json.put("msg", "请去登录!!");

                writer.print(json);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                if (writer != null)
                    writer.close();
            }
            return false;
        }

        //不为空直接获取值
        int id = Token.getUserId(token);
        //赋值
        UserInfo.userId = id;

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
