package springboot.study.springbootstudysocket.biz;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import springboot.study.springbootstudysocket.domain.Character;
import springboot.study.springbootstudysocket.domain.Message;
import springboot.study.springbootstudysocket.domain.MessageDetail;
import springboot.study.springbootstudysocket.domain.Sentence;
import springboot.study.springbootstudysocket.util.KrcText;
import springboot.study.springbootstudysocket.websocket.ChatRoomServerEndPoint;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

//        //前奏时间
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i < sentenceList.size(); i++) {
//
//            messageService.sendTextToAll(ChatRoomServerEndPoint.livingSession,
//                    JSON.toJSONString(sentenceList.get(i)));
//
//            try {
//                Thread.sleep(sentenceList.get(i).getPersistent());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//
//            if (i < sentenceList.size()) {
//                messageService.sendTextToAll(ChatRoomServerEndPoint.livingSession,
//                        JSON.toJSONString(sentenceList.get(i + 1)));
//            }
//        }
    }


    private void initMessageList(){
//        String filename = "D:\\test\\springboot-ws-chatroom\\will_better.krc";//krc文件的全路径加文件名
        String krcText =  "";
        try {
            URL url = ResourceUtils.getURL(resLocation);
            String filename = url.getPath();
            krcText = new KrcText().getKrcText(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String patternStr = "\\[\\d*,\\d*\\]";
        Pattern compile = Pattern.compile(patternStr);
        Matcher matcher = compile.matcher(krcText);
        if (!matcher.find()) {
            return;
        }

        String myStr = krcText.substring(matcher.start());

        String[] lineArr = myStr.split("\n") ;
        List<Sentence> sentenceList = new ArrayList<>(lineArr.length);
        List<Message> messageList = new ArrayList<>(lineArr.length);

        for (int i = 0; i <lineArr.length; i++) {
            String curLineStr = lineArr[i];
            Sentence sentence = new Sentence();
            Matcher curLineMatcher = compile.matcher(curLineStr);

            if (!curLineMatcher.find()) {
                return;
            }
            String[] sentenceTimeArr = curLineStr.substring(1, curLineMatcher.end() - 1).split(",");
            sentence.setO(Long.valueOf(sentenceTimeArr[0]));
            sentence.setD(Long.valueOf(sentenceTimeArr[1]));

            String allCharacterStr = curLineStr.substring(curLineMatcher.end());
            String[] allCharacterArr = allCharacterStr.replaceAll("(<\\d*,\\d*,\\d*>\\D)", "$1/").split("/");

            List<Character> characterList = new ArrayList<>();

            for (int j = 0; j < allCharacterArr.length; j++) {
                String characterStr = allCharacterArr[j];
                if (StringUtils.isEmpty(characterStr) ) {
                    continue;
                }
                Character character = new Character();
                String[] characterTimeArr = characterStr.replaceAll("<", "").replaceAll(">\\D", "").split(",");
                if (characterTimeArr.length != 3) {
                    continue;
                }
                String characterContent = "";
                characterContent = characterStr.substring(characterStr.length() - 1);
                character.setW(">".equals(characterContent) ? "" : characterContent);
                character.setO(Long.valueOf(StringUtils.trimAllWhitespace(characterTimeArr[0])));
                character.setD(Long.valueOf(StringUtils.trimAllWhitespace(characterTimeArr[1])));

                characterList.add(character);
            }
            sentence.setWs(characterList);
            sentenceList.add(sentence);
        }

        for (Sentence sentence : sentenceList) {
            Message message = new Message();
            message.setStart(sentence.getO());
            message.setPersistent(sentence.getD());

            List<Character> characterList = sentence.getWs();
            List<MessageDetail> messageDetailList = parseToMessageDetailList(characterList, cptNum);
            message.setMessageDetailList(messageDetailList);

            messageList.add(message);
        }

        this.messageList = messageList;

    }


    private List<MessageDetail> parseToMessageDetailList(List<Character> characterList, int cptNum) {
        int charsLen = characterList.size();

        int perNum = 1;
        int leftNum = 0;

        if (charsLen > cptNum) {
            perNum = charsLen / cptNum;
            leftNum = charsLen % cptNum;
        }

        List<MessageDetail> messageDetailList = new ArrayList<>();

        for (int i = 0; i < cptNum; i++) {
            MessageDetail messageDetail = new MessageDetail();
            messageDetail.setScreenId(i);
            List<Character> screenCharacterList = new ArrayList<>();

            for (int j = 0; j < perNum; j++) {
                screenCharacterList.add(characterList.get(i + j));
                characterList.remove(characterList.get(i + j));
            }

            messageDetail.setCharacterList(screenCharacterList);

            messageDetailList.add(messageDetail);
        }

        if (leftNum > 0) {
            MessageDetail messageDetail = new MessageDetail();
            messageDetail.setScreenId(cptNum);

            messageDetail.setCharacterList(characterList);
            messageDetailList.add(messageDetail);
        }

        return messageDetailList;
    }

}
