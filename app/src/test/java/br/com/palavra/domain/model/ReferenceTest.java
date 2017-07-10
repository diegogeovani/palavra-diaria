package br.com.palavra.domain.model;

import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class ReferenceTest {

    private final ArrayList<Integer> mVerses = new ArrayList<>();

    @Test
    public void getVersesString_noVerses() {
        Reference reference = new Reference(new Book("Hebreus"));
        assertEquals("must return empty string", "", reference.getVersesString());
    }

    @Test
    public void getVersesString_singleVerse() {
        int verse = 1;
        Reference reference = new Reference(new Book("Hebreus"));
        mVerses.add(verse);
        reference.setVerses(mVerses);
        String expected = String.valueOf(verse);
        assertEquals("must return same verse as string", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_twoVersesRange() {
        Reference reference = new Reference(new Book("Hebreus"));
        int verse1 = 1;
        mVerses.add(verse1);
        int verse2 = 2;
        mVerses.add(verse2);
        reference.setVerses(mVerses);
        String expected = String.format(Locale.getDefault(), "%1$d-%2$d", verse1, verse2);
        assertEquals("must return x-y format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_twoVersesDistinct() {
        Reference reference = new Reference(new Book("Hebreus"));
        int verse1 = 1;
        mVerses.add(verse1);
        int verse2 = 9;
        mVerses.add(verse2);
        reference.setVerses(mVerses);
        String expected = String.format(Locale.getDefault(), "%1$d,%2$d", verse1, verse2);
        assertEquals("must return x,y format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_multipleVersesRange() {
        Reference reference = new Reference(new Book("Hebreus"));
        int verse1 = 2;
        mVerses.add(verse1);
        mVerses.add(3);
        int verse3 = 4;
        mVerses.add(verse3);
        reference.setVerses(mVerses);
        String expected = String.format(Locale.getDefault(), "%1$d-%2$d", verse1, verse3);
        assertEquals("must return x-y format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_multipleVersesDistinct() {
        Reference reference = new Reference(new Book("Hebreus"));
        int verse1 = 2;
        mVerses.add(verse1);
        int verse2 = 6;
        mVerses.add(verse2);
        int verse3 = 10;
        mVerses.add(verse3);
        reference.setVerses(mVerses);
        String expected = String.format(Locale.getDefault(), "%1$d,%2$d,%3$d", verse1, verse2, verse3);
        assertEquals("must return x,y,z format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_multipleVersesMixed() {
        Reference reference = new Reference(new Book("Hebreus"));
        int verse1 = 2;
        mVerses.add(verse1);
        int rangeStartVerse = 6;
        mVerses.add(rangeStartVerse);
        mVerses.add(7);
        mVerses.add(8);
        mVerses.add(9);
        int rangeEndVerse = 10;
        mVerses.add(rangeEndVerse);
        int verse4 = 13;
        mVerses.add(verse4);
        reference.setVerses(mVerses);
        String expected = String.format(Locale.getDefault(), "%1$d,%2$d-%3$d,%4$d", verse1, rangeStartVerse,
                rangeEndVerse, verse4);
        assertEquals("must return w,x-y,z format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_multipleVersesMixed2() {
        Reference reference = new Reference(new Book("Hebreus"));
        int verseA = 7;
        mVerses.add(verseA);
        mVerses.add(8);
        mVerses.add(9);
        int verseB = 10;
        mVerses.add(verseB);
        int verseC = 15;
        mVerses.add(verseC);
        mVerses.add(16);
        int verseD = 17;
        mVerses.add(verseD);
        reference.setVerses(mVerses);
        String expected = String.format(Locale.getDefault(), "%1$d-%2$d,%3$d-%4$d", verseA, verseB,
                verseC, verseD);
        assertEquals("must return w-x,y-z format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_multipleVersesMixed3() {
        Reference reference = new Reference(new Book("Salmos"));
        int verseA = 101;
        mVerses.add(verseA);
        int verseB = 103;
        mVerses.add(verseB);
        int mockVerse = 1004;
        mVerses.add(mockVerse);
        mVerses.add(1005);
        int mockVerse4 = 1006;
        mVerses.add(mockVerse4);
        int mockVerse2 = 1100;
        mVerses.add(mockVerse2);
        int mockVerse3 = 9999;
        mVerses.add(mockVerse3);
        reference.setVerses(mVerses);
        String expected = String.format(Locale.getDefault(), "%1$d,%2$d,%3$d-%4$d,%5$d,%6$d", verseA, verseB,
                mockVerse, mockVerse4, mockVerse2, mockVerse3);
        assertEquals("must return w,x-y,z format", expected, reference.getVersesString());
    }

    @Test
    public void toString_case() {
        String bookName = "Atos";
        Reference reference = new Reference(new Book(bookName));
        reference.setChapter(2);
        reference.setVerses(mVerses);
        String expected = bookName + " 2:9";
        assertEquals("toString must be as expected", expected, reference.toString());
    }

    @After
    public void tearDown() {
        mVerses.clear();
    }

}

