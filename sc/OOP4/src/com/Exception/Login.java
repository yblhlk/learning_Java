package com.Exception;

public class Login {
    public boolean login(User user){
        if(!"wyl".equals(user.getName())) {
            //throw new UsernameNotFoundException("用户名错误！");
        }
        if(!"123456".equals(user.getPwd())) {
            //throw new PasswordNotMatchException("密码错误！");
        }
        System.out.println(0);

        return true;
    }
}
