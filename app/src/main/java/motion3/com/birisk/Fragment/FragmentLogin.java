package motion3.com.birisk.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import motion3.com.birisk.MainActivity;
import motion3.com.birisk.Network.APIConstant;
import motion3.com.birisk.Network.ConnectionDetector;
import motion3.com.birisk.POJO.User;
import motion3.com.birisk.POJO.UserInterface;
import motion3.com.birisk.R;
import motion3.com.birisk.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 5/25/17.
 *
 * @copyright 2017
 */

public class FragmentLogin extends Fragment {

    public static final String PREFS_PRIVATE = "PREFS_PRIVATE";
    private View view;
    private EditText edt_username;
    private EditText edt_pass;
    private TextView txt_forgot;
    private Button btn_login;
    private SharedPreferences prefsprivate;

    private Boolean isInternetPresent = false;
    private ConnectionDetector cd;
    private Vibrator vibe;
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

        view = inflater.inflate(R.layout.login_fragment, container, false);

        LinearLayout parent = (LinearLayout) view.findViewById(R.id.parent);
        setupUI(parent);
//        MainActivity.iv.setVisibility(View.GONE);
        edt_username = (EditText) view.findViewById(R.id.edt_username);
        edt_pass = (EditText) view.findViewById(R.id.edt_pass);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        edt_pass.setOnEditorActionListener(mOnEditorAction);
        txt_forgot = (TextView) view.findViewById(R.id.forgot_pass);
        vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);


        cd = new ConnectionDetector(getActivity());
        prefsprivate = getActivity().getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
        String Username = prefsprivate.getString(SharedPreference.email, "kosong");
        String Password = prefsprivate.getString(SharedPreference.password, "kosong");

        if (!Username.equals("kosong") && !Password.equals("kosong")) {

            autoLoginCheckConnection(Username, Password);

        } else if (Username.equals("kosong") && Password.equals("kosong")) {

                edt_username.setText("");
                edt_pass.setText("");


        }


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

        private void autoLoginCheckConnection(String username, String password) {
            isInternetPresent = cd.isConnectingToInternet();
            if (isInternetPresent) {
                doLogin(username, password);
            } else {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setMessage("Tidak ada koneksi internet");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();

            }

    }

    private void attemptLogin() {
        edt_username.setError(null);
        edt_pass.setError(null);

        String email = edt_username.getText().toString();
        String password = edt_pass.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
//        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
//            edt_pass.setError(getString(R.string.notnull));
//            focusView = edt_pass;
//            cancel = true;
//        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            edt_username.setError(getString(R.string.usernamenull));
            focusView = edt_username;
            vibe.vibrate(100);
            cancel = true;
        } else if (!isPasswordValid(password)) {
            edt_pass.setError(getString(R.string.pass_false));
            vibe.vibrate(100);
            focusView = edt_pass;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            // TODO: doLogin
            //TODO : Sementara langsung intent
//            toMainActivity();
            doLogin(email, password);
        }
    }

    private void doLogin(final String email, final String password) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConstant.APIPARENT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserInterface service = retrofit.create(UserInterface.class);
        Call<User> call = service.getUserDetail(email, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response != null) {
                        String username = response.body().getUserDetail().getUName();
                        String userid = response.body().getUserDetail().getUId();
                        String userpincode = response.body().getUserDetail().getUPincode();
                        String title = response.body().getUserDetail().getUTitle();

                    prefsprivate = getActivity().getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor preEditor = prefsprivate.edit();
                    preEditor.putString(SharedPreference.Username, username);
                    preEditor.putString(SharedPreference.userid, userid);
                    preEditor.putString(SharedPreference.utitle, title);
                    preEditor.putString(SharedPreference.userpincode, userpincode);

                    preEditor.putString(SharedPreference.email,email);
                    preEditor.putString(SharedPreference.password,password);
                    preEditor.commit();

                    toMainActivity();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                android.util.Log.d("onFailure", t.toString());
                Toast.makeText(getActivity(), "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void toMainActivity() {

        prefsprivate = getActivity().getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);

        Boolean firsttime = prefsprivate.getBoolean("hide", false);

        if (firsttime) {
            Intent mainAct = new Intent(getActivity(), MainActivity.class);
            getActivity().finish();
            startActivity(mainAct);
        } else {
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
        return username.length() > 3 && username.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 2;
    }


}
