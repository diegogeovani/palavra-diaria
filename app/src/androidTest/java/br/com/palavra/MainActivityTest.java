package br.com.palavra;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.TypedValue;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.palavra.presentation.ui.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class, false, true);

    @Test
    public void onCreate_alwaysShowDailyWord() {
        onView(withId(R.id.text_daily_word)).check(matches(withText(not(isEmptyOrNullString()))));
        onView(withId(R.id.text_daily_word_ref)).check(matches(withText(not(isEmptyOrNullString()))));
    }

    @Test
    public void dailyWord_scrollable() {
        onView(withId(R.id.text_daily_word)).check(matches(withParent(withId(R.id.viewgroup_main_daily_word))));
        onView(withId(R.id.text_daily_word_ref)).check(matches(withParent(withId(R.id.viewgroup_main_daily_word))));
        onView(withId(R.id.viewgroup_main_daily_word)).check(matches(withParent(withId(R.id.scroll))));
    }

    @Test
    public void dailyWordViews_textSizes() {
        MainActivity activity = mActivityRule.getActivity();
        TextView dailyWordView = (TextView) activity.findViewById(R.id.text_daily_word);
        float dwTextSize = dailyWordView.getTextSize();
        TextView refView = (TextView) activity.findViewById(R.id.text_daily_word_ref);
        float refTextSize = refView.getTextSize();

        float expected = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20,
                activity.getResources().getDisplayMetrics());
        assertEquals("must be the default size", expected, dwTextSize, 0);
        expected = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14,
                activity.getResources().getDisplayMetrics());
        assertEquals("must be the default size", expected, refTextSize, 0);
    }

}
