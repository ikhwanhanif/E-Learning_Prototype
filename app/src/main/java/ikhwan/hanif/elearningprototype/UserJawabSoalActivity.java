package ikhwan.hanif.elearningprototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserJawabSoalActivity extends AppCompatActivity {

    private TextView pertanyaanTextView, belumAdaSoal;
    private RadioGroup jawabanRadioGroup;
    private Button jawabBtn;

    private DatabaseReference databaseReference;
    private List<Soal> daftarSoal;
    private int indexSoal = 0;
    private int totalPoint = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_jawab_soal);

        pertanyaanTextView = findViewById(R.id.pertanyaanTextView);
        jawabanRadioGroup = findViewById(R.id.jawabanRadioGroup);
        jawabBtn = findViewById(R.id.jawabBtn);
        belumAdaSoal = findViewById(R.id.belumAdaSoal);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("soal");

        daftarSoal = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                daftarSoal.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Soal soal = dataSnapshot.getValue(Soal.class);
                    daftarSoal.add(soal);
                }
                if (daftarSoal.size() > 0) {
                    tampilkanSoal();
                } else {
                    belumAdaSoal.setVisibility(View.VISIBLE);
                    pertanyaanTextView.setVisibility(View.GONE);
                    jawabanRadioGroup.setVisibility(View.GONE);
                    jawabBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserJawabSoalActivity.this, "Failed to retrieve question data.", Toast.LENGTH_SHORT).show();
            }
        });

        jawabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = jawabanRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);

                if (selectedRadioButton != null) {
                    String jawabanUser = selectedRadioButton.getText().toString().toLowerCase().substring(0, 1);
                    String jawabanBenar = daftarSoal.get(indexSoal).getJawabanBenar().toLowerCase();

                    if (jawabanUser.equalsIgnoreCase(jawabanBenar)) {
                        totalPoint += 10;
                        Toast.makeText(UserJawabSoalActivity.this, "Correct answer! You get 10 points.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UserJawabSoalActivity.this, "Your Answer is Incorrect.", Toast.LENGTH_SHORT).show();
                    }

                    indexSoal++;
                    if (indexSoal < daftarSoal.size()) {
                        tampilkanSoal();
                    } else {
                        simpanSkorKeFirebase();
                        Intent intent = new Intent(UserJawabSoalActivity.this, FinishActivity.class);
                        intent.putExtra("TOTAL_POINTS", totalPoint);
                        startActivity(intent);
                        finish();

                    }
                } else {
                    Toast.makeText(UserJawabSoalActivity.this, "Choose an answer first.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void tampilkanSoal() {
        Soal soal = daftarSoal.get(indexSoal);
        pertanyaanTextView.setText(soal.getPertanyaan());

        RadioButton jawabanARadioButton = findViewById(R.id.jawabanARadioButton);
        RadioButton jawabanBRadioButton = findViewById(R.id.jawabanBRadioButton);
        RadioButton jawabanCRadioButton = findViewById(R.id.jawabanCRadioButton);
        RadioButton jawabanDRadioButton = findViewById(R.id.jawabanDRadioButton);

        jawabanARadioButton.setText("A. " + soal.getJawabanA());
        jawabanBRadioButton.setText("B. " + soal.getJawabanB());
        jawabanCRadioButton.setText("C. " + soal.getJawabanC());
        jawabanDRadioButton.setText("D. " + soal.getJawabanD());

        jawabanRadioGroup.clearCheck();
    }

    private void simpanSkorKeFirebase() {
        DatabaseReference userScoresRef = FirebaseDatabase.getInstance().getReference().child("user_scores");

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String username = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        UserScore userScore = new UserScore(userId, username, totalPoint);

        userScoresRef.child(userId).setValue(userScore)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UserJawabSoalActivity.this, "Point successfully saved to leaderboard", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UserJawabSoalActivity.this, "Failed to save point to leaderboard", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
