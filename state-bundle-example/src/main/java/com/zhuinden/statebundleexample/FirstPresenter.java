package com.zhuinden.statebundleexample;

import com.zhuinden.statebundle.StateBundle;

/**
 * Created by Zhuinden on 2017.03.08..
 */

public class FirstPresenter {
    static final String TEXT = "TEXT";

    String text;

    FirstFragment firstFragment;

    public void attach(FirstFragment fragment) {
        firstFragment = fragment;
        firstFragment.initialize(text);
    }

    public boolean hasView() {
        return firstFragment != null;
    }

    public void detach(FirstFragment fragment) {
        this.firstFragment = null;
    }

    public void updateText(String text) {
        this.text = text;
    }

    public void restore(StateBundle stateBundle) {
        this.text = stateBundle.getString(TEXT);
    }

    public StateBundle persist() {
        StateBundle stateBundle = new StateBundle();
        stateBundle.putString(TEXT, text);
        return stateBundle;
    }
}
