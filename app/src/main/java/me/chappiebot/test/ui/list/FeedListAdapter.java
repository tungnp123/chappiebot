package me.chappiebot.test.ui.list;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.chappiebot.test.R;
import me.chappiebot.test.data.model.Feed;

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.FeedViewHolder> {

    private FeedSelectedListener feedSelectedListener;
    private final List<Feed> data = new ArrayList<>();

    FeedListAdapter(ListViewModel viewModel, LifecycleOwner lifecycleOwner, FeedSelectedListener feedSelectedListener) {
        this.feedSelectedListener = feedSelectedListener;
        viewModel.getFeeds().observe(lifecycleOwner, feeds -> {
            data.clear();
            if (feeds != null) {
                data.addAll(feeds.getItems());
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_feed_list_item, parent, false);
        return new FeedViewHolder(view, feedSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static final class FeedViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_feed_name)
        TextView tvFeedName;
        @BindView(R.id.tv_description)
        TextView tvDescription;

        private Feed feed;

        FeedViewHolder(View itemView, FeedSelectedListener feedSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (feed != null) {
                    feedSelectedListener.onFeedSelected(feed);
                }
            });
        }

        void bind(Feed feed) {
            this.feed = feed;
            tvFeedName.setText(feed.getTitle());
            tvDescription.setText(feed.getDescription());
        }
    }
}
