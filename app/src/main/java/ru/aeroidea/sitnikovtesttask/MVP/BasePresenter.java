package ru.aeroidea.sitnikovtesttask.MVP;

public abstract class BasePresenter<T extends MVPView> implements MVPPresenter<T> {

    private T view;

    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public T getView() {
        return view;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

}
