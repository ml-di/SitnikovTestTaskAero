package ru.aeroidea.sitnikovtesttask.Database.Loader;

import android.content.Context;
import android.os.AsyncTask;
import java.util.List;
import ru.aeroidea.sitnikovtesttask.Activity.MainActivityView;
import ru.aeroidea.sitnikovtesttask.Adapter.BannerPagerAdapter;
import ru.aeroidea.sitnikovtesttask.Data.BannersData;
import ru.aeroidea.sitnikovtesttask.Database.AppDatabase;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.Presenter.MainFragmentPresenterInterface;

public class LoadBannersFromCache extends AsyncTask<Void, Void, List<BannersData>> {

    private MainFragmentPresenterInterface presenter;

    public LoadBannersFromCache(MainFragmentPresenterInterface presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onPostExecute(List<BannersData> bannersList) {
        super.onPostExecute(bannersList);

        if (bannersList != null) {
            final Context mContext = presenter.getView().getActivityContext();
            final BannerPagerAdapter bannerAdapter = new BannerPagerAdapter(bannersList, mContext);
            presenter.getView().getBannerViewPager().setAdapter(bannerAdapter);
        }
    }

    @Override
    protected List<BannersData> doInBackground(Void... voids) {

        final AppDatabase db = ((MainActivityView) presenter.getView().getActivityContext()).getDatabase();
        if (db.getOpenHelper().getReadableDatabase().isOpen() && db.bannersDao().getCount() > 0) {
            return db.bannersDao().getAllBanners();
        }

        return null;
    }
}
