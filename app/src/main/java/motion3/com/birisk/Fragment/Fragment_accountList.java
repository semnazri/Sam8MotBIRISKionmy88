package motion3.com.birisk.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import motion3.com.birisk.Adapter.AccountListAdapter;
import motion3.com.birisk.MainActivity;
import motion3.com.birisk.Network.APIConstant;
import motion3.com.birisk.POJO.ACcountModel;
import motion3.com.birisk.POJO.Record;
import motion3.com.birisk.POJO.UserInterface;
import motion3.com.birisk.POJO.UserList;
import motion3.com.birisk.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Semmy on 8/3/2017.
 */

public class Fragment_accountList extends Fragment {
    View view;
    private RecyclerView rv;
    //    private Account_ListADapter adapter;
    private AccountListAdapter adapter;
    private LinearLayoutManager lm;
    private List<Record> list_model;
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

        MainActivity.iv.setVisibility(View.GONE);
        MainActivity.logo.setVisibility(View.VISIBLE);
        LinearLayout parent = (LinearLayout) view.findViewById(R.id.parent);
        setupUI(parent);
        edt = (EditText) view.findViewById(R.id.edt_search);

        rv = (RecyclerView) view.findViewById(R.id.rv_account);

        getReallAllAccount();

        return view;
    }

    private void getReallAllAccount() {
        list_model = new ArrayList<>();
        rv.setHasFixedSize(true);
        lm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConstant.APIPARENT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInterface service = retrofit.create(UserInterface.class);
        Call<UserList> call = service.getUserList();
        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {

                int response_status = response.body().getHasMore();

                Log.d("ada", String.valueOf(response_status));

                if (response_status > 0) {


                    for (int i = 0; i < response.body().getRecords().size(); i++) {

                        Record rec = new Record();
                        try {
                            String uid = response.body().getRecords().get(i).getUId();
                            String uname = response.body().getRecords().get(i).getUName();

                            rec.setUName(uname);
                            rec.setUId(uid);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        list_model.add(rec);
                        adapter = new AccountListAdapter(getActivity(), list_model);
                        rv.setAdapter(adapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {

            }
        });
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
