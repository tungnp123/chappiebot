package me.chappiebot.test.ui.main;

import android.os.Bundle;

import me.chappiebot.test.R;
import me.chappiebot.test.base.BaseActivity;
import me.chappiebot.test.ui.list.ListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new ListFragment()).commit();
    }
}
