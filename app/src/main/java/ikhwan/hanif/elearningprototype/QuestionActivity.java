package ikhwan.hanif.elearningprototype;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView lblQuestion;
    RadioButton optionA;
    RadioButton optionB;
    RadioButton optionC;
    RadioButton optionD;
    Button confirm;
    String rightAnswer;
    String Answer;
    List<Question> questions;
    int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        confirm = findViewById(R.id.confirm);
        lblQuestion = findViewById(R.id.lblPergunta);
        optionA = findViewById(R.id.opcaoA);
        optionB = findViewById(R.id.opcaoB);
        optionC = findViewById(R.id.opcaoC);
        optionD = findViewById(R.id.opcaoD);
        score = 0;
        radioGroup = findViewById(R.id.radioGroup);

        questions = new ArrayList<Question>(){
            {
                add(new Question("1. Ria: Good afternoon, Sinta\n" +
                        "\n" +
                        "Sinta: …", "B", "Hi", "Good afternoon, Ria","I am fine", "Thank you"));

                add(new Question("2. Arrange the words! \n" +
                        "\n" +
                        "Is - name - Raka - my", "D", "Raka is my name", "Is my name Raka","Name my Raka is", "My name is Raka "));

                add(new Question("3. Mira: how are you? \n" +
                        "\n" +
                        "Arif: … ", "B", "How are you", "Im fine","Thank you", "Nice to meet you"));

                add(new Question("4. My sister is… She always helps me.", "A", "Kind", "Cruel", "Fat", "Bad"));

                add(new Question("5. Sisi always tells the truth. She is an… girl", "A", "Honest", "Young", "Pretty", "Old"));

                add(new Question("6. My dad has a mother. She is my… ", "B", "Uncle", "Grandmother", "Grandfather", "Cousin"));

                add(new Question("7. An elephant is a big animal. Antonym of big is… ", "A", "Small", "Fat", "Smell", "Thin"));

                add(new Question("8. Father: Do you like watermelon? \n" +
                        "\n" +
                        "Rudi: No, I don't like it\n" +
                        "\n" +
                        "The words mean… ", "B", "Like", "Dislike", "Need", "Want"));

                add(new Question("9. I … to school every day", "A", "Go", "Goes", "Went", "Going"));

                add(new Question("10. Katie works at the library. She always helps people for looking some books. She is… ", "C", "Postman", "Nurse", "Libraries", "Police woman"));
            }
        };

        loadQuestion();
    }


    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion();
    }


    private void loadQuestion(){
        if(questions.size() > 0) {
            Question q = questions.remove(0);
            lblQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));

            rightAnswer = q.getRightAnswer();
        } else {
            SharedPreferences preferences = getSharedPreferences("RaportPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("scoreEnglish", score);
            editor.apply();

            Intent intent = new Intent(this, ShowScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        if (op == R.id.opcaoA){

            Answer="A";

        }else if (op == R.id.opcaoB){

            Answer="B";

        }else if (op == R.id.opcaoC){

            Answer="C";

        }else if (op == R.id.opcaoD){

            Answer="D";

        }

        radioGroup.clearCheck();

        this.startActivity(isRightOrWrong(Answer));

    }

    private Intent isRightOrWrong(String Answer){
        Intent screen;
        if(Answer.equals(rightAnswer)) {
            this.score += 10;
            screen = new Intent(this, RightActivity.class);

        }else {
            screen = new Intent(this, WrongActivity.class);
        }

        return screen;
    }
}
