package motion3.com.birisk.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ricky on 8/24/17.
 */

public class LostResult {

    @SerializedName("changed")
    @Expose
    private String changed;
    @SerializedName("exists")
    @Expose
    private String exists;

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    public String getExists() {
        return exists;
    }

    public void setExists(String exists) {
        this.exists = exists;
    }
}
