package larry.study.utils;

public class HttpClientResult {
    public HttpClientResult(int scInternalServerError) {
        System.out.println(scInternalServerError);
    }
    public HttpClientResult(int scInternalServerError, String content) {
        System.out.println(content);
        System.out.println(scInternalServerError);
    }
}
