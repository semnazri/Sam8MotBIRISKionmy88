package motion3.com.birisk.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Semmy on 8/14/17.
 */

public class Dictionary {

    @SerializedName("has_more")
    @Expose
    private Integer hasMore;
    @SerializedName("records")
    @Expose
    private List<DictRecord> records = null;

    public Integer getHasMore() {
        return hasMore;
    }

    public void setHasMore(Integer hasMore) {
        this.hasMore = hasMore;
    }

    public List<DictRecord> getRecords() {
        return records;
    }

    public void setRecords(List<DictRecord> records) {
        this.records = records;
    }
}
