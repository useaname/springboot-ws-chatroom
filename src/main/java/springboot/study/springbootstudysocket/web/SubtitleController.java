package springboot.study.springbootstudysocket.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.study.springbootstudysocket.domain.Message;

import java.util.List;

@Controller
public class SubtitleController {

    @RequestMapping("getMessageList")
    public List<Message> getMessageList () {

        return null;
    }
}
