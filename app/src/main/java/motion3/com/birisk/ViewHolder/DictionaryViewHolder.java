package motion3.com.birisk.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import motion3.com.birisk.R;

/**
 * Created by Ricky on 8/16/17.
 */

public class DictionaryViewHolder extends RecyclerView.ViewHolder{

    public TextView name, desc;
    public DictionaryViewHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.dict_name);
        desc = (TextView) itemView.findViewById(R.id.dict_desc);
    }
}
