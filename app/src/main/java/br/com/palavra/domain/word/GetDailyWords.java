package br.com.palavra.domain.word;

import br.com.palavra.domain.repository.DailyWordRepository;
import br.com.palavra.domain.model.DailyWord;
import br.com.palavra.domain.UseCase;

public class GetDailyWords extends UseCase<GetDailyWords.RequestValues, GetDailyWords.ResponseValues> {

    private final DailyWordRepository mRepository;
    private String mGetErrorMessage;

    public GetDailyWords(DailyWordRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DailyWord dailyWord = mRepository.getDailyWord();
        if (dailyWord == null) {
            getUseCaseCallback().onError();
        } else {
            getUseCaseCallback().onSuccess(new ResponseValues(dailyWord));
        }
    }

    public String getGetErrorMessage() {
        return mGetErrorMessage;
    }

    public void setGetErrorMessage(String errorMessage) {
        mGetErrorMessage = errorMessage;
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValues implements UseCase.ResponseValue {

        private final DailyWord mDailyWord;

        public ResponseValues(DailyWord dailyWord) {
            mDailyWord = dailyWord;
        }

        public DailyWord getDailyWord() {
            return mDailyWord;
        }
    }

}
