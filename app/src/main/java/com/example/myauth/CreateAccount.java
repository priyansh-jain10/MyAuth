package com.example.myauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccount extends AppCompatActivity {
    private EditText emailText;
    private EditText passwordtext;
    private EditText confirmpasswordtext;
    private Button signUpButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        emailText=findViewById(R.id.signup_emailEditText);
        passwordtext=findViewById(R.id.signup_passwordedittext);
        confirmpasswordtext=findViewById(R.id.signup_passwordedittextconfirm);
        signUpButton=findViewById(R.id.signUpButton);
        mAuth=FirebaseAuth.getInstance();
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=String.valueOf(emailText.getText());
                String password=String.valueOf(passwordtext.getText());
                String confirmpassword=String.valueOf(confirmpasswordtext.getText());
                if(!email.isEmpty()&&!password.isEmpty()){
                    if(password.equals(confirmpassword)){
                        Log.d("create account","initiatesignup");
                        signUpUser(email,password);
                    }
                }
                else{
                    Toast.makeText(CreateAccount.this,"Please enter correct credentials",Toast.LENGTH_LONG);
                    Log.d("create account","signup failed");
                }
            }
        });

    }

    private void signUpUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("createUserWithEmail", "createUserWithEmail:success");
                            Toast.makeText(CreateAccount.this,"Account Created",Toast.LENGTH_LONG);
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(CreateAccount.this,homescreen.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("createUserWithEmail", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CreateAccount.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}