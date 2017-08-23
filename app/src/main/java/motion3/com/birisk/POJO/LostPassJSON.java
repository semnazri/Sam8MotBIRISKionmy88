package motion3.com.birisk.POJO;

/**
 * Created by Ricky on 8/24/17.
 */

public class LostPassJSON {
    private String un, em;

    public LostPassJSON(String un, String em){
        this.un = un;
        this.em = em;
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
     * The em
     */
    public String getEm() {
        return em;
    }

    /**
     *
     * @param em
     * The subyek
     */
    public void setEm(String em) {
        this.em = em;
    }
}
