package ikhwan.hanif.elearningprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ikhwan.hanif.elearningprototype.user.UserDashboard;
import ikhwan.hanif.elearningprototype.user.UserLogin;
import ikhwan.hanif.elearningprototype.user.UserRealDashboard;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user == null){

                    startActivity(new Intent(SplashActivity.this, UserLogin.class));

                }else {

                    startActivity(new Intent(SplashActivity.this, UserRealDashboard.class));

                }
                finish();

            }
        },3000);

    }
}