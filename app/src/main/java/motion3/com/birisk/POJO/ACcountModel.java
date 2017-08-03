package motion3.com.birisk.POJO;

/**
 * Created by Semmy on 8/3/2017.
 */

public class ACcountModel {
    String username, email;

    public ACcountModel(){};

    public ACcountModel(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
