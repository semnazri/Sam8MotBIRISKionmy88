package motion3.com.birisk.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ricky on 8/14/17.
 */

public class RIskRecord {

    @SerializedName("r_id")
    @Expose
    private String rId;
    @SerializedName("r_name")
    @Expose
    private String rName;
    @SerializedName("r_desc")
    @Expose
    private String rDesc;
    @SerializedName("r_url")
    @Expose
    private String rUrl;

    public String getRId() {
        return rId;
    }

    public void setRId(String rId) {
        this.rId = rId;
    }

    public String getRName() {
        return rName;
    }

    public void setRName(String rName) {
        this.rName = rName;
    }

    public String getRDesc() {
        return rDesc;
    }

    public void setRDesc(String rDesc) {
        this.rDesc = rDesc;
    }

    public String getRUrl() {
        return rUrl;
    }

    public void setRUrl(String rUrl) {
        this.rUrl = rUrl;
    }

}
