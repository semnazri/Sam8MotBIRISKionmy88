package motion3.com.birisk.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import motion3.com.birisk.R;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 6/3/17.
 *
 * @copyright 2017
 */

public class FragmentLostPassword extends Fragment {

    private View view;
    private EditText edt_email, edt_name;
    private Vibrator vibe;
    private TextView.OnEditorActionListener mOnEditorAction =
            new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
//                        prefsprivate = getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
//                        SharedPreferences.Editor prefsprivateEditor = prefsprivate.edit();
//                        prefsprivateEditor.putString(WKNDConstant.USERNAME, etUsername.getText().toString());
//                        prefsprivateEditor.commit();
                        attemptChange();
//                        Log.e("username", prefsprivate.getString(WKNDConstant.USERNAME, "empty"));
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

        view = inflater.inflate(R.layout.lost_password_fragment, container, false);
        edt_email = (EditText) view.findViewById(R.id.edt_email_forgot);
        edt_name = (EditText) view.findViewById(R.id.edt_name_forgot);
        edt_name.setOnEditorActionListener(mOnEditorAction);
        vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        LinearLayout parent = (LinearLayout) view.findViewById(R.id.parent);
        setupUI(parent);


        return view;
    }

    private void attemptChange() {
        edt_email.setError(null);
        edt_name.setError(null);

        String username = edt_email.getText().toString();
        String password = edt_name.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
//        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
//            edt_name.setError(getString(R.string.notnull_forgot));
//            focusView = edt_name;
//            cancel = true;
//        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            edt_email.setError(getString(R.string.email_null));
            focusView = edt_email;
            vibe.vibrate(100);
            cancel = true;
        } else if (!isEmailValid(password)) {
            edt_name.setError(getString(R.string.namenull));
            focusView = edt_name;
            vibe.vibrate(100);
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            // TODO: doLogin
            //TODO : Sementara langsung intent
            toLogin();
        }
    }

    private void toLogin() {

        FragmentManager fm = getFragmentManager();
        fm.popBackStack();
//        Intent mainAct = new Intent(getActivity(), MainActivity.class);
//        getActivity().finish();
//        startActivity(mainAct);
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
