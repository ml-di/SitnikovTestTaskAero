package ru.aeroidea.sitnikovtesttask.Fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Objects;
import ru.aeroidea.sitnikovtesttask.Activity.MainActivityView;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.Presenter.BlankFragmentPresenterInterface;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.View.BlankFragmentViewInterface;
import ru.aeroidea.sitnikovtesttask.MVP.Presenter.BlankFragmentPresenter;
import ru.aeroidea.sitnikovtesttask.R;

public class BlankFragmentView extends Fragment implements BlankFragmentViewInterface {

    private BlankFragmentPresenterInterface presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        presenter = new BlankFragmentPresenter();
        presenter.attachView(this);
        if (presenter.isViewAttached()) {
            presenter.viewIsReady();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivityView) Objects.requireNonNull(getActivity())).expandAppBar(false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (presenter.isViewAttached()) {
            presenter.detachView();
        }

        ((MainActivityView) Objects.requireNonNull(getActivity())).expandAppBar(true);

        final String title = ((MainActivityView) getActivity()).getBottomNavigationViewSelectedTitle();
        ((MainActivityView) getActivity()).setTitleToolBar(title);
    }
}
