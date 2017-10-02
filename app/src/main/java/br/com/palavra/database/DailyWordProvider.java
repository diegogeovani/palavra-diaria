package br.com.palavra.database;

import android.content.Context;

import br.com.palavra.domain.model.DailyWord;
import br.com.palavra.domain.repository.DailyWordRepository;

public class DailyWordProvider implements DailyWordRepository {

    private final Context mContext;

    public DailyWordProvider(Context context) {
        mContext = context;
    }

    @Override
    public DailyWord getDailyWord() {
        DailyWord dailyWord = TempSource.getDws(mContext).get(0);
        return dailyWord;
    }

}
