package br.com.palavra;

import br.com.palavra.database.DailyWordRepository;

class DailyWordPresenter implements DailyWordContract.UserActions {

    private final DailyWordContract.View mView;
    private final DailyWordRepository mRepository;

    DailyWordPresenter(DailyWordContract.View view, DailyWordRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void onUiVisible() {
        mView.showDailyWord(mRepository.getDailyWord());
    }

}
