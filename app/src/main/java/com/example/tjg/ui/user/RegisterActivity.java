package com.example.tjg.ui.user;

import static com.example.tjg.R.id.deng_lu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.tjg.R;
import com.example.tjg.ui.user.base.BaseActivity;

import org.xmlpull.v1.XmlPullParser;

public class RegisterActivity extends BaseActivity implements TextWatcher, View.OnClickListener {
    private ImageView ivPhoto;
    private EditText etName;
    private EditText etPassword;
    private EditText etEmail;
    private Button btnRegister;
    private TextView tvGoToLogin;
    private int requestCode = 100;

    private String name;
    private String pass;
    private String email;
    private Context context;

    // 添加常量表达式
    private static final int FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS = 0x80000000;
    private static final int FLAG_LAYOUT_STABLE = 0x00000100;
    private static final int FLAG_LAYOUT_FULLSCREEN = 0x00000400;
    private XmlPullParser etPhoneNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bg_register);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            View view = window.getDecorView();
            view.setSystemUiVisibility(FLAG_LAYOUT_STABLE | FLAG_LAYOUT_FULLSCREEN);
        }

        initView();
    }




    private void initView() {
        ivPhoto = findViewById(R.id.iv_photo);
        etName = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_pass);
        etEmail = findViewById(R.id.et_email);
        btnRegister = findViewById(R.id.bt_register);
        tvGoToLogin = findViewById(deng_lu);

        ivPhoto.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tvGoToLogin.setOnClickListener(this);

        etName.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
        etEmail.addTextChangedListener(this);
    }

    private void register() {
        // 实现注册逻辑
        // 添加手机号注册逻辑
        String phoneNumber = etPhoneNumber.getText().toString().trim();
        // 验证手机号格式是否正确
        if (isValidPhoneNumber(phoneNumber)) {
            // 执行手机号注册逻辑
            // 注册成功后进行登录操作
            login(phoneNumber);
        } else {
            showToast();
        }
    }

    private void login(String phoneNumber) {
        // 执行登录逻辑
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // 验证手机号格式是否正确的逻辑
        return true;
    }

    private void goToLogin() {
        // 跳转到登录页面的逻辑
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // 结束RegisterActivity
    }

    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.requestCode && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            ivPhoto.setImageURI(uri);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        name = etName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        pass = etPassword.getText().toString().trim();
        if (name.length() > 0 && email.length() > 0 && pass.length() >= 6) {
            btnRegister.setEnabled(true);
            btnRegister.setBackgroundResource(R.drawable.bg_register2_not);
        } else {
            btnRegister.setEnabled(false);
            btnRegister.setBackgroundResource(R.drawable.bg_register2_not);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == deng_lu) {
            goToLogin();
        } else if (view.getId() == R.id.bt_register) {
            register();
        } else if (view.getId() == R.id.iv_photo) {
            selectPhoto();
        }
    }

    private void showToast() {
        Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
    }
}