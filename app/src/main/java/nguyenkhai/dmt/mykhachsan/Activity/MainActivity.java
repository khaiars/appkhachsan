package nguyenkhai.dmt.mykhachsan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


import nguyenkhai.dmt.mykhachsan.Adapter.MainViewPagerAdapter;
import nguyenkhai.dmt.mykhachsan.Fragment.Fragment_Tim_Kiem;
import nguyenkhai.dmt.mykhachsan.Fragment.Fragment_Trang_Chu;
import nguyenkhai.dmt.mykhachsan.R;
import nguyenkhai.dmt.mykhachsan.Service.APIService;
import nguyenkhai.dmt.mykhachsan.Service.DataService;
import nguyenkhai.dmt.mykhachsan.Session.SessionManager;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;


   // Button logout;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        init();
      //  logout = (Button) findViewById(R.id.btnlogout);

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //sessionManager.SetLogin(false);
//                Intent intent1 =new Intent(MainActivity.this,MainActivity.class);
//                startActivity(intent1);
//                finish();
//            }
//        });

    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(),"trang chủ");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"tìm kiếm");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.icontimkiem);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);


    }

    private void AnhXa(){
         tabLayout =(TabLayout) findViewById(R.id.myTabLayout);
         viewPager =(ViewPager) findViewById(R.id.myViewPager);

    }
}
