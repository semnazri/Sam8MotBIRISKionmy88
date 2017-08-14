package motion3.com.birisk.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Semmy on 8/12/2017.
 */

public class UserDetail {

    @SerializedName("u_name")
    @Expose
    private String uName;
    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("u_phone")
    @Expose
    private String uPhone;
    @SerializedName("u_address")
    @Expose
    private String uAddress;
    @SerializedName("u_pincode")
    @Expose
    private String uPincode;

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getUPhone() {
        return uPhone;
    }

    public void setUPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getUAddress() {
        return uAddress;
    }

    public void setUAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getUPincode() {
        return uPincode;
    }

    public void setUPincode(String uPincode) {
        this.uPincode = uPincode;
    }

}
