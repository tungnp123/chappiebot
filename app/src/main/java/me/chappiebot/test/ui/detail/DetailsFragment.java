package me.chappiebot.test.ui.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import me.chappiebot.test.R;
import me.chappiebot.test.base.BaseFragment;
import me.chappiebot.test.util.ViewModelFactory;

public class DetailsFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView listView;

    @Inject
    ViewModelFactory viewModelFactory;
    private DetailsViewModel detailsViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.screen_details;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DetailsViewModel.class);

        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        listView.setAdapter(new FeedDetailAdapter(detailsViewModel, this));
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
