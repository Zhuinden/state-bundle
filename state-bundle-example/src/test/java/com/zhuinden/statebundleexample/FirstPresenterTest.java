package com.zhuinden.statebundleexample;

import com.zhuinden.statebundle.StateBundle;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FirstPresenterTest {
    @Mock
    public FirstFragment firstFragment;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    FirstPresenter firstPresenter;

    private static final String TEST_VALUE = "TEST_VALUE";

    @Before
    public void setup() {
        firstPresenter = new FirstPresenter();
    }

    @Test
    public void attach()
            throws Exception {
        firstPresenter.updateText(TEST_VALUE);
        firstPresenter.attach(firstFragment);
        Mockito.verify(firstFragment, Mockito.only()).initialize(TEST_VALUE);
    }

    @Test
    public void detach()
            throws Exception {
        firstPresenter.updateText(TEST_VALUE);
        firstPresenter.attach(firstFragment);
        Mockito.verify(firstFragment, Mockito.only()).initialize(TEST_VALUE);
        assertThat(firstPresenter.hasView()).isTrue();
        firstPresenter.detach(firstFragment);
        firstPresenter.updateText("BLAH");
        Mockito.verify(firstFragment, Mockito.only()).initialize(TEST_VALUE);
        assertThat(firstPresenter.hasView()).isFalse();
    }

    @Test
    public void persist()
            throws Exception {
        firstPresenter.updateText(TEST_VALUE);
        StateBundle stateBundle = firstPresenter.persist();
        assertThat(stateBundle.getString(FirstPresenter.TEXT)).isEqualTo(TEST_VALUE);
    }

    @Test
    public void restore()
            throws Exception {
        firstPresenter.updateText("");
        StateBundle stateBundle = new StateBundle();
        stateBundle.putString(FirstPresenter.TEXT, TEST_VALUE);
        firstPresenter.restore(stateBundle);
        firstPresenter.attach(firstFragment);
        Mockito.verify(firstFragment).initialize(TEST_VALUE);
    }
}