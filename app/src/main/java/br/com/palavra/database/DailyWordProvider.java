package br.com.palavra.database;

import android.content.Context;

import br.com.palavra.R;
import br.com.palavra.domain.model.DailyWord;

public class DailyWordProvider implements DailyWordRepository {

    private final Context mContext;

    public DailyWordProvider(Context context) {
        mContext = context;
    }

    @Override
    public DailyWord getDailyWord() {
        DailyWord dailyWord = new DailyWord();
        dailyWord.setMessage(mContext.getString(R.string.dw_1));
        return dailyWord;
    }

}
