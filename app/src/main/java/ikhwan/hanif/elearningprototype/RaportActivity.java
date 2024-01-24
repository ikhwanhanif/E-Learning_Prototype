package ikhwan.hanif.elearningprototype;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RaportActivity extends AppCompatActivity {
    TextView txtScoreEnglish, txtScoreMath, txtScoreIpa, txtScoreRobotik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raport);

        txtScoreEnglish = findViewById(R.id.txtScoreEnglish);
        txtScoreMath = findViewById(R.id.txtScoreMath);
        txtScoreIpa = findViewById(R.id.txtScoreIpa);
        txtScoreRobotik = findViewById(R.id.txtScoreRobotik);

        // Get scores from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("RaportPrefs", MODE_PRIVATE);
        int scoreEnglish = preferences.getInt("scoreEnglish", 0);
        int scoreMath = preferences.getInt("scoreMath", 0);
        int scoreIpa = preferences.getInt("scoreIpa", 0);
        int scoreRobotik = preferences.getInt("scoreRobotik", 0);

        // Display scores in TextViews
        txtScoreEnglish.setText(String.valueOf(scoreEnglish));
        txtScoreMath.setText(String.valueOf(scoreMath));
        txtScoreIpa.setText(String.valueOf(scoreIpa));
        txtScoreRobotik.setText(String.valueOf(scoreRobotik));
    }
}