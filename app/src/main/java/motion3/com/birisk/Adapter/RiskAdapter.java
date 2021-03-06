package motion3.com.birisk.Adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import motion3.com.birisk.Network.DownloadListener;
import motion3.com.birisk.POJO.RIskRecord;
import motion3.com.birisk.R;
import motion3.com.birisk.ViewHolder.DummyViewHolder;

/**
 * Created by Semmy on 8/14/17.
 */

public class RiskAdapter extends RecyclerView.Adapter<DummyViewHolder> {

    private Context mContext;
    private List<RIskRecord> mValues;
    private DownloadListener listener;

    public RiskAdapter(Context context, List<RIskRecord> items,DownloadListener listener) {
        mContext = context;
        mValues = items;
        this.listener = listener;

    }

    @Override
    public DummyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_risk_repo, parent, false);
//        TextView text_download = (TextView) view.findViewById(R.id.file_downloadname);
//        text_download.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                TextView tv = (TextView) v.findViewById(R.id.download_link);
//
//                String url = tv.getText().toString();
//
//                Log.d("ada", url);
////                Toast.makeText(mContext, url, Toast.LENGTH_SHORT).show();
//            }
//        });

        return new DummyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DummyViewHolder holder, int position) {
        holder.filename.setText(mValues.get(position).getRName());
        holder.file_downloadname.setText(mValues.get(position).getRDesc());
        holder.downloadlink.setText(mValues.get(position).getRUrl());


        holder.file_downloadname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dlink = holder.downloadlink.getText().toString();
//                Toast.makeText(mContext, dlink, Toast.LENGTH_SHORT).show();
                String fname = holder.filename.getText().toString();
                listener.onClick(dlink, fname);

                Toast.makeText(mContext, "Your download has been started", Toast.LENGTH_SHORT).show();
//                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(dlink));
//                request.setTitle(fname);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//                    request.allowScanningByMediaScanner();
//                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                }
//                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fname);
//
//                DownloadManager manager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
//                manager.enqueue(request);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updatelist(List<RIskRecord> list){
        mValues = list;
        notifyDataSetChanged();
    }
}
