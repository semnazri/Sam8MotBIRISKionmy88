package motion3.com.birisk.POJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Semmy on 8/12/2017.
 */

public interface UserInterface {

    @GET("user-details.php")
    Call<User> getUserDetail(@Query("e") String e,@Query("p") String p);

    @GET("users.php")
    Call<UserList>getUserList();
}
