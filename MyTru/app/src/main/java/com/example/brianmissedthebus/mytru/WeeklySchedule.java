package com.example.brianmissedthebus.mytru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class WeeklySchedule extends AppCompatActivity implements View.OnClickListener{

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_schedule);
        back = (ImageButton) findViewById(R.id.backButton4);
        back.setOnClickListener(this);
    }

    public void onClick(View v){

        switch(v.getId()){
            case R.id.backButton4:
                finish();
                break;
            default:
                Log.d("app", "How did we get here?");
        }
    }
}
