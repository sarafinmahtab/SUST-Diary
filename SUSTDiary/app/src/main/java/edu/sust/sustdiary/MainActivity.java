package edu.sust.sustdiary;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import edu.sust.sustdiary.fragments.AboutFragment;
import edu.sust.sustdiary.fragments.AdministrationFragment;
import edu.sust.sustdiary.fragments.DepartmentFragment;
import edu.sust.sustdiary.fragments.HolidayFragment;
import edu.sust.sustdiary.fragments.InstituteAndCenterFragment;
import edu.sust.sustdiary.fragments.LocationFragment;
import edu.sust.sustdiary.fragments.OfficeFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // tags used to attach the fragments
    private static final String TAG_DEPARTMENTS = "departments";
    private static final String TAG_ADMINISTRATION = "admin";
    private static final String TAG_INSTITUTE_AND_CENTER = "institute";
    private static final String TAG_OFFICES = "offices";
    private static final String TAG_HOLIDAYS = "holidays";
    private static final String TAG_ABOUT = "about";
    private static final String TAG_LOCATION = "location";

    public static String CURRENT_TAG = TAG_DEPARTMENTS;

    private String[] activityTitles;

    private Handler handler;

    private int navItemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        handler = new Handler();

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_titles);

        // load nav menu header data
        //loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_DEPARTMENTS;
            loadHomeFragment();
        }
    }


    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_departments:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_DEPARTMENTS;
                        break;
                    case R.id.nav_administration:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_ADMINISTRATION;
                        break;
                    case R.id.nav_institute_and_center:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_INSTITUTE_AND_CENTER;
                        break;
                    case R.id.nav_offices:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_OFFICES;
                        break;
                    case R.id.nav_holidays:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_HOLIDAYS;
                        break;
                    case R.id.nav_about:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_ABOUT;
                        break;
                    case R.id.nav_location:
                        navItemIndex = 6;
                        CURRENT_TAG = TAG_LOCATION;
                        break;
//                    case R.id.nav_about_us:
//                        // launch new intent instead of loading fragment
//                        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
//                        drawer.closeDrawers();
//                        return true;
//                    case R.id.nav_privacy_policy:
//                        // launch new intent instead of loading fragment
//                        startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
//                        drawer.closeDrawers();
//                        return true;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
//                if (menuItem.isChecked()) {
//                    menuItem.setChecked(false);
//                } else {
//                    menuItem.setChecked(true);
//                }
//                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we do not want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we do not want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    private void initViews() {

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }

        toolbarTitleTextView = findViewById(R.id.toolbar_title);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navHeaderView = navigationView.getHeaderView(0);
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_departments:
                navItemIndex = 0;
                CURRENT_TAG = TAG_DEPARTMENTS;
                break;
            case R.id.nav_administration:
                navItemIndex = 1;
                CURRENT_TAG = TAG_ADMINISTRATION;
                break;
            case R.id.nav_institute_and_center:
                navItemIndex = 2;
                CURRENT_TAG = TAG_INSTITUTE_AND_CENTER;
                break;
            case R.id.nav_offices:
                navItemIndex = 3;
                CURRENT_TAG = TAG_OFFICES;
                break;
            case R.id.nav_holidays:
                navItemIndex = 4;
                CURRENT_TAG = TAG_HOLIDAYS;
                break;
            case R.id.nav_about:
                navItemIndex = 5;
                CURRENT_TAG = TAG_ABOUT;
                break;
            case R.id.nav_location:
                navItemIndex = 6;
                CURRENT_TAG = TAG_LOCATION;
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);

        // set toolbar title
        toolbarTitleTextView.setText(activityTitles[navItemIndex]);

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawerLayout.closeDrawers();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable pendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.fragment_container, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        handler.post(pendingRunnable);

        //Closing drawer on item click
        drawerLayout.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getFragment() {
        switch (navItemIndex) {
            case 0:
                return new DepartmentFragment();
            case 1:
                return new AdministrationFragment();
            case 2:
                return new InstituteAndCenterFragment();
            case 3:
                return new OfficeFragment();
            case 4:
                return new HolidayFragment();
            case 5:
                return new AboutFragment();
            case 6:
                return new LocationFragment();
            default:
                return new DepartmentFragment();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        // flag to load home fragment when user presses back key
        // checking if user is on other navigation menu
        // rather than home
//        if (navItemIndex != 0) {
//            navItemIndex = 0;
//            CURRENT_TAG = TAG_FACULTY;
//            loadHomeFragment();
//            return;
//        }

        super.onBackPressed();
    }


    private Toolbar toolbar;
    private TextView toolbarTitleTextView;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private View navHeaderView;
}
