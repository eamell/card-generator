package dev.eamell.terms.cardgenerator;

import dev.eamell.terms.cardgenerator.model.CardGenerationInfo;
import dev.eamell.terms.cardgenerator.service.CardGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController("/card")
@RequiredArgsConstructor
public class CardGeneratorController {
    private final CardGenerator cardGenerator;

    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] generateCards(CardGenerationInfo cardInfo) {
        final ByteArrayOutputStream outputStream = cardGenerator.generateCards(cardInfo);

        return outputStream.toByteArray();
    }
}
