package springboot.study.springbootstudysocket.domain;

import lombok.Data;

import java.util.List;

@Data
public class Message {
    private Long start;
    private Long persistent;
    private List<MessageDetail> messageDetailList;
}
