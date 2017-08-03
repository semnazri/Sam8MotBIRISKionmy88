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
import java.util.List;

import motion3.com.birisk.Adapter.Account_ListADapter;
import motion3.com.birisk.Adapter.DummyAdapter;
import motion3.com.birisk.MainActivity;
import motion3.com.birisk.POJO.ACcountModel;
import motion3.com.birisk.POJO.Dummy_model;
import motion3.com.birisk.R;

/**
 * Created by Semmy on 8/3/2017.
 */

public class Fragment_accountList extends Fragment {
    View view;
    private RecyclerView rv;
    private Account_ListADapter adapter;
    private LinearLayoutManager lm;
    private List<ACcountModel> list_model;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.account_list, container, false);
        MainActivity.iv.setVisibility(View.VISIBLE);
        MainActivity.iv.setImageResource(R.drawable.risk_repository);
        MainActivity.logo.setVisibility(View.GONE);
        LinearLayout parent = (LinearLayout) view.findViewById(R.id.parent);
        setupUI(parent);

        rv = (RecyclerView) view.findViewById(R.id.rv_account);
        list_model = getAllAccount();
        lm = new LinearLayoutManager(getActivity());
        adapter = new Account_ListADapter(getActivity(), list_model);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
//        rv.addItemDecoration(new DividerItemDecoration(getActivity()));
        rv.setAdapter(adapter);
        edt = (EditText) view.findViewById(R.id.edt_search);

        return view;
    }

    private List<ACcountModel> getAllAccount() {

        List<ACcountModel> allNews = new ArrayList<ACcountModel>();
        allNews.add(new ACcountModel("Anindya", "Anindya@dummy.com"));
        allNews.add(new ACcountModel("Berry Charlse", "Berry@dummy.com"));
        allNews.add(new ACcountModel("Cupcake shinoda", "Cupcake@dummy.com"));
        allNews.add(new ACcountModel("Dounut extreme sweet", "Dounut@dummy.com"));
        allNews.add(new ACcountModel("Eclair Yoshiki", "Eclair@dummy.com"));
        allNews.add(new ACcountModel("Frozen Kitamura Yoghurt", "Frozen@dummy.com"));
        allNews.add(new ACcountModel("Gingger Bread", "Gingger@dummy.com"));
        allNews.add(new ACcountModel("Honney Comb", "Honney@dummy.com"));
        allNews.add(new ACcountModel("Ice Cream Sandwich", "Ice@dummy.com"));
        allNews.add(new ACcountModel("Jelly Bean", "Jelly@dummy.com"));
        allNews.add(new ACcountModel("Kitkat Crunch", "Kitkat@dummy.com"));
        allNews.add(new ACcountModel("Lolipop candy", "Lolipop@dummy.com"));
        allNews.add(new ACcountModel("Marshmellow Baked", "Marshmellow@dummy.com"));
        allNews.add(new ACcountModel("Nougat Low Fat", "Nougat@dummy.com"));

        return allNews;

    }

}
