package com.example.brianmissedthebus.mytru;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class WeeklySchedule extends AppCompatActivity implements View.OnClickListener{

    public static final String STUDENT_SCHEDULE = "com.example.brianmissedthebus.mytru";
    ImageButton back;
    String[][] days = new String[5][10];
    ListView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_schedule);
        back = (ImageButton) findViewById(R.id.backButton4);
        back.setOnClickListener(this);
        display = (ListView) findViewById(R.id.weeklyList);
        showSchedule();
    }

    public void showSchedule(){
        separateDays();
        populateList();
    }

    public void populateList(){
        String[] listItems = getListItems();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_layout, listItems);
        display.setAdapter(adapter);
    }

    public String[] getListItems() {
        String[] result = new String[5];
        String[] names = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String temp;
        String startTime, endTime;
        for(int i = 0; i<days.length; i++) {
            int classCount = 0;
            if (days[i][0] == null) {
                result[i] = names[i] + ": 0 classes.";
            } else {
                classCount = days[i].length;
                temp = days[i][0].split("\\|")[6];
                startTime = temp.split("-")[0];
                temp = days[i][days[i].length-1].split("\\|")[6];
                endTime = temp.split("-")[1];
                result[i] = names[i] + ": " + classCount + " classes\n" +
                            "Start: " + startTime + "\nEnd: " + endTime;
                Log.d("app", "Day: " +names[i]);
                Log.d("app", "Class count: " + classCount);
                Log.d("app", "Start Time: " + startTime);
                Log.d("app", "End Time: " + endTime);
            }
        }
        return result;

    }
    public void separateDays() {
        SharedPreferences sp = getSharedPreferences(STUDENT_SCHEDULE, MODE_PRIVATE);
        Set<String> courses = sp.getStringSet("MyCourses", new HashSet<String>());

        if(courses.size()>0){
            String[] courseArray = courses.toArray(new String[courses.size()]);

            Set<String> mon = new HashSet<>();
            Set<String> tues = new HashSet<>();
            Set<String> wed = new HashSet<>();
            Set<String> thurs = new HashSet<>();
            Set<String> fri = new HashSet<>();

            for(int i = 0; i<courseArray.length; i++){
                String[] course = courseArray[i].split("\\|");

                if(course[5].contains("M")) {
                    mon.add(courseArray[i]);
                    Log.d("app", courseArray[i] + " added to monday set.");
                }
                if(course[5].contains("T")) {
                    tues.add(courseArray[i]);
                    Log.d("app", courseArray[i] + " added to tuesday set.");
                }
                if(course[5].contains("W")) {
                    wed.add(courseArray[i]);
                    Log.d("app", courseArray[i] + " added to wednesday set.");
                }
                if(course[5].contains("R")) {
                    thurs.add(courseArray[i]);
                    Log.d("app", courseArray[i] + " added to thursday set.");
                }
                if(course[5].contains("F")) {
                    fri.add(courseArray[i]);
                    Log.d("app", courseArray[i] + " added to friday set.");
                }
            }

            days[0] = timeSort(mon);
            days[1] = timeSort(tues);
            days[2] = timeSort(wed);
            days[3] = timeSort(thurs);
            days[4] = timeSort(fri);
        }
    }

    public String[] timeSort(Set<String> data){

        if(data.size()>0) {
            String[] day = data.toArray(new String[data.size()]);

            int index;
            int t1 = 0;
            int t2 = 0;
            String s, temp1, temp2;
            String time1, time2;

            for (int x = 0; x < day.length - 1; x++) {
                index = x;
                for (int y = x + 1; y < day.length; y++) {
                    temp1 = day[index].split("\\|")[6];
                    time1 = temp1.split(":")[0];
                    temp2 = day[y].split("\\|")[6];
                    time2 = temp2.split(":")[0];
                    try {
                        t1 = Integer.parseInt(time1);
                        t2 = Integer.parseInt(time2);
                    } catch (NumberFormatException e) {
                        Log.d("app", "time sort parse exception");
                    }
                    if (t2 < t1) {
                        index = y;
                    }
                }
                if (index != x) {
                    s = day[x];
                    day[x] = day[index];
                    day[index] = s;
                }
            }
            /*for (int i = 0; i < day.length; i++) {
                Log.d("app", "Array contents: " + day[i]);
            }*/
            return day;
        } else {
            return new String[1];
        }
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
