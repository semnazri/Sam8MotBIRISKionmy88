package motion3.com.birisk.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ricky on 8/14/17.
 */

public class DictRecord {

    @SerializedName("d_id")
    @Expose
    private String dId;
    @SerializedName("d_name")
    @Expose
    private String dName;
    @SerializedName("d_desc")
    @Expose
    private String dDesc;

    public String getDId() {
        return dId;
    }

    public void setDId(String dId) {
        this.dId = dId;
    }

    public String getDName() {
        return dName;
    }

    public void setDName(String dName) {
        this.dName = dName;
    }

    public String getDDesc() {
        return dDesc;
    }

    public void setDDesc(String dDesc) {
        this.dDesc = dDesc;
    }

}
