package com.edu.zju.culture.common;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author y4oung
 * @date 2020/3/31 6:26 PM
 * @description 文件上传下载工具类
 */
public class AppFileUtils {
    //文件上传的保存路径
    public static  String UPLOAD_PATH="/Users/y4oung/IdeaProjects/culture/upload/";//默认值

    static {
        //读取配置文件的存储地址
        InputStream stream = AppFileUtils.class.getClassLoader().getResourceAsStream("file.properties");
        Properties properties = new Properties();
        try{
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String property = properties.getProperty("filepath");
        if(null!=property){
            UPLOAD_PATH = property;
        }
    }

    /**
     * 根据文件老名字得到新名字
     * @param oldName
     * @return
     */
    public static String createNewFileName(String oldName){
        String stuff = oldName.substring(oldName.lastIndexOf("."),oldName.length());
        return IdUtil.simpleUUID().toUpperCase()+stuff;
    }

    /**
     * 文件下载
     * @param path
     * @return
     */
    public static ResponseEntity<Object> createResponseEntity(String path){
        //1.构造文件对象
        File file = new File(UPLOAD_PATH,path);
        System.out.println(file.getAbsolutePath());
        if(file.exists()){
            //将下载的文件，封装byte[]
            byte[] bytes = null;
            try{
                bytes = FileUtil.readBytes(file);
            }catch (Exception e){
                e.printStackTrace();
            }

            //创建封装响应头信息的对象
            HttpHeaders headers = new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //创建ResponseEntity对象
            ResponseEntity<Object> entity = new ResponseEntity<>(bytes,headers,HttpStatus.CREATED);
            return entity;
        }
        return null;

    }

    /**
     * 根据路径改名字 去掉_temp
     * @param icon
     * @return
     */
    public static String renameFile(String icon) {
        File file = new File(UPLOAD_PATH,icon);
        String replace = icon.replace("_temp","");
        if(file.exists()){
            file.renameTo(new File(UPLOAD_PATH,replace));
        }
        return replace;
    }

    /**
     * 根据老路径删除图片
     * @param oldPath
     */
    public static void removeFileByPath(String oldPath) {
        if(!oldPath.equals(Constant.IMAGES_DEFAULTAVATAR_JPG)){
            File file = new File(UPLOAD_PATH,oldPath);
            if(file.exists()){
                file.delete();
            }
        }
    }
}
