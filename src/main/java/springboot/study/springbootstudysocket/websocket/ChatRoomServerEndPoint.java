package springboot.study.springbootstudysocket.websocket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chengxiaoqi1 on 2018/6/27.
 */
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndPoint {

    private static Map<String, Session> livingSession = new ConcurrentHashMap<String, Session>();

    @OnOpen
    public void openSession(@PathParam("username")String username, Session session) {
        String sessionId = session.getId();
        livingSession.put(sessionId, session);
//        sendText(session, "欢迎用户[" + username + "] 来到聊天室");

        sendTextAll("欢迎用户[" + username + "] 来到聊天室");
    }

    @OnMessage
    public void onMessage(@PathParam("username")String username, Session session, String message) {
//        sendText(session, "用户[" + username + "]:" + message);
        sendTextAll("用户[" + username + "]:" + message);
    }

    private void sendText(Session session, String message) {
        RemoteEndpoint.Basic basic = session.getBasicRemote();

        try {
            basic.sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendTextAll(String message) {
        livingSession.forEach(((sessionId, session) -> {
            sendText(session, message);
        }));
    }

    @OnClose
    public void  onClose(@PathParam("username")String username, Session session) {
//        String sessionId = session.getId();
//        sendText(session, "用户[" + username + "] 离开聊天室");

        livingSession.remove(session.getId());
        sendTextAll("用户[" + username + "] 离开聊天室");
    }

}
