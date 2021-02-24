package nguyenkhai.dmt.mykhachsan.Service;

import android.text.GetChars;

import java.util.List;


import nguyenkhai.dmt.mykhachsan.Model.Khachsan;
import nguyenkhai.dmt.mykhachsan.Model.Loaiphong;
import nguyenkhai.dmt.mykhachsan.Model.Thanhpho;
import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


// gui phuong thuc  va nhan  du lieu tra ve
public interface DataService {
    @GET("thanhpho/get")
    Call<List<Thanhpho>> getThanhPho();

    @GET("khachsan/getday")
    Call<List<Khachsan>> GetKhachsanCurrentDay();


    @FormUrlEncoded
    @POST("khachsan/getdanhsach")
    Call<List<Khachsan>> GetDanhsachkhachsantheothanhpho(@Field("idthanhpho") String idthanhpho);

    @FormUrlEncoded
    @POST("lp/getdanhsach")
    Call<List<Loaiphong>> GetDanhsachloaiphongtheokhachsan(@Field("idks") String idks);

    @FormUrlEncoded
    @POST("khachhang/register")
    Call<ResponseBody> CreatUser(@Field("Username") String Username, @Field("Password") String Password,@Field("Email") String Email);

    @FormUrlEncoded
    @POST("khachhang/login")
    Call<ResponseBody> login(@Field("Username") String Username, @Field("Password") String Password);

    @FormUrlEncoded
    @POST("dangkydatphong/datphong")
    Call<ResponseBody> datphong(@Field("tenkh") String tenkh, @Field("ngayo") String ngayo, @Field("Ngaydi") String ngaydi, @Field("MaLP") String  malp);

    @FormUrlEncoded
    @POST("khachsan/timkiem")
    Call<List<Khachsan>> GetSreachKhachSan(@Field("tukhoa") String tukhoa);

}
