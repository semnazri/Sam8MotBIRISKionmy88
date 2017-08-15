package motion3.com.birisk.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import motion3.com.birisk.POJO.DictRecord;
import motion3.com.birisk.R;
import motion3.com.birisk.ViewHolder.DictionaryViewHolder;

/**
 * Created by Ricky on 8/16/17.
 */

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryViewHolder> {
    private Context mContext;
    private List<DictRecord> mValues;

    public DictionaryAdapter(Context context, List<DictRecord> items) {
        mContext = context;
        mValues = items;
    }

    @Override
    public DictionaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dictionary, parent, false);
        return new DictionaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DictionaryViewHolder holder, int position) {
        holder.desc.setText(mValues.get(position).getDDesc());
        holder.name.setText(mValues.get(position).getDName());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
