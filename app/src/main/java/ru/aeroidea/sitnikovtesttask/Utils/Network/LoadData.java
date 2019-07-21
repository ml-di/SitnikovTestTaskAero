package ru.aeroidea.sitnikovtesttask.Utils.Network;

import android.content.Context;
import android.os.AsyncTask;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ru.aeroidea.sitnikovtesttask.Activity.MainActivityView;
import ru.aeroidea.sitnikovtesttask.Adapter.BannerPagerAdapter;
import ru.aeroidea.sitnikovtesttask.Adapter.CollectionsRecyclerViewAdapter;
import ru.aeroidea.sitnikovtesttask.Data.BannersData;
import ru.aeroidea.sitnikovtesttask.Data.CollectionsData;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.Presenter.MainFragmentPresenterInterface;

public class LoadData extends AsyncTask<Void, Void, Boolean> {

    private List<BannersData> bannerList;
    private List<CollectionsData> collectionsList;
    private MainFragmentPresenterInterface presenter;

    public LoadData(MainFragmentPresenterInterface presenter) {

        this.presenter = presenter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        final Context mContext = presenter.getView().getActivityContext();
        ((MainActivityView) mContext).getPresenter().showProgressBar(-1);
    }

    @Override
    protected void onPostExecute(Boolean isUpdate) {
        super.onPostExecute(isUpdate);

        final Context mContext = presenter.getView().getActivityContext();
        if (isUpdate) {
            if (bannerList != null) {
                final BannerPagerAdapter bannerAdapter = new BannerPagerAdapter(bannerList, mContext);
                presenter.getView().getBannerViewPager().setAdapter(bannerAdapter);
            }

            if (collectionsList != null) {
                final RecyclerView.Adapter collectionsAdapter = new CollectionsRecyclerViewAdapter(collectionsList);
                presenter.getView().getCollectionsRecyclerView().setAdapter(collectionsAdapter);
            }
        } else {
            presenter.loadBannersCache();
            presenter.loadCollectionsCache();
        }

        ((MainActivityView) mContext).getPresenter().hideProgressBar();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        final String json = presenter.getData();
        bannerList = presenter.getBannersList(json);
        collectionsList = presenter.getCollectionsList(json);

        if (bannerList != null && bannerList.size() > 0) {
            presenter.setBannersCache(bannerList);
        }

        if (collectionsList != null && collectionsList.size() > 0) {
            presenter.setCollectionsCache(collectionsList);
        }

        return json != null;
    }
}
