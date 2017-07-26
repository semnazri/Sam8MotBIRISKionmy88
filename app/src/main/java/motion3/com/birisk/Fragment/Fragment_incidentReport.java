package motion3.com.birisk.Fragment;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.incident_report, container, false);
        MainActivity.iv.setVisibility(View.VISIBLE);
        MainActivity.logo.setVisibility(View.GONE);
        MainActivity.iv.setImageResource(R.drawable.report);

        return view;
    }
}
