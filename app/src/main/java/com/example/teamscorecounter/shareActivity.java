package com.example.teamscorecounter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*The user should be able to do all of the following:
Call a friend to share the good news
Send a Message with the champion and points they won by
Search on Maps for the Nearest Arena for the sport the app supports
(i.e. “basketball near me”, “baseball near me”, …)
*/
public class shareActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "shareActivity";
    private EditText editTextLoc, editTextPlainMessage;
    private Button buttonLoc, buttonPlainMessage, buttonDial,buttonMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        getReferencesToWidgets();
        setOnClickListenersFOrButtons();
    }

    private void getReferencesToWidgets(){
        editTextLoc = (EditText)findViewById(R.id.editTextLoc);
        editTextPlainMessage = (EditText)findViewById(R.id.editTextPlainMessage);
        buttonLoc = (Button)findViewById(R.id.buttonLoc);
        buttonPlainMessage = (Button)findViewById(R.id.buttonPlainMessage);
        buttonDial = (Button)findViewById(R.id.buttonDial);
        buttonMain = (Button)findViewById(R.id.buttonMain);
    }
    public void setOnClickListenersFOrButtons(){
        buttonDial.setOnClickListener(this);
        buttonLoc.setOnClickListener(this);
        buttonPlainMessage.setOnClickListener(this);
        buttonMain.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
          switch (view.getId()){
              case R.id.buttonDial:
                  Log.d(TAG,"clicked buttonDial");
                  dialNumber();
                  break;
              case R.id.buttonLoc:
                  Log.d(TAG,"clicked buttonLoc");
                  findLocation();
                  break;
              case R.id.buttonPlainMessage:
                  Log.d(TAG,"clicked buttonPlainMessage");
                  sendText();
                  break;
              case R.id.buttonMain:
                  Log.d(TAG,"clicked buttonMain");
                  Intent intent = new Intent(shareActivity.this, MainActivity.class);
                  startActivity(intent);
                  break;

          }
    }

    private void findLocation(){
        Log.d(TAG,"findLocation");
        String loc = editTextLoc.getText().toString();
        Uri geoLoc = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, geoLoc);
        if(intent.resolveActivity(getPackageManager()) == null  ){
            startActivity(intent);
            Log.d(TAG,"worked!");
        }else{
            Toast.makeText(this,"Cannot find location." + loc, Toast.LENGTH_LONG).show();
            Log.d(TAG,"cannot find");
        }
        Log.d(TAG,"end of find");

    }
    private void sendText(){
        Log.d(TAG,"sendText");
        String textToSend = editTextPlainMessage.getText().toString();
        String mineType = "text/plain";
       ShareCompat.IntentBuilder
               .from(this)
               .setType(mineType)
               .setChooserTitle("Pick an aoo")
               .startChooser();
    }
    private void dialNumber(){
        Log.d(TAG,"dial number");
         Intent intent = new Intent(Intent.ACTION_DIAL);
         startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
    }
}