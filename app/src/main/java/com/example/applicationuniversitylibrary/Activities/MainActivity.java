package com.example.applicationuniversitylibrary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationuniversitylibrary.R;
import com.example.applicationuniversitylibrary.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    EditText em, pw;
    Button btnLogin;
    TextView tvregister;
    FirebaseAuth fAuth;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth = FirebaseAuth.getInstance();
        em = findViewById(R.id.et_id_login);
        pw = findViewById(R.id.et_pw_login);
        btnLogin = findViewById(R.id.btn_login_main);
        tvregister = findViewById(R.id.tv_register_here);



        ref = FirebaseDatabase.getInstance().getReference("user"); // initialize FireBase Database

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = em.getText().toString().trim();
                String password = pw.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    em.setError("Email is Required");
                    return;
                    //id field is empty
                }

                if (TextUtils.isEmpty(password)){
                    pw.setError("Password is required");
                    return;
                    //password field is empty

                }




            //FireBase Auth to sign in the user
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(MainActivity.this,"Logged in successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeNav.class));
                        }
                        else{

                            Toast.makeText(MainActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });



    }
}
