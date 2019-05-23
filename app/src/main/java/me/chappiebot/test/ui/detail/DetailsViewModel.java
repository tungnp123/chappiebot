package me.chappiebot.test.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import me.chappiebot.test.data.model.Feed;
import me.chappiebot.test.data.rest.FeedRepository;

public class DetailsViewModel extends ViewModel {

    private final FeedRepository feedRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<Feed> selectedFeed = new MutableLiveData<>();

    public LiveData<Feed> getSelectedFeed() {
        return selectedFeed;
    }

    @Inject
    public DetailsViewModel(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
        disposable = new CompositeDisposable();
    }

    private void fetchFeedDetails() {
        disposable.add(feedRepository.getFeedDetail().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Feed>() {
                    @Override
                    public void onSuccess(Feed value) {
                        selectedFeed.setValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                }));
    }

    public void setSelectedFeed(Feed feed) {
        fetchFeedDetails();
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
