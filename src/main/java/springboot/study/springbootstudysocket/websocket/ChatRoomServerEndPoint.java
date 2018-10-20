package springboot.study.springbootstudysocket.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.study.springbootstudysocket.biz.MessageService;
import springboot.study.springbootstudysocket.biz.SendMessageTask;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chengxiaoqi1 on 2018/6/27.
 */

@ServerEndpoint("/chat-room/{username}")
@Controller
public class ChatRoomServerEndPoint {

    public static Map<String, Session> livingSession = new ConcurrentHashMap<String, Session>();

    @Autowired
    private MessageService messageService;
    @Autowired
    private SendMessageTask sendMessageTask;


    @RequestMapping("sendSubtitle")
    public void sendSubtitle() {
        System.out.println("开始放送字幕。。。。。");
        sendMessageTask.sendMessage();
        System.out.println("字幕放送结束。。。。。");
    }

    @OnOpen
    public void openSession(@PathParam("username")String username, Session session) {
        livingSession.put(username, session);
//        messageService.sendTextToAll(livingSession, "欢迎用户[" + username + "] 来到聊天室");
    }

    @OnMessage
    public void onMessage(@PathParam("username")String username, Session session, String message) {
//        messageService.sendTextToAll(livingSession, "用户[" + username + "]:" + message);
    }

    @OnClose
    public void  onClose(@PathParam("username")String username, Session session) {
        livingSession.remove(session.getId());
//        messageService.sendTextToAll(livingSession, "用户[" + username + "] 离开聊天室");
    }

}
