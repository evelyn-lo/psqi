package azure.line.bot.sleepsurvey;


import java.util.ArrayList;
import java.util.List;

public class User {

    private final List<String> answers;
    private final List<String> questions;
    private int currNum;


    public User() {
        answers = new ArrayList<>();
        questions = new ArrayList<>();

        questions.add(0, "1: 過去一個月來，您晚上通常幾點上床睡覺？(格式為00:00，例如23:12)");
        questions.add(1, "2: 過去一個月來，您在上床後，通常躺多久才能入睡？(輸入分鐘數字)");
        questions.add(2, "3: 過去一個月來，您早上通常幾點起床？(格式為00:00，例如07:30)");
        questions.add(3, "4: 過去一個月來，您每天晚上真正睡著的時間約多少（這可能和您躺在床上所花的時間不同？(格式為00:00，例如10分鐘 00:10)");
        questions.add(4, "5-a: 過去一個月來，您有多少天無法在30分鐘內入睡(輸入0-3)\n0: 從未發生\n1: 每週少於一次\n2: 每週1~2\n3: 每週3次或以上");
        questions.add(5, "5-b: 過去一個月來，您有多少天半夜或清晨醒來(輸入0-3)\n0: 從未發生\n1: 每週少於一次\n2: 每週1~2\n3: 每週3次或以上");
        questions.add(6, "5-c: 過去一個月來，您有多少天需要起床上廁所(輸入0-3)\n0: 從未發生\n1: 每週少於一次\n2: 每週1~2\n3: 每週3次或以上");
        questions.add(7, "5-d: 過去一個月來，您有多少天呼吸不順暢(輸入0-3)\n0: 從未發生\n1: 每週少於一次\n2: 每週1~2\n3: 每週3次或以上");
        questions.add(8, "5-e: 過去一個月來，您有多少天咳嗽或大聲打鼾(輸入0-3)\n0: 從未發生\n1: 每週少於一次\n2: 每週1~2\n3: 每週3次或以上" );
        questions.add(9, "5-f: 過去一個月來，您有多少天感覺很冷(輸入0-3)\n0: 從未發生\n1: 每週少於一次\n2: 每週1~2\n3: 每週3次或以上");
        questions.add(10, "5-g: 過去一個月來，您有多少天感覺很熱(輸入0-3)\n0: 從未發生\n1: 每週少於一次\n2: 每週1~2\n3: 每週3次或以上");
        questions.add(11, "5-h: 過去一個月來，您有多少天作惡夢(輸入0-3)\n0: 從未發生\n1: 每週少於一次\n2: 每週1~2\n3: 每週3次或以上");
        questions.add(12, "5-i: 過去一個月來，您有多少天感到疼痛(輸入0-3)\n0: 從未發生\n1: 每週少於一次\n2: 每週1~2\n3: 每週3次或以上");
        questions.add(13, "6: 過去一個月來，您有多少次需要藉助藥物(醫師處方或成藥)來幫助睡眠(輸入0-3)\n0: 從未發生\n1: 每週少於一次\n2: 每週1~2\n3: 每週3次或以上");
        questions.add(14, "7: 過去一個月來，當您在開車、用餐、從事日常社交活動時，有多少次覺得難以保持清醒狀態(輸入0-3)\n0: 從未發生\n1: 每週少於一次\n2: 每週1~2\n3: 每週3次或以上");
        questions.add(15, "8: 過去一個月來，要打起精神來完成您應該做的事情對您有多少困擾(輸入0-3)\n0: 完全沒有困擾\n1: 只有很少困擾\n2: 有些困擾\n3: 有很大的困擾");
        questions.add(16, "9: 過去一個月來，您對您自己的睡眠品質整體評價如何(輸入0-3)\n0: 非常好\n1: 好\n2: 不好\n3: 非常不好");
        currNum = 0;
    }


    public String ask(int num) {
        return questions.get(num);
    }

    public void addAnswer(int num, String ans) {
        answers.add(num, ans);
    }

    public void setCurrNum(int number) {
        this.currNum = number;
    }

    public int getCurrNum() {
        return currNum;
    }
    public void changeCurrNum(int i)
    {
        currNum += i;
    }

    public List<String> getAns() {
        return answers;
    }

    public boolean checkRightFormat(String s) {
        /**
         *
         * 0, 2, 3 -> 23:13
         * 1 --> any int
         * 4 ~ 12 -> any int (1~5)
         * 13 ~ 16 -> any int (1 ~ 4)
         */

        // 0, 2, 3
        int ans2 = 0;
        if (currNum == 0 || currNum == 2 || currNum == 3) {

            // 23:13
            // ["23", "13"]
            String[] answerParts = s.split(":");
            List<Integer> ans = new ArrayList<>();

            // check every element in this array
            for (String each : answerParts) {

                // if any element is NOT int -> return false
                try {
                    // all int var
                    int t = Integer.parseInt(each);
                    ans.add(t);
                } catch (NumberFormatException e) {
                    // must contain NOT int variable
                    return false;
                }
            }

            return ans.size() == 2;

        } else {
            try {
                ans2 = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        if (currNum == 1) {
            return true;
        }


        // 4 ~ 12
        if (currNum >= 4 && currNum <= 16) {
            if (ans2 >= 0 && ans2 <= 3) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }
    public String allAnswers()
    {
        String ans = "\n";
        for (int i = 0; i < questions.size(); i++)
        {
            if (i != 0 )
            {
            ans += "\n";
            ans += "\n";
            }
            
            ans += "[q]" + questions.get(i);
            ans += "\n";
            ans += "[a]: " + answers.get(i);
            
        }
        return ans;
            
    }
}

