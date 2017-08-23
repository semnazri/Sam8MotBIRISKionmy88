package motion3.com.birisk.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
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

import cn.pedant.SweetAlert.SweetAlertDialog;
import motion3.com.birisk.Network.APIConstant;
import motion3.com.birisk.POJO.LostPassJSON;
import motion3.com.birisk.POJO.LostPassword;
import motion3.com.birisk.POJO.UserInterface;
import motion3.com.birisk.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private Button btn_submit;
    private SweetAlertDialog mDialog;
    private TextView.OnEditorActionListener mOnEditorAction =
            new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
//                        prefsprivate = getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
//                        BIRSKPreference.Editor prefsprivateEditor = prefsprivate.edit();
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
        btn_submit = (Button) view.findViewById(R.id.btn_submit_lost);
        edt_name.setOnEditorActionListener(mOnEditorAction);
        vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptChange();
            }
        });

        LinearLayout parent = (LinearLayout) view.findViewById(R.id.parent);
        setupUI(parent);


        return view;
    }

    private void attemptChange() {
        edt_email.setError(null);
        edt_name.setError(null);

        String email = edt_email.getText().toString();
        String name = edt_name.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
//        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
//            edt_name.setError(getString(R.string.notnull_forgot));
//            focusView = edt_name;
//            cancel = true;
//        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            edt_email.setError(getString(R.string.email_null));
            focusView = edt_email;
            vibe.vibrate(100);
            cancel = true;
        } else if (TextUtils.isEmpty(name)) {
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
            toLogin(name, email);
        }
    }

    private void toLogin(String name, String email) {

        getDialogmuter().show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConstant.APIPARENT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserInterface service = retrofit.create(UserInterface.class);
        Call<LostPassword> call = service.sendLost(new LostPassJSON(name, email));
        call.enqueue(new Callback<LostPassword>() {
            @Override
            public void onResponse(Call<LostPassword> call, Response<LostPassword> response) {
                Log.d("response", response.toString());
                String chang = response.body().getResult().getChanged();
                String exist = response.body().getResult().getExists();

                if (chang.equals("1") && exist.equals("1")) {
                    mDialog.dismissWithAnimation();
                    getDialogSukses("Password sementara anda telah kami kirim ke email\nsilakan cek email anda").show();
                } else {
                    mDialog.dismissWithAnimation();
                    getDialogError("Terjadi kesalahan, silakan periksa kembali nama dan email anda").show();
                }
            }

            @Override
            public void onFailure(Call<LostPassword> call, Throwable t) {
                android.util.Log.d("onFailure", t.toString());
                getDialogFailure("Terjadi kesalahan silakan coba lagi").show();
            }
        });


    }

    private boolean isEmailValid(String username) {
        //TODO: Replace this with your own logic
        return username.length() > 3;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private SweetAlertDialog getDialogmuter() {
        mDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        mDialog.getProgressHelper()
                .setBarColor(getResources().getColor(R.color.colorPrimaryDark));
        mDialog.getProgressHelper()
                .setRimColor(getResources().getColor(R.color.white));
        mDialog.setTitleText("Loading...");
        mDialog.setCancelable(false);
        return mDialog;
    }

    private SweetAlertDialog getDialogSukses(String s) {
        mDialog = new SweetAlertDialog(getActivity());
        mDialog.setTitleText("BIRISK");
        mDialog.setContentText(s);
        mDialog.setConfirmText("Close");
        mDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
        mDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                getFragmentManager().popBackStack();
                mDialog.dismiss();
            }
        });


        return mDialog;
    }


    private SweetAlertDialog getDialogError(String s) {
        mDialog = new SweetAlertDialog(getActivity());
        mDialog.setTitleText("BIRISK");
        mDialog.setContentText(s);
        mDialog.setConfirmText("Close");
        mDialog.changeAlertType(SweetAlertDialog.WARNING_TYPE);
        mDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                mDialog.dismiss();
            }
        });


        return mDialog;
    }

    private SweetAlertDialog getDialogFailure(String s) {
        mDialog = new SweetAlertDialog(getActivity());
        mDialog.setTitleText("BIRISK");
        mDialog.setContentText(s);
        mDialog.setConfirmText("Close");
        mDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
        mDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                mDialog.dismiss();
            }
        });

        return mDialog;
    }

}
