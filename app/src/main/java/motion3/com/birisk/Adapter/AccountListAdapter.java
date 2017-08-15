package motion3.com.birisk.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import motion3.com.birisk.POJO.Record;
import motion3.com.birisk.R;
import motion3.com.birisk.ViewHolder.AccountListViewHolder;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 8/14/17.
 *
 * @copyright 2017
 *
 */

public class AccountListAdapter extends RecyclerView.Adapter<AccountListViewHolder> {

    private Context mContext;
    private List<Record> mValues;

    public AccountListAdapter(Context context, List<Record> items) {
        mContext = context;
        mValues = items;
    }

    @Override
    public AccountListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
        return new AccountListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AccountListViewHolder holder, int position) {

        holder.uid.setText(mValues.get(position).getUId());
        holder.uname.setText(mValues.get(position).getUName());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
