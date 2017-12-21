package com.firetot.quizapptutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class EasyActivity extends AppCompatActivity {

    DataHelper dataHelper;
    TextView questionsText, pointsText, name_ingame, nr_skip;
    ImageButton trueQ, falseQ, go_home;

    RelativeLayout skip;
    int skipp;

    Random r = new Random();
    int n;
    int points =0;
    int SKIP_NUMBER = 8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        dataHelper = new DataHelper(this);

        nr_skip = (TextView) findViewById(R.id.nr_skip);

        questionsText = (TextView) findViewById(R.id.questions);
        pointsText = (TextView) findViewById(R.id.points);
        name_ingame = (TextView) findViewById(R.id.name_ingame);
        trueQ = (ImageButton) findViewById(R.id.trueQ);
        falseQ = (ImageButton) findViewById(R.id.falseQ);
        go_home = (ImageButton) findViewById(R.id.go_home);

        skip = (RelativeLayout) findViewById(R.id.skip);

        nr_skip.setText(""+dataHelper.receiveDataInt("SKIPP", SKIP_NUMBER));

        name_ingame.setText(dataHelper.receiveDataString("NAME", "User"));

        //Add to array
        final String[] arrayQ = {getString(R.string.e1), getString(R.string.e2), getString(R.string.e3), getString(R.string.e4), getString(R.string.e5),
                getString(R.string.e6), getString(R.string.e7), getString(R.string.e8), getString(R.string.e9), getString(R.string.e10)};
        final Boolean[] arrayA = {true, false, false, true, false, true, false, true, true, true};

        final ArrayList<String> questions = new ArrayList<String>(Arrays.asList(arrayQ));
        final ArrayList<Boolean> answers = new ArrayList<Boolean>(Arrays.asList(arrayA));

        go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EasyActivity.this, MainActivity.class));
                finish();
            }
        });

        n = r.nextInt(questions.size());
        questionsText.setText(questions.get(n)); //take random question

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nr_skip.setText(""+dataHelper.receiveDataInt("SKIPP",SKIP_NUMBER));
                skipp = dataHelper.receiveDataInt("SKIPP", SKIP_NUMBER);
                if (dataHelper.receiveDataInt("SKIPP", SKIP_NUMBER) == 0){
                    Toast.makeText(EasyActivity.this, "You have 0 skip", Toast.LENGTH_SHORT).show();
                }else{
                    skipp--;
                    questions.remove(n);
                    answers.remove(n);
                    if (questions.size()==0){
                        result();
                    }else {
                        n = r.nextInt(questions.size());
                        questionsText.setText(questions.get(n));
                        dataHelper.saveDataInt("SKIPP", skipp);
                    }
                }
            }
        });

        trueQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answers.get(n)){
                    points++;
                    questions.remove(n);
                    answers.remove(n);
                    pointsText.setText("Score: "+points);
                    if (questions.size() == 0){
                        result();
                    }else{
                        n = r.nextInt(questions.size());
                        questionsText.setText(questions.get(n));
                    }
                }else{
                    result();
                }
            }
        });

        falseQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answers.get(n)){
                    points++;
                    questions.remove(n);
                    answers.remove(n);
                    pointsText.setText("Score: "+points);
                    if (questions.size() == 0){
                        result();
                    }else{
                        n = r.nextInt(questions.size());
                        questionsText.setText(questions.get(n));
                    }
                }else{
                    result();
                }
            }
        });
    }
    public void result(){
        dataHelper.saveDataInt("POINTS", points);
        startActivity(new Intent(EasyActivity.this, ResultActivity.class));
        finish();
    }
}
