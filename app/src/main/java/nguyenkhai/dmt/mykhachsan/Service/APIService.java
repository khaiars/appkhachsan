package nguyenkhai.dmt.mykhachsan.Service;

public class APIService {
    //truyen dia chi URL de tuong tac server va cau hinh retrofit
    private  static  String base_url= "http://192.168.1.32/zendne5/public/";
    // tao ra function ket hop
    public static DataService getService(){
        // sau khi tra du lieu ve roi ,khoi tao phuong thuc ben dataservice de gui len
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }

}
