package ru.aeroidea.sitnikovtesttask.MVP.Interface.Presenter;

import androidx.fragment.app.Fragment;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.View.MainActivityViewInterface;
import ru.aeroidea.sitnikovtesttask.MVP.MVPPresenter;

public interface MainActivityPresenterInterface extends MVPPresenter<MainActivityViewInterface> {

    void onClickBottomNavigationView(int menuItem);
    void openFragment(Fragment fragment, boolean isBack);
    void adaptersClickItem(Fragment fragment, String title);
    void showProgressBar(int resId);
    void hideProgressBar();

}
