package com.firetot.quizapptutorial;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout easy, normal, hard;

    TextView username, b_easy, b_normal, b_hard;
    ImageView edit_name;

    DataHelper dataHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataHelper = new DataHelper(this);

        easy = (RelativeLayout) findViewById(R.id.easy);
        normal = (RelativeLayout) findViewById(R.id.normal);
        hard = (RelativeLayout) findViewById(R.id.hard);

        edit_name = (ImageView) findViewById(R.id.edit_name);

        username = (TextView) findViewById(R.id.username);
        b_easy = (TextView) findViewById(R.id.b_easy);
        b_normal = (TextView) findViewById(R.id.b_normal);
        b_hard = (TextView) findViewById(R.id.b_hard);

        b_easy.setText(""+dataHelper.receiveDataInt("BEST", 0));
        b_normal.setText(""+dataHelper.receiveDataInt("BESTNORMAL", 0));
        b_hard.setText(""+dataHelper.receiveDataInt("BESTHARD", 0));

        username.setText(dataHelper.receiveDataString("NAME", "User"));

        edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });


        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EasyActivity.class));
            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NormalActivity.class));
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HardActivity.class));
            }
        });
    }

    public void alertDialog(){
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.alert_dialog, null);
        final EditText name = (EditText) view.findViewById(R.id.name);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("Enter Name")
                .setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String s = name.getText().toString();
                        dataHelper.saveDataString("NAME", s);
                        username.setText(dataHelper.receiveDataString("NAME", "User"));
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
