package azure.line.bot.sleepsurvey;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@LineMessageHandler
@RestController
public class SleepSurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleepSurveyApplication.class, args);
	}

	@GetMapping
	public String hello() {
		return "Hello";
	}

	@EventMapping
	public TextMessage echoMessage(MessageEvent<TextMessageContent> event) {
		return new TextMessage(event.getMessage().getText());
	}

}
