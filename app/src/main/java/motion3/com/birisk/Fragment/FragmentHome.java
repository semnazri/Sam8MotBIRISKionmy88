package motion3.com.birisk.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import motion3.com.birisk.MainActivity;
import motion3.com.birisk.R;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 5/25/17.
 *
 * @copyright 2016
 * PT.Bisnis Indonesia Sibertama
 */

public class FragmentHome extends Fragment {

    private View view;
    private ImageView ll_risk_dict,ll_incident,ll_dashboard, ll_risk_repo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_frament, container, false);
        MainActivity.logo.setVisibility(View.VISIBLE);
        MainActivity.iv.setVisibility(View.GONE);

        ll_risk_dict = (ImageView) view.findViewById(R.id.risk_dictionary);
        ll_incident = (ImageView) view.findViewById(R.id.incident_report);
        ll_dashboard = (ImageView) view.findViewById(R.id.risk_dashbaord);
        ll_risk_repo = (ImageView) view.findViewById(R.id.risk_repository);

//        MainActivity.iv.setVisibility(View.GONE);

        ll_incident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Fragment fragment = new Fragment_incidentReport();
                fragment.setArguments(bundle);

                FragmentManager fm = (getActivity()).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container_body, fragment,"home").addToBackStack("home").commit();
            }
        });

        ll_risk_dict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Fragment fragment = new Fragment_riskDictionary();
                fragment.setArguments(bundle);

                FragmentManager fm = (getActivity()).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container_body, fragment,"home").addToBackStack("home").commit();
            }
        });

        ll_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Fragment fragment = new FragmentRiskDashboard();
                fragment.setArguments(bundle);

                FragmentManager fm = (getActivity()).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container_body, fragment,"home").addToBackStack("home").commit();
            }
        });

        ll_risk_repo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Fragment fragment = new FragmentRiskRepository();
                fragment.setArguments(bundle);

                FragmentManager fm = (getActivity()).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container_body, fragment,"home").addToBackStack("home").commit();
            }
        });

        return view;
    }
}
