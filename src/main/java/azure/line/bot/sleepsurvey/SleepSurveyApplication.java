package azure.line.bot.sleepsurvey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(SleepSurveyApplication.class);

    private static User u = new User();

    public static void main(String[] args) {
        SpringApplication.run(SleepSurveyApplication.class, args);
    }

    @EventMapping
    public TextMessage messageEvent(MessageEvent<TextMessageContent> event) {
        log.info("event: {}", event);

        var userId = event.getSource().getUserId();
        var text = event.getMessage().getText();

        if (text.equals("是")) {
            return new TextMessage("開始psqi睡眠品質衡量測驗");
        }

        int count = 0;

        while (count < 9) {
            if (count < 9) {
                return new TextMessage(u.ask(count));
            }
            int ans;

            try {
                ans = Integer.parseInt(text);
            } catch (NumberFormatException exception) {
                ans = -1;
            }
            if (ans != -1) {
                u.addAnswer(count, ans);
            }
        }
        return null;
    }
}


