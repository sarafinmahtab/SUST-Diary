package edu.sust.diary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import edu.sust.diary.models.realm.Teachers;

public class ProfileActivity extends AppCompatActivity {

    private Teachers teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        findViews();
        initViewsAndObjects();
        viewClickListeners();
    }

    private void findViews() {

        profileToolbarTitleTextView = findViewById(R.id.profile_toolbar_title);

        teacherTextView = findViewById(R.id.dialog_teacher_name_text);
        designationTextView = findViewById(R.id.dialog_designation_text);

        addressFullLayout = findViewById(R.id.address_full_layout);
        addressTextView = findViewById(R.id.address_text);


        emailFullLayout = findViewById(R.id.email_full_layout);

        sustEmailLayout = findViewById(R.id.sust_email_layout);
        sustEmailTextView = findViewById(R.id.sust_email_text);
        sustEmailButton = findViewById(R.id.email_button);

        personalEmailLayout = findViewById(R.id.personal_email_layout);
        personalEmailTextView = findViewById(R.id.personal_email_text);
        personalEmailButton = findViewById(R.id.personal_email_button);


        numberFullLayout = findViewById(R.id.number_full_layout);

        phoneLayout = findViewById(R.id.phone_layout);
        phoneTextView = findViewById(R.id.phone_text);
        phoneButton = findViewById(R.id.phone_button);

        mobileLayout = findViewById(R.id.mobile_layout);
        mobileTextView = findViewById(R.id.mobile_text);
        mobileButton = findViewById(R.id.mobile_button);


        websiteFullLayout = findViewById(R.id.site_full_layout);

        websiteLayout = findViewById(R.id.site_layout);
        websiteTextView = findViewById(R.id.site_text);
        websiteButton = findViewById(R.id.site_button);
    }

    private void initViewsAndObjects() {

        String json = getIntent().getStringExtra("teachers");
        String deptCode = getIntent().getStringExtra("dept_code");

        profileToolbarTitleTextView.setText(String.format("Teacher of dept. of %s", deptCode));

        try {

            JSONObject responseObject = new JSONObject(json);

            Gson gson = new Gson();

            Type type = new TypeToken<Teachers>() {
            }.getType();

            teacher = gson.fromJson(responseObject.toString(), type);

            if (teacher.isLeave()) {
                teacherTextView.setText(String.format("%s (On Leave)", teacher.getName()));
            } else {
                teacherTextView.setText(teacher.getName());
            }

            designationTextView.setText(teacher.getDesignation());

            if (teacher.getAddress().isEmpty()) {
                addressFullLayout.setVisibility(View.GONE);
            } else {
                addressFullLayout.setVisibility(View.VISIBLE);
                addressTextView.setText(teacher.getAddress());
            }



            if (teacher.getMail().isEmpty() && teacher.getPersonal().isEmpty()) {
                emailFullLayout.setVisibility(View.GONE);
            } else {

                emailFullLayout.setVisibility(View.VISIBLE);

                if (teacher.getMail().isEmpty()) {
                    sustEmailLayout.setVisibility(View.GONE);
                } else {
                    sustEmailLayout.setVisibility(View.VISIBLE);
                    sustEmailTextView.setText(teacher.getMail());
                }

                if (teacher.getPersonal().isEmpty()) {
                    personalEmailLayout.setVisibility(View.GONE);
                } else {
                    personalEmailLayout.setVisibility(View.VISIBLE);
                    personalEmailTextView.setText(teacher.getPersonal());
                }
            }


            if (teacher.getPhone().isEmpty() && teacher.getMobile().isEmpty()) {
                numberFullLayout.setVisibility(View.GONE);
            } else {

                numberFullLayout.setVisibility(View.VISIBLE);

                if (teacher.getPhone().isEmpty()) {
                    phoneLayout.setVisibility(View.GONE);
                } else {

                    phoneLayout.setVisibility(View.VISIBLE);
                    phoneTextView.setText(teacher.getPhone());
                }

                if (teacher.getMobile().isEmpty()) {
                    mobileLayout.setVisibility(View.GONE);
                } else {

                    mobileLayout.setVisibility(View.VISIBLE);
                    mobileTextView.setText(teacher.getMobile());
                }
            }

            if (teacher.getWebsite().isEmpty()) {
                websiteFullLayout.setVisibility(View.GONE);
            } else {
                websiteFullLayout.setVisibility(View.VISIBLE);

                websiteTextView.setText(teacher.getWebsite());
            }
        } catch (JSONException e) {
            Log.d("JSONException", e.getMessage());
            e.printStackTrace();
        }
    }

    private void viewClickListeners() {

        sustEmailLayout.setOnClickListener(v -> {

        });

        sustEmailButton.setOnClickListener(v -> {

        });

        personalEmailLayout.setOnClickListener(v -> {

        });

        personalEmailButton.setOnClickListener(v -> {

        });

        phoneLayout.setOnClickListener(v -> {

        });

        phoneButton.setOnClickListener(v -> {

        });

        mobileLayout.setOnClickListener(v -> {

        });

        mobileButton.setOnClickListener(v -> {

        });

        websiteLayout.setOnClickListener(v -> {

        });

        websiteButton.setOnClickListener(v -> {

        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private TextView profileToolbarTitleTextView;

    private TextView teacherTextView;
    private TextView designationTextView;

    private LinearLayout addressFullLayout;
    private TextView addressTextView;

    private LinearLayout emailFullLayout;

    private LinearLayout sustEmailLayout;
    private TextView sustEmailTextView;
    private ImageButton sustEmailButton;

    private LinearLayout personalEmailLayout;
    private TextView personalEmailTextView;
    private ImageButton personalEmailButton;

    private LinearLayout numberFullLayout;

    private LinearLayout phoneLayout;
    private TextView phoneTextView;
    private ImageButton phoneButton;

    private LinearLayout mobileLayout;
    private TextView mobileTextView;
    private ImageButton mobileButton;

    private LinearLayout websiteFullLayout;

    private LinearLayout websiteLayout;
    private TextView websiteTextView;
    private ImageButton websiteButton;
}
