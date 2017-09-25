package br.com.palavra.domain.model;

import org.junit.After;
import org.junit.Test;

import java.util.Arrays;
import java.util.Locale;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class ReferenceTest {

    private final TreeSet<Integer> mVerses = new TreeSet<>();

    @Test
    public void toString_bookName() {
        String bookName = "Atos";
        Reference reference = new Reference(new Book(bookName));
        assertEquals("must return book name", bookName, reference.toString());
    }

    @Test
    public void toString_chapter() {
        String bookName = "Atos";
        Reference reference = new Reference(new Book(bookName));
        Integer chapter = 7;
        reference.setChapter(chapter);
        String expected = bookName + ' ' + chapter;
        assertEquals("must return expected format", expected, reference.toString());
    }

    @Test
    public void toString_completeFormat() {
        String bookName = "Atos";
        Reference reference = new Reference(new Book(bookName));
        Integer chapter = 7;
        reference.setChapter(chapter);
        mVerses.add(8);
        mVerses.add(9);
        mVerses.add(10);
        reference.setVerses(mVerses);
        String expected = String.format(Locale.getDefault(), "%1$s %2$d:%3$s", bookName, chapter,
                reference.getVersesString());
        assertEquals("must return expected format", expected, reference.toString());
    }

    @Test
    public void toString_completeFormat2() {
        String bookName = "Atos";
        Reference reference = new Reference(new Book(bookName));
        Integer chapter = 15;
        reference.setChapter(chapter);
        mVerses.add(11);
        mVerses.add(15);
        mVerses.add(104);
        mVerses.add(105);
        mVerses.add(106);
        reference.setVerses(mVerses);
        String expected = String.format(Locale.getDefault(), "%1$s %2$d:%3$s", bookName, chapter,
                reference.getVersesString());
        assertEquals("must return expected format", expected, reference.toString());
    }

    @Test
    public void setChapter_negative() {
        Reference reference = new Reference(new Book("Hebreus"));
        reference.setChapter(-20);
        assertNull("must return no chapter", reference.getChapter());
    }

    @Test
    public void setChapter_zero() {
        Reference reference = new Reference(new Book("Hebreus"));
        reference.setChapter(0);
        assertNull("must return no chapter", reference.getChapter());
    }

    @Test
    public void setChapter_correct() {
        Reference reference = new Reference(new Book("Hebreus"));
        Integer expected = 20;
        reference.setChapter(expected);
        assertEquals("must return chapter", expected, reference.getChapter());
    }

    @Test
    public void setVerses_negative() {
        Reference reference = new Reference(new Book("Hebreus"));
        mVerses.add(-1000);
        reference.setVerses(mVerses);
        int expected = 0;
        assertEquals("must not accept negative", expected, reference.getVerses().size());
    }

    @Test
    public void setVerses_zero() {
        Reference reference = new Reference(new Book("Hebreus"));
        mVerses.add(0);
        reference.setVerses(mVerses);
        int expected = 0;
        assertEquals("must not accept zero", expected, reference.getVerses().size());
    }

    @Test
    public void setVerses_duplicated() {
        Reference reference = new Reference(new Book("Hebreus"));
        int verse = 3;
        mVerses.add(verse);
        mVerses.add(verse);
        mVerses.add(verse);
        reference.setVerses(mVerses);
        int expected = 1;
        assertEquals("must not accept duplicated", expected, reference.getVerses().size());
    }

    @Test
    public void setVerses_unsorted() {
        Reference reference = new Reference(new Book("Hebreus"));
        int verseB = 30;
        mVerses.add(verseB);
        int verseC = 115;
        mVerses.add(verseC);
        int verseA = 2;
        mVerses.add(verseA);
        reference.setVerses(mVerses);

        int i = 0;
        for (int verse : reference.getVerses()) {
            switch (i) {
                case 0:
                    assertEquals("must be in order", verseA, verse);
                    break;
                case 1:
                    assertEquals("must be in order", verseB, verse);
                    break;
                case 2:
                    assertEquals("must be in order", verseC, verse);
                    break;
            }
            i++;
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getVerses_unmodifiableSet() {
        Reference reference = new Reference(new Book("Ẽxodo"));
        mVerses.add(1);
        mVerses.add(10);
        reference.setVerses(mVerses);
        reference.getVerses().remove(0);
    }

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
        assertEquals("must return u,v,w-x,y,d format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_unsorted() {
        Reference reference = new Reference(new Book("Salmos"));
        int verse1 = 99;
        mVerses.add(verse1);
        int verse2 = 100;
        mVerses.add(verse2);
        int verse3 = 2;
        mVerses.add(verse3);
        reference.setVerses(mVerses);
        String expected = String.format(Locale.getDefault(), "%1$d,%2$d-%3$d", verse3, verse1, verse2);
        assertEquals("must return x,y-z sorted format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_parts() {
        Reference reference = new Reference(new Book("Eclesiastes"));
        char part = 'a';
        TreeSet<Character> parts = new TreeSet<>();
        parts.add(part);
        int verse = 3;
        SortedMap<Integer, SortedSet<Character>> verses = new TreeMap<>();
        verses.put(verse, parts);
        int verse2 = 4;
        verses.put(verse2, null);
        reference.setVerses(verses);
        String expected = String.format(Locale.getDefault(), "%1$d%2$s-%3$d", verse, part, verse2);
        assertEquals("must return xy format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_parts2() {
        Reference reference = new Reference(new Book("Eclesiastes"));
        SortedMap<Integer, SortedSet<Character>> verses = new TreeMap<>();
        verses.put(3, new TreeSet<>(Arrays.asList(new Character[]{'a'})));
        verses.put(4, null);
        verses.put(5, null);
        verses.put(7, new TreeSet<>(Arrays.asList(new Character[]{'c', 'd'})));
        verses.put(8, null);
        reference.setVerses(verses);

        String expected = "3a-5,7cd-8";
        assertEquals(expected, reference.getVersesString());
    }

    @After
    public void tearDown() {
        mVerses.clear();
    }

}

