package com.example.simple_game_gtu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        Button btn11 = (Button)findViewById(R.id.start);
        Button btn21 = (Button)findViewById(R.id.help);
        Button btn31 = (Button)findViewById(R.id.exit);

        Intent b11 = new Intent(this,Players.class);
        Intent b21 = new Intent(this,Help.class);

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(b11);
            }
        });


        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(b21);

            }
        });


        btn31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finishAffinity();
                finishAndRemoveTask();
                Toast.makeText(MainActivity.this, "Exit", Toast.LENGTH_SHORT).show();

            }
        });

    }
}