package ru.aeroidea.sitnikovtesttask.Database.Loader;

import android.os.AsyncTask;
import java.util.List;
import ru.aeroidea.sitnikovtesttask.Activity.MainActivityView;
import ru.aeroidea.sitnikovtesttask.Adapter.CollectionsRecyclerViewAdapter;
import ru.aeroidea.sitnikovtesttask.Data.CollectionsData;
import ru.aeroidea.sitnikovtesttask.Database.AppDatabase;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.Presenter.MainFragmentPresenterInterface;

public class LoadCollectionsFromCache extends AsyncTask<Void, Void, List<CollectionsData>> {

    private MainFragmentPresenterInterface presenter;

    public LoadCollectionsFromCache(MainFragmentPresenterInterface presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onPostExecute(List<CollectionsData> collectionsData) {
        super.onPostExecute(collectionsData);

        if (collectionsData != null) {
            final CollectionsRecyclerViewAdapter collectionsAdapter = new CollectionsRecyclerViewAdapter(collectionsData);
            presenter.getView().getCollectionsRecyclerView().setAdapter(collectionsAdapter);
        }
    }

    @Override
    protected List<CollectionsData> doInBackground(Void... voids) {

        final AppDatabase db = ((MainActivityView) presenter.getView().getActivityContext()).getDatabase();
        if (db.getOpenHelper().getReadableDatabase().isOpen() && db.collectionsDao().getCount() > 0) {
            return db.collectionsDao().getAllCollections();
        }

        return null;
    }
}
