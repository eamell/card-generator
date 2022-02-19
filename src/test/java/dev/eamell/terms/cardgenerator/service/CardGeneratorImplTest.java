package dev.eamell.terms.cardgenerator.service;

import dev.eamell.terms.cardgenerator.exception.CardGenerationInfoNotSpecifiedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

class CardGeneratorImplTest {
    @InjectMocks
    private CardGeneratorImpl cardGenerator;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void createdInstance() {
        assertThat(cardGenerator).isInstanceOf(CardGenerator.class);
    }

    @Test
    void generateCards_whenCardInfoNull_shouldThrowException() {
        // given

        // when
        final CardGenerationInfoNotSpecifiedException e =
                catchThrowableOfType(() -> cardGenerator.generateCards(null),
                        CardGenerationInfoNotSpecifiedException.class);

        // then
        assertThat(e).isNotNull();
    }
}
