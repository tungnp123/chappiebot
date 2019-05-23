package me.chappiebot.test.ui.detail;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.chappiebot.test.R;
import me.chappiebot.test.data.model.Feed;

public class FeedDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Feed feed;

    private final List<Feed.Section> data = new ArrayList<>();

    FeedDetailAdapter(DetailsViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.getSelectedFeed().observe(lifecycleOwner, feed -> {
            data.clear();
            if (feed != null) {
                this.feed = feed;
                if (feed.getSections() != null)
                    data.addAll(feed.getSections());
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return -1; // show basic information
        else return data.get(position - 1).getSection_type().getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == -1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_feed_detail_info_item, parent, false);
            return new FeedInfoViewHolder(view);
        } else if (viewType == Feed.SectionType.IMAGE.getType()) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_feed_detail_image_item, parent, false);
            return new FeedImageViewHolder(view);
        } else if (viewType == Feed.SectionType.VIDEO.getType()) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_feed_detail_video_item, parent, false);
            return new FeedVideoViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_feed_detail_text_item, parent, false);
            return new FeedTextViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == -1) {
            ((FeedInfoViewHolder) holder).bind(feed);
        } else if (viewType == Feed.SectionType.TEXT.getType()) {
            ((FeedTextViewHolder) holder).bind(data.get(position - 1));
        } else {
            ((FeedImageViewHolder) holder).bind(data.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + (feed != null ? 1 : 0);
    }

    static final class FeedInfoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView feedTitle;
        @BindView(R.id.tv_description)
        TextView feedDescription;
        @BindView(R.id.iv_avatar)
        ImageView avatar;
        @BindView(R.id.tv_publish_name)
        TextView feedPublishName;

        FeedInfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Feed feed) {
            feedTitle.setText(feed.getTitle());
            feedDescription.setText(feed.getDescription());
            if (feed.getPublisher() != null) {
                feedPublishName.setText(feed.getPublisher().getName());
//                Glide.with(avatar.getContext()).load(android.util.Base64.decode(feed.getPublisher().getIcon()
//                        .replace("data:image/jpeg;base64,","")
//                        .replace("//Z",""), android.util.Base64.DEFAULT))
//                        .into(avatar);
            }
        }
    }

    static final class FeedTextViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_caption)
        TextView tvCaption;

        FeedTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Feed.Section section) {
            tvCaption.setText(section.getContent().getText());
        }
    }

    static class FeedImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_caption)
        TextView tvCaption;
        @BindView(R.id.iv_thumbnail)
        ImageView ivThumbnail;

        FeedImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Feed.Section section) {
            tvCaption.setText(section.getContent().getCaption());
            if (section.getSection_type() == Feed.SectionType.IMAGE)
                Glide.with(ivThumbnail.getContext()).load(section.getContent().getHref()).into(ivThumbnail);
            else if (section.getSection_type() == Feed.SectionType.VIDEO)
                Glide.with(ivThumbnail.getContext()).load(section.getContent().getPreview_image().getHref()).into(ivThumbnail);
        }
    }

    static final class FeedVideoViewHolder extends FeedImageViewHolder {


        FeedVideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Feed.Section section) {
            super.bind(section);
            // TODO: handle play buton

        }
    }
}
