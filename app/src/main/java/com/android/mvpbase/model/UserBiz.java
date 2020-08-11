package com.android.mvpbase.model;

import android.util.Log;

import com.android.mvpbase.UserContainer;

public class UserBiz  implements UserContainer.IUserBiz {
    @Override
    public void login(final String username, final String password, final UserContainer.LoginListener loginListener) {
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000); //请求服务器中的数据
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if (("wxy".equals(username)) && ("123456".equals(password))){
                    User user = new User();
                    user.setName(username);
                    user.setPassword(password);
                    loginListener.loginSuccess(user);
                }else {
                    loginListener.loginFaile();
                }
            }
        }.start();
    }
}
