package motion3.com.birisk.POJO;

/**
 * Created by Ricky on 8/22/17.
 */

public class IncidentReportJSON {
    private String subyek,catatan,lokasi,tanggallapor,tanggalevent,waktuevent,tanggalaction,levelevent;

    public IncidentReportJSON(String subyek,String catatan, String lokasi, String tanggallapor,String tanggalevent,String waktuevent,String tanggalaction,String levelevent){
        this.subyek = subyek;
        this.catatan = catatan;
        this.lokasi = lokasi;
        this.tanggallapor = tanggallapor;
        this.tanggalevent = tanggalevent;
        this.waktuevent = waktuevent;
        this.tanggalaction = tanggalaction;
        this.levelevent = levelevent;
    }

    /**
     *
     * @return
     * The subyek
     */
    public String getSubyek() {
        return subyek;
    }

    /**
     *
     * @param subyek
     * The subyek
     */
    public void setSubyek(String subyek) {
        this.subyek = subyek;
    }


    /**
     *
     * @return
     * The catatan
     */
    public String getCatatan() {
        return catatan;
    }

    /**
     *
     * @param catatan
     * The catatan
     */
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    /**
     *
     * @return
     * The lokasi
     */
    public String getLokasi() {
        return lokasi;
    }

    /**
     *
     * @param lokasi
     * The lokasi
     */
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    /**
     *
     * @return
     * The tanggallapor
     */
    public String getTanggallapor() {
        return tanggallapor;
    }

    /**
     *
     * @param tanggallapor
     * The tanggallapor
     */
    public void setTanggallapor(String tanggallapor) {
        this.tanggallapor = tanggallapor;
    }

    /**
     *
     * @return
     * The tanggalevent
     */
    public String getTanggalevent() {
        return tanggalevent;
    }

    /**
     *
     * @param tanggalevent
     * The tanggalevent
     */
    public void setTanggalevent(String tanggalevent) {
        this.tanggalevent = tanggalevent;
    }

    /**
     *
     * @return
     * The waktuevent
     */
    public String getWaktuevent() {
        return waktuevent;
    }

    /**
     *
     * @param waktuevent
     * The waktuevent
     */
    public void setWaktuevent(String waktuevent) {
        this.waktuevent = waktuevent;
    }

    /**
     *
     * @return
     * The tanggalaction
     */
    public String getTanggalaction() {
        return tanggalaction;
    }

    /**
     *
     * @param tanggalaction
     * The tanggalaction
     */
    public void setTanggalaction(String tanggalaction) {
        this.tanggalaction = tanggalaction;
    } /**
     *
     * @return
     * The levelevent
     */
    public String getLevelevent() {
        return levelevent;
    }

    /**
     *
     * @param levelevent
     * The levelevent
     */
    public void setLevelevent(String levelevent) {
        this.levelevent = levelevent;
    }




}
