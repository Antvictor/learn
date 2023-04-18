package antvictor.study.server.impl;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import antvictor.study.server.FileService;

import java.io.*;

@Service
public class FIleServiceImpl implements FileService {

    @Override
    public void uploadFile(MultipartFile file) {
        String newFIlePath = "/Users/exceedy/job/test/";
        // 获取文件后缀
        if (file != null) {
            String fileName = file.getOriginalFilename().trim();
            System.out.println("fileName:" + fileName);
            String suffx = fileName.substring(fileName.lastIndexOf("."));

            String uploadName = newFIlePath + "中文测试" + suffx;

            // 初始化输入流及输出流
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                // 获取输入流
                inputStream = file.getInputStream();
                File filePath = new File(newFIlePath);
                // 是否存在，不存在就创建
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                outputStream = new FileOutputStream(new File(uploadName));
                IOUtils.copy(inputStream,outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 关闭流
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
