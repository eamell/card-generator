package dev.eamell.terms.cardgenerator.model;

import java.util.List;

public record CardGenerationInfo(List<Term> terms, PageInfo pageInfo) {
}
