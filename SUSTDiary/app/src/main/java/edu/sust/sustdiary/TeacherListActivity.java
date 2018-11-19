package edu.sust.sustdiary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.sust.sustdiary.adapters.TeacherListAdapter;
import edu.sust.sustdiary.models.Teacher;

public class TeacherListActivity extends AppCompatActivity {

    private List<Teacher> teacherList;

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

        toolbarTitleTextView.setText("Dept. of CSE");

        teacherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        teacherRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        teacherRecyclerView.setHasFixedSize(true);

        getTeacherList();
        TeacherListAdapter teacherListAdapter = new TeacherListAdapter(this, teacherList);
        teacherRecyclerView.setAdapter(teacherListAdapter);
    }

    private void getTeacherList() {
        teacherList = new ArrayList<>();

        Teacher teacher1 = new Teacher();
        teacher1.setTeacherID(1);
        teacher1.setTeacherName("Dr Muhammed Zafar Iqbal");
        teacher1.setDesignation("Professor");
        teacher1.setOfficeAddress("Room no: 324, Department of Computer Science and Engineering, Dr. M. A. Wazed Miah IICT Building, Shahjalal University of Science and Technology, Sylhet-3114");
        teacher1.setSustEmail("mzi@sust.edu");
        teacher1.setPersonalEmail("mzi@gmail.com");
        teacher1.setPhoneNumber("+880-821-713850");
        teacher1.setMobileNumber("+01553555000");
        teacherList.add(teacher1);

        Teacher teacher2 = new Teacher();
        teacher2.setTeacherID(2);
        teacher2.setTeacherName("Dr Mohammad Shahidur Rahman");
        teacher2.setDesignation("Professor");
        teacher2.setSustEmail("rahmanms@sust.edu");
        teacher2.setPersonalEmail("rahmanms.bd@gmail.com");
        teacherList.add(teacher2);

        Teacher teacher3 = new Teacher();
        teacher3.setTeacherID(3);
        teacher3.setTeacherName("Dr Mohammad Reza Selim");
        teacher3.setDesignation("Professor & Head");
        teacher3.setOfficeAddress("Room: 320, Dept. of CSE, Dr. M.A. Wazed Miah IICT Building, SUST");
        teacher3.setPhoneNumber("+88-01972357830");
        teacherList.add(teacher3);

        Teacher teacher4 = new Teacher();
        teacher4.setTeacherID(4);
        teacher4.setTeacherName("M. Jahirul Islam, PhD., PEng.");
        teacher4.setDesignation("Professor");
        teacher4.setSustEmail("jahir-cse@sust.edu");
        teacher4.setPhoneNumber("+880-821-713850");
        teacherList.add(teacher4);

        Teacher teacher5 = new Teacher();
        teacher5.setTeacherID(5);
        teacher5.setTeacherName("Mohammad Abdullah Al Mumin");
        teacher5.setDesignation("Associate Professor");
        teacherList.add(teacher5);

        Teacher teacher6 = new Teacher();
        teacher6.setTeacherID(6);
        teacher6.setTeacherName("Md Masum");
        teacher6.setDesignation("Associate Professor");
        teacherList.add(teacher6);

        Teacher teacher7 = new Teacher();
        teacher7.setTeacherID(7);
        teacher7.setTeacherName("Dr. Farida Chowdhury");
        teacher7.setDesignation("Associate Professor");
        teacherList.add(teacher7);

        Teacher teacher8 = new Teacher();
        teacher8.setTeacherID(8);
        teacher8.setTeacherName("Md Saiful Islam");
        teacher8.setDesignation("Assistant Professor");
        teacherList.add(teacher8);

        Teacher teacher9 = new Teacher();
        teacher9.setTeacherID(9);
        teacher9.setTeacherName("Sabir Ismail");
        teacher9.setDesignation("Assistant Professor (On Leave)");
        teacherList.add(teacher9);

        Teacher teacher10 = new Teacher();
        teacher10.setTeacherID(10);
        teacher10.setTeacherName("Md Mahadi Hasan Nahid");
        teacher10.setDesignation("Lecturer");
        teacherList.add(teacher10);
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
