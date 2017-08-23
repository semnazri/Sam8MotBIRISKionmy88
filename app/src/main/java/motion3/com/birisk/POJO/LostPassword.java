package motion3.com.birisk.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ricky on 8/24/17.
 */

public class LostPassword {

    @SerializedName("result")
    @Expose
    private LostResult result;

    public LostResult getResult() {
        return result;
    }

    public void setResult(LostResult result) {
        this.result = result;
    }
}
