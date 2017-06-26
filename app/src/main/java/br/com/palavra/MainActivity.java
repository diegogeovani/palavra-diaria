package br.com.palavra;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.palavra.database.DailyWordProvider;
import br.com.palavra.domain.model.DailyWord;
import br.com.palavra.domain.usecase.GetDailyWords;
import br.com.palavra.domain.usecase.UseCaseHandler;

public class MainActivity extends Activity implements DailyWordContract.View {

    private DailyWordPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetDailyWords getDailyWords = new GetDailyWords(new DailyWordProvider(this));
        mPresenter = new DailyWordPresenter(this, UseCaseHandler.getInstance(), getDailyWords);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onUiVisible();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showDailyWord(DailyWord dailyWord) {
        TextView textView = (TextView) findViewById(R.id.text_daily_word);
        textView.setText(dailyWord.getMessage());
    }

}
