package springboot.study.springbootstudysocket.domain;

import lombok.Data;

import java.util.List;

@Data
public class MessageDetail {
    private int screenId;
    private List<Character> characterList;
}
