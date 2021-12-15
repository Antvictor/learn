package yangchao.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import yangchao.study.server.FileService;

@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public Object uploadFile(@RequestParam("file")MultipartFile file) {
        fileService.uploadFile(file);
        return "success";
    }


}
