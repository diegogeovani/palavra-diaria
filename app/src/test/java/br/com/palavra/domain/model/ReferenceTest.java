package br.com.palavra.domain.model;

import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
        int verse2 = 3;
        mVerses.add(verse2);
        int verse3 = 4;
        mVerses.add(verse3);
        reference.setVerses(mVerses);
        String expected = String.format(Locale.getDefault(), "%1$d-%2$d", verse1, verse3);
        assertEquals("must return x-y format", expected, reference.getVersesString());
        fail("CONTINUE TESTING getVersesString");
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

