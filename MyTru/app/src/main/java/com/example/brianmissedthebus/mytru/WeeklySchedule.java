package com.example.brianmissedthebus.mytru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class WeeklySchedule extends AppCompatActivity implements View.OnClickListener{

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Test", "entered Weekly Schedule");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_schedule);
        back = (ImageButton) findViewById(R.id.backButton4);
        back.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weekly_schedule, menu);
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
            case R.id.backButton4:
                finish();
                break;
        }

    }
}
