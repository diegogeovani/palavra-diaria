package br.com.palavra.domain.async;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import br.com.palavra.domain.UseCase;
import br.com.palavra.domain.UseCaseScheduler;

/**
 * This singleton class will make sure that each use case operation gets a background thread.
 * <p/>
 * Created by dmilicic on 7/29/15.
 */
public class UseCaseThreadPoolScheduler implements UseCaseScheduler {

    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>();
    // This is a singleton
    private static volatile UseCaseThreadPoolScheduler sThreadExecutor;
    private ThreadPoolExecutor mThreadPoolExecutor;

    private UseCaseThreadPoolScheduler() {
        mThreadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TIME_UNIT,
                WORK_QUEUE);
    }

    /**
     * Returns a singleton instance of this executor. If the executor is not initialized then it initializes it and returns
     * the instance.
     */
    public static UseCaseThreadPoolScheduler getInstance() {
        if (sThreadExecutor == null) {
            sThreadExecutor = new UseCaseThreadPoolScheduler();
        }

        return sThreadExecutor;
    }

    @Override
    public void execute(Runnable runnable) {
        mThreadPoolExecutor.submit(runnable);
    }

    @Override
    public <V extends UseCase.ResponseValue> void notifyResponse(V response, UseCase.UseCaseCallback<V> useCaseCallback) {
        mThreadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onSuccess(response);
            }
        });
    }

    @Override
    public <V extends UseCase.ResponseValue> void onError(UseCase.UseCaseCallback<V> useCaseCallback) {
        mThreadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onError();
            }
        });
    }

}
