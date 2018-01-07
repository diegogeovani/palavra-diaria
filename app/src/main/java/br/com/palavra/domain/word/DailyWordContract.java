package br.com.palavra.domain.word;

import br.com.palavra.domain.Presenter;
import br.com.palavra.domain.model.DailyWord;
import br.com.palavra.domain.BaseView;

public interface DailyWordContract {

    interface View extends BaseView {
        void showDailyWord(DailyWord dailyWord);
    }

    interface UserActions extends Presenter {

    }

}
