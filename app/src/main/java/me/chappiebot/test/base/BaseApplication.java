package me.chappiebot.test.base;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import me.chappiebot.test.di.component.ApplicationComponent;
import me.chappiebot.test.di.component.DaggerApplicationComponent;

public class BaseApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }
}
