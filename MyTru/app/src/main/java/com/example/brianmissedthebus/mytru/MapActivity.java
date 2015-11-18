package com.example.brianmissedthebus.mytru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton back;
    ImageView map;
    float zoom = 0.5f;
    boolean zoomedOut = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        back = (ImageButton) findViewById(R.id.backButton6);
        back.setOnClickListener(this);
        map = (ImageView) findViewById(R.id.campusMap);
        map.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }


    protected void onResume()
    {
        super.onResume();
        Toast toast = Toast.makeText(getApplicationContext(), "Tap to zoom out", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void onClick(View v){

        switch(v.getId()){
            case R.id.backButton6:
                finish();
                break;
            case R.id.campusMap:
                if(zoomedOut) {
                    v.setScaleX(1);
                    v.setScaleY(1);
                    zoomedOut = false;
                }
                else {
                    v.setScaleX(zoom);
                    v.setScaleY(zoom);
                    zoomedOut = true;
                }
                break;
            default:
                Log.d("app", "How did we get here?");
        }
    }
}
