package azure.line.bot.sleepsurvey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CalculateScore {
    private static final Logger log = LoggerFactory.getLogger(CalculateScore.class);

    public static int component1(List<String> ans) {
        return Integer.parseInt(ans.get(16));
    }

    public static int component2(List<String> ans) {

        int question2Answer = Integer.parseInt(ans.get(1));
        int question5aAnswer = Integer.parseInt(ans.get(4));

        int q2 = 0;
        int score = 0;

        if (question2Answer <= 15) {
            q2 = 0;
        } else if (question2Answer <= 30) {
            q2 = 1;
        } else if (question2Answer <= 60) {
            q2 = 2;
        } else {
            q2 = 3;
        }

        score = q2 + question5aAnswer;

        if (score == 0) {
            return 0;
        } else if (score <= 2) {
            return 1;
        } else if (score <= 4) {
            return 2;
        } else {
            return 3;
        } 
    }

    public static int component5(List<String> ans) {
        //component 5 (5 - 12)
        int sum = 0;
        int value = 0;
        for (int i = 5; i <= 12; i++) {
            value = Integer.parseInt(ans.get(i));
            sum = sum + value;
        }

        // range:
        if (sum == 0) {
            return 0;
        }

        else if (sum <= 9) {
            return 1;
        }

        else if (sum <= 18) {
            return 2;
        } else {
            return 3;
        }
    }

    public static int component6(List<String> ans) {
        String answer = ans.get(13);
        return Integer.parseInt(answer);
    }

    public static int component7(List<String> ans) {
        int ans1 = Integer.parseInt(ans.get(14));
        int ans2 = Integer.parseInt(ans.get(15));

        int sum = ans1 + ans2;

        if (sum == 0) {
            return 0;
        }

        else if (sum <= 2) {
            return 1;
        }

        else if (sum <= 4) {
            return 2;
        }

        else  {
            return 3;
        }
        

    }


    public static int calculateScore(User user) {

        log.info("{}", user.getAns());

        List<String> ans = user.getAns();

        int score = 0;

        score = score + component1(ans);
        score = score + component2(ans);
        score = score + component3(ans);
        score = score + component4(ans);
        score = score + component5(ans);
        score = score + component6(ans);
        score = score + component7(ans);

        return score;
    }

    public static int component3(List<String> Ans) {

        String[] answers = Ans.get(3).split(":");
        List<Integer> ans = new ArrayList<>();
        // check every element in this array
        for (String each : answers) {

            // if any element is NOT int -> return false
            try {
                // all int var
                ans.add(Integer.parseInt(each));

            } catch (NumberFormatException e) {
                // must contain NOT int variable

            }
        }
        // todo why + 1?
        int minduration = ans.get(0) * 60 + ans.get(1);
        
        if (minduration > 420) {
            return 0;
        } else if (minduration >= 360) {
            return 1;
        } else if (minduration >= 300) {
            return 2;
        } else {
            return 3;
        }
        
    }

    //component 4 (0, 2, 3) -> parse to int
    public static int component4(List<String> ans) {

        // 2 -> 23:00
        // 3 -> 06:00

        String[] gotoBed = ans.get(0).split(":");
        String[] getUp = ans.get(2).split(":");
        String[] totalSleepTime = ans.get(3).split(":");

        List<Integer> ansInt = new ArrayList<>();

        for (String each : gotoBed) {
            ansInt.add(Integer.parseInt(each));
        }
        for (String each : getUp) {
            ansInt.add(Integer.parseInt(each));
        }
        for (String each : totalSleepTime) {
            ansInt.add(Integer.parseInt(each));
        }

        //calculate in bed time
        // goto, getup, tst
        int inbedMins;
        // sleep at night, wake up in the morning
        if (ansInt.get(0) > 12 && ansInt.get(2) <= 12) {
            inbedMins = (24 - ansInt.get(0) + ansInt.get(2)) * 60;

            // sleep at morning and wake up at morning
        } else if (ansInt.get(0) <= 12 && ansInt.get(2) <= 12) {
            inbedMins = ((ansInt.get(2) - ansInt.get(0)) * 60);

            // sleep at morning and wake up night
        } else if (ansInt.get(0) <= 12 && ansInt.get(0) > 12) {
            inbedMins = (ansInt.get(2) - ansInt.get(0)) * 60;
        } 
        //sleep at night, wake up at night
        else {
            inbedMins = (ansInt.get(2) - ansInt.get(0)) * 60;
        }

        inbedMins += ansInt.get(3);
        inbedMins -= ansInt.get(1);

        int sleepTime = ansInt.get(4) * 60 + ansInt.get(5);


        double sleepEff = (double) sleepTime / (double) inbedMins;

        if (sleepEff > 0.85) {
            return 0;
        } else if (sleepEff >= 0.75) {
            return 1;
        } else if (sleepEff >= 0.65) {
            return 2;
        } else {
            return 3;
        }


    }


}
