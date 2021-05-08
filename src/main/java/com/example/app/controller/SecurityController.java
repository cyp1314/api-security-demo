package com.example.app.controller;

import com.example.app.entity.User;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class SecurityController {

    @PostMapping("/save")
    public Map save(@RequestBody User user){
        String sign = user.getSign();
        System.out.println(sign);
        System.out.println(user.toString());
        HashMap<Object, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("msg", "success");
        return res;
    }

    @PostMapping("/test")
    public Map test(HttpServletRequest request){
        byte[] bodyBytes = new byte[0];
        try {
            bodyBytes = StreamUtils.copyToByteArray(request.getInputStream());
            String body = new String(bodyBytes, request.getCharacterEncoding());

            System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<Object, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("msg", "success");
        return res;
    }
}
