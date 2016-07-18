package com.triangleleft.flashcards.mvp.vocabular;

import com.triangleleft.flashcards.service.common.exception.ServerException;
import com.triangleleft.flashcards.service.vocabular.VocabularyModule;
import com.triangleleft.flashcards.service.vocabular.VocabularyWord;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VocabularyListPresenterTest {

    @Mock
    VocabularyModule module;
    @Mock
    VocabularyNavigator navigator;
    @Mock
    IVocabularyListView view;
    private VocabularyListPresenter presenter;

    @Before
    public void before() {
        presenter = new VocabularyListPresenter(module, navigator, Schedulers.immediate());
    }

//    @Test
//    public void onWordSelectedWouldDelegateToNavigator() {
//        VocabularyWord mockWord = mock(VocabularyWord.class);
//
//        presenter.onWordSelected(mockWord);
//        verify(navigator).onWordSelected(mockWord);
//    }

    @Test
    public void onLoadListWouldStartLoadingList() {
        when(module.loadVocabularyWords()).thenReturn(Observable.empty());

        presenter.onLoadList();
        verify(module).loadVocabularyWords();
    }

    @Test
    public void onDestroyWouldUnsubscribe() {
        AtomicBoolean unsubscribed = new AtomicBoolean(false);
        // Create empty observable to notify us when it's unsubscribed from
        Observable<List<VocabularyWord>> observable = Observable.empty();
        observable = observable.doOnUnsubscribe(() -> unsubscribed.set(true));
        when(module.loadVocabularyWords()).thenReturn(observable);

        // Simulate list load
        presenter.onLoadList();
        presenter.onDestroy();

        assertTrue(unsubscribed.get());
    }

    @Test
    public void onBindWouldStartLoadingList() {
        when(module.loadVocabularyWords()).thenReturn(Observable.empty());

        presenter.onBind(view);

        verify(module).loadVocabularyWords();
        verify(view).showProgress();
    }

    @Test
    public void whenHasListOnBindWouldShowList() {
        List<VocabularyWord> list = Collections.singletonList(mock(VocabularyWord.class));
        when(module.loadVocabularyWords()).thenReturn(Observable.just(list));
        presenter.onBind(view);
        presenter.onUnbind();
        reset(view);

        presenter.onBind(view);

        //verify(view).showWords(list, );
    }

    @Test
    public void onRefreshListWouldStartLoadingList() {
        when(module.refreshVocabularyWords()).thenReturn(Observable.empty());

        presenter.onRefreshList();

        verify(module).refreshVocabularyWords();
    }

    @Test
    public void onListLoadErrorWouldShowError() {
        when(module.loadVocabularyWords()).thenReturn(Observable.error(new ServerException()));

        presenter.onCreate();
        presenter.onBind(view);

        verify(view).showLoadError();
    }

    @Test
    public void onListRefreshWouldShowError() {
        when(module.loadVocabularyWords()).thenReturn(Observable.empty());
        presenter.onBind(view);
        reset(view);
        when(module.loadVocabularyWords()).thenReturn(Observable.error(new RuntimeException()));

        presenter.onRefreshList();

        verify(view).showRefreshError();
    }
}