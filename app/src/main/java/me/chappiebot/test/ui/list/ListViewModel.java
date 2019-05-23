package me.chappiebot.test.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import me.chappiebot.test.data.model.Item;
import me.chappiebot.test.data.rest.FeedRepository;

public class ListViewModel extends ViewModel {

    private final FeedRepository feedRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<Item> feeds = new MutableLiveData<>();
    private final MutableLiveData<Boolean> feedLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public ListViewModel(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
        disposable = new CompositeDisposable();
        fetchFeed();
    }

    LiveData<Item> getFeeds() {
        return feeds;
    }
    LiveData<Boolean> getError() {
        return feedLoadError;
    }
    LiveData<Boolean> getLoading() {
        return loading;
    }

    private void fetchFeed() {
        loading.setValue(true);
        disposable.add(feedRepository.getNewFeeds().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Item>() {
                    @Override
                    public void onSuccess(Item value) {
                        feedLoadError.setValue(false);
                        feeds.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        feedLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
