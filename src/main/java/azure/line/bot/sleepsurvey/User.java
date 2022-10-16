package azure.line.bot.sleepsurvey;


import java.util.ArrayList;
import java.util.List;

public class User {

    private List<Integer> answers;
    private List<String> questions;

    public User() {
        answers = new ArrayList<>();
        questions = new ArrayList<>();
        questions.add(0, "1.過去一個月來，您晚上通常幾點上床睡覺？(格式為00:00，例如23:12)");
        questions.add(1, "2.過去一個月來，您在上床後，通常躺多久才能入睡？(輸入數字)");
        questions.add(2, "3.過去一個月來，您早上通常幾點起床？(格式為00:00，例如07:30)");
        questions.add(3, "4.過去一個月來，您每天晚上真正睡著的時間約多少（這可能和您躺在床上所花的時間不同）？(格式為00:00，例如10分鐘 00:10)");
        questions.add(4, "5.過去一個月來，您的睡眠有多少次受到下列干擾？(1:從未發生，2:每週少於一次，3:每週1~2次，4:每週3次或以上)\n 5-a. 無法在 30 分鐘入睡");
        questions.add(5, "(1:從未發生，2:每週少於一次，3:每週1~2次，4:每週3次或以上)：\n5-b. 半夜或清晨醒來");
        questions.add(6, "(1:從未發生，2:每週少於一次，3:每週1~2次，4:每週3次或以上)：\n5-c. 需要起床上廁所");
        questions.add(7, "(1:從未發生，2:每週少於一次，3:每週1~2次，4:每週3次或以上)：\n5-d. 呼吸不順暢");
        questions.add(8, "(1:從未發生，2:每週少於一次，3:每週1~2次，4:每週3次或以上)：\n5-e. 咳嗽或大聲打鼾");
        questions.add(9, "(1:從未發生，2:每週少於一次，3:每週1~2次，4:每週3次或以上)：\n5-f. 感覺很冷");
        questions.add(10, "(1:從未發生，2:每週少於一次，3:每週1~2次，4:每週3次或以上)：\n5-g. 感覺很熱");
        questions.add(11, "(1:從未發生，2:每週少於一次，3:每週1~2次，4:每週3次或以上)：\n5-h. 作惡夢");
        questions.add(12, "(1:從未發生，2:每週少於一次，3:每週1~2次，4:每週3次或以上)：\n5-i. 疼痛");
        questions.add(13, "6.過去一個月來，您有多少次需要藉助藥物(醫師處方或成藥)來幫助睡眠？(1:從未發生，2:每週少於一次，3:每週1~2次，4:每週3次或以上)：");
        questions.add(14, "7.過去一個月來，當您在開車、用餐、從事日常社交活動時，有多少次覺得難以保持清醒狀態？(1:從未發生，2:每週少於一次，3:每週1~2次，4:每週3次或以上)：");
        questions.add(15, "8.過去一個月來，要打起精神來完成您應該做的事情對您有多少困擾(1:完全沒有困擾，2:很少困擾，3:有些困擾，4:有很大的困擾)？");
        questions.add(16, "9.過去一個月來，您對您自己的睡眠品質整體評價如何(1:非常好，2:好，3:不好，4:非常不好)？");

    }

    public String ask(int num) {
        return questions.get(num);
    }

    public void addAnswer(int num, int ans) {
        answers.add(num, ans);
    }


}
