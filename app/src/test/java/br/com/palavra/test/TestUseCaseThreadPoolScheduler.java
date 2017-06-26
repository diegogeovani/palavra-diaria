package br.com.palavra.test;

import br.com.palavra.domain.usecase.UseCase;
import br.com.palavra.domain.usecase.UseCaseScheduler;

public final class TestUseCaseThreadPoolScheduler implements UseCaseScheduler {

    @Override
    public void execute(Runnable runnable) {
        runnable.run();
    }

    @Override
    public <V extends UseCase.ResponseValue> void notifyResponse(V response, UseCase.UseCaseCallback<V> useCaseCallback) {
        useCaseCallback.onSuccess(response);
    }

    @Override
    public <V extends UseCase.ResponseValue> void onError(UseCase.UseCaseCallback<V> useCaseCallback) {
        useCaseCallback.onError();
    }

}

