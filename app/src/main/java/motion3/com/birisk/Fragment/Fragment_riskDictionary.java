package motion3.com.birisk.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import motion3.com.birisk.MainActivity;
import motion3.com.birisk.Network.APIConstant;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_risk_dictionary, container, false);
        MainActivity.iv.setVisibility(View.VISIBLE);
        MainActivity.iv.setImageResource(R.drawable.risk_dictionary);
        MainActivity.logo.setVisibility(View.GONE);

        title = (TextView) view.findViewById(R.id.title);
        desc = (TextView) view.findViewById(R.id.text_dictionary);


        loadDictionary();
        return view;
    }

    private void loadDictionary() {

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
                        title.setText(response.body().getRecords().get(i).getDName());
                        desc.setText(response.body().getRecords().get(i).getDDesc());

                    }


                }
            }

            @Override
            public void onFailure(Call<Dictionary> call, Throwable t) {

            }
        });
    }
}
