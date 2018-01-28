package br.com.palavra.domain.model;

import java.util.List;

public class Book {

    private String mName;
    private Testament mTestament;
    private int mChaptersNumber;
    private List<String> mAuthors;
    private String mDescription;

    public Book(String name) {
        mName = name;
    }

    public Book(String name, Testament testament) {
        mName = name;
        mTestament = testament;
    }

    public String getName() {
        return mName;
    }

    public void setChaptersNumber(int chaptersNumber) {
        mChaptersNumber = chaptersNumber;
    }

}
