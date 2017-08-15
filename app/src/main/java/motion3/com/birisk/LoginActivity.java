package motion3.com.birisk;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import motion3.com.birisk.Fragment.FragmentLogin;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 6/3/17.
 *
 * @copyright 2017
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splasch_screen);
        setContentView(R.layout.login_activity);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_bro, new FragmentLogin());
        fragmentTransaction.commit();

        String kampret = String.valueOf(fragmentManager.getBackStackEntryCount());

        Log.d("kampret", kampret);

    }
}
