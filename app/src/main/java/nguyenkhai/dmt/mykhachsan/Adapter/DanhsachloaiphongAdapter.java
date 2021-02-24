package nguyenkhai.dmt.mykhachsan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


import nguyenkhai.dmt.mykhachsan.Activity.DatphongActivity;
import nguyenkhai.dmt.mykhachsan.Model.Loaiphong;
import nguyenkhai.dmt.mykhachsan.R;

public class DanhsachloaiphongAdapter  extends RecyclerView.Adapter<DanhsachloaiphongAdapter.ViewHoder>{
    Context context;
    ArrayList<Loaiphong> mangloaiphong;

    public DanhsachloaiphongAdapter(Context context, ArrayList<Loaiphong> mangloaiphong) {
        this.context = context;
        this.mangloaiphong = mangloaiphong;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_loai_phong,parent,false);
        return new ViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        Loaiphong loaiphong = mangloaiphong.get(position);
        holder.txttenloaiphong.setText(loaiphong.getTenLP());
        holder.txtgia.setText(loaiphong.getGia()+ " VNĐ/ đêm");
        Picasso.with(context).load(mangloaiphong.get(position).getHinhLP()).into(holder.imgbackgroundloaiphong);
        Picasso.with(context).load(mangloaiphong.get(position).getHinhLP()).into(holder.imgaloaiphong);
    }


    @Override
    public int getItemCount() {
        return mangloaiphong.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{
        TextView txttenloaiphong;
        ImageView imgaloaiphong;
        ImageView imgbackgroundloaiphong;
        TextView txtgia;
        Button button;

        public ViewHoder(@NonNull final View itemView) {
            super(itemView);
            txttenloaiphong = itemView.findViewById(R.id.textviewtenloaiphong);
            imgbackgroundloaiphong = itemView.findViewById(R.id.imageviewbackgroundloaiphong);
            imgaloaiphong = itemView.findViewById(R.id.imageviewloaiphong);
            txtgia = itemView.findViewById(R.id.textviewgia);
            button = itemView.findViewById(R.id.datphong);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DatphongActivity.class);
                    intent.putExtra("datphong",mangloaiphong.get(getPosition()));
                    context.startActivity(intent);

                }
            });
        }
    }
}
