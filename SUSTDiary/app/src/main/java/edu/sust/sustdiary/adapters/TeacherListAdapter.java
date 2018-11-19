package edu.sust.sustdiary.adapters;

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

import edu.sust.sustdiary.R;
import edu.sust.sustdiary.models.Teacher;

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.TeacherViewHolder> {

    private Context context;
    private List<Teacher> teacherList;

    public TeacherListAdapter(Context context, List<Teacher> teacherList) {
        this.context = context;
        this.teacherList = teacherList;
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
        final Teacher teacher = teacherList.get(position);

        teacherViewHolder.teacherNameTextView.setText(teacher.getTeacherName());
        teacherViewHolder.designationTextView.setText(teacher.getDesignation());

        teacherViewHolder.teacherLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContactDialog(teacher);
            }
        });
    }

    private void openContactDialog(Teacher teacher) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.setContentView(R.layout.dialog_contact_info);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogScale;
        dialog.setCancelable(true);

        ImageButton closeDialogButton = dialog.findViewById(R.id.dialog_close_button);

        closeDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView teacherTextView = dialog.findViewById(R.id.dialog_teacher_name_text);
        TextView designationTextView = dialog.findViewById(R.id.dialog_designation_text);

        teacherTextView.setText(teacher.getTeacherName());
        designationTextView.setText(teacher.getDesignation());


        LinearLayout officeAddressFullLayout = dialog.findViewById(R.id.office_address_full_layout);
        TextView officeAddressTextView = dialog.findViewById(R.id.office_address_text);

        if (teacher.getOfficeAddress() != null) {
            officeAddressFullLayout.setVisibility(View.VISIBLE);
            officeAddressTextView.setText(teacher.getOfficeAddress());
        } else {
            officeAddressFullLayout.setVisibility(View.GONE);
        }


        LinearLayout emailFullLayout = dialog.findViewById(R.id.email_full_layout);

        LinearLayout sustEmailLayout = dialog.findViewById(R.id.sust_email_layout);
        TextView sustEmailTextView = dialog.findViewById(R.id.sust_email_text);
        ImageButton sustEmailButton = dialog.findViewById(R.id.email_button);

        LinearLayout personalEmailLayout = dialog.findViewById(R.id.personal_email_layout);
        TextView personalEmailTextView = dialog.findViewById(R.id.personal_email_text);
        ImageButton personalEmailButton = dialog.findViewById(R.id.personal_email_button);

        if (teacher.getSustEmail() != null || teacher.getPersonalEmail() != null) {

            emailFullLayout.setVisibility(View.VISIBLE);

            if (teacher.getSustEmail() != null) {
                sustEmailLayout.setVisibility(View.VISIBLE);
                sustEmailTextView.setText(teacher.getSustEmail());

                sustEmailButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            } else {
                sustEmailLayout.setVisibility(View.GONE);
            }

            if (teacher.getPersonalEmail() != null) {
                personalEmailLayout.setVisibility(View.VISIBLE);
                personalEmailTextView.setText(teacher.getPersonalEmail());

                personalEmailButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            } else {
                personalEmailLayout.setVisibility(View.GONE);
            }
        } else {
            emailFullLayout.setVisibility(View.GONE);
        }

        LinearLayout numberFullLayout = dialog.findViewById(R.id.number_full_layout);

        LinearLayout phoneLayout = dialog.findViewById(R.id.phone_layout);
        TextView phoneTextView = dialog.findViewById(R.id.phone_text);
        ImageButton phoneButton = dialog.findViewById(R.id.phone_button);

        LinearLayout mobileLayout = dialog.findViewById(R.id.mobile_layout);
        TextView mobileTextView = dialog.findViewById(R.id.mobile_text);
        ImageButton mobileButton = dialog.findViewById(R.id.mobile_button);

        if (teacher.getPhoneNumber() != null || teacher.getMobileNumber() != null) {

            numberFullLayout.setVisibility(View.VISIBLE);

            if (teacher.getPhoneNumber() != null) {
                phoneLayout.setVisibility(View.VISIBLE);
                phoneTextView.setText(teacher.getPhoneNumber());

                phoneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            } else {
                phoneLayout.setVisibility(View.GONE);
            }

            if (teacher.getMobileNumber() != null) {
                mobileLayout.setVisibility(View.VISIBLE);
                mobileTextView.setText(teacher.getMobileNumber());

                mobileButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            } else {
                mobileLayout.setVisibility(View.GONE);
            }
        } else {
            numberFullLayout.setVisibility(View.GONE);
        }

        dialog.show();
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
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
