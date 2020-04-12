package com.huangjinyong.library.util.other;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.UUID;


/**
 * @author huangjinyong
 */
public class ImageUp {

    /**
     * j将图片copy到src img目录下 该目录用户保存上传的图片
     * @param path 路径
     * @return 返回生成文件名称 用于数据库保存
     */
    public static String upload(String path) throws IOException {
        File inImg=new File(path);
        String outImgName=UUID.randomUUID().toString().replaceAll("-","")+inImg.getName();
        File outImg=new File("src/img",outImgName);
        outImg.createNewFile();
        FileChannel inChannel = new FileInputStream(inImg).getChannel();
        FileChannel outChannel=new FileOutputStream(outImg).getChannel();
        inChannel.transferTo(0,inChannel.size(),outChannel);
        return outImgName;
    }

}
