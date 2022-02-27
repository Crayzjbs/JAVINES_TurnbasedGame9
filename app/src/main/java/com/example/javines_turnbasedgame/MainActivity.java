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

    String heroName = "CJ";
    int heroHP = 1300;
    int heroMp = 1000;
    int heroMinDamage = 100;
    int heroMaxDamage = 150;

    //MonsStats

    String monsName = "Cuats";
    int monsterHP = 3000;
    int monsterMP = 400;
    int monsterMinDamage = 50;
    int monsterMaxDamage = 55;

    int turnNumber = 1;

    //status
    boolean disabledstatus = false;
    int statuscounter = 0;
    int buttoncounter = 0;


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
        txtHeroHp.setText(String.valueOf(heroHP));
        txtHeroMP.setText(String.valueOf(heroMp));

        txtMonsHp.setText(monsName);
        txtMonsHp.setText(String.valueOf(monsterHP));
        txtMonsMP.setText(String.valueOf(monsterMP));

        txtLog = findViewById(R.id.txtCombatLog);

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
        skill4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        txtLog.findViewById(R.id.txtCombatLog);

        Random randomizer = new Random();
        int herodps = randomizer.nextInt(heroMaxDamage - heroMinDamage) + heroMaxDamage;
        int monsdps = randomizer.nextInt(monsterMaxDamage - monsterMinDamage) + monsterMinDamage;

        int damageBoost = randomizer.nextInt(5);

        if(damageBoost==1){
            monsterHP = monsterHP -(heroMaxDamage + 50);



        }

        int lifeSteal = randomizer.nextInt(100);
        if (lifeSteal<=30){
            heroHP = heroHP + (herodps - 100);
            turnNumber++;
            txtHeroHp.setText(String.valueOf(heroHP));
            btnNextTurn.setText("Next Turn(" + turnNumber + ")");

        }

        //skill1 button conditions

        if (turnNumber % 2 != 1) {//enemy turn disable the button
            skill1.setEnabled(false);
        }else if (turnNumber % 2 == 1) {
            skill1.setEnabled(true);
        }

        if(buttoncounter>0){
            skill1.setEnabled(false);
            buttoncounter--;
        }
        else if(buttoncounter==0){
            skill1.setEnabled(true);
        }

        //Skill 4 button conditions

        if(turnNumber % 2 != 1){//button disabler if the skill have been used
            skill4.setEnabled(false);
        }
        else if(turnNumber%2 ==1){
            skill4.setEnabled(true);
        }
        if(buttoncounter>0){
            skill4.setEnabled(false);
            buttoncounter--;
        }
        else if(buttoncounter==0){
            skill4.setEnabled(true);
        }


       //skill 4


        switch (v.getId()){
            case R.id.btnSkill4://damage up

                monsterHP = monsterHP - (heroMaxDamage + 110);
                turnNumber++;
                txtMonsHp.setText(String.valueOf(monsterHP));
                btnNextTurn.setText("Next Turn(" + turnNumber + ")");

                txtLog.setText(" Ally " + heroName + " punched "+ monsName + " for " + (heroMaxDamage + 110) + " pure damage ");


                //Condition
                if (monsterHP < 0) {//even
                    txtLog.setText( " Ally " + heroName + " punched "+ monsName + " for " + (heroMaxDamage + 110) + " pure damage " + heroName + " WON!");
                    heroHP = 1300;
                    monsterHP = 3000;
                    turnNumber = 1;
                    btnNextTurn.setText("Play Again");
                }
                buttoncounter=12;
                buttoncounter--;



                break;
        }

        switch (v.getId()) {



            // Skill 1



            case R.id.btnSkill1: //STUN
                monsterHP = monsterHP - 200;
                turnNumber++;
                txtMonsHp.setText(String.valueOf(monsterHP));
                btnNextTurn.setText("Next Turn(" + turnNumber + ")");

                txtLog.setText("Ally " + heroName + " stuned " + monsName + " and dealt " + 200 + " damage to the enemy. Enemy stunned for 3 turns ");

                disabledstatus = true;
                statuscounter = 3;

                //Condition for stun
                if (monsterHP <= 0) {//even
                    txtLog.setText("The ally" + heroName + " dealt " + herodps + " damage to the enemy");
                    heroHP = 1300;
                    monsterHP = 3000;
                    turnNumber = 1;
                    btnNextTurn.setText("Play Again");
                }
                buttoncounter=12;
                buttoncounter--;

                break;
            case R.id.btnNextTurn:
                if (turnNumber % 2 == 1) { //odd
                    monsterHP = monsterHP - 200;
                    turnNumber++;
                    txtMonsHp.setText(String.valueOf(monsterHP));
                    btnNextTurn.setText("Next Turn(" + turnNumber + ")");

                    txtLog.setText(" Ally " + heroName + " striked "+ monsName + " with " + herodps + " pure damage ");

                    //Condition
                    if (monsterHP < 0) {//even
                        txtLog.setText(" The ally " + heroName + " dealt " + herodps + " damage to the enemy. " + heroName + " WON!");
                        heroHP = 1300;
                        monsterHP = 3000;
                        turnNumber = 1;
                        btnNextTurn.setText("Play Again");
                    }
                    if(statuscounter>0){ //stun reduce while enemy is still stunned
                        statuscounter--;
                        if(statuscounter==0){
                            disabledstatus=false;
                        }

                    }
                    buttoncounter--;

                } else if (turnNumber % 2 != 1) {

                    if(disabledstatus==true){
                        txtLog.setText(" Cuats is stunned for " + String.valueOf(statuscounter)+ " turns ");
                        statuscounter--;
                        if(statuscounter==0){
                            disabledstatus=false;
                        }
                    }

                    else{
                        heroHP = heroHP - herodps;
                        turnNumber++;
                        txtHeroHp.setText(String.valueOf(heroHP));
                        btnNextTurn.setText("Next Turn(" + turnNumber + ")");

                        txtLog.setText(" Enemy " + monsName + " striked "+ heroName+ " with " + herodps + " pure damage ");

                        //Condition
                        if (heroHP <= 0) {
                            txtLog.setText(" The enemy " + monsName + " dealt " + monsdps + " damage to the ally." + monsName + " WON!");
                            heroHP = 1300;
                            monsterHP = 3000;
                            turnNumber = 1;
                            btnNextTurn.setText(" Play Again ");
                        }
                    }
                    buttoncounter--;

                }
                break;

        }
    }

}
