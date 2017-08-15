package motion3.com.birisk.POJO;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Semmy on 8/14/17.
 */

public interface DictionaryINterface {

    @GET("dictionary.php")
    Call<Dictionary> getDictionary();

}
