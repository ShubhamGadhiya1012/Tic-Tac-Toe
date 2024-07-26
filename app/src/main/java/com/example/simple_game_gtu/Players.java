package com.example.simple_game_gtu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Players extends AppCompatActivity  {

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        //getSupportActionBar().hide();


        EditText et1 = (EditText)findViewById(R.id.player1);
        EditText et2 = (EditText)findViewById(R.id.player2);



        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name1 = et1.getText().toString();
                String name2 = et2.getText().toString();
                Intent inext = new Intent(getApplicationContext(),MainGame.class);

                if(!name1.equals("") && !name2.equals("")) {

                    inext.putExtra("key1", name1);
                    inext.putExtra("key2", name2);
                    startActivity(inext);
                    finish();
                }
                else{

                    Toast.makeText(Players.this, "Enter The Player Name", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}