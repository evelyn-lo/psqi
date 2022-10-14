package azure.line.bot.sleepsurvey;


import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Integer> answers;
    private List<String> questions;
    public User()
    {
        answers = new ArrayList<Integer>();
        questions = new ArrayList<String>();
        questions.add(1, " During the past month, what time have you usually gone to bed at night? ");
    }

    public String  ask(int num)
    {
        return questions.get(num);
    }
    public void addAnswer(int num, int ans)
    {
        answers.add(num, ans);
    }




}
