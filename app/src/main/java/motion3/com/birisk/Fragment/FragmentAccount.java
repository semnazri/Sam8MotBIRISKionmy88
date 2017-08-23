package motion3.com.birisk.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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
import de.hdodenhof.circleimageview.CircleImageView;
import motion3.com.birisk.MainActivity;
import motion3.com.birisk.Network.APIConstant;
import motion3.com.birisk.POJO.ChangePass;
import motion3.com.birisk.POJO.ChangePassJSON;
import motion3.com.birisk.POJO.LostPassJSON;
import motion3.com.birisk.POJO.LostPassword;
import motion3.com.birisk.POJO.UserInterface;
import motion3.com.birisk.R;
import motion3.com.birisk.BIRSKPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static motion3.com.birisk.BIRSKPreference.email;

/**
 * Created by Semmy on 8/12/2017.
 */

public class FragmentAccount extends Fragment {

    public static final String PREFS_PRIVATE = "PREFS_PRIVATE";
    private View view;
    private TextView tv_name, tv_email;
    private CircleImageView image_person;
    private EditText edt_email, edt_new_pass, edt_new_retypePass;
    private Button btn_submit;
    private Vibrator vibe;
    private SharedPreferences prefsprivate;
    private SweetAlertDialog mDialog;

    String nama,usrid,phone,address,pincode;
    private TextView.OnEditorActionListener mOnEditorAction =
            new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
//                        prefsprivate = getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
//                        BIRSKPreference.Editor prefsprivateEditor = prefsprivate.edit();
//                        prefsprivateEditor.putString(WKNDConstant.USERNAME, etUsername.getText().toString());
//                        prefsprivateEditor.commit();
                        validasi();
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

        view = inflater.inflate(R.layout.fragment_account, container, false);

        MainActivity.iv.setVisibility(View.GONE);
        MainActivity.logo.setVisibility(View.VISIBLE);


        LinearLayout parent = (LinearLayout) view.findViewById(R.id.parent);
        setupUI(parent);

        tv_name = (TextView) view.findViewById(R.id.person_name);
        tv_email = (TextView) view.findViewById(R.id.person_email);
        image_person = (CircleImageView) view.findViewById(R.id.profile_image);
        edt_email = (EditText) view.findViewById(R.id.edt_email_changepass);
        edt_new_pass = (EditText) view.findViewById(R.id.edt_newpass_changepass);
        edt_new_retypePass = (EditText) view.findViewById(R.id.edt_repass_changepass);
        btn_submit = (Button) view.findViewById(R.id.btn_user);
        edt_new_retypePass.setOnEditorActionListener(mOnEditorAction);
        vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        prefsprivate = getActivity().getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
        nama = prefsprivate.getString(BIRSKPreference.Username, "nama");
        usrid = prefsprivate.getString(BIRSKPreference.userid, "userid");

        tv_name.setText(nama);
        tv_email.setText(usrid);
        edt_email.setText(usrid);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasi();
            }
        });

        return view;
    }

    private void validasi() {
        edt_email.setError(null);
        edt_new_pass.setError(null);
        edt_new_retypePass.setError(null);

        String email = edt_email.getText().toString();
        String newpass = edt_new_pass.getText().toString();
        String retypepass = edt_new_retypePass.getText().toString();

        boolean cancel = false;
        View focusView = null;
//
//        if (!TextUtils.isEmpty(email) || !isPasswordValid(newpass)) {
//            edt_new_pass.setError(getString(R.string.notnull));
//            focusView = edt_new_pass;
//            cancel = true;
//        }
        if (!isEmailValid(email)){
            edt_email.setError(getString(R.string.email_false));
            focusView = edt_email;
            vibe.vibrate(100);
            cancel = true;
        }else if (!isPasswordValid(newpass)) {
            edt_new_pass.setError(getString(R.string.pass_false));
            focusView = edt_new_pass;
            vibe.vibrate(100);
            cancel = true;
        } else if (!isPasswordValid(retypepass)) {
            edt_new_retypePass.setError(getString(R.string.pass_false));
            vibe.vibrate(100);
            focusView = edt_new_retypePass;
            cancel = true;
        }

        // Check for a valid email email.
        if (TextUtils.isEmpty(email)) {
            edt_email.setError(getString(R.string.usernamenull));
            focusView = edt_email;
            vibe.vibrate(100);
            cancel = true;
        }else if (TextUtils.isEmpty(newpass)) {
            edt_new_pass.setError(getString(R.string.passnull));
            focusView = edt_new_pass;
            vibe.vibrate(100);
            cancel = true;
        }else if (TextUtils.isEmpty(retypepass)) {
            edt_new_retypePass.setError(getString(R.string.passnull));
            focusView = edt_new_retypePass;
            vibe.vibrate(100);
            cancel = true;
        }



        if (newpass.equals(retypepass) && isPasswordValid(newpass) && isPasswordValid(retypepass)) {
            tohome(email,newpass);
        }else {
            edt_new_retypePass.setError(getString(R.string.doesntmatch));
            focusView = edt_new_retypePass;
            vibe.vibrate(100);
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        }


    }

    private void tohome(String email, String newpass) {

        getDialogmuter().show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConstant.APIPARENT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserInterface service = retrofit.create(UserInterface.class);
        Call<ChangePass> call = service.changePass(new ChangePassJSON(email, newpass));
        call.enqueue(new Callback<ChangePass>() {
            @Override
            public void onResponse(Call<ChangePass> call, Response<ChangePass> response) {
                Log.d("response", response.toString());
                String chang = response.body().getResult().getChanged();
                String failed = response.body().getResult().getFailed();

                if (chang.equals("1") && failed.equals("0")) {
                    mDialog.dismissWithAnimation();
                    getDialogSukses("Password anda telah berhasil diubah").show();
                } else {
                    mDialog.dismissWithAnimation();
                    getDialogError("Terjadi kesalahan, silakan coba kembali").show();
                }
            }

            @Override
            public void onFailure(Call<ChangePass> call, Throwable t) {
                android.util.Log.d("onFailure", t.toString());
                mDialog.dismissWithAnimation();
                getDialogFailure("Terjadi kesalahan silakan coba kembali").show();
            }
        });
    }

    private boolean isEmailValid(String username) {
        //TODO: Replace this with your own logic
        return username.length() > 6 && username.contains("@");
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
