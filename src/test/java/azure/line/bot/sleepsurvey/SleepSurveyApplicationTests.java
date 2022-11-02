package azure.line.bot.sleepsurvey;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SleepSurveyApplicationTests {

    private User user = new User();

    @Test
    void checkQuestion1Format() {
        user.setCurrNum(0);

        String test = "23:00";

        boolean isFormatted = user.checkRightFormat(test);

        assertTrue(isFormatted);
    }

    @Test
    void checkQuestion2Format() {
        user.setCurrNum(1);
        String test = "80";
        boolean isFormatted = user.checkRightFormat(test);
        assertTrue(isFormatted);
    }
    @Test
    void checkQuestion3Format() {
        user.setCurrNum(2);
        String test = "07:25";
        boolean isFormatted = user.checkRightFormat(test);
        assertTrue(isFormatted);
    }
    @Test
    void checkQuestion4Format() {
        user.setCurrNum(3);
        String test = "08:00";
        boolean isFormatted = user.checkRightFormat(test);
        assertTrue(isFormatted);
    }
    @Test
    void checkQuestion5Format() {
        user.setCurrNum(4);
        String test = "1";
        boolean isFormatted = user.checkRightFormat(test);
        assertTrue(isFormatted);
    }
    @Test
    void checkQuestion6Format() {
        user.setCurrNum(5);
        String test = "0";
        boolean isFormatted = user.checkRightFormat(test);
        assertTrue(isFormatted);
    }
    @Test
    void checkQuestion7Format() {
        user.setCurrNum(6);
        String test = "2";
        boolean isFormatted = user.checkRightFormat(test);
        assertTrue(isFormatted);
    }



    @Test
    void checkQuestion8Format() {
        user.setCurrNum(7);
        String test = "2";
        boolean isFormatted = user.checkRightFormat(test);
        assertTrue(isFormatted);
    }
    @Test
    void checkQuestion9Format() {
        user.setCurrNum(8);
        String test = "0";
        boolean isFormatted = user.checkRightFormat(test);
        assertTrue(isFormatted);
    }
    @Test
    void checkQuestion_10_Format() {
        user.setCurrNum(9);
        String test = "1";
        boolean isFormatted = user.checkRightFormat(test);
        assertTrue(isFormatted);
    }
    @Test
    void checkQuestion_11_Format() {
        user.setCurrNum(10);
        String test = "0";
        boolean isFormatted = user.checkRightFormat(test);
        assertTrue(isFormatted);
    }
    @Test
    void checkQuestion_12_Format() {
        user.setCurrNum(11);
        String test = "2";
        boolean isFormatted = user.checkRightFormat(test);
        assertTrue(isFormatted);
    }
    @Test
    void checkQuestion_13_Format() {
        user.setCurrNum(12);
        String test = "3";
        boolean isFormatted = user.checkRightFormat(test);
        assertTrue(isFormatted);
    }
    @Test
    void checkQuestion_14_Format() {
        user.setCurrNum(13);
        String test = "3";
        boolean isFormatted = user.checkRightFormat(test);
        assertTrue(isFormatted);
    }
    @Test
    void checkQuestion_15_Format() {
        user.setCurrNum(14);
        String test = "2";
        boolean isFormatted = user.checkRightFormat(test);
        assertTrue(isFormatted);
    }
    @Test
    void checkQuestion_16_Format() {
        user.setCurrNum(15);
        String test = "0";
        boolean isFormatted = user.checkRightFormat(test);
        assertTrue(isFormatted);
    }
}
