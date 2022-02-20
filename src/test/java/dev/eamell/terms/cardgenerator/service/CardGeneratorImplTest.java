package dev.eamell.terms.cardgenerator.service;

import dev.eamell.terms.cardgenerator.exception.CardGenerationInfoNotSpecifiedException;
import dev.eamell.terms.cardgenerator.model.CardGenerationInfo;
import dev.eamell.terms.cardgenerator.model.PageInfo;
import dev.eamell.terms.cardgenerator.model.Size;
import dev.eamell.terms.cardgenerator.model.Term;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class CardGeneratorImplTest {
    @InjectMocks
    private CardGeneratorImpl cardGenerator;

    @Mock
    private PdfBoxObjectCreator pdfBoxObjectCreator;

    @Mock
    private PDDocument document;

    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        given(pdfBoxObjectCreator.createDocument()).willReturn(document);
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

    @Test
    void generateCards_whenHasCardInfo_shouldCreateDocumentAndSaveIt() throws Exception {
        // given
        final CardGenerationInfo cardGenerationInfo =
                new CardGenerationInfo(List.of(new Term("term", "definition")),
                        new PageInfo(new Size(612, 792), new Size(252, 144)));

        // when
        final ByteArrayOutputStream outputStream = cardGenerator.generateCards(cardGenerationInfo);

        // then
        verify(document).save(outputStream);
    }
}
