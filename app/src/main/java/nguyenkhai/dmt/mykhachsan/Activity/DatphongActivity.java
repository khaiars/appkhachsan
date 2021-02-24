package nguyenkhai.dmt.mykhachsan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import nguyenkhai.dmt.mykhachsan.Model.Loaiphong;
import nguyenkhai.dmt.mykhachsan.R;
import nguyenkhai.dmt.mykhachsan.Service.APIService;
import nguyenkhai.dmt.mykhachsan.Service.DataService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatphongActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private EditText edtngayo;
    private EditText edtngaydi;
    private int year, month, day;
    private EditText edttenkh;
    private Button btnDatphong;
    private Button btnBack;
    private ProgressDialog pDialog;
    Loaiphong loaiphong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datphong);
        getData();
        addControls();
     //   addEvents();
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate1(year, month + 1, day);
        showDate2(year, month +1,day);
    }

    private void addEvents() {
        btnDatphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get data input
                String tenkh = edttenkh.getText().toString().trim();
                String ngayo = edtngaydi.getText().toString().trim();
                String ngaydi = edtngaydi.getText().toString().trim();

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatphongActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        edtngayo = (EditText) findViewById(R.id.txtngayo);
        edtngaydi = (EditText) findViewById(R.id.txtngaydi);
        edttenkh = (EditText) findViewById(R.id.edittenkh);
        btnDatphong = (Button) findViewById(R.id.datphong);
        btnBack = (Button) findViewById(R.id.back);

        btnDatphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TenKh = edttenkh.getText().toString();
                String NgayDen = convertDate(edtngayo.getText().toString());
                String NgayDi = convertDate(edtngaydi.getText().toString());
             //   Toast.makeText(getApplication(),"gfg",Toast.LENGTH_LONG).show();
                DataService dataService = APIService.getService();
                Call<ResponseBody> callback = dataService.datphong(TenKh, NgayDen, NgayDi, loaiphong.getMaLP());
                callback.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.body().string());
                            int success = jsonObject.getInt("success");
                            String messages = jsonObject.getString("messages");
                            if (success == 1) {
                                Toast.makeText(DatphongActivity.this, messages, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(DatphongActivity.this, messages, Toast.LENGTH_LONG).show();
                            }

                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(DatphongActivity.this, "Đã có lỗi xảy ra!", Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatphongActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setDate(View view) {
        showDialog(999);
        showDialog(888);
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }

        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate1(arg1, arg2 + 1, arg3);
           // showDate2(arg1, arg2 + 1, arg3);
        }

    };
    private DatePickerDialog.OnDateSetListener myDateListener1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub

             showDate2(arg1, arg2 + 1, arg3);
        }

    };

    private void showDate1(int year, int month, int day) {
        edtngaydi.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));

    }
    private void showDate2(int year, int month, int day) {
        edtngayo.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    private void getData() {
        Intent intent = getIntent();
        loaiphong = (Loaiphong) intent.getSerializableExtra("datphong");

    }

    private String convertDate(String oldDateString) {
        final String OLD_FORMAT = "dd/MM/yyyy";
        final String NEW_FORMAT = "yyyy/MM/dd";


        String newDateString;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = null;
        try {
            d = sdf.parse(oldDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);

        return newDateString;
    }
}
