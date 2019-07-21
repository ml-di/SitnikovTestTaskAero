package ru.aeroidea.sitnikovtesttask.MVP.Interface.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import ru.aeroidea.sitnikovtesttask.Database.AppDatabase;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.Presenter.MainActivityPresenterInterface;
import ru.aeroidea.sitnikovtesttask.MVP.MVPView;

public interface MainActivityViewInterface extends MVPView {

    void initBottomNavigationView(int resId);
    void initToolBar(int resId);
    void initAppBar(int resId);
    void initContentLayout(int resId);
    void initCollapsingToolBar(int resId);

    void setTitleToolBar(String title);

    void showBackArrowToolBar();
    void hideBackArrowToolBar();

    void expandAppBar(boolean isExpand);

    String getBottomNavigationViewSelectedTitle();
    int getIndexBottomNavigationView();
    int getContentLayoutId();
    String getTitleBottomNavigationView(int index);
    Fragment getActualFragment(int menuItem);
    MainActivityPresenterInterface getPresenter();
    FragmentManager getActivityFragmentManager();
    AppDatabase getDatabase();
}
