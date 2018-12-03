package edu.sust.diary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import edu.sust.diary.adapters.TeacherListAdapter;
import edu.sust.diary.models.realm.Teachers;

public class TeacherListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        Toolbar toolbar = findViewById(R.id.teacher_list_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        findViews();
        initViewsAndObjects();
    }

    private void findViews() {
        toolbarTitleTextView = findViewById(R.id.teacher_list_toolbar_title);
        teacherRecyclerView = findViewById(R.id.teacher_recycler_view);
    }

    private void initViewsAndObjects() {

        List<Teachers> teachersList = new ArrayList<>();

        teacherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        teacherRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        teacherRecyclerView.setHasFixedSize(true);

        String deptCode = getIntent().getStringExtra("dept_code");
        toolbarTitleTextView.setText(String.format("Dept. of %s", deptCode));

        String json = getIntent().getStringExtra("teachers_list");

        try {
            JSONArray responseArray = new JSONArray(json);

            Gson gson = new Gson();

            for (int index = 0; index < responseArray.length(); index++) {

                Type type = new TypeToken<Teachers>() {
                }.getType();

                JSONObject responseObject = responseArray.getJSONObject(index);

                Teachers teacher = gson.fromJson(responseObject.toString(), type);

                teachersList.add(teacher);
            }

            TeacherListAdapter teacherListAdapter = new TeacherListAdapter(this, deptCode, teachersList);
            teacherRecyclerView.setAdapter(teacherListAdapter);

        } catch (JSONException e) {

            Log.d("JSONException", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private TextView toolbarTitleTextView;
    private RecyclerView teacherRecyclerView;
}
