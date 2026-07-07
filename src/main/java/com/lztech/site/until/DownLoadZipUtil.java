package com.lztech.site.until;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DownLoadZipUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DownLoadZipUtil.class);

    public static void fileToZip(String filePath, ZipOutputStream zipOut, String realFileName)  {
        try {
            URL url = new URL(filePath);
            HttpURLConnection httpUrl = (HttpURLConnection)url.openConnection();
            httpUrl.connect();
            BufferedInputStream bis = new BufferedInputStream(httpUrl.getInputStream());
            // 缓冲
            final int size = 1024,ten = 10;
            byte[] bufferArea = new byte[size * ten];
            // 将当前文件作为一个zip实体写入压缩流,realFileName代表压缩文件中的文件名称
            zipOut.putNextEntry(new ZipEntry(realFileName));
            int length = 0;
            // 写操作
            while ((length = bis.read(bufferArea, 0, size * ten)) != -1) {
                zipOut.write(bufferArea, 0, length);
            }
            zipOut.closeEntry();
            //关闭流
            // 需要注意的是缓冲流必须要关闭流,否则输出无效
            bis.close();
            // 压缩流不必关闭,使用完后再关
        }catch (Exception e){
            LOGGER.error("fileToZip->",e);
            return;
        }

    }
}
