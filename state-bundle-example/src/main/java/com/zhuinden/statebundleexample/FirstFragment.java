package com.zhuinden.statebundleexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by Owner on 2017. 03. 07..
 */

public class FirstFragment extends Fragment {
    public static final String TAG = "FirstFragment";

    FirstPresenter firstPresenter;

    @OnTextChanged(R.id.first_edittext)
    public void editTextChanged(Editable text) {
        firstPresenter.updateText(text.toString());
    }

    @BindView(R.id.first_edittext)
    EditText editText;

    @OnClick(R.id.first_button)
    public void clickFirst() {
        MainActivity.get(getContext()).goToSecond();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.firstPresenter = new FirstPresenter();
        if(savedInstanceState != null) {
            firstPresenter.restore(savedInstanceState.getParcelable("PRESENTER_STATE"));
        }
        firstPresenter.attach(this);
    }

    @Override
    public void onDestroyView() {
        firstPresenter.detach(this);
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("PRESENTER_STATE", firstPresenter.persist());
    }

    public void initialize(String text) {
        this.editText.setText(text);
    }
}
