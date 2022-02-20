package dev.eamell.terms.cardgenerator.service;

import dev.eamell.terms.cardgenerator.exception.CardGenerationFailureException;
import dev.eamell.terms.cardgenerator.exception.CardGenerationInfoNotSpecifiedException;
import dev.eamell.terms.cardgenerator.model.CardGenerationInfo;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CardGeneratorImpl implements CardGenerator {
    private final PdfBoxObjectCreator pdfBoxObjectCreator;

    @Override
    public ByteArrayOutputStream generateCards(CardGenerationInfo cardInfo) {
        if (cardInfo == null) {
            throw new CardGenerationInfoNotSpecifiedException();
        }

        try (PDDocument document = pdfBoxObjectCreator.createDocument()) {
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            document.save(outputStream);

            return outputStream;
        } catch (IOException e) {
            throw new CardGenerationFailureException("Error saving document", e);
        }
    }
}
