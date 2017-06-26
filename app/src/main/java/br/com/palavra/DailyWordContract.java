package br.com.palavra;

import br.com.palavra.domain.model.DailyWord;
import br.com.palavra.presentation.ui.BaseView;

public interface DailyWordContract {

    interface View extends BaseView {
        void showDailyWord(DailyWord dailyWord);
    }

    interface UserActions extends Presenter {

    }

}
