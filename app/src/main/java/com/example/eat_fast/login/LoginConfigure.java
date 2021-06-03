package com.example.eat_fast.login;

public class LoginConfigure {

    boolean run;
    private static LoginConfigure instance;

    private LoginConfigure(boolean run) {
        this.run = run;
    }

    private LoginConfigure() {
    }

    public static LoginConfigure getInstance() {
        if (instance == null)
            instance = new LoginConfigure();

            return instance;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}
