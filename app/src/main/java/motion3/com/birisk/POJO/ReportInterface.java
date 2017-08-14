package motion3.com.birisk.POJO;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Ricky on 8/14/17.
 */

public interface ReportInterface {

    @FormUrlEncoded
    @POST("report.php")
    Call<ResponseBody> sendReport(@Field("Contains") String contains);
}
