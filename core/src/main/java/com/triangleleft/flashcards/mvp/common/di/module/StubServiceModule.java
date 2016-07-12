package com.triangleleft.flashcards.mvp.common.di.module;

import com.triangleleft.flashcards.mvp.common.di.scope.ApplicationScope;
import com.triangleleft.flashcards.service.cards.IFlashcardsModule;
import com.triangleleft.flashcards.service.cards.stub.StubFlashcardsModule;
import com.triangleleft.flashcards.service.login.LoginModule;
import com.triangleleft.flashcards.service.login.stub.StubLoginModule;
import com.triangleleft.flashcards.service.settings.SettingsModule;
import com.triangleleft.flashcards.service.settings.stub.StubSettingsModule;
import com.triangleleft.flashcards.service.vocabular.IVocabularyModule;
import com.triangleleft.flashcards.service.vocabular.stub.StubVocabularyModule;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class StubServiceModule {

    @ApplicationScope
    @Binds
    abstract LoginModule loginModule(StubLoginModule module);

    @ApplicationScope
    @Binds
    abstract IVocabularyModule vocabularModule(StubVocabularyModule module);

    @ApplicationScope
    @Binds
    abstract IFlashcardsModule flashcardsModule(StubFlashcardsModule module);

    @ApplicationScope
    @Binds
    abstract SettingsModule settingsModule(StubSettingsModule module);
}
