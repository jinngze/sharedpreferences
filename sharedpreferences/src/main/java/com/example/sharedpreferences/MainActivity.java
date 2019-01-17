package com.example.sharedpreferences;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;



import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_ed_name)
    EditText mainEdName;
    @BindView(R.id.main_ed_pwd)
    EditText mainEdPwd;
    @BindView(R.id.main_btn_dl)
    Button mainBtnDl;
    @BindView(R.id.main_btn_zc)
    Button mainBtnZc;
    @BindView(R.id.main_cb_mm)
    CheckBox mainCbMm;
    @BindView(R.id.btn_san)
    Button btnSan;
    private CheckBox ck_jizhu;

    private SharedPreferences main_share;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        ck_jizhu = findViewById(R.id.main_cb_mm);

        main_share = getSharedPreferences("User", MODE_PRIVATE);
        editor = main_share.edit();

        boolean ischeck = main_share.getBoolean("ischeck", false);
        if (ischeck) {
            String mainname = main_share.getString("mainname", null);
            String mainpwd = main_share.getString("mainpwd", null);

            mainEdName.setText(mainname);
            mainEdPwd.setText(mainpwd);
            ck_jizhu.setChecked(true);
        }

    }

    @OnClick({R.id.main_btn_dl, R.id.main_btn_zc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_btn_dl:


                String mainname = mainEdName.getText().toString();
                String mainpwd = mainEdPwd.getText().toString();

                sharedPreferences = getSharedPreferences("userinfo", RegisterActivity.MODE_PRIVATE);
                String name = sharedPreferences.getString("name", "");
                String pwd = sharedPreferences.getString("pwd", "");

                if(name.trim().equals("") || pwd.trim().equals("")){
                    Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (mainname.trim().equals(name) && mainpwd.trim().equals(pwd)) {
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

                    if (ck_jizhu.isChecked()) {

                        editor.putString("mainname", mainname);
                        editor.putString("mainpwd", mainpwd);

                        editor.putBoolean("ischeck", true);
                        editor.commit();
                    }

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "用户名或密码错误，请查看帐号或重新注册", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.main_btn_zc:
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }


}
