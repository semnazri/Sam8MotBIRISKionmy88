package motion3.com.birisk.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import motion3.com.birisk.POJO.ACcountModel;
import motion3.com.birisk.POJO.Dummy_model;
import motion3.com.birisk.R;
import motion3.com.birisk.ViewHolder.AccountVH;
import motion3.com.birisk.ViewHolder.DummyViewHolder;

/**
 * Created by Semmy on 8/3/2017.
 */

public class Account_ListADapter extends RecyclerView.Adapter<AccountVH> {
    private Context mContext;
    private List<ACcountModel> mValues;

    public Account_ListADapter(Context context, List<ACcountModel> items) {
        mContext = context;
        mValues = items;
    }



    @Override
    public AccountVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
                return new AccountVH(view);
    }

    @Override
    public void onBindViewHolder(AccountVH holder, int position) {
        holder.name.setText(mValues.get(position).getUsername());
        holder.email.setText(mValues.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
