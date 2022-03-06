package dev.eamell.terms.cardgenerator.controller;

import dev.eamell.terms.cardgenerator.CardGeneratorController;
import dev.eamell.terms.cardgenerator.model.CardGenerationInfo;
import dev.eamell.terms.cardgenerator.model.Margin;
import dev.eamell.terms.cardgenerator.model.PageInfo;
import dev.eamell.terms.cardgenerator.model.Size;
import dev.eamell.terms.cardgenerator.service.CardGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import static org.mockito.BDDMockito.given;

public class CardGeneratorControllerTest {
    @InjectMocks
    private CardGeneratorController controller;

    @Mock
    private CardGenerator cardGenerator;

    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void cleanUp() throws Exception {
        openMocks.close();
    }

    @Test
    void createdInstance_shouldBeARestController() {
        Assertions.assertThat(controller.getClass()).hasAnnotation(RestController.class);

        RestController restController = controller.getClass().getAnnotation(RestController.class);
        Assertions.assertThat(restController.value()).isEqualTo("/card");
    }

    @Test
    void generateCards_shouldHavePostMapping() throws Exception {
        final Method generateCardsMethod = controller.getClass().getMethod("generateCards", CardGenerationInfo.class);

        final PostMapping postMappingAnnotation = generateCardsMethod.getAnnotation(PostMapping.class);
        Assertions.assertThat(postMappingAnnotation.value()).containsExactly("/generate");
        Assertions.assertThat(postMappingAnnotation.produces()).containsExactly(MediaType.APPLICATION_PDF_VALUE);

        final ResponseBody responseBodyAnnotation = generateCardsMethod.getAnnotation(ResponseBody.class);
        Assertions.assertThat(responseBodyAnnotation).isNotNull();
    }

    @Test
    void generateCode_shouldReturnResultOfCardGeneration() {
        // given
        CardGenerationInfo cardGenerationInfo =
                new CardGenerationInfo(Collections.emptyList(),
                        new PageInfo(new Size(0, 0),
                                     new Size(0, 0),
                                     Margin.equalMargin(0)));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.writeBytes("test string".getBytes(StandardCharsets.UTF_8));

        given(cardGenerator.generateCards(cardGenerationInfo)).willReturn(outputStream);

        // when
        byte[] responseBody = controller.generateCards(cardGenerationInfo);

        // then
        Assertions.assertThat(responseBody).asString(StandardCharsets.UTF_8).isEqualTo("test string");
    }
}
