package nguyenkhai.dmt.mykhachsan.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Khachsan implements Serializable {

@SerializedName("idKS")
@Expose
private String idKS;
@SerializedName("tenKS")
@Expose
private String tenKS;
@SerializedName("HinhKS")
@Expose
private String hinhKS;

public String getIdKS() {
return idKS;
}

public void setIdKS(String idKS) {
this.idKS = idKS;
}

public String getTenKS() {
return tenKS;
}

public void setTenKS(String tenKS) {
this.tenKS = tenKS;
}

public String getHinhKS() {
    return "http://192.168.1.32/zendne5/images/khachsan/" + hinhKS;
}

public void setHinhKS(String hinhKS) {
this.hinhKS = hinhKS;
}

}