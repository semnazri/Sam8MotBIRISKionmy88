package motion3.com.birisk.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ricky on 8/22/17.
 */

public class IncidentReportData {
    @SerializedName("transactionid")
    @Expose
    private String transactionid;
    @SerializedName("messageid")
    @Expose
    private String messageid;

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

}
