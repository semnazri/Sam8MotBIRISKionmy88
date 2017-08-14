package motion3.com.birisk.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import motion3.com.birisk.MainActivity;
import motion3.com.birisk.R;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 6/21/17.
 *
 * @copyright 2016
 * PT.Bisnis Indonesia Sibertama
 */

public class Fragment_incidentReport extends Fragment {

    private View view;
    private Vibrator vibe;
    private RadioGroup radioGroup;
    private RadioButton rB1;
    private Button btn_submit;
    private EditText edt_tgl_pelaporan, edt_waktu_kejadian, edt_tindaklanjut;
    Calendar calendar_kejadian = Calendar.getInstance();
    Calendar calendar_tindaklanjut = Calendar.getInstance();
    Calendar calendar_waktu_kejadian = Calendar.getInstance();
    Calendar calendar_waktu_lapor = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.incident_report, container, false);
        MainActivity.iv.setVisibility(View.VISIBLE);
        MainActivity.logo.setVisibility(View.GONE);
        MainActivity.iv.setImageResource(R.drawable.report);

        radioGroup = (RadioGroup) view.findViewById(R.id.radio_grup);
//        rB1 = (RadioButton) view.findViewById(R.id.level1);
//        rB2 = (RadioButton) view.findViewById(R.id.level2);
//        rB3 = (RadioButton) view.findViewById(R.id.level3);
//        rB1.setChecked(true);

        edt_tgl_pelaporan = (EditText) view.findViewById(R.id.edt_tgl_pelaporan);
        edt_waktu_kejadian = (EditText) view.findViewById(R.id.edt_wkt_kejadian);
        edt_tindaklanjut = (EditText) view.findViewById(R.id.edt_tgl_tindaklanjut);
        btn_submit = (Button) view.findViewById(R.id.btn_submit);

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
                Log.d("now", String.valueOf(sdf.format(calendar_kejadian.getTime())));

                int selectedId = radioGroup.getCheckedRadioButtonId();
                rB1 = (RadioButton) view.findViewById(selectedId);

                Log.d("radio", String.valueOf(rB1.getText()));

            }
        });


        return view;
    }

    private void updateLabelTindakLanjut() {
        String myFormat = "MM-dd-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edt_tindaklanjut.setText(sdf.format(calendar_tindaklanjut.getTime()));

    }

    private void updateLabelpelaporan() {

        String myFormat = "MM-dd-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edt_tgl_pelaporan.setText(sdf.format(calendar_kejadian.getTime()));
    }
}
