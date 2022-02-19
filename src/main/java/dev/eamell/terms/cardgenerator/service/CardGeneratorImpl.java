package dev.eamell.terms.cardgenerator.service;

import dev.eamell.terms.cardgenerator.exception.CardGenerationInfoNotSpecifiedException;
import dev.eamell.terms.cardgenerator.model.CardGenerationInfo;

import java.io.ByteArrayOutputStream;

public class CardGeneratorImpl implements CardGenerator {
    @Override
    public ByteArrayOutputStream generateCards(CardGenerationInfo cardInfo) {
        throw new CardGenerationInfoNotSpecifiedException();
    }
}
