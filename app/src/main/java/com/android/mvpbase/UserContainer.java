package com.android.mvpbase;

import com.android.mvpbase.model.User;

public interface UserContainer {

    public interface UserLoginView{
        String getName();
        String getPassword();
        void clearName();
        void clearPassword();
        void showLoading();
        void hideLoading();
        void toActivity();
        void showFaileTosat();
    }

    public interface UserLoginPresenter{
        void login();
        void cancle();
        void detachView();
    }

    public interface LoginListener{
        void loginSuccess(User user);
        void loginFaile();
    }

    public interface IUserBiz{
        public void login(String username, String password, LoginListener loginListener);
    }
}
