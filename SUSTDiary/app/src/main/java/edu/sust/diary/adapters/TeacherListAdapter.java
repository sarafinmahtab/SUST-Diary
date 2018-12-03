package edu.sust.diary.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import edu.sust.diary.ProfileActivity;
import edu.sust.diary.R;
import edu.sust.diary.models.realm.Teachers;

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.TeacherViewHolder> {

    private Context context;
    private List<Teachers> teachersList;

    private String deptCode;

    public TeacherListAdapter(Context context, String deptCode, List<Teachers> teachersList) {
        this.context = context;
        this.deptCode = deptCode;
        this.teachersList = teachersList;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_single_teacher,
                viewGroup, false);
        return new TeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder teacherViewHolder, int position) {
        final Teachers teachers = teachersList.get(position);

        teacherViewHolder.teacherNameTextView.setText(teachers.getName());
        teacherViewHolder.designationTextView.setText(teachers.getDesignation());

        teacherViewHolder.teacherLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProfileActivity.class);

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            String json = gson.toJson(teachers);

            intent.putExtra("teachers", json);
            intent.putExtra("dept_code", deptCode);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return teachersList.size();
    }

    class TeacherViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout teacherLayout;

        private TextView teacherNameTextView;
        private TextView designationTextView;

        TeacherViewHolder(@NonNull View itemView) {
            super(itemView);

            teacherLayout = itemView.findViewById(R.id.teacher_layout);

            teacherNameTextView = itemView.findViewById(R.id.teacher_name_text);
            designationTextView = itemView.findViewById(R.id.designation_text);
        }
    }
}
