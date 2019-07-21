package ru.aeroidea.sitnikovtesttask.MVP.Interface.Presenter;

import java.util.List;
import ru.aeroidea.sitnikovtesttask.Data.BannersData;
import ru.aeroidea.sitnikovtesttask.Data.CollectionsData;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.View.MainFragmentViewInterface;
import ru.aeroidea.sitnikovtesttask.MVP.MVPPresenter;

public interface MainFragmentPresenterInterface extends MVPPresenter<MainFragmentViewInterface> {

    void setBannersCache(List<BannersData> bannersData);
    void setCollectionsCache(List<CollectionsData> collectionsData);

    String getData();
    List<BannersData> getBannersList(String json);
    List<CollectionsData> getCollectionsList(String json);
    void loadBannersCache();
    void loadCollectionsCache();

    void loadAndSetData();
}
