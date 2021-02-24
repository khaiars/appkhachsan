package nguyenkhai.dmt.mykhachsan.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nguyenkhai.dmt.mykhachsan.Adapter.SearchKhachSanAdapter;
import nguyenkhai.dmt.mykhachsan.Model.Khachsan;
import nguyenkhai.dmt.mykhachsan.R;
import nguyenkhai.dmt.mykhachsan.Service.APIService;
import nguyenkhai.dmt.mykhachsan.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    Toolbar toolbar;
    RecyclerView recyclerViewsreach;
    TextView txtkhongcodulieu;
    SearchKhachSanAdapter searchKhachSanAdapter;


    View view;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        toolbar = view.findViewById(R.id.toolbarsreach);
        recyclerViewsreach = view.findViewById(R.id.recycleviewsreach);
        txtkhongcodulieu = view.findViewById(R.id.textviewkhongcodulieu);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.sreach_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_sreach);
        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SreachTuKhoaKhachSan(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void SreachTuKhoaKhachSan(String query){
        DataService dataService = APIService.getService();
        Call<List<Khachsan>> callback = dataService.GetSreachKhachSan(query);

        callback.enqueue(new Callback<List<Khachsan>>() {
            @Override
            public void onResponse(Call<List<Khachsan>> call, Response<List<Khachsan>> response) {
                ArrayList<Khachsan> mangkhachsan = (ArrayList<Khachsan>) response.body();
                if(mangkhachsan.size()>0) {
                    searchKhachSanAdapter = new SearchKhachSanAdapter(getActivity(), mangkhachsan);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewsreach.setLayoutManager(linearLayoutManager);
                    recyclerViewsreach.setAdapter(searchKhachSanAdapter);
                    txtkhongcodulieu.setVisibility(View.GONE);
                    recyclerViewsreach.setVisibility(View.VISIBLE);
                }else{
                    recyclerViewsreach.setVisibility(View.GONE);
                    txtkhongcodulieu.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<List<Khachsan>> call, Throwable t) {

            }
        });
    }
}
