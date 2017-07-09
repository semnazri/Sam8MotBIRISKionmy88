package motion3.com.birisk.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import motion3.com.birisk.Adapter.DummyAdapter;
import motion3.com.birisk.MainActivity;
import motion3.com.birisk.POJO.Dummy_model;
import motion3.com.birisk.R;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 6/22/17.
 *
 * @copyright 2016
 * PT.Bisnis Indonesia Sibertama
 */

public class FragmentRiskRepository extends Fragment {
    View view;
    private RecyclerView rv;
    private DummyAdapter adapter;
    private LinearLayoutManager lm;
    private List<Dummy_model> list_model;
    private EditText edt;
    private TextView.OnEditorActionListener mOnEditorAction =
            new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {

//                        search();
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

//    private void search() {
//        Collections.sort(list_model, new Comparator<Dummy_model>() {
//            @Override
//            public int compare(Dummy_model o1, Dummy_model o2) {
//                return o1.getFile_name().compareTo(o2.getFile_name());
//            }
//        });
//        adapter.notifyDataSetChanged();
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.risk_repository, container, false);
        MainActivity.iv.setVisibility(View.VISIBLE);
//        MainActivity.iv.setImageResource(R.drawable.risk);
        MainActivity.logo.setVisibility(View.GONE);
        LinearLayout parent = (LinearLayout) view.findViewById(R.id.parent);
        setupUI(parent);

        rv = (RecyclerView) view.findViewById(R.id.rv_download);
        list_model = getAllDownload();
        lm = new LinearLayoutManager(getActivity());
        adapter = new DummyAdapter(getActivity(), list_model);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        edt = (EditText) view.findViewById(R.id.edt_search);

        return view;
    }

    private List<Dummy_model> getAllDownload() {
        List<Dummy_model> allNews = new ArrayList<Dummy_model>();
        allNews.add(new Dummy_model(1, "Pertolongan Pertama pada kebakaran", "cara menanggulangi kebakaran kecil"));
        allNews.add(new Dummy_model(2, "Pertolongan Pertama pada kebakaran", "cara menanggulangi kebakaran sedang"));
        allNews.add(new Dummy_model(3, "Pertolongan Pertama pada kebakaran", "cara menanggulangi kebakaran besar"));
        allNews.add(new Dummy_model(4, "Pertolongan Pertama pada Bencana Gempa", "Panduan Prosedur Sigap Bencana Gempa"));
        allNews.add(new Dummy_model(5, "Pertolongan Pertama pada Pemadaman Listrik", "Panduan dummy"));


        return allNews;
    }
}
