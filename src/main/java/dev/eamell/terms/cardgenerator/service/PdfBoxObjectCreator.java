package dev.eamell.terms.cardgenerator.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

public interface PdfBoxObjectCreator {
    PDDocument createDocument();

    PDPage createPage();

    PDPageContentStream createContentStream(PDDocument document, PDPage page) throws IOException;
}
