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

        questions.add(0, "1.過去一個月來，您晚上通常幾點上床睡覺？(格式為00:00，例如23:12)");
        questions.add(1, "2.過去一個月來，您在上床後，通常躺多久才能入睡？(輸入分鐘數字)");
        questions.add(2, "3.過去一個月來，您早上通常幾點起床？(格式為00:00，例如07:30)");
        questions.add(3, "4.過去一個月來，您每天晚上真正睡著的時間約多少（這可能和您躺在床上所花的時間不同？(格式為00:00，例如10分鐘 00:10)");
        questions.add(4, "5.過去一個月來，您的睡眠有多少次受到下列干擾？5-a. 無法在 30 分鐘入睡 (0:從未發生，1:每週少於一次，2:每週1~2次，3:每週3次或以上)");
        questions.add(5, "5.過去一個月來，您的睡眠有多少次受到下列干擾？5-b. 半夜或清晨醒來 (0:從未發生，1:每週少於一次，2:每週1~2次，3:每週3次或以上)");
        questions.add(6, "5.過去一個月來，您的睡眠有多少次受到下列干擾？5-c. 需要起床上廁所 (0:從未發生，1:每週少於一次，2:每週1~2次，3:每週3次或以上)");
        questions.add(7, "5.過去一個月來，您的睡眠有多少次受到下列干擾？5-d. 呼吸不順暢 (0:從未發生，1:每週少於一次，2:每週1~2次，3:每週3次或以上)");
        questions.add(8, "5.過去一個月來，您的睡眠有多少次受到下列干擾？5-e. 咳嗽或大聲打鼾 (0:從未發生，1:每週少於一次，2:每週1~2次，3:每週3次或以上)");
        questions.add(9, "5.過去一個月來，您的睡眠有多少次受到下列干擾？5-f. 感覺很冷 (0:從未發生，1:每週少於一次，2:每週1~2次，3:每週3次或以上)");
        questions.add(10, "5.過去一個月來，您的睡眠有多少次受到下列干擾？5-g. 感覺很熱 (0:從未發生，1:每週少於一次，2:每週1~2次，3:每週3次或以上)");
        questions.add(11, "5.過去一個月來，您的睡眠有多少次受到下列干擾？5-h. 作惡夢 (0:從未發生，1:每週少於一次，2:每週1~2次，3:每週3次或以上)");
        questions.add(12, "5.過去一個月來，您的睡眠有多少次受到下列干擾？5-i. 疼痛 (0:從未發生，1:每週少於一次，2:每週1~2次，3:每週3次或以上)");
        questions.add(13, "6.過去一個月來，您有多少次需要藉助藥物(醫師處方或成藥)來幫助睡眠？(0:從未發生，1:每週少於一次，2:每週1~2次，3:每週3次或以上)");
        questions.add(14, "7.過去一個月來，當您在開車、用餐、從事日常社交活動時，有多少次覺得難以保持清醒狀態？(0:從未發生，1:每週少於一次，2:每週1~2次，3:每週3次或以上)");
        questions.add(15, "8.過去一個月來，要打起精神來完成您應該做的事情對您有多少困擾(0:完全沒有困擾，1:很少困擾，2:有些困擾，3:有很大的困擾)？");
        questions.add(16, "9.過去一個月來，您對您自己的睡眠品質整體評價如何(0:非常好，1:好，2:不好，3:非常不好)？");
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
            String[] answers = s.split(":");

            // check every element in this array
            for (String each : answers) {

                // if any element is NOT int -> return false
                try {
                    // all int var
                    int ans = Integer.parseInt(each);
                    return true;
                } catch (NumberFormatException e) {
                    // must contain NOT int variable
                    return false;
                }
            }


        } else {
            try {
                ans2 = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        // 1
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
}

