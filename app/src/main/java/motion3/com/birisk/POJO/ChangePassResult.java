package motion3.com.birisk.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ricky on 8/24/17.
 */

public class ChangePassResult {

    @SerializedName("changed")
    @Expose
    private String changed;
    @SerializedName("failed")
    @Expose
    private String failed;

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    public String getFailed() {
        return failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }


}
