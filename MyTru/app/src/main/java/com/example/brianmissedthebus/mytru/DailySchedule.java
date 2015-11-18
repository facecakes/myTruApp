package com.example.brianmissedthebus.mytru;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.Set;

public class DailySchedule extends AppCompatActivity implements View.OnClickListener{

    ImageButton back;
    ListView classList;
    String[] classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_schedule);
        back = (ImageButton) findViewById(R.id.backButton);
        back.setOnClickListener(this);

        classList = (ListView) findViewById(R.id.dailyList);

        Intent i = getIntent();
        classes = i.getStringArrayExtra("Day");

        showClasses();
    }

    public void showClasses(){
        String[] listItems = getListItems();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_layout, listItems);
        classList.setAdapter(adapter);
    }

    public String[] getListItems(){
        String[] result = new String[classes.length];
        String[] temp;
        for(int i = 0; i<result.length; i++){
            temp = classes[i].split("\\|");
            result[i] = temp[1] + " " + temp[2] + "\n" + temp[3] +
                    "\n" + temp[6] + " - " + temp[7];
        }
        return result;
    }

    public void onClick(View v){

        switch(v.getId()){
            case R.id.backButton:
                finish();
                break;
            default:
                Log.d("app", "How did we get here?");
        }
    }
}
