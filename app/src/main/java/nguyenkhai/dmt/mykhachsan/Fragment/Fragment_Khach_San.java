package nguyenkhai.dmt.mykhachsan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


import nguyenkhai.dmt.mykhachsan.Activity.DanhsachloaiphongActivity;
import nguyenkhai.dmt.mykhachsan.Adapter.KhachsanAdapter;
import nguyenkhai.dmt.mykhachsan.Model.Khachsan;
import nguyenkhai.dmt.mykhachsan.R;
import nguyenkhai.dmt.mykhachsan.Service.APIService;
import nguyenkhai.dmt.mykhachsan.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Khach_San extends Fragment {
    View view;
    TextView txttitlekhachsan;
    ListView lvkhachsan;
    TextView txtmorekhachsan;
    ArrayList<Khachsan> khachsans;
    KhachsanAdapter khachsanAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_khach_san,container,false);
        txttitlekhachsan = view.findViewById(R.id.textviewtitlekhachsan);
        lvkhachsan = view.findViewById(R.id.listviewkhachsan);
       // txtmorekhachsan = view.findViewById(R.id.textviewmorekhachsan);
        GetData();
//        txtmorekhachsan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), DanhsachkhachsanActivity.class);
//                startActivity(intent);
//            }
//        });

        return view;
    }



    private void GetData() {
        DataService dataService = APIService.getService();
        final Call<List<Khachsan>> callback = dataService.GetKhachsanCurrentDay();
        callback.enqueue((new Callback<List<Khachsan>>() {
            @Override
            public void onResponse(Call<List<Khachsan>> call, Response<List<Khachsan>> response) {
                khachsans = (ArrayList<Khachsan>) response.body();

                khachsanAdapter = new KhachsanAdapter(getActivity(),android.R.layout.simple_list_item_1, khachsans);
                lvkhachsan.setAdapter(khachsanAdapter);
//                txtmorekhachsan.setText("xem them");
                lvkhachsan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), DanhsachloaiphongActivity.class);
                        intent.putExtra("itemloaiphong",khachsans.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Khachsan>> call, Throwable t) {


            }
        }));
    }
}
