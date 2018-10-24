package springboot.study.springbootstudysocket.domain;

import lombok.Data;

@Data
public class InstructMessage {
    private String order;
    private String message;
    private int fontSize;
}
