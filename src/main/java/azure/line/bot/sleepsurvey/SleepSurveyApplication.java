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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@LineMessageHandler
@RestController
public class SleepSurveyApplication {

    private final Logger log = LoggerFactory.getLogger(SleepSurveyApplication.class);

    private static final Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(SleepSurveyApplication.class, args);
    }

    @EventMapping
    public TextMessage messageEvent(MessageEvent<TextMessageContent> event) {
        log.info("event: {}", event);

        var userId = event.getSource().getUserId();
        var text = event.getMessage().getText();

        User user = getUser(userId);

        if (text.equals("是")) {
            
            String question1 = user.ask(0);
            user.changeDone(false);
            user.setCurrNum(0);
            return new TextMessage("開始匹茲堡睡眠品質量表(共有九題)\n\n*隨時回傳[重答]可更改上題答案*\n*隨時回傳[是]可重新回答整個問卷*\n\n" + question1 );
            
        }
        if (text.equals("所有答案")){
            if (user.getDone()){
                return new TextMessage("以下為問卷的所有答案: \n" + user.allAnswers());
            }
        }
          
        
        /**if (text.equals("重新")) {

            // remove the old user data, and create the new user object
            User newUser = removeAndCreate(userId);
            String question1 = newUser.ask(0);
            newUser.setCurrNum(0);
            return new TextMessage("開始匹茲堡睡眠品質量表(共有九題)\n\n*若想更改答案可回傳[重答]*\n*若想重新填寫整個問卷可回傳[是]*\n\n" + question1);
        }**/

        int userCurrentNum = user.getCurrNum();

        boolean isFormatted = user.checkRightFormat(text);
        if (isFormatted) {

            user.addAnswer(userCurrentNum, text);
            user.setCurrNum(userCurrentNum + 1);

            if (user.getCurrNum() == 17) {
                int score = CalculateScore.calculateScore(user);
                user.changeDone(true);
                return new TextMessage("恭喜您答完所有問題！您的匹茲堡睡眠質量指數為: " + score + "\n\n<5 為正常\n>=5 可考慮就醫\n\n" + "-輸入\"所有答案\"我將回傳您所有的答案\n-輸入\"是\"可重新開始測驗");
            }

            return new TextMessage(user.ask(user.getCurrNum())); }
        else {
    		
                            if (text.equals("重答")) {

            user.setCurrNum(userCurrentNum-1);
            return new TextMessage(user.ask(user.getCurrNum()));
        }

            if (user.getCurrNum() == 0) {
                return new TextMessage("格式錯誤，請輸入\"是\"來重新開始");
            }

            // return 'you input wrong format'
            return new TextMessage("格式錯誤請重新回答 \n" + user.ask(userCurrentNum));
        }

    }

    private User removeAndCreate(String userId) {
        User newUser = new User();
        users.put(userId, newUser);
        return newUser;
    }

    private User getUser(String userId) {
        User u;
        if (users.containsKey(userId)) {
            u = users.get(userId);
        } else {
            u = new User();
            users.put(userId, u);
        }
        return u;
    }
}


