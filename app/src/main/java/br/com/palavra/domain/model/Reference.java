package br.com.palavra.domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Reference {

    private final Book mBook;
    private final SortedSet<Verse> mVerses;
    private final List<SortedSet<Character>> mVersesParts;
    private Integer mChapter;

    public Reference(Book book) {
        mBook = book;
        mVerses = new TreeSet<>();
        mVersesParts = new ArrayList<>();
    }

    private static boolean isValidVerse(Verse verse) {
        return verse.getNumber() > 0;
    }

    @Override
    public String toString() {
        String reference = "%1$s";
        if (mChapter != null && mChapter > 0) reference += " %2$d";
        if (!mVerses.isEmpty()) reference += ":%3$s";
        return String.format(reference, mBook.getName(), mChapter, getVersesString());
    }

    public String getVersesString() {
        StringBuilder sb = new StringBuilder("");
        if (!mVerses.isEmpty()) {
            String trace = "-";
            String comma = ",";
            Verse previousVerse = null;

            int i = 0;
            for (Verse verse : mVerses) {
                if (i == 0) {
                    sb.append(verse);
                    appendVerseParts(sb, 0);
                    previousVerse = verse;
                    i++;
                    continue;
                }

                if (verse.getNumber() - previousVerse.getNumber() == 1) {
                    int traceLastIndex = sb.lastIndexOf(trace);
                    if (sb.length() > 2 && traceLastIndex >= 0) {
                        String a = trace + previousVerse;
                        String b = sb.substring(traceLastIndex, sb.length());
                        if (a.equals(b)) {
                            sb.delete(traceLastIndex, sb.length());
                        }
                    }
                    sb.append(trace).append(verse);
                    appendVerseParts(sb, i);

                } else {
                    sb.append(comma).append(verse);
                    appendVerseParts(sb, i);
                }
                previousVerse = verse;
                i++;
            }
        }
        return sb.toString();
    }

    public void addVerse(Verse verse) {
        if (isValidVerse(verse)) {
            mVerses.add(verse);
            mVersesParts.add(new TreeSet<>());
        }
    }

    public void addVerse(Verse verse, Character part) {
        if (isValidVerse(verse)) {
            mVerses.add(verse);
            mVersesParts.add(new TreeSet<>(Collections.singletonList(part)));
        }
    }

    public void addVerse(Verse verse, Character[] parts) {
        if (isValidVerse(verse)) {
            mVerses.add(verse);
            mVersesParts.add(new TreeSet<>(Arrays.asList(parts)));
        }
    }

    public Integer getChapter() {
        return mChapter;
    }

    public void setChapter(int chapter) {
        if (chapter > 0) {
            mChapter = chapter;
        }
    }

    public SortedSet<Verse> getVerses() {
        return Collections.unmodifiableSortedSet(mVerses);
    }

    private void appendVerseParts(StringBuilder sb, int verseIndex) {
        SortedSet<Character> parts = mVersesParts.get(verseIndex);
        if (parts != null) {
            for (Character p : parts) {
                sb.append(p);
            }
        }
    }

}
