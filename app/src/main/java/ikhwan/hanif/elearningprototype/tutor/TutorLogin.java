package ikhwan.hanif.elearningprototype.tutor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ikhwan.hanif.elearningprototype.R;
import ikhwan.hanif.elearningprototype.user.UserDashboard;
import ikhwan.hanif.elearningprototype.user.UserRealDashboard;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class TutorLogin extends AppCompatActivity {

    EditText et_email, et_password, et_username, et_admin;
    Button btn_login;
    TextView tv_registerBtn;

    ImageView imageHome, imageVideo, imageAdmin;

    TextView tv_forgotPassword;

    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

    Pattern pat = Pattern.compile(emailRegex);

    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_login);

        imageHome = findViewById(R.id.image1);
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(TutorLogin.this, UserRealDashboard.class));
                finish();

            }
        });
        imageVideo = findViewById(R.id.image2);
        imageVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(TutorLogin.this, UserDashboard.class));
                finish();

            }
        });
        imageAdmin = findViewById(R.id.image3);
        imageAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(new Intent(TutorLogin.this, TutorLogin.class)));
                finish();

            }
        });

        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_admin = findViewById(R.id.et_adminCode);
        btn_login = findViewById(R.id.btn_login);
        tv_registerBtn = findViewById(R.id.tv_registerButton);
        tv_forgotPassword = findViewById(R.id.tv_forgotPassword);

        tv_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TutorLogin.this, TutorResetPasswordActivity.class));
            }
        });

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        tv_registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TutorLogin.this, TutorRegister.class));
            }
        });

    }

    private void performLogin() {
        String username = et_username.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String admin = et_admin.getText().toString();

        String adminCode = "admin123";

        if (email.isEmpty()) {
            et_email.setError("Masukkan Email Anda!");
            return;
        } else if (!pat.matcher(email).matches()) {
            et_email.setError("Masukkan Email Yang Benar!");
            return;
        } else if (password.isEmpty()) {
            et_password.setError("Masukkan Password!");
            return;
        } else if (password.length() < 6) {
            et_password.setError("Password Harus Setidaknya 6 Karakter Atau Lebih!");
            return;
        } else {


            if (admin.matches(adminCode)){
                progressDialog.setMessage("Mencoba Masuk....");
                progressDialog.setTitle("Loading...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            sendUserToMainActivity(username);
                            Toast.makeText(TutorLogin.this, "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(TutorLogin.this, "Gagal Masuk", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } else {

                Toast.makeText(TutorLogin.this, "Kode Admin yang anda masukkan salah!", Toast.LENGTH_SHORT).show();

            }


        }
    }

    private void sendUserToMainActivity(String username) {
        Intent intent = new Intent(TutorLogin.this, TutorDashboard.class);
        intent.putExtra("username", username);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}