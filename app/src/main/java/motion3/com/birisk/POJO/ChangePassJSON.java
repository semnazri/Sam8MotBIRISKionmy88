package motion3.com.birisk.POJO;

/**
 * Created by Ricky on 8/24/17.
 */

public class ChangePassJSON {
    private String un, ps;
    public ChangePassJSON(String un, String ps){
        this.un = un;
        this.ps = ps;
    }

    /**
     *
     * @return
     * The un
     */
    public String getUn() {
        return un;
    }

    /**
     *
     * @param un
     * The un
     */
    public void setUn(String un) {
        this.un = un;
    }

    /**
     *
     * @return
     * The ps
     */
    public String getPs() {
        return ps;
    }

    /**
     *
     * @param ps
     * The ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }
}
