package nguyenkhai.dmt.mykhachsan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


import nguyenkhai.dmt.mykhachsan.Activity.DanhsachksActivity;
import nguyenkhai.dmt.mykhachsan.Model.Thanhpho;
import nguyenkhai.dmt.mykhachsan.R;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Thanhpho> arrayListbanner ;

    public BannerAdapter(Context context, ArrayList<Thanhpho> arrayListbanner) {
        this.context = context;
        this.arrayListbanner = arrayListbanner;
    }

    @Override
    public int getCount() {
        return arrayListbanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner,null);
        ImageView imagebackgroundbanner = view.findViewById(R.id.imageviewbackgroundbanner);


        TextView txttitlesongbanner = view.findViewById(R.id.textviewtitleloaiphong);
        Picasso.with(context).load(arrayListbanner.get(position).getUrlHinh()).into(imagebackgroundbanner);

        txttitlesongbanner.setText(arrayListbanner.get(position).getTenTP());
   //     txtmotasongbanner.setText(arrayListbanner.get(position).getTenKS());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhsachksActivity.class);
                intent.putExtra("banner",arrayListbanner.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
