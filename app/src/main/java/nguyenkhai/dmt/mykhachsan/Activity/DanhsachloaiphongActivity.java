package nguyenkhai.dmt.mykhachsan.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import nguyenkhai.dmt.mykhachsan.Adapter.DanhsachloaiphongAdapter;
import nguyenkhai.dmt.mykhachsan.Model.Khachsan;
import nguyenkhai.dmt.mykhachsan.Model.Loaiphong;
import nguyenkhai.dmt.mykhachsan.R;
import nguyenkhai.dmt.mykhachsan.Service.APIService;
import nguyenkhai.dmt.mykhachsan.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachloaiphongActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachloaiphong;

    ArrayList<Loaiphong> mangloaiphong;
    DanhsachloaiphongAdapter danhsachloaiphongAdapter;
    Khachsan khachsan;
    Loaiphong loaiphong;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachloaiphong);

        DataIntent();
        AnhXa();
        init();

        if(khachsan !=null && !khachsan.getTenKS().equals("")){
            setVaLueInView(khachsan.getTenKS(),khachsan.getHinhKS());
            GetDatakhachsan(khachsan.getIdKS());
        }
           if(loaiphong !=null && !loaiphong.getTenLP().equals("")){
               setVaLueInView(loaiphong.getTenLP(),loaiphong.getHinhLP());
               GetDatakhachsan(loaiphong.getMaLP());
           }
    }


    private void AnhXa() {
        coordinatorLayout =  findViewById(R.id.coordinatordanhsachloaiphong);
        collapsingToolbarLayout =findViewById(R.id.collapsingtoolbar);
        toolbar =findViewById(R.id.toolbardanhsach);
        recyclerViewdanhsachloaiphong =findViewById(R.id.recycleviewloaiphong);
        button = findViewById(R.id.datphong);


    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

    }

    private void GetDatakhachsan(String idKS) {
        DataService dataService = APIService.getService();
        Call<List<Loaiphong>> callback = dataService.GetDanhsachloaiphongtheokhachsan(idKS);
        callback.enqueue(new Callback<List<Loaiphong>>() {
            @Override
            public void onResponse(Call<List<Loaiphong>> call, Response<List<Loaiphong>> response) {
                mangloaiphong = (ArrayList<Loaiphong>) response.body();
                danhsachloaiphongAdapter = new DanhsachloaiphongAdapter(DanhsachloaiphongActivity.this,mangloaiphong);
                recyclerViewdanhsachloaiphong.setLayoutManager(new LinearLayoutManager(DanhsachloaiphongActivity.this));
                recyclerViewdanhsachloaiphong.setAdapter((danhsachloaiphongAdapter));
            }

            @Override
            public void onFailure(Call<List<Loaiphong>> call, Throwable t) {

            }
        });
    }

    private void setVaLueInView(String tenKS, String hinhKS)  {
        collapsingToolbarLayout.setTitle(tenKS);
        try {
            URL url =new URL(hinhKS);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);

            collapsingToolbarLayout.setBackground(bitmapDrawable);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("itemloaiphong")) {
                khachsan = (Khachsan) intent.getSerializableExtra("itemloaiphong");
                Toast.makeText(this, khachsan.getTenKS(), Toast.LENGTH_SHORT).show();
            }
            if (intent.hasExtra("itemloaiphong1")) {
                loaiphong = (Loaiphong) intent.getSerializableExtra("itemloaiphong1");
                Toast.makeText(this, loaiphong.getTenLP(), Toast.LENGTH_SHORT).show();
            }

        }
    }
}
