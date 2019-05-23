package me.chappiebot.test.data.rest;

import javax.inject.Inject;

import io.reactivex.Single;
import me.chappiebot.test.data.model.Feed;
import me.chappiebot.test.data.model.Item;

public class FeedRepository {

    private final FeedService feedService;

    @Inject
    public FeedRepository(FeedService feedService) {
        this.feedService = feedService;
    }

    public Single<Item> getNewFeeds() {
        return feedService.getNewFeeds();
    }

    public Single<Feed> getFeedDetail() {
        return feedService.getFeedDetail();
    }
}
