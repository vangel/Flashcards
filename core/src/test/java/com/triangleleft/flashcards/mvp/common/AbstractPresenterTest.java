package com.triangleleft.flashcards.mvp.common;

import com.triangleleft.flashcards.mvp.common.presenter.AbstractPresenter;
import com.triangleleft.flashcards.mvp.common.view.IView;
import com.triangleleft.flashcards.mvp.common.view.IViewAction;
import com.triangleleft.flashcards.mvp.common.view.delegate.IViewDelegate;
import com.triangleleft.flashcards.mvp.common.view.delegate.IViewDelegateFactory;
import com.triangleleft.flashcards.mvp.common.view.delegate.ViewDelegateFactory;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import android.support.annotation.NonNull;

@RunWith(MockitoJUnitRunner.class)
public class AbstractPresenterTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Spy
    public AbstractPresenter<IView> presenter;

    @Mock
    public IView view;

    @BeforeClass
    public static void beforeClass() {
        ViewDelegateFactory.setDefault(new IViewDelegateFactory() {
            @Override
            public <View extends IView> IViewDelegate<View> buildViewDelegate() {
                return new IViewDelegate<View>() {
                    @Override
                    public void onBind(@NonNull View view) {

                    }

                    @Override
                    public void onUnbind() {

                    }

                    @Override
                    public void post(@NonNull IViewAction<View> action) {

                    }
                };
            }
        });
    }

}