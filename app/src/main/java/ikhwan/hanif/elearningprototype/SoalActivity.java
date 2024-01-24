package ikhwan.hanif.elearningprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import ikhwan.hanif.elearningprototype.user.UserRealDashboard;

public class SoalActivity extends AppCompatActivity {

    CardView bahasaInggris, matematika, ipa, robotik;
    ImageView bahasaInggrisIV, matematikaIV, ipaIV, robotikIV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        robotik = findViewById(R.id.robotik);
        robotik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SoalActivity.this, QuestionRobotikActivity.class));

            }
        });
        robotikIV= findViewById(R.id.robotikIV);
        robotikIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SoalActivity.this, QuestionRobotikActivity.class));

            }
        });
        ipa = findViewById(R.id.ipa);
        ipa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SoalActivity.this, QuestionIpaActivity.class));


            }
        });
        ipaIV = findViewById(R.id.ipaIV);
        ipaIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SoalActivity.this, QuestionIpaActivity.class));


            }
        });

        matematika = findViewById(R.id.matematika);
        matematika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SoalActivity.this, QuestionMatematikaActivity.class));


            }
        });
        matematikaIV = findViewById(R.id.matematikaIV);
        matematikaIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SoalActivity.this, QuestionMatematikaActivity.class));


            }
        });


        bahasaInggris = findViewById(R.id.bahasaInggris);
        bahasaInggris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SoalActivity.this, TambahSoalJawabSoalActivity.class));


            }
        });

        bahasaInggrisIV = findViewById(R.id.bahasaInggrisIV);
        bahasaInggrisIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SoalActivity.this, TambahSoalJawabSoalActivity.class));


            }
        });

    }
}