package com.firetot.quizapptutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HardResultActivity extends AppCompatActivity {

    DataHelper dataHelper;

    TextView currentScore, bestScore, tv_user;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_result);
        dataHelper = new DataHelper(this);

        currentScore = (TextView) findViewById(R.id.currentPoints);
        bestScore = (TextView) findViewById(R.id.bestScore);
        tv_user = (TextView) findViewById(R.id.tv_user);
        home = (Button) findViewById(R.id.home);

        int points = dataHelper.receiveDataInt("POINTSHARD", 0);
        int best = dataHelper.receiveDataInt("BESTHARD", 0);

        currentScore = (TextView) findViewById(R.id.currentPoints);
        bestScore = (TextView) findViewById(R.id.bestScore);

        tv_user.setText("Good luck next time, "+ dataHelper.receiveDataString("NAME", "User"));

        currentScore.setText(""+points);

        if (points > best){
            best = points;
            dataHelper.saveDataInt("BESTHARD", best);
        }

        bestScore.setText(""+best);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HardResultActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}
