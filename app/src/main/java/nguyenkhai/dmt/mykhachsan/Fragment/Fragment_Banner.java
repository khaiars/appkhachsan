package nguyenkhai.dmt.mykhachsan.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

import nguyenkhai.dmt.mykhachsan.Adapter.BannerAdapter;
import nguyenkhai.dmt.mykhachsan.Model.Thanhpho;
import nguyenkhai.dmt.mykhachsan.R;
import nguyenkhai.dmt.mykhachsan.Service.APIService;
import nguyenkhai.dmt.mykhachsan.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container,false);
        AnhXa();
        getData();

        return view;
    }


    private void AnhXa() {
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.indicatordefault);
    }

    public void getData(){
        DataService dataService = APIService.getService();
        Call<List<Thanhpho>> callback = dataService.getThanhPho();
        callback.enqueue(new Callback<List<Thanhpho>>() {

            @Override
            public void onResponse(Call<List<Thanhpho>> call, Response<List<Thanhpho>> response) {
                ArrayList<Thanhpho> banners = (ArrayList<Thanhpho>) response.body();
              //  Log.d("ccc",banners.get(0).getTenTP());
                bannerAdapter = new BannerAdapter(getActivity(),banners);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem =viewPager.getCurrentItem();
                        currentItem++;
                            if(currentItem>=viewPager.getAdapter().getCount()){
                                currentItem =0;
                            }
                            viewPager.setCurrentItem(currentItem, true);
                            handler.postDelayed(runnable,4500);
                        }
                    };
                handler.postDelayed(runnable,4500);

            }

            @Override
            public void onFailure(Call<List<Thanhpho>> call, Throwable t) {

            }
        });
    }
}
