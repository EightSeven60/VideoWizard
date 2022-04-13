package com.example.vproject.ui;

import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.vproject.R;
import com.example.vproject.storageinteraction.StorageHandler;
import com.example.vproject.user.CurrentUser;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class VideoFeedActivity extends AppCompatActivity {

    private boolean isInForeground;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Button btnNavVideoFeed;
    private Button btnNavProfile;
    private Button btnNavSettings;
    private Button btnNavEditProfile;
    private ScrollView videoScrollView;
    private LinearLayout linearLayoutVideoFragment;
    private ConstraintLayout videoConstraintLayout;
    private FragmentTransaction fragmentTransaction;

    private boolean isExternalStorageReadable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
        Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())) {
            Log.i("State", "Storage Readable");
            return true;
        }
        else return false;
    }

    private boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void readFile(View v, String filename) {
        if (isExternalStorageReadable()) {
            StringBuilder builder = new StringBuilder();
            /*
            try {
                File myFile = new File(Environment.getExternalStorageDirectory(), filename);

                FileInputStream fileInputStream = new FileInputStream(myFile);
                if (fileInputStream != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            */
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isInForeground = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_feed);
        drawerLayout = findViewById(R.id.drawerLayoutNav);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("Your Activity Title"); // for set actionbar title
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navView = findViewById(R.id.navView);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();


                if (id == R.id.videoFeed) {
                    VideoFragment videoFragment = new VideoFragment(VideoFeedActivity.this);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.videoConstraintLayout, videoFragment);
                    fragmentTransaction.commitNow();
                    videoFragment.createViewsForFiles(
                            StorageHandler.getVideoFiles(
                                    Environment.getExternalStorageDirectory().toString() + StorageHandler.getVideoFolder()));
                }
                else if (id == R.id.profile) {
                    //TODO implement profile menu
                    ProfileFragment profileFragment = new ProfileFragment(VideoFeedActivity.this);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.videoConstraintLayout, profileFragment);
                    fragmentTransaction.commitNow();
                    profileFragment.setup();
                }
                else if (id == R.id.settings) {
                    //TODO implement settings menu
                    SettingsFragment settingsFragment = new SettingsFragment(VideoFeedActivity.this);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.videoConstraintLayout, settingsFragment);
                    fragmentTransaction.commitNow();
                    settingsFragment.setup();
                }

                TextView welcomeTV = findViewById(R.id.WelcomeTV);
                if (welcomeTV != null) ((ViewManager)welcomeTV.getParent()).removeView(welcomeTV);
                drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }
        });

        StorageHandler.updateVideoFolderPath(getSharedPreferences("Settings", MODE_PRIVATE)
                .getString("videoFolderPath", "/VideoApp"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return actionBarDrawerToggle.onOptionsItemSelected(menuItem) || super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isInForeground = true;
    }
    @Override
    protected void onPause() {
        super.onPause();
        isInForeground = false;
    }
}