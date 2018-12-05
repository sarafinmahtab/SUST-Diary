package edu.sust.diary.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import edu.sust.diary.R;
import edu.sust.diary.models.realm.Teachers;

public class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.QueryViewHolder> {

    private Context context;
    private List<Teachers> teachersList;

    public QueryAdapter(Context context, List<Teachers> teachersList) {
        this.context = context;
        this.teachersList = teachersList;
    }

    @NonNull
    @Override
    public QueryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_single_teacher,
                viewGroup, false);
        return new QueryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueryViewHolder queryViewHolder, int i) {
        final Teachers teachers = teachersList.get(i);

        queryViewHolder.teacherNameTextView.setText(teachers.getName());
        queryViewHolder.designationTextView.setText(teachers.getDesignation());

        queryViewHolder.teacherLayout.setOnClickListener(v -> {
//            Intent intent = new Intent(context, ProfileActivity.class);
//            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return teachersList.size();
    }

    class QueryViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout teacherLayout;

        private TextView teacherNameTextView;
        private TextView designationTextView;

        QueryViewHolder(@NonNull View itemView) {
            super(itemView);

            teacherLayout = itemView.findViewById(R.id.teacher_layout);

            teacherNameTextView = itemView.findViewById(R.id.teacher_name_text);
            designationTextView = itemView.findViewById(R.id.designation_text);
        }
    }
}
