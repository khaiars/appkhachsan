package nguyenkhai.dmt.mykhachsan.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Thanhpho implements Serializable {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("TenTP")
    @Expose
    private String tenTP;
    @SerializedName("urlHinh")
    @Expose
    private String urlHinh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenTP() {
        return tenTP;
    }

    public void setTenTP(String tenTP) {
        this.tenTP = tenTP;
    }

    public String getUrlHinh() {
        return "http://192.168.1.32/zendne5/images/thanhpho/" + urlHinh;
    }

    public void setUrlHinh(String urlHinh) {
        this.urlHinh = urlHinh;
    }

}