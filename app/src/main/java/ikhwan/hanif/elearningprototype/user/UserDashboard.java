package ikhwan.hanif.elearningprototype.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import ikhwan.hanif.elearningprototype.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ikhwan.hanif.elearningprototype.CourseListAdapter;
import ikhwan.hanif.elearningprototype.ModelCourse;
import ikhwan.hanif.elearningprototype.tutor.AddCourse;
import ikhwan.hanif.elearningprototype.tutor.TutorLogin;

public class UserDashboard extends AppCompatActivity {

    private ArrayList<ModelCourse> videoArrayList;
    private CourseListAdapter adapterVideo;
    private RecyclerView videosRv;

    ImageView imageHome, imageVideo, imageAdmin;

    String category = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);


        imageHome = findViewById(R.id.image1);
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(UserDashboard.this, UserRealDashboard.class));
                finish();

            }
        });
        imageVideo = findViewById(R.id.image2);
        imageVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(UserDashboard.this, UserDashboard.class));
                finish();

            }
        });
        imageAdmin = findViewById(R.id.image3);
        imageAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(new Intent(UserDashboard.this, TutorLogin.class)));
                finish();

            }
        });


        videosRv = findViewById(R.id.courseRv);

        Intent intent = getIntent();
        category = intent.getStringExtra(UserRealDashboard.CATEGORY);

        loadCourseList();

    }

    private void loadCourseList() {
        videoArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(AddCourse.COURSES);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot : ds.getChildren()) {
                        ModelCourse modelVideo = dataSnapshot.getValue(ModelCourse.class);
                        if (modelVideo.getCategory().equals(category)) {
                            videoArrayList.add(modelVideo);
                        }
                    }
                }
                adapterVideo = new CourseListAdapter(UserDashboard.this, videoArrayList);
                videosRv.setAdapter(adapterVideo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}