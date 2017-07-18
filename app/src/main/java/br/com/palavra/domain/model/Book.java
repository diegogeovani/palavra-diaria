package br.com.palavra.domain.model;

import java.util.List;

public class Book {

    private String mName;
    private Testament testament;
    private int mChaptersNumber;
    private List<String> authors;
    private String description;

    public Book(String name) {
        mName = name;
    }

    public Book(String name, Testament testament) {
        mName = name;
        this.testament = testament;
    }

    public String getName() {
        return mName;
    }

    public int getChaptersNumber() {
        return mChaptersNumber;
    }

    public void setChaptersNumber(int chaptersNumber) {
        mChaptersNumber = chaptersNumber;
    }

}
