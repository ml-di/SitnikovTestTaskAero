package ru.aeroidea.sitnikovtesttask.Fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;
import java.util.Objects;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.Presenter.MainFragmentPresenterInterface;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.View.MainFragmentViewInterface;
import ru.aeroidea.sitnikovtesttask.MVP.Presenter.MainFragmentPresenter;
import ru.aeroidea.sitnikovtesttask.R;

public class MainFragmentView extends Fragment implements MainFragmentViewInterface {

    private Context mContext;
    private MainFragmentPresenterInterface presenter;
    private ViewPager bannerViewPager;
    private RecyclerView collectionsRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        presenter = new MainFragmentPresenter();
        presenter.attachView(this);
        if (presenter.isViewAttached()) {
            presenter.viewIsReady();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (presenter.isViewAttached()) {
            presenter.detachView();
        }
    }

    @Override
    public void initViewPager(int resId) {
        bannerViewPager = Objects.requireNonNull(getView()).findViewById(resId);
    }

    @Override
    public void initTabLayout(int resId) {
        final TabLayout viewPagerIndicator = Objects.requireNonNull(getView()).findViewById(resId);
        if (bannerViewPager != null) {
            viewPagerIndicator.setupWithViewPager(bannerViewPager);
        }
    }

    @Override
    public void initRecyclerView(int resId) {
        collectionsRecyclerView = Objects.requireNonNull(getView()).findViewById(resId);
        final RecyclerView.LayoutManager collectionsRecyclerViewLayoutManger = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        collectionsRecyclerView.setHasFixedSize(true);
        collectionsRecyclerView.setLayoutManager(collectionsRecyclerViewLayoutManger);
    }

    @Override
    public Context getActivityContext() {
        return mContext;
    }

    @Override
    public ViewPager getBannerViewPager() {
        return bannerViewPager;
    }

    @Override
    public RecyclerView getCollectionsRecyclerView() {
        return collectionsRecyclerView;
    }

    @Override
    public MainFragmentPresenterInterface getPresenter() {
        return presenter;
    }
}
