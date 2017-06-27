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
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import motion3.com.birisk.Fragment.FragmentHome;
import motion3.com.birisk.Fragment.FragmentLogin;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static AppCompatActivity activity;
    public static FragmentManager manager;
    public static Context context;
    public static ActionBarDrawerToggle toggle;
    public static DrawerLayout mDrawerLayout;
    public static ImageView iv,logo;
    public static TextView tv;
    Toolbar mToolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        activity = MainActivity.this;
        manager = getSupportFragmentManager();

        context = getApplicationContext();
        activity = MainActivity.this;
        manager = getSupportFragmentManager();

        Showtoolbar();
        iv = (ImageView) findViewById(R.id.img_tolbar);
        logo = (ImageView)findViewById(R.id.logo_toolbar_center);
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
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        Fragment fragment = null;


        if (id == R.id.home) {
            // Handle the camera action
//            fragment = new FragmentHome();
            fragment = new FragmentHome();
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
//
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
        }
        else if (backStackCount >= 2) {
            fragmentManager.popBackStack();
            FragmentManager.BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(
                    1);
            getSupportFragmentManager().popBackStack(entry.getId(),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().executePendingTransactions();

            navigationView.getMenu().getItem(0).setChecked(true);

        }
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
