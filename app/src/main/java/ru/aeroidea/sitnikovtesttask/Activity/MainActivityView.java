package ru.aeroidea.sitnikovtesttask.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import ru.aeroidea.sitnikovtesttask.Application;
import ru.aeroidea.sitnikovtesttask.Database.AppDatabase;
import ru.aeroidea.sitnikovtesttask.Fragment.BlankFragmentView;
import ru.aeroidea.sitnikovtesttask.Fragment.MainFragmentView;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.Presenter.MainActivityPresenterInterface;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.View.MainActivityViewInterface;
import ru.aeroidea.sitnikovtesttask.MVP.Presenter.MainActivityPresenter;
import ru.aeroidea.sitnikovtesttask.R;
import ru.aeroidea.sitnikovtesttask.Utils.AppBarLayoutBehavior;

public class MainActivityView extends AppCompatActivity implements MainActivityViewInterface {

    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private AppBarLayout appBar;
    private CollapsingToolbarLayout collapsingToolbar;
    private CoordinatorLayout contentLayout;

    private FragmentManager mFragmentManager;
    private Fragment mainFragment;
    private Fragment blankFragment;
    private MainActivityPresenterInterface presenter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            presenter.onClickBottomNavigationView(menuItem.getItemId());
            presenter.openFragment(getActualFragment(menuItem.getItemId()), false);
            return true;
        });
    }

    private void init () {
        mFragmentManager = getSupportFragmentManager();
        mainFragment = new MainFragmentView();
        blankFragment = new BlankFragmentView();

        presenter = new MainActivityPresenter();
        presenter.attachView(this);
        if (presenter.isViewAttached()) {
            presenter.viewIsReady();
            final int index = presenter.getView().getIndexBottomNavigationView();
            presenter.openFragment(getActualFragment(index), false);
            setTitleToolBar(getBottomNavigationViewSelectedTitle());
        }

        if (db == null || !db.isOpen())
            db = Application.getInstance().getDatabase();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter.isViewAttached() && isFinishing()) {
            presenter.detachView();
        }
        if(db != null && db.getOpenHelper().getReadableDatabase().isOpen()) {
            db.close();
        }
    }

    @Override
    public void onBackPressed()  {
        super.onBackPressed();
        mFragmentManager.popBackStack();
        hideBackArrowToolBar();
    }

    @Override
    public void initBottomNavigationView(int resId) {
        bottomNavigationView = findViewById(resId);
    }

    @Override
    public void initToolBar(int resId) {
        toolbar = findViewById(resId);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initAppBar(int resId) {
        appBar = findViewById(resId);
    }

    @Override
    public void initCollapsingToolBar(int resId) {
        collapsingToolbar = findViewById(resId);
    }

    @Override
    public void setTitleToolBar(String title) {
        collapsingToolbar.setTitle(title);
    }

    @Override
    public void showBackArrowToolBar() {
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
            toolbar.setNavigationOnClickListener(view -> {
                mFragmentManager.popBackStack();
                hideBackArrowToolBar();
            });
        }
    }

    @Override
    public void hideBackArrowToolBar() {
        if (toolbar != null) {
            toolbar.setNavigationIcon(null);
        }
    }

    @Override
    public void expandAppBar(boolean isExpand) {
        appBar.setExpanded(isExpand);

        final CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBar.getLayoutParams();
        final AppBarLayoutBehavior behavior = (AppBarLayoutBehavior) params.getBehavior();
        if (behavior != null) {
            behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
                @Override
                public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                    return isExpand;
                }
            });
            behavior.setEnabled(isExpand);
        }
    }

    @Override
    public int getIndexBottomNavigationView() {
        return bottomNavigationView.getSelectedItemId();
    }

    @Override
    public String getBottomNavigationViewSelectedTitle() {
        final int index = getIndexBottomNavigationView();
        return getTitleBottomNavigationView(index);
    }

    @Override
    public String getTitleBottomNavigationView(int index) {
        return bottomNavigationView.getMenu().findItem(index).getTitle().toString();
    }

    @Override
    public void initContentLayout(int resId) {
        contentLayout = findViewById(resId);
    }

    @Override
    public Fragment getActualFragment(int menuItem) {
        final Fragment view;
        if (menuItem == R.id.bnv_main) {
            view = mainFragment;
        } else {
            view = blankFragment;
        }
        return view;
    }

    @Override
    public MainActivityPresenterInterface getPresenter () {
        return presenter;
    }

    @Override
    public FragmentManager getActivityFragmentManager() {
        return mFragmentManager;
    }

    @Override
    public int getContentLayoutId() {
        return contentLayout.getId();
    }

    @Override
    public AppDatabase getDatabase() {
        return db;
    }

    @Override
    public Context getContext() {
        return this;
    }
}
