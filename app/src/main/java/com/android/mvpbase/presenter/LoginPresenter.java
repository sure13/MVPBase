package com.android.mvpbase.presenter;

import android.os.Handler;
import android.util.Log;

import com.android.mvpbase.UserContainer;
import com.android.mvpbase.model.User;
import com.android.mvpbase.model.UserBiz;

public class LoginPresenter implements UserContainer.UserLoginPresenter {

    public UserContainer.UserLoginView userLoginView;
    public UserBiz userBiz;
    //对应视图页面销毁的标志位,当视图销毁后回调就不需要处理了
    private boolean destroyFlag;

    public Handler handler = new Handler();

    public LoginPresenter(UserContainer.UserLoginView userLoginView){
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    @Override
    public void login() {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getName(), userLoginView.getPassword(), new UserContainer.LoginListener() {
            @Override
            public void loginSuccess(User user) {
                if (!destroyFlag){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            userLoginView.toActivity();
                            userLoginView.hideLoading();
                        }
                    });
                }
            }

            @Override
            public void loginFaile() {
                if (!destroyFlag) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            userLoginView.showFaileTosat();
                            userLoginView.hideLoading();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void cancle() {
        userLoginView.clearName();
        userLoginView.clearPassword();
    }

    @Override
    public void detachView() {
       destroyFlag = true;
       userLoginView = null;
    }
}
