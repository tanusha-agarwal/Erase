package com.example.erase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class SignUp extends AppCompatActivity {

    EditText edt1,edt2;
    Button btn;
    TextView txt;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth=FirebaseAuth.getInstance();
        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        btn=findViewById(R.id.button);
        txt=findViewById(R.id.txt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt1.getText().toString();
                String password = edt2.getText().toString();
                if(email.isEmpty()&&password.isEmpty())
                {
                    Toast.makeText(SignUp.this,"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (email.isEmpty()) {
                        edt1.setError("Please enter E-mail Address");
                        edt1.requestFocus();
                    } else if (password.isEmpty()) {
                        edt2.setError("Please enter your password");
                        edt2.requestFocus();
                    }
                    else if(password.length()!=10)
                    {
                        edt2.setError("Please enter password of length 10");
                        edt2.requestFocus();
                    }
                }
                if(!(email.isEmpty()&&password.isEmpty()))
                {
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(SignUp.this,Main.class));


                            } else {
                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(SignUp.this, errorMessage, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(SignUp.this, "Sign Up Unsuccessful, Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this,Login.class));
            }
        });
    }

}
