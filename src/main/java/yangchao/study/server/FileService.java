package yangchao.study.server;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    void uploadFile(MultipartFile file);
}
