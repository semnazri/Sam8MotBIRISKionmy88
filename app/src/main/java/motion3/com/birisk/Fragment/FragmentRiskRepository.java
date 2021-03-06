package motion3.com.birisk.Fragment;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import motion3.com.birisk.Adapter.RiskAdapter;
import motion3.com.birisk.MainActivity;
import motion3.com.birisk.Network.APIConstant;
import motion3.com.birisk.Network.ConnectionDetector;
import motion3.com.birisk.Network.DownloadListener;
import motion3.com.birisk.POJO.DictRecord;
import motion3.com.birisk.POJO.Dummy_model;
import motion3.com.birisk.POJO.RIskRecord;
import motion3.com.birisk.POJO.Risk;
import motion3.com.birisk.POJO.RiskInterface;
import motion3.com.birisk.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 6/22/17.
 *
 * @copyright 2017
 */

public class FragmentRiskRepository extends Fragment implements DownloadListener {
    View view;
    DownloadListener listener;
    private RecyclerView rv;
    private RiskAdapter adapter;
    private LinearLayoutManager lm;
    //    private List<Dummy_model> list_model;
    private List<RIskRecord> list_model;
    private ConnectionDetector cd;
    private Boolean isInternetPresent = false;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cd = new ConnectionDetector(getActivity());
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
        MainActivity.iv.setImageResource(R.drawable.title_knowladge);
        MainActivity.logo.setVisibility(View.GONE);
        CoordinatorLayout parent = (CoordinatorLayout) view.findViewById(R.id.parent);
        setupUI(parent);

        rv = (RecyclerView) view.findViewById(R.id.rv_download);
//        list_model = getAllDownload();
//        lm = new LinearLayoutManager(getActivity());
////        adapter = new DummyAdapter(getActivity(), list_model);
//        rv.setHasFixedSize(true);
//        rv.setLayoutManager(lm);
////        rv.addItemDecoration(new DividerItemDecoration(getActivity()));
//        rv.setAdapter(adapter);
        edt = (EditText) view.findViewById(R.id.edt_search);
        edt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        loadrepository();


        return view;
    }




    private void loadrepository() {

        list_model = new ArrayList<>();
        rv.setHasFixedSize(true);
        lm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConstant.APIPARENT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RiskInterface service = retrofit.create(RiskInterface.class);
        Call<Risk> call = service.getRiskRepository();
        call.enqueue(new Callback<Risk>() {
            @Override
            public void onResponse(Call<Risk> call, Response<Risk> response) {

                int response_status = response.body().getHasMore();

                Log.d("ada", String.valueOf(response_status));

                if (response_status > 0) {
                    for (int i = 0; i < response.body().getRecords().size(); i++) {

                        RIskRecord rec = new RIskRecord();
                        try {
                            String desc = response.body().getRecords().get(i).getRDesc();
                            String id = response.body().getRecords().get(i).getRId();
                            String name = response.body().getRecords().get(i).getRName();
                            String url = response.body().getRecords().get(i).getRUrl();

                            rec.setRDesc(desc);
                            rec.setRId(id);
                            rec.setRName(name);
                            rec.setRUrl(url);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        list_model.add(rec);

                        adapter = new RiskAdapter(getActivity(), list_model, FragmentRiskRepository.this);
                        rv.setAdapter(adapter);

                        addSearchFucntion();

                    }

                }

            }

            @Override
            public void onFailure(Call<Risk> call, Throwable t) {

            }
        });

    }


    @Override
    public void onClick(String url, String title) {
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {
            ((MainActivity) getActivity()).setData(url, title);
            Log.d("url donlot", url);
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                ((MainActivity) getActivity()).Download();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
        } else if (isInternetPresent.equals(false)) {
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
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

        List<RIskRecord> temp = new ArrayList<>();
        for (RIskRecord d: list_model){
            if (d.getRName().contains(text)){
                temp.add(d);
            }
        }
        adapter.updatelist(temp);
    }
}
