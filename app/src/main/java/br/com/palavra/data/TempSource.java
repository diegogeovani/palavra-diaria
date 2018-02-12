package br.com.palavra.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.palavra.R;
import br.com.palavra.domain.model.Book;
import br.com.palavra.domain.model.DailyWord;
import br.com.palavra.domain.model.Reference;
import br.com.palavra.domain.model.Testament;
import br.com.palavra.domain.model.Verse;

// TODO: temporary
final class TempSource {

    private TempSource() {

    }

    public static List<DailyWord> getDws(Context context) {
        ArrayList<DailyWord> dailyWords = new ArrayList<>();

        Book book = new Book(context.getString(R.string.book_psalms), Testament.OLD);
        book.setChaptersCount(150);
        Reference ref = new Reference(book);
        ref.setChapter(68);
        ref.addVerse(new Verse(20));
        DailyWord dw = new DailyWord();
        dw.setMessage(context.getString(R.string.daily_word_psalms_68_20));
        dw.setReference(ref);
        dailyWords.add(dw);

        book = new Book(context.getString(R.string.book_hebrews), Testament.NEW);
        book.setChaptersCount(13);
        ref = new Reference(book);
        ref.setChapter(13);
        ref.addVerse(new Verse(8));
        dw = new DailyWord();
        dw.setMessage(context.getString(R.string.book_hebrews));
        dw.setReference(ref);
        dailyWords.add(dw);

        return dailyWords;
    }

}
