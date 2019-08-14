package com.example.musjoy.service;

import com.example.musjoy.mapper.UserMapper;
import com.example.musjoy.pojo.ErrorEnum;
import com.example.musjoy.pojo.LoginVo;
import com.example.musjoy.pojo.ResultDTO;
import com.example.musjoy.pojo.User;
import com.example.musjoy.utils.AesUtil;
import com.example.musjoy.utils.SecurityUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Resource
    private HttpServletRequest request;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private UserMapper userMapper;


    public ResultDTO register(User user){
        userMapper.insertSelective(user);
        return new ResultDTO();
    }

    public ResultDTO login(User loginUser) throws Exception {
       User user= userMapper.selectByPhoneAndPwd(loginUser);
        if (null==user){
            return   new ResultDTO(ErrorEnum.ERROR_OF_USERNOTEXIST);
        }

        String loginToken = AesUtil.aesEncode(loginUser.getDeviceid() + " " + user.getId() + " " + new Date().getTime());
        redisTemplate.opsForValue().set(user.getId()+"",loginUser.getDeviceid());
        System.out.println(redisTemplate);
        System.err.println(redisTemplate.opsForValue().get(user.getId()));
        Map<String,Object> map= new HashMap<>();
        map.put("token",loginToken);
        map.put("user",user);
        return   new ResultDTO<>(map);
    }

    public ResultDTO login2(User loginUser) throws Exception {
        User user= userMapper.selectByPhoneAndPwd(loginUser);
        if (null==user){
            return   new ResultDTO(ErrorEnum.ERROR_OF_USERNOTEXIST);
        }
        String loginToken = UUID.randomUUID().toString();
        Map<String,Object> map= new HashMap<>();
        map.put("token",loginToken);
        redisTemplate.opsForValue().set(loginToken,1,60, TimeUnit.SECONDS);
        map.put("user",user);
        return   new ResultDTO<>(map);
    }

    public static void main(String[] args) throws Exception {
        String sa="1234534343.1212.1565663569542";
        String s = SecurityUtil.parseMD5("123");
        System.out.println(s);
    }
}
