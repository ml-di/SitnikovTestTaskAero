package ru.aeroidea.sitnikovtesttask.MVP.Interface.Presenter;

import java.util.ArrayList;
import ru.aeroidea.sitnikovtesttask.Data.BannersData;
import ru.aeroidea.sitnikovtesttask.Data.CollectionsData;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.View.MainFragmentViewInterface;
import ru.aeroidea.sitnikovtesttask.MVP.MVPPresenter;

public interface MainFragmentPresenterInterface extends MVPPresenter<MainFragmentViewInterface> {

    String getData();
    ArrayList<BannersData> getBannersList(String json);
    ArrayList<CollectionsData> getCollectionsList(String json);

    void loadAndSetData();
}
