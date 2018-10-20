package springboot.study.springbootstudysocket.domain;

import lombok.Data;

import java.util.List;

@Data
public class Message {
    private Long nextSendTime;
    private List<MessageDetail> messageDetailList;
}
