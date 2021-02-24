package nguyenkhai.dmt.mykhachsan.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import nguyenkhai.dmt.mykhachsan.Activity.LoginActivity;
import nguyenkhai.dmt.mykhachsan.Activity.MainActivity;
import nguyenkhai.dmt.mykhachsan.R;
import nguyenkhai.dmt.mykhachsan.Session.SessionManager;

public class Fragment_Trang_Chu extends Fragment {
    View view;
    Button btnLogout;
    SessionManager sessionManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trang_chu,container,false);
        anhXa();
        return view;
    }

    private void anhXa() {
        btnLogout = view.findViewById(R.id.btnlogout);

        sessionManager = new SessionManager(getActivity());
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.deteleData();
                Toast.makeText(getActivity(),"Đăng xuất thành công!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }


}
