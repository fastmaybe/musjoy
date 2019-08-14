package com.example.musjoy.base;

import com.example.musjoy.pojo.ErrorEnum;
import com.example.musjoy.pojo.ResultDTO;
import com.example.musjoy.utils.AesUtil;
import com.example.musjoy.utils.SendMsgUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      String requestToken=  request.getHeader("token");
      if (null==requestToken){
          ResultDTO resultDTO = new ResultDTO(ErrorEnum.ERROR_OF_USERNOTEXIST);
          SendMsgUtil.sendJsonMessage(response,resultDTO);
//        response.setStatus(403);
          return false;
      }
        Object value = redisTemplate.opsForValue().get(requestToken);
        if (null!=value){
            redisTemplate.expire(requestToken,60, TimeUnit.SECONDS);
            return true;
        }
        ResultDTO resultDTO = new ResultDTO(ErrorEnum.ERROR_OF_USERNOTEXIST);
        SendMsgUtil.sendJsonMessage(response,resultDTO);
//        response.setStatus(403);
          return false;
    }
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String[] split = new String[0];
//        String deviceId = null;
//        String requestToken=null;
//
//        try {
//            requestToken = AesUtil.aesDecode( request.getHeader("token"));
//            split = requestToken.split(" ");
//            System.out.println(Arrays.toString(split));
//            System.out.println(redisTemplate);
//            deviceId = (String) redisTemplate.opsForValue().get(split[1]);
//            System.err.println(deviceId);
//        } catch (Exception e) {
//            response.setStatus(401);
//            return false;
//        }
//        if (Strings.isEmpty(deviceId) || Strings.isEmpty(requestToken)){
//            response.setStatus(402);
//            return false;
//        }
//        if (deviceId.equals(split[0]) && (new Date(Long.valueOf(split[2])).getTime()+ 1000*60*5)> new Date().getTime()){
//            return true;
//        }
//        response.setStatus(403);
//        return false;
//    }
}
