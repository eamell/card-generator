package dev.eamell.terms.cardgenerator.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

public class PdfBoxObjectCreatorImpl implements PdfBoxObjectCreator {
    @Override
    public PDDocument createDocument() {
        return new PDDocument();
    }

    @Override
    public PDPage createPage() {
        return new PDPage();
    }

    @Override
    public PDPageContentStream createContentStream(PDDocument document, PDPage page) throws IOException {
        return new PDPageContentStream(document, page);
    }
}
