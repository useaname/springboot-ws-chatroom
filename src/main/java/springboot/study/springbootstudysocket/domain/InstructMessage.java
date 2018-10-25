package springboot.study.springbootstudysocket.domain;

import lombok.Data;

@Data
public class InstructMessage {
    private String order;// setFontSize,
    private String message;
    private int fontSize;
}
