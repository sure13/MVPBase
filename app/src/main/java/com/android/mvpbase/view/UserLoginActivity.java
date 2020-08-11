package com.android.mvpbase.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.mvpbase.R;
import com.android.mvpbase.UserContainer;
import com.android.mvpbase.presenter.LoginPresenter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserLoginActivity extends AppCompatActivity implements UserContainer.UserLoginView {

    private EditText editTextPassword;
    private EditText editTextName;
    private Button buttonOK;
    private Button buttonCancle;

    private LoginPresenter loginPresenter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    public void initView(){
        editTextName = (EditText) findViewById(R.id.edit_name);
        editTextPassword = (EditText) findViewById(R.id.edit_password);
        buttonOK = (Button) findViewById(R.id.button_sure);
        buttonCancle = (Button) findViewById(R.id.button_cancle);
    }

    public void initData(){
        loginPresenter = new LoginPresenter(this);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.login();
            }
        });
        buttonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.cancle();
            }
        });
        dialog = new ProgressDialog(this);
        dialog.setMessage("Lodaing");
    }

    @Override
    public String getName() {
        return editTextName.getText().toString();
    }

    @Override
    public String getPassword() {
        return editTextPassword.getText().toString();
    }

    @Override
    public void clearName() {
        editTextName.setText("");
    }

    @Override
    public void clearPassword() {
        editTextPassword.setText("");
    }

    @Override
    public void showLoading() {
        dialog.show();
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void toActivity() {
        Toast.makeText(this,"跳转到登录成功页面", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFaileTosat() {
        Toast.makeText(this,"登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog = null;
        loginPresenter.detachView();
    }
}
