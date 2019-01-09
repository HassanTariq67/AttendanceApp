package com.naqibhatti.mod.madfinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

public class CourseList extends ArrayAdapter<Courses> {

    private Activity context;
    List<Courses> courses;
    ArrayList<String> selectedcourses = new ArrayList<String>();

    public CourseList(Activity context,List<Courses> courses){
        super(context,R.layout.course_list,courses);
        this.context = context;
        this.courses = courses;
    }

    public ArrayList<String> getSelectedcourses() {
        return selectedcourses;
    }

    public void setSelectedcourses(ArrayList<String> selectedcourses) {
        this.selectedcourses = selectedcourses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.course_list, null, true);

        final CheckBox attendanceCheckBox = (CheckBox) listViewItem.findViewById(R.id.checkBox);

        Courses course = courses.get(position);
        attendanceCheckBox.setText(course.getcName());

        attendanceCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selectedcourses.add(attendanceCheckBox.getText().toString());
                }
                else{
                    selectedcourses.remove(attendanceCheckBox.getText().toString());
                }
            }
        });

        return listViewItem;
    }
}
