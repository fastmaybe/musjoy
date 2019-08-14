package com.example.musjoy;

import com.example.musjoy.mapper.UserMapper;
import com.example.musjoy.pojo.User;
import com.example.musjoy.utils.JpushUtils;
import com.example.musjoy.utils.SnowFlake;
import com.github.tobato.fastdfs.domain.MataData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MusjoyApplicationTests {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private FastFileStorageClient fastFileStorageClient;

    @Resource
    private SnowFlake snowFlake;
//    @Resource
//    private JpushUtils jpushUtils;
    @Test
    public void contextLoads() {
            //设置推送参数
            //这里就可以自定义推送参数了
            Map<String, String> parm = new HashMap<String, String>();
            //设备id,指定设备推送id
            parm.put("id", "客户端定义的别名");
            //设置提示信息,内容是文章标题
            parm.put("msg","测试测试");
            //附加参数
            parm.put("qwe","123");
             JpushUtils.jpushAll(parm);


    }

    @Test
    public void test1(){
        long nextId = snowFlake.getNextId();
        long nextId2 = snowFlake.getNextId();
        long nextId3 = snowFlake.getNextId();
        long nextId4 = snowFlake.getNextId();
        long nextId5 = snowFlake.getNextId();

        System.out.println(nextId -nextId2);
        System.out.println(nextId2 -nextId3);
        System.out.println(nextId3 -nextId4);
        System.out.println(nextId4 -nextId5);
    }
    @Test
    public void test2() throws FileNotFoundException {

        // 设置文件信息
        Set<MataData> mataData = new HashSet<>();
        mataData.add(new MataData("author", "zonghui"));
        mataData.add(new MataData("description", "xxx文件，嘿嘿嘿"));
        File file = new File("D:\\download\\location.png");

        InputStream in= new FileInputStream(file);
        // 上传   （文件上传可不填文件信息，填入null即可）
        StorePath storePath = fastFileStorageClient.uploadFile(in,file.length(),FilenameUtils.getExtension(file.getName()),null);
        System.err.println(storePath);

    }

    @Test
    public void test3() {
       redisTemplate.opsForHash().put("12","23","34");
       redisTemplate.expire("12",5, TimeUnit.SECONDS);
    }

}
