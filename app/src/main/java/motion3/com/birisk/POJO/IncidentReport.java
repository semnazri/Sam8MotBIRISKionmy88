package motion3.com.birisk.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ricky on 8/22/17.
 */

public class IncidentReport {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private IncidentReportData data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public IncidentReportData getData() {
        return data;
    }

    public void setData(IncidentReportData data) {
        this.data = data;
    }

}
