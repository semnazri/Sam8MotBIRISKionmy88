package motion3.com.birisk.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import motion3.com.birisk.R;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 8/14/17.
 *
 * @copyright 2016
 * PT.Bisnis Indonesia Sibertama
 */

public class AccountListViewHolder extends RecyclerView.ViewHolder {
    public TextView uid, uname;
    public AccountListViewHolder(View itemView) {
        super(itemView);

        uid = (TextView) itemView.findViewById(R.id.user_name);
        uname = (TextView) itemView.findViewById(R.id.user_email);
    }
}
