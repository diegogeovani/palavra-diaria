package br.com.palavra.presentation.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.palavra.R;
import br.com.palavra.database.DailyWordProvider;
import br.com.palavra.domain.model.DailyWord;
import br.com.palavra.domain.word.GetDailyWords;
import br.com.palavra.domain.async.UseCaseHandler;
import br.com.palavra.presentation.DailyWordContract;
import br.com.palavra.presentation.DailyWordPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements DailyWordContract.View {

    @BindView(R.id.text_daily_word) TextView mDailyWordText;
    @BindView(R.id.text_daily_word_ref) TextView mReferenceText;

    private DailyWordPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        mDailyWordText.setText(dailyWord.getMessage());
        mReferenceText.setText(dailyWord.getReference().toString());
    }

}
