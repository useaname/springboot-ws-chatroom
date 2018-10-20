package springboot.study.springbootstudysocket.biz.impl;

import springboot.study.springbootstudysocket.biz.MessageService;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;

public class MessageServiceImpl implements MessageService{

    @Override
    public void sendTextToAll(Map<String, Session> livingSession, String message) {
        livingSession.forEach(((username, session) -> {
            sendText(session, message);
        }));
    }

    public void sendText(Session session, String message) {
        RemoteEndpoint.Basic basic = session.getBasicRemote();

        try {
            basic.sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
