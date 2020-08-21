package com.example.teamscorecounter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView giantScore;
    private TextView ravenScore;
    private int giantCount = 0;
    private int ravenCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        giantScore = (TextView) findViewById(R.id.GiantScore);
        ravenScore = (TextView) findViewById(R.id.RavensScore);

    }

    @Override
    protected void onStart() {
        super.onStart();

        giantCount =0;
        ravenCount = 0;

        giantScore.setText("Downs: 0");
        ravenScore.setText("Downs: 0");
    }


    public void countUpTeamOne(View view) {
        giantCount++;

        if (giantCount < 6)giantScore.setText("Downs: " + giantCount);

        if (giantCount == 5 && ravenCount != 5) {
            changeActivity("Giants");
        }
    }

    public void countUpTeamTwo(View view) {
        ravenCount++;
        try {
            if (ravenCount < 6) ravenScore.setText("Downs: " + ravenCount);

            if (ravenCount == 5 && giantCount != 5) {
                changeActivity("Ravens");
            }
        }catch (Exception e){
            Log.d("msg","hello");
        }
    }

    public void changeActivity(String winner) {
        Intent intent = new Intent(MainActivity.this, WinnerActivity.class);
        intent.putExtra("winner", winner).putExtra("giantScore", giantCount).putExtra("ravenScore", ravenCount);

        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("giantCount", giantCount);
        outState.putInt("ravenCount", ravenCount);
        outState.putString("giantScore", giantScore.getText().toString());
        outState.putString("ravenScore", ravenScore.getText().toString());

        onRestoreInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        giantCount = (int) savedInstanceState.get("giantCount");
        giantScore.setText((String) savedInstanceState.get("giantScore"));
        ravenCount = (int) savedInstanceState.get("ravenCount");
        ravenScore.setText((String) savedInstanceState.get("ravenScore"));
    }

}
