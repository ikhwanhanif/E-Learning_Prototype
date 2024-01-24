package ikhwan.hanif.elearningprototype.tutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import ikhwan.hanif.elearningprototype.AdapterCourseListAdapter;
import ikhwan.hanif.elearningprototype.AdminTambahSoalActivity;
import ikhwan.hanif.elearningprototype.BuatSoalActivity;
import ikhwan.hanif.elearningprototype.MainActivity;
import ikhwan.hanif.elearningprototype.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ikhwan.hanif.elearningprototype.CourseListAdapter;
import ikhwan.hanif.elearningprototype.ModelCourse;
import ikhwan.hanif.elearningprototype.user.UserLogin;
import ikhwan.hanif.elearningprototype.user.UserRealDashboard;

public class TutorDashboard extends AppCompatActivity {
    FloatingActionButton addVideoBtn, addSoalBtn;

    private ArrayList<ModelCourse> videoArrayList;
    private AdapterCourseListAdapter adapterVideo;
    private RecyclerView videosRv;

    ImageView keluar;

    String username = "";

    private static TutorDashboard instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_dashboard);

        instance = this;

        addSoalBtn = findViewById(R.id.addSoalBtn);
        addSoalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TutorDashboard.this, BuatSoalActivity.class));
            }
        });

        keluar = findViewById(R.id.keluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Toast.makeText(TutorDashboard.this,"Keluar",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TutorDashboard.this, UserLogin.class));
                finish();

            }
        });

        addVideoBtn = findViewById(R.id.addVideoBtn);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        addVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TutorDashboard.this, AddCourse.class).putExtra("username", username));
            }
        });

        videosRv = findViewById(R.id.courseRv);

        loadCourseList();
    }

    public static TutorDashboard getInstance(){

        return instance;

    }

    public void myMethod(){

        startActivity(new Intent(TutorDashboard.this, UserRealDashboard.class));

    }

    private void loadCourseList() {
        videoArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(AddCourse.COURSES).child(username);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ModelCourse modelVideo = ds.getValue(ModelCourse.class);
                    videoArrayList.add(modelVideo);
                }
                adapterVideo = new AdapterCourseListAdapter(TutorDashboard.this, videoArrayList);
                videosRv.setAdapter(adapterVideo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}