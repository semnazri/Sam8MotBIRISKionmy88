package motion3.com.birisk.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
    private RadioButton rB1,rB2,rB3;
    private EditText edt_tgl_pelaporan;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.incident_report, container, false);
        MainActivity.iv.setVisibility(View.VISIBLE);
        MainActivity.logo.setVisibility(View.GONE);
        MainActivity.iv.setImageResource(R.drawable.report);

        radioGroup = (RadioGroup) view.findViewById(R.id.radio_grup);
        rB1 = (RadioButton) view.findViewById(R.id.level1);
        rB2 = (RadioButton) view.findViewById(R.id.level2);
        rB3 = (RadioButton) view.findViewById(R.id.level3);
        rB1.setChecked(true);

        edt_tgl_pelaporan = (EditText)view.findViewById(R.id.edt_tgl_pelaporan);

        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edt_tgl_pelaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });






        return view;
    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edt_tgl_pelaporan.setText(sdf.format(myCalendar.getTime()));
    }
}
