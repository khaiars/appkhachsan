package nguyenkhai.dmt.mykhachsan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


import nguyenkhai.dmt.mykhachsan.Activity.DanhsachksActivity;
import nguyenkhai.dmt.mykhachsan.Activity.DanhsachloaiphongActivity;
import nguyenkhai.dmt.mykhachsan.Model.Khachsan;
import nguyenkhai.dmt.mykhachsan.R;

public class DanhsachkhachsanAdapter extends RecyclerView.Adapter<DanhsachkhachsanAdapter.ViewHolder>{
    Context context;
    ArrayList<Khachsan> mangkhachsan;

    public DanhsachkhachsanAdapter(Context context, ArrayList<Khachsan> mangkhachsan) {
        this.context = context;
        this.mangkhachsan = mangkhachsan;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_khach_san,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Khachsan khachsan = mangkhachsan.get(position);
        holder.txttenkhachsan1.setText(khachsan.getTenKS());
        Picasso.with(context).load(mangkhachsan.get(position).getHinhKS()).into(holder.imgbackgroundkhachsan1);
        Picasso.with(context).load(mangkhachsan.get(position).getHinhKS()).into(holder.imgkhachsan1);
    }
    @Override
    public int getItemCount() {
        return mangkhachsan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttenkhachsan1;
        ImageView imgkhachsan1;
        ImageView imgbackgroundkhachsan1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenkhachsan1 = itemView.findViewById(R.id.textviewtenkhachsan1);
            imgbackgroundkhachsan1 = itemView.findViewById(R.id.imageviewbackgroundkhachsan1);
            imgkhachsan1 = itemView.findViewById(R.id.imageviewkhachsan1);
            imgbackgroundkhachsan1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachloaiphongActivity.class);
                    intent.putExtra("itemloaiphong", mangkhachsan.get(getPosition()));
                    context.startActivity(intent);
                }
            });


        }
    }
}
