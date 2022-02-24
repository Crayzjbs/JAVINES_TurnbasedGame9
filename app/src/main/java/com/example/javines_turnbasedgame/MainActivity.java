package com.example.javines_turnbasedgame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Global variable
    TextView txtHeroName, txtMonsName, txtHeroHp, txtMonsHp, txtHeroMP, txtMonsMP, txtHeroDPS, txtMonsDPS, txtLog;
    Button btnNextTurn;
    ImageButton skill1, skill2, skill3, skill4;

    //HeroStats

    String heroName = "ChesCuares";
    int heroHP = 1500;
    int heroMp = 1000;
    int heroMinDamage = 100;
    int heroMaxDamage = 150;

    //MonsStats

    String monsName = "AdrianCuaton";
    int monsterHP = 3000;
    int monsterMP = 400;
    int monsterMinDamage = 40;
    int monsterMaxDamage = 55;

    int turnNumber = 1;

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
        txtHeroDPS = findViewById(R.id.txtHeroDPS);
        txtMonsDPS = findViewById(R.id.txtMonsDPS);

        txtHeroHp.setText(heroName);
        txtHeroHp.setText(String.valueOf(heroMp));
        txtHeroMP.setText(String.valueOf(heroHP));

        txtMonsHp.setText(monsName);
        txtMonsHp.setText(String.valueOf(monsterHP));
        txtMonsMP.setText(String.valueOf(monsterMP));

        txtLog.findViewById(R.id.txtCombatLog);

        //Damage Display
        txtHeroDPS.setText(String.valueOf(heroMinDamage) + " ~ " + String.valueOf(heroMaxDamage));
        txtMonsDPS.setText(String.valueOf(monsterMinDamage) + " ~ " + String.valueOf(monsterMaxDamage));

        skill1 = findViewById(R.id.btnSkill1);
        skill2 = findViewById(R.id.btnSkill2);
        skill3 = findViewById(R.id.btnSkill3);
        skill4 = findViewById(R.id.btnSkill4);

        //button onClick Listener
        btnNextTurn.setOnClickListener(this);
        skill1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        txtLog.findViewById(R.id.txtCombatLog);

        Random randomizer = new Random();
        int herodps = randomizer.nextInt(heroMaxDamage - heroMinDamage) + heroMaxDamage;
        int monsdps = randomizer.nextInt(monsterMaxDamage - monsterMinDamage) + monsterMinDamage;

        //skill1 button condition
        if (turnNumber % 2 != 1) {//enemy turn disable the button
            skill1.setEnabled(false);
        }else if (turnNumber % 2 == 1) {
            skill1.setEnabled(true);
        }
        switch (v.getId()) {
            case R.id.btnSkill1:
                monsterHP = monsterHP - herodps;
                turnNumber++;
                txtMonsHp.setText(String.valueOf(monsterHP));
                btnNextTurn.setText("Next Turn(" + turnNumber + ")");

                txtLog.setText("Ally" + heroName + "dealt" + herodps + " damage to the enemy");

                //Condition
                if (monsterHP < 0) {//even
                    txtLog.setText("The ally" + heroName + " dealt " + herodps + " damage to the enemy." + heroName + "WON!");
                    int heroHP = 1500;
                    int monsterHP = 3000;
                    int turnNumber = 1;
                    btnNextTurn.setText("Play Again");
                }
                break;
            case R.id.btnNextTurn:
                if (turnNumber % 2 == 1) { //odd
                    monsterHP = monsterHP - 200;
                    turnNumber++;
                    txtMonsHp.setText(String.valueOf(monsterHP));
                    btnNextTurn.setText("Next Turn(" + turnNumber + ")");

                    txtLog.setText("Ally" + heroName + "dealt" + herodps + " damage to the enemy");

                    //Condition
                    if (monsterHP < 0) {//even
                        txtLog.setText("The ally" + heroName + " dealt " + herodps + " damage to the enemy." + heroName + "WON!");
                        int heroHP = 1500;
                        int monsterHP = 3000;
                        int turnNumber = 1;
                        btnNextTurn.setText("Play Again");
                    }


                } else if (turnNumber % 2 != 1) {
                    heroHP = heroHP - herodps;
                    turnNumber++;
                    txtHeroHp.setText(String.valueOf(heroHP));
                    btnNextTurn.setText("Next Turn(" + turnNumber + ")");

                    txtLog.setText("Enemy" + monsName + "dealt" + herodps + " damage to    the enemy");

                    //Condition
                    if (monsterHP < 0) {
                        txtLog.setText("The enemy" + monsName + " dealt " + monsdps + " damage to the ally." + monsName + "WON!");
                        int heroHP = 1500;
                        int monsterHP = 3000;
                        int turnNumber = 1;
                        btnNextTurn.setText("Play Again");
                    }
                }
                break;

        }
    }

}
