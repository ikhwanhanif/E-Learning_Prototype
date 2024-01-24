package ikhwan.hanif.elearningprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionMatematikaActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_question_matematika);

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
                add(new Question("1. Angka yang tidak memiliki angkanya sendiri?", "A", "Nol", "Satu", "Dua", "Tiga"));

                add(new Question("2. 53 dibagi empat sama dengan berapa?", "D", "10", "15","11", "13"));

                add(new Question("3. Siapa yang menemukan tanda sama dengan '='?", "B", "Alfin", "Robert Rekam","Al-Khwarizmi", "Abu Ja'far"));

                add(new Question("4. Pada tahun berapa angka Nol(0) ditemukan?", "C", "342 M", "453 M", "458 M", "214 M"));

                add(new Question("5. Berapa Nilai Pi?", "D", "3.15", "3.24", "3.23", "3.14"));

                add(new Question("6. 334 × 7 + 335 sama dengan berapa?", "B", "1653", "2673", "2774", "1672"));

                add(new Question("7. 1203 + 806 + 409 sama dengan berapa?", "C", "2514", "3428", "2418", "3248"));

                add(new Question("8. 35 dibaca…", "A", "Tiga puluh lima", "Tiga lima", "ga ma", "Tiega poloeh liema"));

                add(new Question("9. Berikut ini yang termasuk bilangan genap adalah…", "D", "421", "203", "431", "378"));

                add(new Question("10. Seratus tiga belas ditulis menjadi…", "C", "313", "131", "113", "311"));
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
            editor.putInt("scoreMath", score);
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