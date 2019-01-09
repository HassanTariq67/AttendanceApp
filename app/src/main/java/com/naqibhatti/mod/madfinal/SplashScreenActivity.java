package com.naqibhatti.mod.madfinal;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    public final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreenActivity.this, UsersActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

/* MAP OF THE ENTIRE APPLICATION:
Classes Class, Course, Student, Teacher are all models and not activities.

First Activity is this one. This is a simple splash screen ppf the app that lasts for 2 seconds.
Second Activity is UsersActivity. Here the user selects if he is a student or admin.
Admin password is hardcoded. The username is user and password is password.
Both logins are different
%%%If the user selects student then he can either create a new student or enroll the student in a ccourse.
Student Registration is where the registration happens andd StudentCourseActivity is where the user can enroll in a course. We will populate it with a listview of all the courses saved in the database.

$$$$$$$$$$$$$If the user selects admin then he can either create a new class, a new course, a teacher or assign teacher to a class.
ClassCreate and CourseCreate are simple. Hre you take the data supplied and store it in the database.
In TeacherCreate much of the same thing happens. We make a new teacher and store his details in te databse.

In TeacherAssign show a list of the courses ame and besides them give a dropdown to select which teacher is assigned to a class.
 */