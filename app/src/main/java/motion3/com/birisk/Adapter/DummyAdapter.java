package motion3.com.birisk.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import motion3.com.birisk.POJO.Dummy_model;
import motion3.com.birisk.R;
import motion3.com.birisk.ViewHolder.DummyViewHolder;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 7/9/17.
 *
 * @copyright 2017
 */

public class DummyAdapter extends RecyclerView.Adapter<DummyViewHolder>{
    private Context mContext;
    private List<Dummy_model> mValues;

    public DummyAdapter(Context context, List<Dummy_model> items) {
        mContext = context;
        mValues = items;
    }
    @Override
    public DummyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_risk_repo, parent, false);
        TextView text_download = (TextView) view.findViewById(R.id.file_downloadname);
        text_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Your Download Has Started", Toast.LENGTH_SHORT).show();
            }
        });

        return new DummyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DummyViewHolder holder, int position) {
        holder.filename.setText(mValues.get(position).getFile_name());
        holder.file_downloadname.setText(mValues.get(position).getKeterangan());
        holder.downloadlink.setText(mValues.get(position).getKeterangan());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
