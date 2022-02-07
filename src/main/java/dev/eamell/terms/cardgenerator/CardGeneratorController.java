package dev.eamell.terms.cardgenerator;

import dev.eamell.terms.cardgenerator.model.CardGenerationInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/card")
public class CardGeneratorController {
    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    byte[] generateCards(CardGenerationInfo cardInfo) {
        return null;
    }
}
