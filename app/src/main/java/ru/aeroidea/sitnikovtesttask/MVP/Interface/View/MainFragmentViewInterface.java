package ru.aeroidea.sitnikovtesttask.MVP.Interface.View;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.Presenter.MainFragmentPresenterInterface;
import ru.aeroidea.sitnikovtesttask.MVP.MVPView;

public interface MainFragmentViewInterface extends MVPView {

    void initViewPager(int resId);
    void initTabLayout(int resId);
    void initRecyclerView(int resId);

    Context getActivityContext();
    ViewPager getBannerViewPager();
    RecyclerView getCollectionsRecyclerView();
    MainFragmentPresenterInterface getPresenter();
}
