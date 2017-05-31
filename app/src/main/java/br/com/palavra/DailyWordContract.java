package br.com.palavra;

import br.com.palavra.domain.model.DailyWord;

public interface DailyWordContract {

    interface View {
        void showDailyWord(DailyWord dailyWord);
    }

    interface UserActions extends Presenter {

    }

}
