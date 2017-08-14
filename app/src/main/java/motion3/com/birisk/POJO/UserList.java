package motion3.com.birisk.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 8/14/17.
 *
 * @copyright 2016
 * PT.Bisnis Indonesia Sibertama
 */

public class UserList {

    @SerializedName("has_more")
    @Expose
    private Integer hasMore;
    @SerializedName("records")
    @Expose
    private List<Record> records = new ArrayList<Record>();

    public Integer getHasMore() {
        return hasMore;
    }

    public void setHasMore(Integer hasMore) {
        this.hasMore = hasMore;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
