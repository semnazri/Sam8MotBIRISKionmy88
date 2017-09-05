package motion3.com.birisk.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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

import motion3.com.birisk.Adapter.DictionaryAdapter;
import motion3.com.birisk.MainActivity;
import motion3.com.birisk.Network.APIConstant;
import motion3.com.birisk.POJO.DictRecord;
import motion3.com.birisk.POJO.Dictionary;
import motion3.com.birisk.POJO.DictionaryINterface;
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

public class Fragment_riskDictionary extends Fragment {
    private View view;
    TextView title, desc;
    private RecyclerView recyclerView;
    private DictionaryAdapter adapter;
    private LinearLayoutManager lm;
    private List<DictRecord> listmodel;
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
        view = inflater.inflate(R.layout.fragment_risk_dictionary, container, false);
        MainActivity.iv.setVisibility(View.VISIBLE);
        MainActivity.iv.setImageResource(R.drawable.risk_dictionary);
        MainActivity.logo.setVisibility(View.GONE);
        CoordinatorLayout parent = (CoordinatorLayout) view.findViewById(R.id.parent);
        setupUI(parent);
        edt = (EditText) view.findViewById(R.id.edt_search);
        edt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        recyclerView = (RecyclerView) view.findViewById(R.id.dict_rv);



        loadDictionary();
        return view;
    }

    private void loadDictionary() {
        listmodel = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        lm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lm);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConstant.APIPARENT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DictionaryINterface service = retrofit.create(DictionaryINterface.class);
        Call<Dictionary> call = service.getDictionary();
        call.enqueue(new Callback<Dictionary>() {
            @Override
            public void onResponse(Call<Dictionary> call, Response<Dictionary> response) {
                int response_status = response.body().getHasMore();
                if (response_status > 0) {
                    for (int i = 0; i < response.body().getRecords().size(); i++) {
                        DictRecord rec = new DictRecord();
                        try {
                            String name = response.body().getRecords().get(i).getDName();
                            String desc = response.body().getRecords().get(i).getDDesc();

                            rec.setDDesc(desc);
                            rec.setDName(name);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        listmodel.add(rec);
                        adapter = new DictionaryAdapter(getActivity(),listmodel);
                        recyclerView.setAdapter(adapter);
                        
                        addSearchFucntion();
                    }


                }
            }

            @Override
            public void onFailure(Call<Dictionary> call, Throwable t) {

            }
        });
    }

    private void addSearchFucntion() {

        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
//                query = query.toString().toLowerCase();
//
//                final List<DictRecord> listnyadah = new ArrayList<DictRecord>();
//
//                for (int i = 0; i < listmodel.size(); i++) {
//
//                    final String text = listmodel.get(i).getDName().toString().toLowerCase();
//                    if (text.contains(query)) {
//
//                        listnyadah.add(listmodel.get(i));
//                        adapter.notifyDataSetChanged();
//                    }
//                }

                fiter(query.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void fiter(String text) {

        List<DictRecord> temp = new ArrayList<>();
        for (DictRecord d: listmodel){
            if (d.getDName().contains(text)){
                temp.add(d);
            }
        }
        adapter.updatelist(temp);
    }
}
