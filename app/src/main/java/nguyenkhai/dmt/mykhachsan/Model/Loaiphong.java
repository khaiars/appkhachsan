package nguyenkhai.dmt.mykhachsan.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Loaiphong implements Serializable {

@SerializedName("MaLP")
@Expose
private String maLP;
@SerializedName("TenLP")
@Expose
private String tenLP;
@SerializedName("Gia")
@Expose
private String gia;
@SerializedName("urlHinh")
@Expose
private String hinhLP;
    @SerializedName("IdKS")
    @Expose
    private String idks;
public String getMaLP() {
return maLP;
}

public void setMaLP(String maLP) {
this.maLP = maLP;
}

public String getTenLP() {
return tenLP;
}

public void setTenLP(String tenLP) {
this.tenLP = tenLP;
}

public String getGia() {
return gia;
}

public void setGia(String gia) {
this.gia = gia;
}

public String getHinhLP() {
return "http://192.168.1.32/zendne5/images/loaiphong/" + hinhLP;
}

public void setHinhLP(String hinhLP) {
this.hinhLP = hinhLP;
}
    public String getIdks() {
        return idks;
    }

    public void setIdks(String idks) {
        this.idks = idks;
    }

}