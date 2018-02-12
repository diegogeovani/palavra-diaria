package br.com.palavra.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Book {

    private String mName;
    private Testament mTestament;
    private int mChaptersCount;
    private String mDescription;
    private LocalDate mApproximateYearOfWriting;
    private Translation mTranslation;
    private List<Author> mAuthors;

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

    public void setChaptersCount(int chaptersCount) {
        mChaptersCount = chaptersCount;
    }

}
