package com.example.examenminimo2dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    String username;

    Handler handler = new Handler();

    public String getUsername(View v){
        EditText usernameContainer;
        usernameContainer = (EditText)findViewById(R.id.editTextTextPersonName);
        return usernameContainer.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    }
    public void openMainActivity(){

        Intent mainactivity = new Intent(this, MainActivity.class);
        mainactivity.putExtra("username", this.username);
        startActivity(mainactivity);
    }

    public void OnClick(View v){
        username = getUsername(v);
        openMainActivity();
    }
}