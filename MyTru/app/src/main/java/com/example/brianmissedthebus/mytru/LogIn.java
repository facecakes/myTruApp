package com.example.brianmissedthebus.mytru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class LogIn extends AppCompatActivity implements View.OnClickListener{

    ImageButton back;
    String studentNumber, password;
    EditText numberIn, passIn;
    Button signIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        back = (ImageButton) findViewById(R.id.backButton3);
        back.setOnClickListener(this);
        numberIn = (EditText) findViewById(R.id.studentNumberIn);
        passIn = (EditText) findViewById(R.id.passwordIn);
        signIn = (Button) findViewById(R.id.saveButton);
        signIn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){

        switch(v.getId()){
            case R.id.backButton3:
                finish();
                break;
            case R.id.saveButton:
                studentNumber = numberIn.getText().toString();
                password = passIn.getText().toString();
                //verify
                Log.i("Test", "strings set");
                //display weekly schedule
                Intent newActivity0 = new Intent(LogIn.this, WeeklySchedule.class);
                startActivity(newActivity0);
                Log.i("Test", "Intent passed");
                break;
        }

    }
}
