package com.example.javines_turnbasedgame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtHeroName,txtMonsName,txtHeroHp,txtMonsHp,txtHeroMP,txtMonsMP;
    Button btnNextTurn;

    //HeroStats

    String heroName = "Ches Cuares";
    int heroHP = 1500;
    int heroHp = 1000;
    int heroMinDamage = 100;
    int heroMaxDamage = 150;

    //MonsStats

    String monsName = "Adrian Cuaton";
    int monsterHP = 3000;
    int monsterMP = 400;
    int monsterMinDamage = 40;
    int monsterMaxDamage = 55;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtHeroName = findViewById(R.id.txtHeroName);
        txtMonsName = findViewById(R.id.txtMonsName);
        txtHeroHp = findViewById(R.id.txtHeroHP);
        txtMonsHp = findViewById(R.id.txtMonsHP);
        txtHeroMP = findViewById(R.id.txtHeroMP);
        txtMonsMP = findViewById(R.id.txtMonsMP);
        btnNextTurn = findViewById(R.id.btnNextTurn);

        txtHeroHp.setText(heroName);
        txtHeroHp.setText(String.valueOf(heroHp));
        txtHeroMP.setText(String.valueOf(heroHP));

        txtMonsHp.setText(monsName);
        txtMonsHp.setText(String.valueOf(monsterHP));
        txtMonsMP.setText(String.valueOf(monsterMP));


        //button onClick Listener
        btnNextTurn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int turnNumber= 1;
        Random randomizer = new Random();
        int herodps = randomizer.nextInt((heroMaxDamage - heroMinDamage) + heroMaxDamage);
        int monsdps = randomizer.nextInt((monsterMaxDamage - monsterMinDamage) + monsterMaxDamage);

        switch(v.getId()) {
            case R.id.btnNextTurn:

                if(turnNumber % 2 ==1){
                    monsterHP = monsterHP - herodps;
                    turnNumber++;
                    txtMonsHp.setText(String.valueOf(monsterHP));
                }
                else if(turnNumber%2 !=1){
                    heroHP = heroHP - herodps;
                    turnNumber++;
                    txtHeroHp.setText(String.valueOf(heroHP));
                }
                break;
        }


    }
}
