package com.example.simple_game_gtu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainGame extends AppCompatActivity  implements View.OnClickListener {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,refresh;
    TextView header;

    int PLAYER_0 = 0;
    int PLAYER_x = 1;

    int activePlayer = PLAYER_0;

    int[] fillpos = {-1,-1,-1,-1,-1,-1,-1,-1,-1,1};

    boolean isGameActive = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);







        header = findViewById(R.id.header);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        refresh = findViewById(R.id.ref);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);

        Intent ginet = getIntent();
        String play1 = ginet.getStringExtra("key1");
        String play2 = ginet.getStringExtra("key2");
        header.setText("0 : "+play1);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                restartGame();
            }
        });



    }

    //---------------------------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gamemenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int name = item.getItemId();
        if(name==R.id.exitmenu){
            MainGame.this.finishAffinity();
            finishAndRemoveTask();
            //Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
        }
        else if(name==R.id.ref){

            restartGame();
        }
        else if(name==R.id.newgame){

            Intent iback = new Intent(this,Players.class);
            startActivity(iback);
            finish();

        }


        return super.onOptionsItemSelected(item);
    }


    //-------------------------------------------------------------------




    @Override
    public void onClick(View view) {

        Intent ginet = getIntent();
        String play1 = ginet.getStringExtra("key1");
        String play2 = ginet.getStringExtra("key2");



        if(!isGameActive)
            return;

        // Game Logic

        Button clickedBtn = findViewById(view.getId());
        int clickedTag = Integer.parseInt(view.getTag().toString());

        // not repeat on same button

        if(fillpos[clickedTag] != -1) {
            return;
        }

        fillpos[clickedTag] = activePlayer;


        // Set Text On Button As per Round
        if(activePlayer == PLAYER_0){
            clickedBtn.setText("0");
            activePlayer = PLAYER_x;
            header.setText("X : "+play2);

        }
        else{
            clickedBtn.setText("X");

            activePlayer = PLAYER_0;
            header.setText("0 : "+play1);
        }

        checkWin();

    }

    private void checkWin(){



        // check for who is the winner
        int[][] winpos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        int i = 0;
        for (i =0 ; i<8 ; i++){

            int val0 = winpos[i][0];
            int val1 = winpos[i][1];
            int val2 = winpos[i][2];

            if(fillpos[val0] == fillpos[val1] && fillpos[val1] == fillpos[val2]){
                // Winner Declare

                if (fillpos[val0]!=-1){

                    isGameActive = false;

                    if(fillpos[val0] == PLAYER_0) {
                        showBox("0 IS WINNER");
                    }
                    else{
                        showBox("X IS WINNER");
                    }
                }

            }

        }


    }

    private void showBox(String winerText){

        new AlertDialog.Builder(this).setTitle(winerText).setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                restartGame();
            }
        }).show();


    }

    private void restartGame(){

        activePlayer = PLAYER_0;
        fillpos = new int[] {-1,-1,-1,-1,-1,-1,-1,-1,-1};
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        isGameActive = true;
        Intent ginet = getIntent();
        String play1 = ginet.getStringExtra("key1");
        String play2 = ginet.getStringExtra("key2");
        header.setText("0 : "+play1);


    }


}