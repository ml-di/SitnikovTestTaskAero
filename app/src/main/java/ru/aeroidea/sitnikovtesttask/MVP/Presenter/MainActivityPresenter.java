package ru.aeroidea.sitnikovtesttask.MVP.Presenter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import ru.aeroidea.sitnikovtesttask.Fragment.ProgressBarFragment;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.Presenter.MainActivityPresenterInterface;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.View.MainActivityViewInterface;
import ru.aeroidea.sitnikovtesttask.MVP.BasePresenter;
import ru.aeroidea.sitnikovtesttask.R;

public class MainActivityPresenter extends BasePresenter<MainActivityViewInterface> implements MainActivityPresenterInterface {

    @Override
    public void viewIsReady() {
        getView().initBottomNavigationView(R.id.bottomNavigationView);
        getView().initToolBar(R.id.toolBar);
        getView().initContentLayout(R.id.contentLayout);
        getView().initAppBar(R.id.appBar);
        getView().initCollapsingToolBar(R.id.collapsingToolBar);

        getView().expandAppBar(true);
    }

    @Override
    public void onClickBottomNavigationView(int menuItem) {
        final String title = getView().getTitleBottomNavigationView(menuItem);
        getView().setTitleToolBar(title);
    }

    @Override
    public void openFragment(Fragment fragment, boolean isBack) {

        final FragmentManager fragmentManager = getView().getActivityFragmentManager();

        if (isBack) {
            fragmentManager
                    .beginTransaction()
                    .addToBackStack("BLANK")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(getView().getContentLayoutId(), fragment)
                    .commit();
            getView().showBackArrowToolBar();
        } else {
            fragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(getView().getContentLayoutId(), fragment)
                    .commit();
            getView().hideBackArrowToolBar();
        }
    }

    @Override
    public void showProgressBar(int resId) {

        if (resId == -1)
            resId = getView().getContentLayoutId();

        getView()
                .getActivityFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .add(resId, new ProgressBarFragment(), "PROGRESS")
                .commit();
    }

    @Override
    public void hideProgressBar() {
        Fragment progressFragment = getView().getActivityFragmentManager().findFragmentByTag("PROGRESS");
        if (progressFragment != null) {
            getView()
                    .getActivityFragmentManager()
                    .beginTransaction()
                    .remove(progressFragment)
                    .commit();
        }
    }

    @Override
    public void adaptersClickItem(Fragment fragment, String title) {
        openFragment(fragment, true);
        getView().setTitleToolBar(title);
    }
}
