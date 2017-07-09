package motion3.com.birisk.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import motion3.com.birisk.MainActivity;
import motion3.com.birisk.R;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 5/25/17.
 *
 * @copyright 2016
 * PT.Bisnis Indonesia Sibertama
 */

public class FragmentLogin extends Fragment {

    public static final String PREFS_PRIVATE = "PREFS_PRIVATE";
    private View view;
    private EditText edt_username;
    private EditText edt_pass;
    private TextView txt_forgot;
    private Button btn_login;
    private SharedPreferences prefsprivate;
    private TextView.OnEditorActionListener mOnEditorAction =
            new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {

                        attemptLogin();
                        return true;
                    }
                    return false;
                }
            };

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.login_fragment,container,false);

        LinearLayout parent = (LinearLayout) view.findViewById(R.id.parent);
        setupUI(parent);
//        MainActivity.iv.setVisibility(View.GONE);
        edt_username = (EditText) view.findViewById(R.id.edt_username);
        edt_pass = (EditText) view.findViewById(R.id.edt_pass);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        edt_pass.setOnEditorActionListener(mOnEditorAction);
        txt_forgot = (TextView) view.findViewById(R.id.forgot_pass);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        txt_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Fragment fragment = new FragmentLostPassword();
                fragment.setArguments(bundle);

                FragmentManager fm = (getActivity()).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container_bro, fragment, "home").addToBackStack("home").commit();
            }
        });


        return view;
    }

    private void attemptLogin() {
        edt_username.setError(null);
        edt_pass.setError(null);

        String username = edt_username.getText().toString();
        String password = edt_pass.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            edt_pass.setError(getString(R.string.notnull));
            focusView = edt_pass;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            edt_username.setError(getString(R.string.usernamenull));
            focusView = edt_username;
            cancel = true;
        } else if (!isEmailValid(password)) {
            edt_pass.setError(getString(R.string.passnull));
            focusView = edt_pass;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            // TODO: doLogin
            //TODO : Sementara langsung intent
            toMainActivity();
//            doMOPLogin(etUsername.getText().toString(), etPassword.getText().toString());
        }
    }

    private void toMainActivity() {

        prefsprivate = getActivity().getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);

        Boolean firsttime = prefsprivate.getBoolean("hide", false);

        if (firsttime){
            Intent mainAct = new Intent(getActivity(), MainActivity.class);
            getActivity().finish();
            startActivity(mainAct);
        }else {
            Bundle bundle = new Bundle();
            Fragment fragment = new FragmentChangePassword();
            fragment.setArguments(bundle);

            FragmentManager fm = (getActivity()).getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.container_bro, fragment).commit();
        }


    }

    private boolean isEmailValid(String username) {
        //TODO: Replace this with your own logic
        return username.length() > 3;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


}
