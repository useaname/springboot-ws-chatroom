package springboot.study.springbootstudysocket.biz;

import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.Map;

@Service
public interface MessageService {

    void sendTextToAll(Map<String, Session> livingSession , String message);

    void sendText(Session session, String message);
}
