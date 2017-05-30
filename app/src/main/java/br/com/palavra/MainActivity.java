package br.com.palavra;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.palavra.database.DailyWordProvider;
import br.com.palavra.domain.DailyWord;

public class MainActivity extends Activity implements DailyWordContract.View {

    private DailyWordPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new DailyWordPresenter(this, new DailyWordProvider(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onUiVisible();
    }

    @Override
    public void showDailyWord(DailyWord dailyWord) {
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(dailyWord.getMessage());
    }


}
