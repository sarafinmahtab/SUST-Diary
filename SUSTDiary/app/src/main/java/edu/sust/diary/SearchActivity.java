package edu.sust.diary;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.sust.diary.adapters.QueryAdapter;
import edu.sust.diary.models.realm.Teachers;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

public class SearchActivity extends AppCompatActivity {

    private List<Teachers> teachersList;

    private QueryAdapter queryAdapter;

    private Realm realm;

    private String phonePattern;
    private String emailPattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        findViews();
        initViews();
    }

    private void findViews() {
        queryRecyclerView = findViewById(R.id.query_recycler_view);
    }

    private void initViews() {

        phonePattern = "((\\+|0)?88( )?)?([0-9]){11}|(((\\+|0)?88)?(-| )0821( |-))?([0-9]{6})";
        emailPattern = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$";

        realm = Realm.getDefaultInstance();

        teachersList = new ArrayList<>();

        queryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        queryRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        queryRecyclerView.setHasFixedSize(true);

        queryAdapter = new QueryAdapter(this, teachersList);
        queryRecyclerView.setAdapter(queryAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        searchView.onActionViewExpanded();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                Pattern numberPattern = Pattern.compile(phonePattern);
                Matcher numberMatcher = numberPattern.matcher(s);

                Pattern mailPattern = Pattern.compile(emailPattern);
                Matcher emailMatcher = mailPattern.matcher(s);

                RealmResults<Teachers> realmResults;

                if (numberMatcher.matches()){

                    realmResults = realm.where(Teachers.class)
                            .equalTo("phone", s, Case.INSENSITIVE)
                            .or()
                            .equalTo("mobile", s, Case.INSENSITIVE)
                            .findAll();

                    teachersList.clear();
                    teachersList = realm.copyFromRealm(realmResults);

                    queryAdapter = new QueryAdapter(SearchActivity.this, teachersList);
                    queryRecyclerView.setAdapter(queryAdapter);
                    queryAdapter.notifyDataSetChanged();

                } else if (emailMatcher.matches()) {

                    realmResults = realm.where(Teachers.class)
                            .equalTo("mail", s, Case.INSENSITIVE)
                            .or()
                            .equalTo("personal", s, Case.INSENSITIVE)
                            .findAll();

                    teachersList.clear();
                    teachersList = realm.copyFromRealm(realmResults);

                    queryAdapter = new QueryAdapter(SearchActivity.this, teachersList);
                    queryRecyclerView.setAdapter(queryAdapter);
                    queryAdapter.notifyDataSetChanged();

                } else {

                    realmResults = realm.where(Teachers.class)
                            .equalTo("name", s, Case.INSENSITIVE)
                            .findAll();

                    teachersList.clear();
                    teachersList = realm.copyFromRealm(realmResults);

                    queryAdapter = new QueryAdapter(SearchActivity.this, teachersList);
                    queryRecyclerView.setAdapter(queryAdapter);
                    queryAdapter.notifyDataSetChanged();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                RealmResults<Teachers> realmResults =
                        realm.where(Teachers.class)
                                .like("name", "*"+s+"*", Case.INSENSITIVE)
                                .findAll();

                teachersList.clear();
                teachersList = realm.copyFromRealm(realmResults);

                queryAdapter = new QueryAdapter(SearchActivity.this, teachersList);
                queryRecyclerView.setAdapter(queryAdapter);
                queryAdapter.notifyDataSetChanged();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    private RecyclerView queryRecyclerView;
}
