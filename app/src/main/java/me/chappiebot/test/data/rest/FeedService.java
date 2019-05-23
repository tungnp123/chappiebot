package me.chappiebot.test.data.rest;

import io.reactivex.Single;
import me.chappiebot.test.data.model.Feed;
import me.chappiebot.test.data.model.Item;
import retrofit2.http.GET;

public interface FeedService {

    @GET("s/dl/fy6ny7syutxl1yd/newsfeed.json")
    Single<Item> getNewFeeds();

    @GET("s/dl/v83n38kvsm6qw62/detail.json")
    Single<Feed> getFeedDetail();
}
