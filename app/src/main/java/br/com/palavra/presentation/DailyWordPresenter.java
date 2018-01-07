package br.com.palavra.presentation;

import br.com.palavra.domain.word.GetDailyWords;
import br.com.palavra.domain.UseCase;
import br.com.palavra.domain.async.UseCaseHandler;

public class DailyWordPresenter implements DailyWordContract.UserActions {

    private final DailyWordContract.View mView;
    private final UseCaseHandler mUseCaseHandler;
    private final GetDailyWords mGetUseCase;

    public DailyWordPresenter(DailyWordContract.View view, UseCaseHandler useCaseHandler, GetDailyWords getUseCase) {
        mView = view;
        mUseCaseHandler = useCaseHandler;
        mGetUseCase = getUseCase;
    }

    @Override
    public void onUiVisible() {
        GetDailyWords.RequestValues requestValues = new GetDailyWords.RequestValues();
        mUseCaseHandler.execute(mGetUseCase, requestValues, new UseCase.UseCaseCallback<GetDailyWords.ResponseValues>() {
            @Override
            public void onSuccess(GetDailyWords.ResponseValues response) {
                mView.showDailyWord(response.getDailyWord());
            }

            @Override
            public void onError() {
                mView.showError(mGetUseCase.getGetErrorMessage());
            }
        });
    }

}
