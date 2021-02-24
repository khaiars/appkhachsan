package nguyenkhai.dmt.mykhachsan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import nguyenkhai.dmt.mykhachsan.R;
import nguyenkhai.dmt.mykhachsan.Service.APIService;
import nguyenkhai.dmt.mykhachsan.Service.DataService;
import nguyenkhai.dmt.mykhachsan.Session.SessionManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUserName;
    private EditText edtPassWord;
    private Button btnLogin;
    private Button btnRegister;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
    }

    private void AnhXa() {
        edtUserName = (EditText) findViewById(R.id.editUsername);
        edtPassWord = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        sessionManager = new SessionManager(LoginActivity.this);

        if (sessionManager.checkLogin()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Username = edtUserName.getText().toString().trim();
                    String Password = edtPassWord.getText().toString().trim();
                 ///   Toast.makeText(getApplication(),Password,Toast.LENGTH_LONG).show();
                    Login(Username, Password);
                }
            });


            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        }

    }

    private void Login(final String username, final String password) {
        DataService dataService = APIService.getService();
        final Call<ResponseBody> callback = dataService.login(username, password);
        callback.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response.body().string());
                    int success = jsonObject.getInt("success");
                    Log.d("ss",username + "-" + password + "sss");
                    String messages = jsonObject.getString("messages");
                    if (success == 1) {
                        String userId = jsonObject.getString("userId");
                        String password = jsonObject.getString("userPassword");
                        String username = jsonObject.getString("userName");
                        Toast.makeText(LoginActivity.this, messages, Toast.LENGTH_LONG).show();
                        sessionManager.setLogin(true);
                        sessionManager.setUserId(userId);
                        sessionManager.setKeyPassword(password);
                        sessionManager.setUserName(username);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {

                        Toast.makeText(LoginActivity.this, messages, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Đã có lỗi xảy ra!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
