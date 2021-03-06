package com.triangleleft.flashcards.service.cards.rest;

import static com.annimon.stream.Collectors.toList;

import com.annimon.stream.Stream;
import com.google.gson.annotations.SerializedName;
import com.triangleleft.flashcards.service.cards.FlashcardTestResult;
import com.triangleleft.flashcards.service.cards.FlashcardWordResult;

import java.util.List;

public class FlashcardResultsController {

    @SerializedName("flashcard_results")
    List<FlashcardResultModel> flashcardResults;
    @SerializedName("learning_language")
    String learningLanguage;
    @SerializedName("ui_language")
    String uiLanguage;

    public FlashcardResultsController(FlashcardTestResult result) {
        learningLanguage = result.getLearningLanguage();
        uiLanguage = result.getUiLanguage();
        flashcardResults = Stream.of(result.getWordResults())
            .map(FlashcardResultModel::new)
            .collect(toList());
    }

    static class FlashcardResultModel {

        @SerializedName("id")
        String id;
        @SerializedName("count")
        int count = 1;
        @SerializedName("correct")
        int correct;

        public FlashcardResultModel(FlashcardWordResult result) {
            id = result.getWord().getId();
            correct = result.isCorrect() ? 1 : 0;
        }

    }
}
