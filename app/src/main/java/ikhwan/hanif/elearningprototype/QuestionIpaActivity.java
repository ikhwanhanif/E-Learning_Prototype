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

public class QuestionIpaActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_question_ipa);

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
                add(new Question("1. Kemampuan kelelawar dapat mengetahui pada lingkungan sekitarnya dengan menggunakan system sonar. System tersebut bisa dikenal dengan istilah apakah kemampuan yang dimiliki oleh kelelawar = ...", "A", "Ekolokasi", "Mimikri","Adaptasi", "Habitat"));

                add(new Question("2. Udara yang telah masuk ke rongga hidung dan dapat diteruskan ke batang tenggorokan. Pada batang tenggorokan telah tersusun atas tulang-tulang rawan yang kemudian bercabang dua. Cabang batang tenggorokan tersebut bisa juga disebut = …", "D", "Pleura", "Pupil","Trakea", "Bronkus"));

                add(new Question("3. Ada sebuah kuda dan jerapah merupakan contoh hewan pemakan tumbuhan. Kuda dan jerapah adalah pemakan dedaunan. Hewan pemakan tumbuhan dapat dinamakan = …", "C", "Klorofil", "Karnivora","Herbivora", "Omnivora"));

                add(new Question("4. Jika suatu bunyi merambat di ruang hampa udara, maka yang akan terjadi adalah = ...", "B", "Bunyi itu akan langsung terdengar", "Bunyi tersebut tidak bisa terdengar lantaran tidak ada medium perambatan", "Bunyi itu dapat terdengar pada radius di bawah 1 meter", "Bunyi tersebut akan terdengar, tetapi membutuhkan waktu tertentu untuk merambat"));

                add(new Question("5. Lensa mata adalah bagian mata yang berfungsi = ...", "A", "Mengatur daya akomodasi mata", "Melindungi mata dari keringat", "Membentuk bayangan benda", "Mengatur banyaknya cahaya yang masuk ke mata"));

                add(new Question("6. Hewan dan tumbuhan yang dilindungi berada di tempat-tempat khusus berikut, kecuali = ...", "D", "Cagar alam", "Suaka margasatwa", "Taman nasional", "Hutan bakau"));

                add(new Question("7. Pada orang bergolongan darah AB, tidak ditemukan = ...", "C", "Aglutinogen pada eritrositnya", "Aglutinin pada eritrositnya", "Aglutinin pada plasmanya", "Aglutinogen pada plasmanya"));

                add(new Question("8. Sumber daya alam yang tidak dapat diperbaharui adalah = …", "C", "Tanah", "Air", "Batu bara", "Hutan"));

                add(new Question("9. Organ tubuh yang dapat melindungi tubuh dari keracunan adalah = ...", "B", "Jantung", "Hati", "Paru-paru", "Usus"));

                add(new Question("10. Kamera menggunakan alat optik, yaitu lensa. Jenis lensa yang digunakan adalah lensa = ...", "A", "Cembung", "Cekung", "Ceking", "Kembung"));
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
            editor.putInt("scoreIpa", score);
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