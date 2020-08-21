package com.example.teamscorecounter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WinnerActivity extends AppCompatActivity {

    private TextView winnerScore;
    private Button clickShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_winner);

            Intent intent = getIntent();

            winnerScore = (TextView) findViewById(R.id.winnerScore);

            int score1 = getKeyInt("giantScore");
            int score2 = getKeyInt("ravenScore");

            int difference = score1 > score2 ? score1 - score2 : score2 - score1;

            String winner = intent.getExtras().get("winner").toString();

            winnerScore.setText(String.format("%s won by %d points", winner, difference));
            clickShare = (Button)findViewById(R.id.shareButton);
        } catch (Exception e) {
            Log.d("msg", "hello1");
        }
    }
       public void share(View view){
           Intent letsShare = new Intent(WinnerActivity.this, shareActivity.class);
           startActivity(letsShare);
       }
    public int getKeyInt(String team) {
        return Integer.parseInt(getIntent().getExtras().get(team).toString());

    }
}
