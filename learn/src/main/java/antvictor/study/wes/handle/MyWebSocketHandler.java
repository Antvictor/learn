package antvictor.study.wes.handle;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

    private final static Map<String,WebSocketSession> session = new ConcurrentHashMap<>();



    /**
     * 建立连接
     * @param webSocketSession
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {

    }

    /**
     *  可以获取前端传来的信息
     * @param webSocketSession
     * @param webSocketMessage
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    /**
     *  连接报错
     * @param webSocketSession
     * @param throwable
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    /**
     *  连接关闭
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }


    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


}
