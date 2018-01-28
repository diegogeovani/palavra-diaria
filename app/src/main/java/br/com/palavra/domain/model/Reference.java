package br.com.palavra.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class Reference {

    private final Book mBook;
    private Integer mChapter;
    private SortedSet<Integer> mVerses;
    private List<SortedSet<Character>> mVersesParts;

    public Reference(Book book) {
        this.mBook = book;
    }

    @Override
    public String toString() {
        String reference = "%1$s";
        if (mChapter != null && mChapter > 0) reference += " %2$d";
        if (mVerses != null && !mVerses.isEmpty()) reference += ":%3$s";
        return String.format(reference, mBook.getName(), mChapter, getVersesString());
    }

    public Integer getChapter() {
        return mChapter;
    }

    public void setChapter(int chapter) {
        if (chapter > 0) {
            mChapter = chapter;
        }
    }

    public SortedSet<Integer> getVerses() {
        return Collections.unmodifiableSortedSet(mVerses);
    }

    public void setVerses(SortedSet<Integer> verses) {
        for (Iterator<Integer> iterator = verses.iterator(); iterator.hasNext(); ) {
            int verse = iterator.next();
            if (verse <= 0) {
                iterator.remove();
            }
        }
        mVerses = verses;
    }

    public void setVerses(SortedMap<Integer, SortedSet<Character>> verses) {
        TreeSet<Integer> verseSet = new TreeSet<>();
        ArrayList<SortedSet<Character>> versesParts = new ArrayList<>();

        for (Iterator<Map.Entry<Integer, SortedSet<Character>>> verseIterator = verses.entrySet().iterator();
             verseIterator.hasNext(); ) {
            Map.Entry<Integer, SortedSet<Character>> entry = verseIterator.next();
            int verse = entry.getKey();

            if (verse <= 0) {
                verseIterator.remove();

            } else {
                SortedSet<Character> parts = entry.getValue();
                if (parts != null) {
                    for (Iterator<Character> partIterator = parts.iterator(); partIterator.hasNext(); ) {
                        char part = partIterator.next();
                        if (!Character.isLetter(part)) {
                            partIterator.remove();
                        }
                    }
                }
                verseSet.add(verse);
                versesParts.add(parts);
            }

            if (verseSet.size() == versesParts.size()) {
                mVerses = verseSet;
                mVersesParts = versesParts;
            } else {
                // TODO: exception
                throw new IllegalStateException("TODO");
            }
        }
    }

    public String getVersesString() {
        StringBuilder verses = new StringBuilder("");
        if (mVerses != null && !mVerses.isEmpty()) {
            String trace = "-";
            String comma = ",";
            int previousVerse = 0;

            int i = 0;
            for (int verse : mVerses) {
                if (i == 0) {
                    verses.append(verse);
                    appendVerseParts(verses, 0);
                    previousVerse = verse;
                    i++;
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
                    appendVerseParts(verses, i);

                } else {
                    verses.append(comma).append(verse);
                    appendVerseParts(verses, i);
                }
                previousVerse = verse;
                i++;
            }
        }
        return verses.toString();
    }

    private void appendVerseParts(StringBuilder stringBuilder, int verseIndex) {
        if (mVersesParts != null) {
            SortedSet<Character> parts = mVersesParts.get(verseIndex);
            if (parts != null) {
                parts.forEach(stringBuilder::append);
            }
        }
    }

}
