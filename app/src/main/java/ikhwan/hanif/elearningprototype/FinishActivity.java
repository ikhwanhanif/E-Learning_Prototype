package ikhwan.hanif.elearningprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FinishActivity extends AppCompatActivity {

    TextView point;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);



        point = findViewById(R.id.realPoint);

        // Retrieve the total points from the intent
        int totalPoints = getIntent().getIntExtra("TOTAL_POINTS", 0);

        // Set the text of the TextView
        point.setText(String.valueOf(totalPoints));


    }
}