package nguyenkhai.dmt.mykhachsan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;


import nguyenkhai.dmt.mykhachsan.Activity.DanhsachloaiphongActivity;
import nguyenkhai.dmt.mykhachsan.Model.Khachsan;
import nguyenkhai.dmt.mykhachsan.R;


public class KhachsanAdapter extends ArrayAdapter<Khachsan> {
    public KhachsanAdapter(@NonNull Context context, int resource, @NonNull List<Khachsan> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{

        TextView txttenkhachsan;
        ImageView imgkhachsan;
        ImageView imgbackgroundkhachsan;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_khach_san,null);
            viewHolder = new ViewHolder();
            viewHolder.txttenkhachsan = convertView.findViewById(R.id.textviewtenkhachsan);
            viewHolder.imgbackgroundkhachsan =convertView.findViewById(R.id.imageviewbackgroundkhachsan);
            viewHolder.imgkhachsan = convertView.findViewById(R.id.imageviewkhachsan);
            convertView.setTag(viewHolder);
            }
        else
            {
            viewHolder =(ViewHolder) convertView.getTag();
            }
        final Khachsan khachsan =getItem(position);
        Picasso.with(getContext()).load(khachsan.getHinhKS()).into(viewHolder.imgbackgroundkhachsan);
        Picasso.with(getContext()).load(khachsan.getHinhKS()).into(viewHolder.imgkhachsan);
        viewHolder.txttenkhachsan.setText(khachsan.getTenKS());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DanhsachloaiphongActivity.class);
                intent.putExtra("itemloaiphong",getItem(position));
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
