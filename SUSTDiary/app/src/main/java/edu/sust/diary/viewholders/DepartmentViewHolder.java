package edu.sust.diary.viewholders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import edu.sust.diary.R;
import edu.sust.diary.TeacherListActivity;
import edu.sust.diary.expandablelist.ChildViewHolder;
import edu.sust.diary.models.realm.Departments;
import edu.sust.diary.models.realm.Teachers;

public class DepartmentViewHolder extends ChildViewHolder {

    private LinearLayout departmentLayout;

    private TextView departmentTextView;

    private Context context;

    public DepartmentViewHolder(Context context, View itemView) {
        super(itemView);

        this.context = context;

        departmentLayout = itemView.findViewById(R.id.department_layout);

        departmentTextView = itemView.findViewById(R.id.department_name_text);
    }

    public void bind(final Departments departments) {
        departmentTextView.setText(departments.getName());

        departmentLayout.setOnClickListener(v -> {

                Intent intent = new Intent(context, TeacherListActivity.class);

                List<Teachers> teachersList = departments.getTeachers();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                String json = gson.toJson(teachersList);

                intent.putExtra("dept_code", departments.getCode());
                intent.putExtra("teachers_list", json);
                context.startActivity(intent);
        });
    }
}
