package com.example.brianmissedthebus.mytru;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LogIn extends AppCompatActivity implements View.OnClickListener{


    /*
        Using shared preferences for the prototype, this can be changed in the future.
        Shared preferences make sense because we are storing a very small amount of information.
     */

    public static final String STUDENT_SCHEDULE = "com.example.brianmissedthebus.mytru";
    public static final String DATA = "com.example.brianmissedthebus.mytru";

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

                if(logIn(studentNumber,password))
                {
                    Intent newActivity0 = new Intent(LogIn.this, WeeklySchedule.class);
                    startActivity(newActivity0);
                    Log.i("Test", "Intent passed");
                }
                break;
            default:
                Log.d("app", "How did we get here?");
        }

    }

    private boolean logIn(String studentID, String pw){
        // In here, this is where we would do a pull request to the TRU servers for the student account validation and then pull schedule.
        // Going to pretend it is being validated.
        // Validating with stored preferences.
        SharedPreferences sp = getSharedPreferences(DATA, MODE_PRIVATE);
        Set<String> students = sp.getStringSet("Students", new HashSet<String>());

        //Log.d("app", students.toString());
        //Log.d("app", "Size: " + students.size());

        Boolean retVal = false;

        if(students.size()>0){
            String[] studentArray = students.toArray(new String[students.size()]);

            // Testing for each student.
            for(int x = 0; x<studentArray.length; x++){
                //Log.d("app", studentArray[x].toString());
                String[] info = studentArray[x].split("\\|");

                //Log.d("app", "Length: " + info.length);
                //Log.d("app", "ID: " + info[0]);
                //Log.d("app", "PW: " + info[1]);

                if(info[0].equals(studentID) && info[1].equals(pw)){
                    retVal = true;
                    Log.d("app", "Login valid for student: " + studentID);

                    // Syncing courses here.
                    String[] studentArrayNPW = new String[info.length-1];
                    for(int y = 1; y<studentArrayNPW.length; y++)
                        studentArrayNPW[y]=info[y+1];
                    studentArrayNPW[0]=info[0];
                    syncCourses(studentArrayNPW);
                    break;
                }
            }
        }
        if(!retVal)
            Log.d("app", "Login unsuccessful with: " + studentID + " PW: " + pw);

        return retVal;
    }

    private void syncCourses(String[] studentInfo){
        SharedPreferences sp = getSharedPreferences(DATA, MODE_PRIVATE);
        Set<String> courseSet = sp.getStringSet("Courses", new HashSet<String>());

        if(courseSet.size()>0){

            String[] courses = courseSet.toArray(new String[courseSet.size()]);
            SharedPreferences thisStudentSP = getSharedPreferences(STUDENT_SCHEDULE, MODE_PRIVATE);
            SharedPreferences.Editor editor = thisStudentSP.edit();

            editor.remove("MyCourses");
            editor.remove("Student");
            Set<String> thisStudentSet = new HashSet<>();

            // Storing student ID for easy access later.
            editor.putString("Student", studentInfo[0]);
            Log.d("app", Arrays.toString(studentInfo));

            for(int x = 1; x<studentInfo.length; x++){
                // Populating MyCourses set. In here we will lookup each course CRN and then save it to another shared pref.
                for(int y = 0; y<courses.length; y++){
                    String courseCRN = courses[y].substring(0,courses[y].indexOf('|'));
                    //Log.d("app", courseCRN);
                    if(studentInfo[x].equals(courseCRN))
                    {
                        Log.d("app", studentInfo[x] + " == " + courseCRN);
                        Log.d("app", "Student has: " + courseCRN);
                        thisStudentSet.add(courses[y]);
                    }

                }
            }
            editor.putStringSet("MyCourses", thisStudentSet);
            editor.commit();
            Log.d("app", thisStudentSP.getString("Student", "DNE"));
            Log.d("app", thisStudentSP.getStringSet("MyCourses",new HashSet<String>()).toString());
        }
    }
}
