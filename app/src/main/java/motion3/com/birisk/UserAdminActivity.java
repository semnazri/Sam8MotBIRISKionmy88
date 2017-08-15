package motion3.com.birisk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 6/3/17.
 *
 * @copyright 2017
 */

public class UserAdminActivity extends AppCompatActivity {

    private Button btn_user, btn_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splasch_screen);
        setContentView(R.layout.user_admin_layout);

        btn_user = (Button) findViewById(R.id.btn_user);
        btn_admin = (Button) findViewById(R.id.btn_admin);

        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserAdminActivity.this, LoginActivity.class);
                i.putExtra("admin", "1");
//                finish();
                startActivity(i);
            }
        });

        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserAdminActivity.this, LoginActivity.class);
                i.putExtra("user", "2");
//                finish();
                startActivity(i);
            }
        });


    }
}
