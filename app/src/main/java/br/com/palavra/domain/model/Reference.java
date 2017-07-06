package br.com.palavra.domain.model;

import java.util.Locale;

public class Reference {

    private Book book;
    private int mChapter;
    private int[] mVerses;

    public Reference(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        String reference = "%1$s";
        if (mChapter > 0) reference += " %2$d";
        if (mVerses != null && mVerses.length > 0)  {
            // TODO: identify ranges, points and separation between verses
        }
        return String.format(Locale.getDefault(), reference, book.getName(), mChapter);
    }

    public int getChapter() {
        return mChapter;
    }

    public void setChapter(int chapter) {
        mChapter = chapter;
    }

    public int[] getVerses() {
        return mVerses;
    }

    public void setVerses(int[] verses) {
        mVerses = verses;
    }

}
