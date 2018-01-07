package br.com.palavra.util;

import org.junit.Test;

import java.util.Locale;

import br.com.palavra.domain.util.CountrySettings;

import static org.junit.Assert.assertEquals;

public class CountrySettingsTest {

    @Test
    public void locale() {
        Locale expected = new Locale("pt", "BR");
        assertEquals("must return Brazil locale", expected, CountrySettings.LOCALE);
    }

}
