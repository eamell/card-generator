package dev.eamell.terms.cardgenerator.service;

import dev.eamell.terms.cardgenerator.exception.CardGenerationInfoNotSpecifiedException;
import dev.eamell.terms.cardgenerator.model.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
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

    @Mock
    private PDPage page;

    @Mock
    private PDPageContentStream pageContentStream;

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
                        new PageInfo(new Size(612, 792),
                                     new Size(252, 144),
                                     Margin.equalMargin(32)));

        // when
        final ByteArrayOutputStream outputStream = cardGenerator.generateCards(cardGenerationInfo);

        // then
        verify(document).save(outputStream);
    }

    @Test
    void generateCards_whenHasATerm_shouldCreateACardWithThatTerm() throws Exception {
        // given
        final CardGenerationInfo cardGenerationInfo =
                new CardGenerationInfo(List.of(new Term("term", "definition")),
                        new PageInfo(new Size(612, 792),
                                     new Size(252, 144),
                                     Margin.equalMargin(32)));

        // TODO this is a sample to help remember what to do
        // this sets the line color
        pageContentStream.setStrokingColor(Color.BLACK);
        // this sets the fill line color
        pageContentStream.setNonStrokingColor(Color.BLACK);

        final Size pageSize = cardGenerationInfo.pageInfo().pageSize();
        final Size cardSize = cardGenerationInfo.pageInfo().cardSize();
        final Margin margins = cardGenerationInfo.pageInfo().margins();
        int numCardColumns = (int) ((pageSize.width()
                - (margins.left() + margins.right())) % cardSize.width());
        
        float columnStart = ((pageSize.width() + margins.left())
                - (cardSize.width() * numCardColumns))/2;

        int numCardRows = (int) ((pageSize.height() -
                (margins.top() + margins.bottom())) % cardSize.height());
        float rowStart = (pageSize.height() - (cardSize.height() * numCardRows))/2;

        pageContentStream.addRect(columnStart, rowStart, cardSize.width(), cardSize.height());

        // when

        // then
    }
}
