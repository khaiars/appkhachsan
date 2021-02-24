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

import nguyenkhai.dmt.mykhachsan.Activity.DanhsachloaiphongActivity;
import nguyenkhai.dmt.mykhachsan.Model.Khachsan;
import nguyenkhai.dmt.mykhachsan.Model.Thanhpho;
import nguyenkhai.dmt.mykhachsan.R;



public class SearchKhachSanAdapter extends RecyclerView.Adapter<SearchKhachSanAdapter.ViewHolder>{

    Context context;
    ArrayList<Khachsan> mangkhachsan;

    public SearchKhachSanAdapter(Context context, ArrayList<Khachsan> mangkhachsan) {
        this.context = context;
        this.mangkhachsan = mangkhachsan;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_search_khach_san,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Khachsan khachsan = mangkhachsan.get(position);

        holder.txttenkhachsan.setText(khachsan.getTenKS());

        Picasso.with(context).load(khachsan.getHinhKS()).into(holder.imgkhachsan);

    }

    @Override
    public int getItemCount() {
        return mangkhachsan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttenkhachsan;

        ImageView imgkhachsan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenkhachsan = itemView.findViewById(R.id.textviewsearchtenkhachsan);

            imgkhachsan = itemView.findViewById(R.id.imageviewSearchkhachsan);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachloaiphongActivity.class);
                    intent.putExtra("itemloaiphong",mangkhachsan.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
