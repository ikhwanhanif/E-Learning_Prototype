package ikhwan.hanif.elearningprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TambahSoalJawabSoalActivity extends AppCompatActivity {

    Button jawab, ldBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_soal_jawab_soal);

        jawab = findViewById(R.id.jawabSoal);
        jawab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TambahSoalJawabSoalActivity.this,UserJawabSoalActivity.class));

            }
        });

        ldBoard = findViewById(R.id.leaderBoard);
        ldBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TambahSoalJawabSoalActivity.this, LeaderboardActivity.class));
            }
        });

    }
}