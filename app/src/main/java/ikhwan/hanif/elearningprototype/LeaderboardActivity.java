package ikhwan.hanif.elearningprototype;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    private ListView leaderboardListView;
    private List<UserScore> userScoresList;

    TextView belumAdaNilai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        belumAdaNilai = findViewById(R.id.tidakAdaNilai);

        leaderboardListView = findViewById(R.id.leaderboardListView);
        userScoresList = new ArrayList<>();

        // Mendapatkan referensi ke Firebase Realtime Database untuk skor pengguna
        DatabaseReference userScoresRef = FirebaseDatabase.getInstance().getReference().child("user_scores");

        // Mendapatkan data skor pengguna dari Firebase
        userScoresRef.orderByChild("score").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userScoresList.clear();

                for (DataSnapshot userScoreSnapshot : snapshot.getChildren()) {
                    UserScore userScore = userScoreSnapshot.getValue(UserScore.class);
                    userScoresList.add(userScore);
                }

                if (userScoresList.isEmpty()) {

                    belumAdaNilai.setVisibility(View.VISIBLE);

                } else {
                    tampilkanLeaderboard();
                }

                // Mengurutkan skor pengguna dari yang tertinggi
                Collections.reverse(userScoresList);

                // Menampilkan skor pengguna dalam ListView

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }

    private void tampilkanLeaderboard() {
        ArrayAdapter<UserScore> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userScoresList);
        leaderboardListView.setAdapter(adapter);

    }
}

