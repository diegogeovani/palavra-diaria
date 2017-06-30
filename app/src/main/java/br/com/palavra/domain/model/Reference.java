package br.com.palavra.domain.model;

import java.util.List;

public class Reference {

    private Book book;
    private int chapter;
    private List<Integer> verses;

    public Reference(Book book) {
        this.book = book;
    }

}
