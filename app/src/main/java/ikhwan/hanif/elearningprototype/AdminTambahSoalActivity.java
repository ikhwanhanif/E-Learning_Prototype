package ikhwan.hanif.elearningprototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminTambahSoalActivity extends AppCompatActivity {

    private EditText pertanyaanEditText, jawabanAEditText, jawabanBEditText, jawabanCEditText, jawabanDEditText;
    private RadioGroup radioGroup;
    private Button tambahSoalBtn, resetLdBoard, resetSoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tambah_soal);

        // Inisialisasi elemen UI
        pertanyaanEditText = findViewById(R.id.pertanyaanEditText);
        jawabanAEditText = findViewById(R.id.jawabanAEditText);
        jawabanBEditText = findViewById(R.id.jawabanBEditText);
        jawabanCEditText = findViewById(R.id.jawabanCEditText);
        jawabanDEditText = findViewById(R.id.jawabanDEditText);
        radioGroup = findViewById(R.id.radioGroup);
        tambahSoalBtn = findViewById(R.id.tambahSoalBtn);
        resetLdBoard = findViewById(R.id.resetLeaderboard);
        resetSoal = findViewById(R.id.resetSoal);

        resetSoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAllSoal();
            }
        });

        resetLdBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetLeaderboard();
            }
        });

        // Menangani klik pada tombol tambah soal
        tambahSoalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahSoalKeFirebase();
            }
        });
    }

    private void resetAllSoal() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference soalRef = database.getReference("soal");

        // Hapus semua entri pada leaderboard
        soalRef.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError == null) {
                    // Jika leaderboard berhasil direset
                    Toast.makeText(AdminTambahSoalActivity.this, "Soal berhasil direset", Toast.LENGTH_SHORT).show();
                } else {
                    // Jika terjadi kesalahan
                    Toast.makeText(AdminTambahSoalActivity.this, "Gagal mereset Soal: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void resetLeaderboard() {
        // Inisialisasi Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference leaderboardRef = database.getReference("user_scores");

        // Hapus semua entri pada leaderboard
        leaderboardRef.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError == null) {
                    // Jika leaderboard berhasil direset
                    Toast.makeText(AdminTambahSoalActivity.this, "Leaderboard berhasil direset", Toast.LENGTH_SHORT).show();
                } else {
                    // Jika terjadi kesalahan
                    Toast.makeText(AdminTambahSoalActivity.this, "Gagal mereset leaderboard: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void tambahSoalKeFirebase() {
        // Ambil nilai dari elemen UI
        String pertanyaan = pertanyaanEditText.getText().toString();
        String jawabanA = jawabanAEditText.getText().toString();
        String jawabanB = jawabanBEditText.getText().toString();
        String jawabanC = jawabanCEditText.getText().toString();
        String jawabanD = jawabanDEditText.getText().toString();

        // Ambil ID jawaban yang dipilih
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

        if (selectedRadioButton == null) {
            // Jika tidak ada jawaban yang dipilih
            Toast.makeText(this, "Pilih jawaban benar", Toast.LENGTH_SHORT).show();
            return;
        }

        String jawabanBenar = selectedRadioButton.getText().toString();

        // Inisialisasi Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("soal");

        // Buat objek Soal dan simpan ke Firebase Database
        String idSoal = databaseReference.push().getKey();
        Soal soal = new Soal(idSoal, pertanyaan, jawabanA, jawabanB, jawabanC, jawabanD, jawabanBenar);
        databaseReference.child(idSoal).setValue(soal, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError == null) {
                    // Jika data berhasil ditambahkan
                    Toast.makeText(AdminTambahSoalActivity.this, "Soal berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    resetForm();
                } else {
                    // Jika terjadi kesalahan
                    Toast.makeText(AdminTambahSoalActivity.this, "Gagal menambahkan soal: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetForm() {
        // Mengosongkan elemen UI setelah menambahkan soal
        pertanyaanEditText.setText("");
        jawabanAEditText.setText("");
        jawabanBEditText.setText("");
        jawabanCEditText.setText("");
        jawabanDEditText.setText("");
        radioGroup.clearCheck();
    }
}
