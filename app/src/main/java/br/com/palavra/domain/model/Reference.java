package br.com.palavra.domain.model;

import java.util.List;

public class Reference {

    private Book book;
    private int mChapter;
    private List<Integer> mVerses;

    public Reference(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        String reference = "%1$s";
        if (mChapter > 0) reference += " %2$d";
        if (mVerses != null && mVerses.size() > 0) {
            // TODO: identify ranges, points and separation between verses
        }
        return String.format(reference, book.getName(), mChapter);
    }

    public int getChapter() {
        return mChapter;
    }

    public void setChapter(int chapter) {
        mChapter = chapter;
    }

    public List<Integer> getVerses() {
        return mVerses;
    }

    public void setVerses(List<Integer> verses) {
        mVerses = verses;
    }

    public String getVersesString() {
        StringBuilder verses = new StringBuilder("");
        if (mVerses != null && !mVerses.isEmpty()) {
            if (mVerses.size() == 1) {
                return String.valueOf(mVerses.get(0));

            } else {
                String trace = "-";
                String comma = ",";
                int previousVerse = 0;

                for (int i = 0; i < mVerses.size(); i++) {
                    int verse = mVerses.get(i);
                    if (i == 0) {
                        verses.append(verse);
                        previousVerse = verse;
                        continue;
                    }

                    if (verse - previousVerse == 1) {
                        int traceLastIndex = verses.lastIndexOf(trace);
                        if (verses.length() > 2 && traceLastIndex >= 0) {
                            String a = trace + previousVerse;
                            String b = verses.substring(traceLastIndex, verses.length());
                            if (a.equals(b)) {
                                verses.delete(traceLastIndex, verses.length());
                            }
                        }
                        verses.append(trace).append(verse);

                    } else {
                        verses.append(comma).append(verse);
                    }
                    previousVerse = verse;
                }
            }
        }
        return verses.toString();
    }

}
