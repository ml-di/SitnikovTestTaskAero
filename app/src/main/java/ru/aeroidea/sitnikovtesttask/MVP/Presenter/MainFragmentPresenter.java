package ru.aeroidea.sitnikovtesttask.MVP.Presenter;

import android.database.sqlite.SQLiteException;
import android.util.Base64;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import ru.aeroidea.sitnikovtesttask.Activity.MainActivityView;
import ru.aeroidea.sitnikovtesttask.Data.BannersData;
import ru.aeroidea.sitnikovtesttask.Data.CollectionsData;
import ru.aeroidea.sitnikovtesttask.Database.AppDatabase;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.Presenter.MainFragmentPresenterInterface;
import ru.aeroidea.sitnikovtesttask.MVP.Interface.View.MainFragmentViewInterface;
import ru.aeroidea.sitnikovtesttask.MVP.BasePresenter;
import ru.aeroidea.sitnikovtesttask.R;
import ru.aeroidea.sitnikovtesttask.Database.Loader.LoadBannersFromCache;
import ru.aeroidea.sitnikovtesttask.Database.Loader.LoadCollectionsFromCache;
import ru.aeroidea.sitnikovtesttask.Utils.Network.LoadData;

public class MainFragmentPresenter extends BasePresenter<MainFragmentViewInterface> implements MainFragmentPresenterInterface {

    @Override
    public void viewIsReady() {
        getView().initViewPager(R.id.viewPagerBanner);
        getView().initTabLayout(R.id.viewPagerIndicator);
        getView().initRecyclerView(R.id.recyclerViewCollections);

        loadAndSetData();
    }

    @Override
    public String getData() {
        try {
            final String loginpass = "loginarea:passarea";
            final String basicAuth = "Basic " + new String(Base64.encode(loginpass.getBytes(), 0));

            final URL url = new URL("https://5.188.105.11/api/content/main");
            final HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setHostnameVerifier((hostname, session) -> true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", basicAuth);
            con.setDoOutput(true);

            final DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes("{\"countryCode\": \"ru\"}");
            out.flush();
            out.close();

            final int status = con.getResponseCode();
            final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            final StringBuilder content = new StringBuilder();
            String inputLine;
            while((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            if (status == HttpURLConnection.HTTP_OK) {
                return content.toString();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BannersData> getBannersList(String json) {

        if (json != null) {
            final List<BannersData> bannersList = new ArrayList<>();

            try {
                final JSONObject jObject = new JSONObject(json);
                final JSONArray banners = jObject.getJSONArray("banners");
                for (int i = 0; i < banners.length(); i++) {
                    final JSONObject o = banners.getJSONObject(i);
                    final int id = o.getInt("id");

                    final String title;
                    final String img;

                    if (!o.getString("breadCrumbTitle").equals("null")) {
                        title = o.getString("breadCrumbTitle");
                    } else {
                        title = o.getString("title").replaceAll("&lt;br&gt;", "\n");
                    }

                    if (!o.getString("breadCrumbImg").equals("null")) {
                        img = o.getString("breadCrumbImg");
                    } else {
                        img = o.getString("mobilePicture");
                    }

                    bannersList.add(new BannersData(id, title, img));
                }

                return bannersList;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<CollectionsData> getCollectionsList(String json) {
        if (json != null) {

            final List<CollectionsData> collectionsList = new ArrayList<>();
            try {
                final JSONObject jsonObject = new JSONObject(json);
                final JSONArray collections = jsonObject.getJSONArray("collections");
                for (int i = 0; i < collections.length(); i++) {
                    final JSONObject o = collections.getJSONObject(i);
                    final int id = o.getInt("id");
                    final String name = o.getString("name");
                    final String img = o.getString("img");
                    final int productsCount = o.getInt("productsCount");
                    collectionsList.add(new CollectionsData(id, name, img, productsCount));
                }

                return collectionsList;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void setBannersCache(List<BannersData> bannersData) {

        final AppDatabase db = ((MainActivityView) getView().getActivityContext()).getDatabase();
        try {
            if (db.getOpenHelper().getWritableDatabase().isOpen()) {
                if (db.bannersDao().getCount() > 0) {
                    db.bannersDao().deleteAll();
                }

                for (BannersData banner : bannersData) {
                    db.bannersDao().insert(banner);
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            Toast.makeText(getView().getActivityContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setCollectionsCache(List<CollectionsData> collectionsData) {

        AppDatabase db = ((MainActivityView) getView().getActivityContext()).getDatabase();
        try {
            if (db.getOpenHelper().getWritableDatabase().isOpen()) {
                if (db.collectionsDao().getCount() > 0) {
                    db.collectionsDao().deleteAll();
                }

                for (CollectionsData collection : collectionsData) {
                    db.collectionsDao().insert(collection);
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            Toast.makeText(getView().getActivityContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loadBannersCache() {
        new LoadBannersFromCache(getView().getPresenter()).execute();
    }

    @Override
    public void loadCollectionsCache() {
        new LoadCollectionsFromCache(getView().getPresenter()).execute();
    }

    @Override
    public void loadAndSetData() {
        new LoadData(getView().getPresenter()).execute();
    }
}
