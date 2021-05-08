package com.example.app.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.app.filter.BodyReaderRequestWrapper;
import com.example.app.util.Sign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

@Slf4j
@Component
public class SecurityInterceptor implements HandlerInterceptor {
    // 在 Controller 处理请求之前被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求参数
        String queryString = request.getQueryString();
        log.info("请求参数:{}", queryString);

        // 解决springboot 拦截器校验签名后controller就获取不到数据，只能用一次
        BodyReaderRequestWrapper servletRequestWrapper = new BodyReaderRequestWrapper(request);
        String body = servletRequestWrapper.getBodyString(request);


        //获取请求body
//        byte[] bodyBytes = StreamUtils.copyToByteArray(request.getInputStream());
//        String body = new String(bodyBytes, request.getCharacterEncoding());

//        SortedMap map = JSON.parseObject(body, SortedMap.class);
//        String temp = map.get("sign").toString();
//        map.remove("sign");
//        String sign = Sign.createSign("UTF-8", map);
//        System.out.println(sign);
//        log.info("请求体：{}", body);
//        request.setAttribute("startTime", System.currentTimeMillis());
//        if (temp.equals(sign)){
//            log.debug(">>>验证成功!");
//            return true;
//        }else {
//            response.setCharacterEncoding( "UTF-8");
//            response.setContentType( "application/json; charset=utf-8");
//            PrintWriter out = null ;
//            try{
//                JSONObject res = new JSONObject();
//                res.put( "success", "false");
//                res.put( "msg", "签名失败！");
//                out = response.getWriter();
//                out.append(res.toString());
//                return false;
//            }
//            catch (Exception e){
//                log.debug("--->异常");
//                e.printStackTrace();
//                response.sendError( 500);
//                return false;
//            }
//
//        }
        return true;
    }
    // 在 Controller 处理请求执行完成后、生成视图前执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor.postHandle");
    }
    // 在 DispatcherServlet 完全处理请求后被调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (long)request.getAttribute("startTime");
        long endTime =  System.currentTimeMillis();

        long duration = endTime - startTime;
        System.out.println("MyInterceptor.afterCompletion: " + request.getRequestURL() +" duration: " + duration + "ms");
    }
}
