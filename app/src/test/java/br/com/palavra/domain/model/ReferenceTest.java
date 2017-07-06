package br.com.palavra.domain.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ReferenceTest {

    @Test
    public void getVersesString_case() {
        fail("FINISH ME");
    }

    @Test
    public void toString_case() {
        String bookName = "Atos";
        Reference reference = new Reference(new Book(bookName));
        reference.setChapter(2);
        reference.setVerses(new int[]{1});
        String expected = bookName + " 2:9";
        assertEquals("toString must be as expected", expected, reference.toString());
    }

}

