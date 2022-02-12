package dev.eamell.terms.cardgenerator.service;

import dev.eamell.terms.cardgenerator.model.CardGenerationInfo;

import java.io.ByteArrayOutputStream;

public interface CardGenerator {
    ByteArrayOutputStream generateCards(CardGenerationInfo cardInfo);
}
