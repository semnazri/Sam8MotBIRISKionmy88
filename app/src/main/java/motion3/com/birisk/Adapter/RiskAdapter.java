package motion3.com.birisk.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import motion3.com.birisk.POJO.RIskRecord;
import motion3.com.birisk.R;
import motion3.com.birisk.ViewHolder.DummyViewHolder;

/**
 * Created by Ricky on 8/14/17.
 */

public class RiskAdapter extends RecyclerView.Adapter<DummyViewHolder> {

    private Context mContext;
    private List<RIskRecord> mValues;

    public RiskAdapter(Context context, List<RIskRecord> items) {
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
        holder.filename.setText(mValues.get(position).getRName());
        holder.file_downloadname.setText(mValues.get(position).getRDesc());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
