package com.example.myauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currUser=mAuth.getCurrentUser();
        if(currUser!=null){
            gotoHomePage();
        }
        else{
            gotoAuthentication();
        }
    }

    public void gotoAuthentication(){
        Intent intent=new Intent(MainActivity.this, SignInEmailandPassword.class);
        startActivity(intent);
    }
    public void gotoHomePage(){
        Intent intent=new Intent(MainActivity.this,homescreen.class);
    }
}