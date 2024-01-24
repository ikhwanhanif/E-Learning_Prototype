package ikhwan.hanif.elearningprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BuatSoalActivity extends AppCompatActivity {

    Button inggrisBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_soal);

        inggrisBtn = findViewById(R.id.bahasaInggrisBuatSoal);
        inggrisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuatSoalActivity.this, AdminTambahSoalActivity.class));
            }
        });

    }
}