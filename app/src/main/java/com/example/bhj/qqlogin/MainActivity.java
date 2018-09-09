package com.example.bhj.qqlogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNumber;
    private EditText etPassword;
    private CheckBox cbRememberPwd;
    private CheckBox cbAutoLogin;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//不显示标题,必须写在setContentView前面
        setContentView(R.layout.activity_main);

        //  /data/data/包名/files
        this.getFilesDir();

        etNumber = findViewById(R.id.et_number);
        etPassword = findViewById(R.id.et_password);
        cbRememberPwd = findViewById(R.id.cb_remember_pwd);
        cbAutoLogin = findViewById(R.id.cb_auto_login);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);

        //回显示数据
        Map<String, String> userInfoMap = UtilsOfSDCard.getUserInfo(this);

        if (userInfoMap != null) {
            etNumber.setText(userInfoMap.get("number"));
            etPassword.setText(userInfoMap.get("password"));
        }


    }

    @Override
    public void onClick(View v) {//执行登录的操作
        //1.取出账号和密码
        String number = etNumber.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(number) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请正确输入", Toast.LENGTH_SHORT).show();
            return;
        }

        //2.判断是否记住密码,并存起来
        if (cbRememberPwd.isChecked()) {

            Log.d("bai", "记住密码");

            boolean isSuccess = UtilsOfSDCard.saveUserInfo(this, number, password);

            if (isSuccess) {
                Log.d("bai", "保存成功");
                Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
            }

        }

        // 3.登录
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();

    }
}

