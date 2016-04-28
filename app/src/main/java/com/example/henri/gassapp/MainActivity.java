package com.example.henri.gassapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText emailTxt = (EditText)findViewById(R.id.textEmail);
        EditText passwordTxt = (EditText)findViewById(R.id.textPassword);

        Button loginBtn = (Button)findViewById(R.id.loginBtn);

        final String email = String.valueOf(emailTxt.getText());
        final String password = String.valueOf(passwordTxt.getText());

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(logar(email, password)){
                    goToGetInformations();
                }
            }
        });

    }


    public boolean logar(String email, String password){

        if(true){
            return true;
        }
        return false;
    }

    public void goToGetInformations(){
        Intent intent = new Intent(this, GetInformations.class);
        startActivity(intent);

    }



}
