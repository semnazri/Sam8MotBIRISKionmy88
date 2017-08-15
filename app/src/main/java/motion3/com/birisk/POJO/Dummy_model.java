package motion3.com.birisk.POJO;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 7/9/17.
 *
 * @copyright 2017
 */

public class Dummy_model {

    String file_name, keterangan;
    int id;

    public Dummy_model() {
    }

    public Dummy_model(int id, String filename, String keterangan) {
        this.id = id;
        this.file_name = filename;
        this.keterangan = keterangan;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
