package motion3.com.birisk.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import motion3.com.birisk.R;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 7/9/17.
 *
 * @copyright 2017
 */

public class DummyViewHolder extends RecyclerView.ViewHolder {
    public TextView filename, file_downloadname, downloadlink;

    public DummyViewHolder(View itemView) {
        super(itemView);
        filename = (TextView) itemView.findViewById(R.id.file_name);
        file_downloadname = (TextView) itemView.findViewById(R.id.file_downloadname);
        downloadlink = (TextView) itemView.findViewById(R.id.download_link);

    }
}
