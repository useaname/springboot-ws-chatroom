package springboot.study.springbootstudysocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import springboot.study.springbootstudysocket.websocket.ChatRoomServerEndPoint;

@SpringBootApplication
@EnableWebSocket
public class SpringbootStudySocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootStudySocketApplication.class, args);
	}


	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	@Bean
	public ChatRoomServerEndPoint chatRoomServerEndPoint() {
		return new ChatRoomServerEndPoint();
	}
}
