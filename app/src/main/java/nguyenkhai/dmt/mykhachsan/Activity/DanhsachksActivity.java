package nguyenkhai.dmt.mykhachsan.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import nguyenkhai.dmt.mykhachsan.Adapter.DanhsachkhachsanAdapter;
import nguyenkhai.dmt.mykhachsan.Model.Khachsan;
import nguyenkhai.dmt.mykhachsan.Model.Thanhpho;
import nguyenkhai.dmt.mykhachsan.R;
import nguyenkhai.dmt.mykhachsan.Service.APIService;
import nguyenkhai.dmt.mykhachsan.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachksActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachkhachsan;
    ImageView imageViewdanhsachkhachsan;
    ArrayList<Khachsan> mangkhachsan;
    DanhsachkhachsanAdapter danhsachkhachsanAdapter;
    Thanhpho thanhpho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachks);
        DataIntent();
        AnhXa();
        init();
        if(thanhpho !=null && !thanhpho.getId().equals("")){
            setVaLueInView(thanhpho.getTenTP(),thanhpho.getUrlHinh());
            GetDataThanhPho(thanhpho.getId());
        }
    }

    private void DataIntent() {
        Intent intent =getIntent();
        if(intent !=null){
            if (intent.hasExtra("banner")){
                thanhpho = (Thanhpho) intent.getSerializableExtra("banner");
            }

        }
    }

    private void AnhXa() {
        coordinatorLayout =  findViewById(R.id.coordinatordanhsachkhachsan);
        collapsingToolbarLayout =findViewById(R.id.collapsingtoolbar);
        toolbar =findViewById(R.id.toolbardanhsach);
        recyclerViewdanhsachkhachsan =findViewById(R.id.recycleviewkhachsan);
        imageViewdanhsachkhachsan = findViewById(R.id.imageviewdanhsachkhachsan);
    }

    private void setVaLueInView(String tenTP, String urlHinh) {
        collapsingToolbarLayout.setTitle(tenTP);
        try {
            URL url =new URL(urlHinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);

            collapsingToolbarLayout.setBackground(bitmapDrawable);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(urlHinh).into(imageViewdanhsachkhachsan);
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

    private void GetDataThanhPho(String idthanhpho) {
        DataService dataService = APIService.getService();
        Call<List<Khachsan>> callback = dataService.GetDanhsachkhachsantheothanhpho(idthanhpho);
        callback.enqueue(new Callback<List<Khachsan>>() {
            @Override
            public void onResponse(Call<List<Khachsan>> call, Response<List<Khachsan>> response) {
                mangkhachsan = (ArrayList<Khachsan>) response.body();
                danhsachkhachsanAdapter = new DanhsachkhachsanAdapter(DanhsachksActivity.this,mangkhachsan);
                recyclerViewdanhsachkhachsan.setLayoutManager(new LinearLayoutManager(DanhsachksActivity.this));
                recyclerViewdanhsachkhachsan.setAdapter((danhsachkhachsanAdapter));
            }

            @Override
            public void onFailure(Call<List<Khachsan>> call, Throwable t) {

            }
        });
    }
}
