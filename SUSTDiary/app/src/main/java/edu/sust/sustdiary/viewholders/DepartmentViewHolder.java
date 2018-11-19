package edu.sust.sustdiary.viewholders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.sust.sustdiary.R;
import edu.sust.sustdiary.TeacherListActivity;
import edu.sust.sustdiary.expandablelist.ChildViewHolder;
import edu.sust.sustdiary.models.Department;

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

    public void bind(final Department department) {
        departmentTextView.setText(department.getDepartmentName());

        departmentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, TeacherListActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
