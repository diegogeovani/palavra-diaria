package br.com.palavra;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.palavra.domain.repository.DailyWordRepository;
import br.com.palavra.domain.model.DailyWord;
import br.com.palavra.domain.word.GetDailyWords;
import br.com.palavra.domain.async.UseCaseHandler;
import br.com.palavra.presentation.DailyWordContract;
import br.com.palavra.presentation.DailyWordPresenter;
import br.com.palavra.test.TestUseCaseThreadPoolScheduler;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DailyWordPresenterTest {

    @Mock DailyWordContract.View mMockView;
    @Mock DailyWordRepository mMockRepository;

    @Test
    public void onUiVisible_repoSuccess() {
        DailyWord mockDailyWord = new DailyWord();
        when(mMockRepository.getDailyWord()).thenReturn(mockDailyWord);
        UseCaseHandler useCaseHandler = new UseCaseHandler(new TestUseCaseThreadPoolScheduler());
        GetDailyWords useCase = new GetDailyWords(mMockRepository);
        DailyWordPresenter presenter = new DailyWordPresenter(mMockView, useCaseHandler, useCase);
        presenter.onUiVisible();

        verify(mMockRepository, times(1)).getDailyWord();
        verify(mMockView, times(1)).showDailyWord(mockDailyWord);
    }

    @Test
    public void onUiVisible_repoFailure() {
        UseCaseHandler useCaseHandler = new UseCaseHandler(new TestUseCaseThreadPoolScheduler());
        GetDailyWords useCase = new GetDailyWords(mMockRepository);
        String errorMessage = "error 123";
        useCase.setGetErrorMessage(errorMessage);
        DailyWordPresenter presenter = new DailyWordPresenter(mMockView, useCaseHandler, useCase);
        presenter.onUiVisible();

        verify(mMockRepository, times(1)).getDailyWord();
        verify(mMockView, times(1)).showError(errorMessage);
    }

}
