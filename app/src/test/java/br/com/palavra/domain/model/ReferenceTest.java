package br.com.palavra.domain.model;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ReferenceTest {

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
        reference.addVerse(new Verse(8));
        reference.addVerse(new Verse(9));
        reference.addVerse(new Verse(10));
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
        reference.addVerse(new Verse(11));
        reference.addVerse(new Verse(15));
        reference.addVerse(new Verse(104));
        reference.addVerse(new Verse(105));
        reference.addVerse(new Verse(106));
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
        reference.addVerse(new Verse(-1000));
        int expected = 0;
        assertEquals("must not accept negative", expected, reference.getVerses().size());
    }

    @Test
    public void setVerses_zero() {
        Reference reference = new Reference(new Book("Hebreus"));
        reference.addVerse(new Verse(0));
        int expected = 0;
        assertEquals("must not accept zero", expected, reference.getVerses().size());
    }

    @Test
    public void setVerses_duplicated() {
        Reference reference = new Reference(new Book("Hebreus"));
        Verse verse = new Verse(3);
        reference.addVerse(verse);
        reference.addVerse(verse);
        reference.addVerse(verse);
        int expected = 1;
        assertEquals("must not accept duplicated", expected, reference.getVerses().size());
    }

    @Test
    public void setVerses_unsorted() {
        Reference reference = new Reference(new Book("Hebreus"));
        Verse verseB = new Verse(30);
        reference.addVerse(verseB);
        Verse verseC = new Verse(155);
        reference.addVerse(verseC);
        Verse verseA = new Verse(2);
        reference.addVerse(verseA);

        int i = 0;
        for (Verse verse : reference.getVerses()) {
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
        Verse verse = new Verse(1);
        reference.addVerse(verse);
        reference.addVerse(new Verse(10));
        reference.getVerses().remove(verse);
    }

    @Test
    public void getVersesString_noVerses() {
        Reference reference = new Reference(new Book("Hebreus"));
        assertEquals("must return empty string", "", reference.getVersesString());
    }

    @Test
    public void getVersesString_singleVerse() {
        Verse verse = new Verse(1);
        Reference reference = new Reference(new Book("Hebreus"));
        reference.addVerse(verse);
        String expected = String.valueOf(verse);
        assertEquals("must return same verse as string", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_twoVersesRange() {
        Reference reference = new Reference(new Book("Hebreus"));
        Verse verse1 = new Verse(1);
        reference.addVerse(verse1);
        Verse verse2 = new Verse(2);
        reference.addVerse(verse2);
        String expected = String.format(Locale.getDefault(), "%1$d-%2$d", verse1.getNumber(), verse2.getNumber());
        assertEquals("must return x-y format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_twoVersesDistinct() {
        Reference reference = new Reference(new Book("Hebreus"));
        Verse verse1 = new Verse(1);
        reference.addVerse(verse1);
        Verse verse2 = new Verse(9);
        reference.addVerse(verse2);
        String expected = String.format(Locale.getDefault(), "%1$d,%2$d", verse1.getNumber(), verse2.getNumber());
        assertEquals("must return x,y format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_multipleVersesRange() {
        Reference reference = new Reference(new Book("Hebreus"));
        Verse verse1 = new Verse(2);
        reference.addVerse(verse1);
        reference.addVerse(new Verse(3));
        Verse verse3 = new Verse(4);
        reference.addVerse(verse3);
        String expected = String.format(Locale.getDefault(), "%1$d-%2$d", verse1.getNumber(), verse3.getNumber());
        assertEquals("must return x-y format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_multipleVersesDistinct() {
        Reference reference = new Reference(new Book("Hebreus"));
        Verse verse1 = new Verse(2);
        reference.addVerse(verse1);
        Verse verse2 = new Verse(6);
        reference.addVerse(verse2);
        Verse verse3 = new Verse(10);
        reference.addVerse(verse3);
        String expected = String.format(Locale.getDefault(), "%1$d,%2$d,%3$d", verse1.getNumber(), verse2.getNumber(), verse3.getNumber());
        assertEquals("must return x,y,z format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_multipleVersesMixed() {
        Reference reference = new Reference(new Book("Hebreus"));
        Verse verse1 = new Verse(2);
        reference.addVerse(verse1);
        Verse rangeStartVerse = new Verse(6);
        reference.addVerse(rangeStartVerse);
        reference.addVerse(new Verse(7));
        reference.addVerse(new Verse(8));
        reference.addVerse(new Verse(9));
        Verse rangeEndVerse = new Verse(10);
        reference.addVerse(rangeEndVerse);
        Verse verse4 = new Verse(13);
        reference.addVerse(verse4);
        String expected = String.format(Locale.getDefault(), "%1$d,%2$d-%3$d,%4$d", verse1.getNumber(), rangeStartVerse.getNumber(),
                rangeEndVerse.getNumber(), verse4.getNumber());
        assertEquals("must return w,x-y,z format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_multipleVersesMixed2() {
        Reference reference = new Reference(new Book("Hebreus"));
        Verse verseA = new Verse(7);
        reference.addVerse(verseA);
        reference.addVerse(new Verse(8));
        reference.addVerse(new Verse(9));
        Verse verseB = new Verse(10);
        reference.addVerse(verseB);
        Verse verseC = new Verse(15);
        reference.addVerse(verseC);
        reference.addVerse(new Verse(16));
        Verse verseD = new Verse(17);
        reference.addVerse(verseD);
        String expected = String.format(Locale.getDefault(), "%1$d-%2$d,%3$d-%4$d", verseA.getNumber(), verseB.getNumber(),
                verseC.getNumber(), verseD.getNumber());
        assertEquals("must return w-x,y-z format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_multipleVersesMixed3() {
        Reference reference = new Reference(new Book("Salmos"));
        Verse verseA = new Verse(101);
        reference.addVerse(verseA);
        Verse verseB = new Verse(103);
        reference.addVerse(verseB);
        Verse mockVerse = new Verse(1004);
        reference.addVerse(mockVerse);
        reference.addVerse(new Verse(1005));
        Verse mockVerse4 = new Verse(1006);
        reference.addVerse(mockVerse4);
        Verse mockVerse2 = new Verse(1100);
        reference.addVerse(mockVerse2);
        Verse mockVerse3 = new Verse(9999);
        reference.addVerse(mockVerse3);
        String expected = String.format(Locale.getDefault(), "%1$d,%2$d,%3$d-%4$d,%5$d,%6$d", verseA.getNumber(), verseB.getNumber(),
                mockVerse.getNumber(), mockVerse4.getNumber(), mockVerse2.getNumber(), mockVerse3.getNumber());
        assertEquals("must return u,v,w-x,y,d format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_unsorted() {
        Reference reference = new Reference(new Book("Salmos"));
        Verse verse1 = new Verse(99);
        reference.addVerse(verse1);
        Verse verse2 = new Verse(100);
        reference.addVerse(verse2);
        Verse verse3 = new Verse(2);
        reference.addVerse(verse3);
        String expected = String.format(Locale.getDefault(), "%1$d,%2$d-%3$d", verse3.getNumber(), verse1.getNumber(), verse2.getNumber());
        assertEquals("must return x,y-z sorted format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_parts() {
        Reference reference = new Reference(new Book("Eclesiastes"));
        char part = 'a';
        Verse verse = new Verse(3);
        reference.addVerse(verse, part);
        Verse verse2 = new Verse(4);
        reference.addVerse(verse2);

        String expected = String.format(Locale.getDefault(), "%1$d%2$s-%3$d", verse.getNumber(), part, verse2.getNumber());
        assertEquals("must return xy format", expected, reference.getVersesString());
    }

    @Test
    public void getVersesString_parts2() {
        Reference reference = new Reference(new Book("Eclesiastes"));
        reference.addVerse(new Verse(3), 'a');
        reference.addVerse(new Verse(4));
        reference.addVerse(new Verse(5));
        reference.addVerse(new Verse(7), new Character[]{'c', 'd'});
        reference.addVerse(new Verse(8));
        String expected = "3a-5,7cd-8";
        assertEquals(expected, reference.getVersesString());
    }

}
