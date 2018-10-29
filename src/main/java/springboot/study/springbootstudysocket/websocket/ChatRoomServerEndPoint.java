package springboot.study.springbootstudysocket.websocket;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.study.springbootstudysocket.biz.MessageService;
import springboot.study.springbootstudysocket.biz.SendMessageTask;
import springboot.study.springbootstudysocket.domain.InstructMessage;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chengxiaoqi1 on 2018/6/27.
 */

@ServerEndpoint("/chat-room/{screenId}")
@Controller
@RequestMapping("subtitle")
public class ChatRoomServerEndPoint {

    public static Map<String, Session> livingSession = new ConcurrentHashMap<String, Session>();

    @Autowired
    private MessageService messageService;

    @RequestMapping("/manager")
    public void instruct(@RequestBody InstructMessage message) {
        messageService.sendTextToAll(livingSession, JSON.toJSONString(message));
    }


    @OnOpen
    public void openSession(@PathParam("screenId")String screenId, Session session) {
//        if (livingSession.containsKey(screenId)){
//            return;
//        }
        livingSession.put(screenId, session);
        System.out.println("connect screen ID = " + screenId);
//        messageService.sendTextToAll(livingSession, "欢迎用户[" + username + "] 来到聊天室");
    }

    @OnMessage
    public void onMessage(@PathParam("username")String username, Session session, String message) {
//        messageService.sendTextToAll(livingSession, "用户[" + username + "]:" + message);
    }

    @OnClose
    public void  onClose(@PathParam("username")String username, Session session) {
        livingSession.remove(session.getId());
        System.out.println("close Id = " + session.getId());
//        messageService.sendTextToAll(livingSession, "用户[" + username + "] 离开聊天室");
    }

}
