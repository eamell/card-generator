package dev.eamell.terms.cardgenerator.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

class PdfBoxObjectCreatorImplTest {
    private PdfBoxObjectCreatorImpl objectCreator;

    @BeforeEach
    void setUp() {
        objectCreator = new PdfBoxObjectCreatorImpl();
    }

    @Test
    void createdInstance() {
        assertThat(objectCreator).isInstanceOf(PdfBoxObjectCreator.class);
    }

    @Test
    void createDocument_shouldReturnANewInstance() throws Exception {
        // given

        // when
        final PDDocument document = objectCreator.createDocument();

        // then
        assertThat(document).isNotNull();

        document.close();
    }

    @Test
    void createPage_shouldReturnANewInstance() {
        // given

        // when
        final PDPage page = objectCreator.createPage();

        // then
        assertThat(page).isNotNull();
    }

    @Test
    void createContentStream_whenDocumentAndPageSpecified_shouldCreateNewInstance() throws Exception {
        // given
        final PDDocument document = new PDDocument();
        final PDPage page = new PDPage();

        // when
        final PDPageContentStream contentStream = objectCreator.createContentStream(document, page);

        // then
        assertThat(contentStream).isNotNull();
    }

    @Test
    void createContentStream_whenDocumentNull_shouldThrowException() {
        // given
        final PDPage page = new PDPage();

        // when
        final NullPointerException e =
                catchThrowableOfType(() -> objectCreator.createContentStream(null, page),
                        NullPointerException.class);

        // then
        assertThat(e).isNotNull();
    }

    @Test
    void createContentStream_whenPageNull_shouldThrowException() {
        // given
        final PDDocument document = new PDDocument();

        // when
        final NullPointerException e =
                catchThrowableOfType(() -> objectCreator.createContentStream(document, null),
                        NullPointerException.class);

        // then
        assertThat(e).isNotNull();
    }

    @Test
    void createContentStream_whenPageAndDocumentNull_shouldThrowException() {
        // given

        // when
        final NullPointerException e =
                catchThrowableOfType(() -> objectCreator.createContentStream(null, null),
                        NullPointerException.class);

        // then
        assertThat(e).isNotNull();
    }
}
