package springboot.study.springbootstudysocket.biz;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import springboot.study.springbootstudysocket.domain.Message;
import springboot.study.springbootstudysocket.domain.MessageDetail;
import springboot.study.springbootstudysocket.websocket.ChatRoomServerEndPoint;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@Component
public class SendMessageTask {

    @Autowired
    private MessageService messageService;

    @Value("${resLocation}")
    private String resLocation;

    @Value("${cptNum}")
    private int cptNum;

    private List<Message> messageList;

    public void sendMessage() {

        //前奏时间
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < messageList.size(); i++) {

            messageService.sendTextToAll(ChatRoomServerEndPoint.livingSession,
                    JSON.toJSONString(messageList.get(i)));

            try {
                Thread.sleep(messageList.get(i).getNextSendTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (i < messageList.size()) {
                messageService.sendTextToAll(ChatRoomServerEndPoint.livingSession,
                        JSON.toJSONString(messageList.get(i + 1)));
            }
        }
    }


    @PostConstruct
    private void initMessageList(String resLocation) {

        InputStream messageStream = this.getClass().getClassLoader().getResourceAsStream(resLocation);
        Properties messageProperties = new Properties();

        try {
            messageProperties.load(messageStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Message> messageList = new ArrayList<>();

        Set<Object> keys = messageProperties.keySet();
        for (Object key : keys) {


            String messageStr = (String)key;
            List<MessageDetail> messageDetailList = parseStrToMessageDetail(messageStr, cptNum);

            String nextSendTimeStr = (String)messageProperties.get(key);

            Message message = new Message();
            message.setMessageDetailList(messageDetailList);
            message.setNextSendTime(Long.valueOf(nextSendTimeStr));

            messageList.add(message);
        }

        this.messageList = messageList;
    }


    private List<MessageDetail> parseStrToMessageDetail(String messageStr, int cptNum) {
        char[] chars = messageStr.toCharArray();
        int charsLen = chars.length;

        int perNum = 1;
        int leftNum = 0;

        if (charsLen > cptNum) {
            perNum = charsLen / cptNum;
            leftNum = charsLen % cptNum;
        }

        List<MessageDetail> messageDetailList = new ArrayList<>();

        for (int i = 0; i < charsLen; i++) {
            MessageDetail messageDetail = new MessageDetail();

            messageDetail.setScreenId(i);
            String text = "";

            if (i + perNum < charsLen) {
                text = messageStr.substring(i, i + perNum);
            } else if (leftNum > 0) {
                text = messageStr.substring(charsLen - leftNum , charsLen);
            }
            messageDetail.setText(text);
            messageDetailList.add(messageDetail);
        }

        return messageDetailList;
    }

}
