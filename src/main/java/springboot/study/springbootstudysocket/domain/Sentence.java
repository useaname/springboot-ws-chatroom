package springboot.study.springbootstudysocket.domain;

import lombok.Data;

import java.util.List;

@Data
public class Sentence {
    private long o;
    private long d;
    private List<Character> ws;
}
