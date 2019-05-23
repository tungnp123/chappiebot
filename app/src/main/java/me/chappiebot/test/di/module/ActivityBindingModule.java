package me.chappiebot.test.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.chappiebot.test.ui.main.MainActivity;
import me.chappiebot.test.ui.main.MainFragmentBindingModule;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}
