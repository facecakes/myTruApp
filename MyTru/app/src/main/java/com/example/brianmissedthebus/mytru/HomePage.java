package com.example.brianmissedthebus.mytru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;


public class HomePage extends AppCompatActivity implements View.OnClickListener {

    ImageButton nav, bus, schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_home_page);

        nav = (ImageButton) findViewById(R.id.navButton);
        nav.setOnClickListener(this);
        bus = (ImageButton) findViewById(R.id.busButton);
        bus.setOnClickListener(this);
        schedule = (ImageButton) findViewById(R.id.scheduleButton);
        schedule.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
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
            case R.id.scheduleButton:
                Intent newActivity0 = new Intent(HomePage.this, LogIn.class);
                startActivity(newActivity0);
                break;
            case R.id.navButton:
                Intent newActivity1 = new Intent(HomePage.this, ComingSoon.class);
                startActivity(newActivity1);
                break;
            case R.id.busButton:
                Intent newActivity2 = new Intent(HomePage.this, ComingSoon.class);
                startActivity(newActivity2);
                break;

        }

    }
}
