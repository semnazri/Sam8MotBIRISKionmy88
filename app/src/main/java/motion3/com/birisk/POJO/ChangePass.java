package motion3.com.birisk.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ricky on 8/24/17.
 */

public class ChangePass {

    @SerializedName("result")
    @Expose
    private ChangePassResult result;

    public ChangePassResult getResult() {
        return result;
    }

    public void setResult(ChangePassResult result) {
        this.result = result;
    }
}
