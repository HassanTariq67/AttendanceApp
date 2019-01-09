package com.naqibhatti.mod.madfinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TeacherList extends ArrayAdapter<Teacher> {

    private Activity context;
    List<Teacher> teachers;

    public TeacherList(Activity context,List<Teacher> teachers){
        super(context,R.layout.course_list,teachers);
        this.context = context;
        this.teachers = teachers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.textview, null, true);

        final TextView textEdit = (TextView) listViewItem.findViewById(R.id.textView2);

        Teacher teacher = teachers.get(position);
        textEdit.setText(teacher.getName());

        return listViewItem;
    }
}
