package azure.line.bot.sleepsurvey;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@LineMessageHandler
public class SleepSurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleepSurveyApplication.class, args);
	}

	@EventMapping
	public TextMessage echoMessage(MessageEvent<TextMessageContent> event) {
		return new TextMessage(event.getMessage().getText());
	}

}
