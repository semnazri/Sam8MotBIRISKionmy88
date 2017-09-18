package motion3.com.birisk.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.CoordinatorLayout;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import motion3.com.birisk.BIRSKPreference;
import motion3.com.birisk.MainActivity;
import motion3.com.birisk.Network.APIConstant;
import motion3.com.birisk.Network.ConnectionDetector;
import motion3.com.birisk.POJO.IncidentReport;
import motion3.com.birisk.POJO.IncidentReportJSON;
import motion3.com.birisk.POJO.ReportInterface;
import motion3.com.birisk.POJO.User;
import motion3.com.birisk.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 6/21/17.
 *
 * @copyright 2017
 */

public class Fragment_incidentReport extends Fragment {

    private View view;
    public static final String PREFS_PRIVATE = "PREFS_PRIVATE";
    private SharedPreferences prefsprivate;
    private Vibrator vibe;
    private RadioGroup radioGroup;
    private RadioButton rB1;
    private Button btn_submit;
    private SweetAlertDialog mDialog;
    private Boolean isInternetPresent = false;
    private ConnectionDetector cd;
    private EditText edt_tgl_pelaporan, edt_waktu_kejadian, edt_tindaklanjut, edtSubject, edt_catatan, edt_lokasi;
    Calendar calendar_kejadian = Calendar.getInstance();
    Calendar calendar_tindaklanjut = Calendar.getInstance();
    Calendar calendar_waktu_kejadian = Calendar.getInstance();
    Calendar calendar_waktu_lapor = Calendar.getInstance();

    private TextView.OnEditorActionListener mOnEditorAction =
            new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {

                        hideSoftKeyboard(getActivity());
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
        view = inflater.inflate(R.layout.incident_report, container, false);
        MainActivity.iv.setVisibility(View.VISIBLE);
        MainActivity.logo.setVisibility(View.GONE);
        MainActivity.iv.setImageResource(R.drawable.report);

        CoordinatorLayout parent = (CoordinatorLayout) view.findViewById(R.id.parent);
        setupUI(parent);

        radioGroup = (RadioGroup) view.findViewById(R.id.radio_grup);


        edt_tgl_pelaporan = (EditText) view.findViewById(R.id.edt_tgl_pelaporan);
        edt_waktu_kejadian = (EditText) view.findViewById(R.id.edt_wkt_kejadian);
        edt_tindaklanjut = (EditText) view.findViewById(R.id.edt_tgl_tindaklanjut);
        edtSubject = (EditText) view.findViewById(R.id.edt_subject);
        edt_catatan = (EditText) view.findViewById(R.id.edt_notes);
        edt_lokasi = (EditText) view.findViewById(R.id.edt_location);
        edt_lokasi.setOnEditorActionListener(mOnEditorAction);
        vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        btn_submit = (Button) view.findViewById(R.id.btn_submit);

        cd = new ConnectionDetector(getActivity());

        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar_kejadian.set(Calendar.YEAR, year);
                calendar_kejadian.set(Calendar.MONTH, monthOfYear);
                calendar_kejadian.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelpelaporan();
            }

        };

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar_tindaklanjut.set(Calendar.YEAR, year);
                calendar_tindaklanjut.set(Calendar.MONTH, monthOfYear);
                calendar_tindaklanjut.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelTindakLanjut();
            }

        };

        edt_tgl_pelaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date1, calendar_kejadian
                        .get(Calendar.YEAR), calendar_kejadian.get(Calendar.MONTH),
                        calendar_kejadian.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edt_tindaklanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date2, calendar_waktu_kejadian
                        .get(Calendar.YEAR), calendar_waktu_kejadian.get(Calendar.MONTH),
                        calendar_waktu_kejadian.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edt_waktu_kejadian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = calendar_waktu_kejadian.get(Calendar.HOUR_OF_DAY);
                int minute = calendar_waktu_kejadian.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edt_waktu_kejadian.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.show();
            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myFormat = "dd-MM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//                Log.d("now", String.valueOf(sdf.format(calendar_kejadian.getTime())));

                int selectedId = radioGroup.getCheckedRadioButtonId();
                rB1 = (RadioButton) view.findViewById(selectedId);

                Log.d("radio", String.valueOf(rB1.getText()));
                String Subject = edtSubject.getText().toString();
                String Catatatan = edt_catatan.getText().toString();
                String Lokasi = edt_lokasi.getText().toString();
                String tgl_kjadian = edt_tgl_pelaporan.getText().toString();
                String wkt_kejadian = edt_waktu_kejadian.getText().toString();
                String wkt_tndk_lnjt = edt_tindaklanjut.getText().toString();
                String level = String.valueOf(rB1.getText());
                prefsprivate = getActivity().getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
                String Username = prefsprivate.getString(BIRSKPreference.email, "kosong");

                validasi(Subject, Catatatan, Lokasi, tgl_kjadian, wkt_kejadian,Username, wkt_tndk_lnjt, level);

            }
        });


        return view;
    }

    private void validasi(String subject, String catatatan, String lokasi, String tgl_kjadian, String wkt_kejadian,String Username, String wkt_tndk_lnjt, String level) {
        edtSubject.setError(null);
        edt_catatan.setError(null);
        edt_lokasi.setError(null);
        edt_tgl_pelaporan.setError(null);
        edt_tindaklanjut.setError(null);
        edt_waktu_kejadian.setError(null);

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(subject)) {
            edtSubject.setError("Subject harap diisi");
            focusView = edtSubject;
            vibe.vibrate(100);
            cancel = true;
        } else if (TextUtils.isEmpty(catatatan)) {
            edt_catatan.setError("Catatan harap diisi");
            focusView = edt_catatan;
            vibe.vibrate(100);
            cancel = true;
        } else if (TextUtils.isEmpty(lokasi)) {
            edt_lokasi.setError("Harap tentukan lokasi");
            focusView = edt_lokasi;
            vibe.vibrate(100);
            cancel = true;
        } else if (TextUtils.isEmpty(tgl_kjadian)) {
            edt_tgl_pelaporan.setError("Harap tentukan tanggal kejadian");
            focusView = edt_tgl_pelaporan;
            vibe.vibrate(100);
            cancel = true;
        } else if (TextUtils.isEmpty(wkt_kejadian)) {
            edt_waktu_kejadian.setError("Harap tentukan waktu kejadian");
            focusView = edt_waktu_kejadian;
            vibe.vibrate(100);
            cancel = true;
        } else if (TextUtils.isEmpty(wkt_tndk_lnjt)) {
            edt_tindaklanjut.setError("Harap tentukan waktu tindaklanjut");
            focusView = edt_tindaklanjut;
            vibe.vibrate(100);
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            Calendar c = Calendar.getInstance();
            SimpleDateFormat curent_date = new SimpleDateFormat("dd-MM-yyyy");
            String now = curent_date.format(c.getTime());

            isInternetPresent = cd.isConnectingToInternet();
            if (isInternetPresent) {

                ConfirmationDIalog("Apakah anda yakin ?",subject, catatatan, lokasi, now, tgl_kjadian, wkt_kejadian,Username, wkt_tndk_lnjt, level).show();
//                sendReport(subject, catatatan, lokasi, now, tgl_kjadian, wkt_kejadian, wkt_tndk_lnjt, level);


            }else {
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


    }

    private void sendReport(String subject, String catatatan, String lokasi, String now, String tgl_kjadian,String Username, String wkt_kejadian, String wkt_tndk_lnjt, String level) {
//        Toast.makeText(getActivity(), "Ngga kosong hore", Toast.LENGTH_SHORT).show();
        getDialogmuter().show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConstant.APIPARENT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ReportInterface service = retrofit.create(ReportInterface.class);
        Call<IncidentReport> call = service.sendReport(new IncidentReportJSON(subject, catatatan, lokasi, now, tgl_kjadian,Username, wkt_kejadian,wkt_tndk_lnjt,level));
        call.enqueue(new Callback<IncidentReport>() {
            @Override
            public void onResponse(Call<IncidentReport> call, Response<IncidentReport> response) {
                Log.d("response", response.toString());

                if (response.body().getSuccess() == true) {
                    mDialog.dismissWithAnimation();
                    getDialog("Laporan kejadian berhasil dikirim").show();
                } else {
                    mDialog.dismissWithAnimation();
                    getDialogError("Terjadi Kesalahan, silakan Ulang kembali").show();
                }
            }

            @Override
            public void onFailure(Call<IncidentReport> call, Throwable t) {
                android.util.Log.d("onFailure", t.toString());
                getDialogFailure("Terjadi kesalahan silakan coba lagi").show();
            }
        });

    }


    private void updateLabelTindakLanjut() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edt_tindaklanjut.setText(sdf.format(calendar_tindaklanjut.getTime()));

    }

    private void updateLabelpelaporan() {

        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edt_tgl_pelaporan.setText(sdf.format(calendar_kejadian.getTime()));
    }

    private SweetAlertDialog getDialog(String s) {
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
        mDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
        mDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                mDialog.dismiss();
            }
        });


        return mDialog;
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

    private SweetAlertDialog ConfirmationDIalog(String s, final String subject, final String catatatan, final String lokasi, final String now, final String tgl_kjadian, final String wkt_kejadian,final String Username, final String wkt_tndk_lnjt, final String level) {
        mDialog = new SweetAlertDialog(getActivity());
        mDialog.setTitleText("BIRISK");
        mDialog.setContentText(s);
        mDialog.setConfirmText("Send");
        mDialog.setCancelText("Cancel");
        mDialog.changeAlertType(SweetAlertDialog.WARNING_TYPE);
        mDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                mDialog.dismiss();
                sendReport(subject, catatatan, lokasi, now, tgl_kjadian, wkt_kejadian, Username, wkt_tndk_lnjt, level);
            }
        });
        mDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                mDialog.dismissWithAnimation();
            }
        });
        return mDialog;
    }
}
