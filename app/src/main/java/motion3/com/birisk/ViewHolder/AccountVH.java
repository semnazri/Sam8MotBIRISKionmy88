package motion3.com.birisk.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import motion3.com.birisk.R;

/**
 * Created by Semmy on 8/3/2017.
 */

public class AccountVH extends RecyclerView.ViewHolder {
    public TextView name, email;

    public AccountVH(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.user_name);
        email = (TextView) itemView.findViewById(R.id.user_email);
    }
}
