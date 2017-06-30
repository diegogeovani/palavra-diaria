package br.com.palavra;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class, false, true);

    @Test
    public void onCreate_alwaysShowDailyWord() {
        onView(withId(R.id.text_daily_word)).check(matches(withText(not(isEmptyOrNullString()))));
    }

    @Test
    public void dailyWordTextView_scrollable() {
        onView(withId(R.id.text_daily_word)).check(matches(withParent(withId(R.id.viewgroup_main_daily_word))));
        onView(withId(R.id.viewgroup_main_daily_word)).check(matches(withParent(withId(R.id.scroll))));
    }

}
