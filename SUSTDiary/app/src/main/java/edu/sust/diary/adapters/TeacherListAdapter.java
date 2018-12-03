package edu.sust.diary.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import edu.sust.diary.R;
import edu.sust.diary.models.realm.Teachers;

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.TeacherViewHolder> {

    private Context context;
    private List<Teachers> teachersList;

    public TeacherListAdapter(Context context, List<Teachers> teachersList) {
        this.context = context;
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

        teacherViewHolder.teacherLayout.setOnClickListener(v -> openContactDialog(teachers));
    }

    private void openContactDialog(Teachers teachers) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.setContentView(R.layout.dialog_contact_info);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogScale;
        dialog.setCancelable(true);

        ImageButton closeDialogButton = dialog.findViewById(R.id.dialog_close_button);

        closeDialogButton.setOnClickListener(v -> dialog.dismiss());

        TextView teacherTextView = dialog.findViewById(R.id.dialog_teacher_name_text);
        TextView designationTextView = dialog.findViewById(R.id.dialog_designation_text);

        teacherTextView.setText(teachers.getName());
        designationTextView.setText(teachers.getDesignation());


        LinearLayout addressFullLayout = dialog.findViewById(R.id.address_full_layout);
        TextView addressTextView = dialog.findViewById(R.id.address_text);

        if (teachers.getAddress().isEmpty()) {
            addressFullLayout.setVisibility(View.GONE);
        } else {
            addressFullLayout.setVisibility(View.VISIBLE);
            addressTextView.setText(teachers.getAddress());
        }


        LinearLayout emailFullLayout = dialog.findViewById(R.id.email_full_layout);

        LinearLayout sustEmailLayout = dialog.findViewById(R.id.sust_email_layout);
        TextView sustEmailTextView = dialog.findViewById(R.id.sust_email_text);
        ImageButton sustEmailButton = dialog.findViewById(R.id.email_button);

        LinearLayout personalEmailLayout = dialog.findViewById(R.id.personal_email_layout);
        TextView personalEmailTextView = dialog.findViewById(R.id.personal_email_text);
        ImageButton personalEmailButton = dialog.findViewById(R.id.personal_email_button);

        if (teachers.getMail().isEmpty() && teachers.getPersonal().isEmpty()) {
            emailFullLayout.setVisibility(View.GONE);
        } else {

            emailFullLayout.setVisibility(View.VISIBLE);

            if (teachers.getMail().isEmpty()) {
                sustEmailLayout.setVisibility(View.GONE);
            } else {
                sustEmailLayout.setVisibility(View.VISIBLE);
                sustEmailTextView.setText(teachers.getMail());

                sustEmailButton.setOnClickListener(v -> {

                });
            }

            if (teachers.getPersonal().isEmpty()) {
                personalEmailLayout.setVisibility(View.GONE);
            } else {
                personalEmailLayout.setVisibility(View.VISIBLE);
                personalEmailTextView.setText(teachers.getPersonal());

                personalEmailButton.setOnClickListener(v -> {

                });
            }
        }

        LinearLayout numberFullLayout = dialog.findViewById(R.id.number_full_layout);

        LinearLayout phoneLayout = dialog.findViewById(R.id.phone_layout);
        TextView phoneTextView = dialog.findViewById(R.id.phone_text);
        ImageButton phoneButton = dialog.findViewById(R.id.phone_button);

        LinearLayout mobileLayout = dialog.findViewById(R.id.mobile_layout);
        TextView mobileTextView = dialog.findViewById(R.id.mobile_text);
        ImageButton mobileButton = dialog.findViewById(R.id.mobile_button);

        if (teachers.getPhone().isEmpty() && teachers.getMobile().isEmpty()) {
            numberFullLayout.setVisibility(View.GONE);
        } else {

            numberFullLayout.setVisibility(View.VISIBLE);

            if (teachers.getPhone().isEmpty()) {
                phoneLayout.setVisibility(View.GONE);
            } else {

                phoneLayout.setVisibility(View.VISIBLE);
                phoneTextView.setText(teachers.getPhone());

                phoneButton.setOnClickListener(v -> {

                });
            }

            if (teachers.getMobile().isEmpty()) {
                mobileLayout.setVisibility(View.GONE);
            } else {

                mobileLayout.setVisibility(View.VISIBLE);
                mobileTextView.setText(teachers.getMobile());

                mobileButton.setOnClickListener(v -> {

                });
            }
        }

        dialog.show();
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
