package ikhwan.hanif.elearningprototype.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ikhwan.hanif.elearningprototype.R;
import ikhwan.hanif.elearningprototype.RaportActivity;
import ikhwan.hanif.elearningprototype.SoalActivity;
import ikhwan.hanif.elearningprototype.TambahSoalJawabSoalActivity;
import ikhwan.hanif.elearningprototype.tutor.TutorLogin;

public class UserRealDashboard extends AppCompatActivity {

    CardView cv_web, cv_frontend, cv_backend, cv_database, cv_android, cv_machineLearning;
    String[] courses = {"B.Inggris", "Matematika", "IPA", "Robotik"};

    ImageView imageHome, imageVideo, imageAdmin, keluar;

    Button soalBtn,soal;
    private TextView emailText;

    public static final String CATEGORY = "Kelas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_real_dashboard);

        emailText = findViewById(R.id.emailText);

        soal = findViewById(R.id.soal);
        soal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserRealDashboard.this, SoalActivity.class));
            }
        });


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();

        if (mUser != null) {
            String username = mUser.getDisplayName();
            if (username != null && !username.isEmpty()) {
                emailText.setText(getGreetings() + " " + username);
            }
        }



        keluar = findViewById(R.id.keluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Toast.makeText(UserRealDashboard.this,"Keluar",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserRealDashboard.this, UserLogin.class));
                finish();

            }
        });


        imageHome = findViewById(R.id.image1);
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(UserRealDashboard.this, UserRealDashboard.class));
                finish();

            }
        });
        imageVideo = findViewById(R.id.image2);
        imageVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(UserRealDashboard.this, UserDashboard.class));
                finish();

            }
        });
        imageAdmin = findViewById(R.id.image3);
        imageAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(new Intent(UserRealDashboard.this, TutorLogin.class)));
                finish();

            }
        });


        cv_web = findViewById(R.id.cv_web);
        cv_frontend = findViewById(R.id.cv_frontend);
        cv_backend = findViewById(R.id.cv_backend);
        cv_database = findViewById(R.id.cv_database);
        cv_android = findViewById(R.id.cv_android);
        cv_machineLearning = findViewById(R.id.cv_machineLearning);

        cv_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserRealDashboard.this, UserDashboard.class).putExtra(CATEGORY, courses[0]));
            }
        });
        cv_frontend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserRealDashboard.this, UserDashboard.class).putExtra(CATEGORY, courses[1]));
            }
        });
        cv_backend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserRealDashboard.this, UserDashboard.class).putExtra(CATEGORY, courses[2]));
            }
        });
        cv_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserRealDashboard.this, UserDashboard.class).putExtra(CATEGORY, courses[3]));
            }
        });
        cv_android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserRealDashboard.this, UserDashboard.class).putExtra(CATEGORY, courses[4]));
            }
        });
        cv_machineLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserRealDashboard.this, UserDashboard.class).putExtra(CATEGORY, courses[5]));
            }
        });

    }

    private String getGreetings() {
        // Dapatkan waktu saat ini
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // Tentukan salam berdasarkan waktu hari
        if (hour >= 0 && hour < 12) {
            return "Selamat Pagi";
        }else if (hour >= 12 && hour < 16) {
            return "Selamat Siang";
        } else if (hour >= 16 && hour < 20) {
            return "Selamat Sore";
        }
        else {
            return "Selamat Malam";
        }
    }
}