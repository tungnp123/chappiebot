package me.chappiebot.test.ui.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.chappiebot.test.ui.detail.DetailsFragment;
import me.chappiebot.test.ui.list.ListFragment;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListFragment provideListFragment();

    @ContributesAndroidInjector
    abstract DetailsFragment provideDetailsFragment();
}
