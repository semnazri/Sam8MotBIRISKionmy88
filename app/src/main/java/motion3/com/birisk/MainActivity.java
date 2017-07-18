package motion3.com.birisk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import motion3.com.birisk.Fragment.FragmentHome;
import motion3.com.birisk.Fragment.FragmentLogin;
import motion3.com.birisk.Fragment.FragmentRiskDashboard;
import motion3.com.birisk.Fragment.FragmentRiskRepository;
import motion3.com.birisk.Fragment.Fragment_incidentReport;
import motion3.com.birisk.Fragment.Fragment_riskDictionary;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static AppCompatActivity activity;
    public static FragmentManager manager;
    public static Context context;
    public static ActionBarDrawerToggle toggle;
    public static DrawerLayout mDrawerLayout;
    public static ImageView iv, logo;
    public static TextView tv;
    Toolbar mToolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splasch_screen);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        activity = MainActivity.this;
        manager = getSupportFragmentManager();

        context = getApplicationContext();
        activity = MainActivity.this;
        manager = getSupportFragmentManager();

        Showtoolbar();
        iv = (ImageView) findViewById(R.id.img_tolbar);
        logo = (ImageView) findViewById(R.id.logo_toolbar_center);
//        iv.setVisibility(View.GONE);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        MainActivity.activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MainActivity.toggle.syncState();

        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.burger_24dp);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        mDrawerLayout.setDrawerListener(toggle);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));

    }

    private void Showtoolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_new);
        mToolbar.setTitle("");

        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.back:
                onBackPressed();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        Fragment fragment = null;


        if (id == R.id.home) {
            fragment = new FragmentHome();
        } else if (id == R.id.account) {
            Toast.makeText(activity, "Cooming soon!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.r_dictionary) {
            fragment = new Fragment_riskDictionary();
        } else if (id == R.id.r_repository) {
            fragment = new FragmentRiskRepository();
        } else if (id == R.id.r_dasrhboard) {
            fragment = new FragmentRiskDashboard();
        } else if (id == R.id.incident) {
            fragment = new Fragment_incidentReport();
        } else if (id == R.id.signout) {
            Signout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if (fragment != null) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment, "home").addToBackStack("menu");
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle("");
        }


        return true;
    }

    private void Signout() {
        Intent i = new Intent(this, FragmentLogin.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
//        Fragment fragment = manager.findFragmentById(R.id.container_body);
//        String fragmentName = fragment.getClass().getSimpleName();
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.container_body); // get the fragment that is currently loaded in placeholder
        Object tag = f.getTag();
        int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
//
//        Log.d("nana", fragmentName);
//
//        if (fragmentName.equals("FragmentListHalaman_new") || fragmentName.equals("FragmentDetail")) {
//            super.onBackPressed();


//            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            toggle.syncState();
//        } else
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            Log.d("ada disini ya", "drower close");
        } else if (backStackCount >= 2 && tag.equals("home")){
            FragmentManager.BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(
                    1);
            getSupportFragmentManager().popBackStack(entry.getId(),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().executePendingTransactions();

        }
//        else if (backStackCount >= 2) {
//            fragmentManager.popBackStack();
//            FragmentManager.BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(
//                    1);
//            getSupportFragmentManager().popBackStack(entry.getId(),
//                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            getSupportFragmentManager().executePendingTransactions();
//
//            navigationView.getMenu().getItem(0).setChecked(true);
//
//        }
        else
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Do you want to close this application?")
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int wich) {
                                finish();

                            }
                        }).setNegativeButton("No", null).show();
    }
}
