package ru.aeroidea.sitnikovtesttask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;

import ru.aeroidea.sitnikovtesttask.Activity.MainActivityView;
import ru.aeroidea.sitnikovtesttask.Data.BannersData;
import ru.aeroidea.sitnikovtesttask.Fragment.BlankFragmentView;
import ru.aeroidea.sitnikovtesttask.R;
import ru.aeroidea.sitnikovtesttask.Utils.LoadImageFromUrl;

public class BannerPagerAdapter extends PagerAdapter {

    private ArrayList<BannersData> bannersList;
    private Context mContext;

    public BannerPagerAdapter(ArrayList<BannersData> bannersList, Context context) {
        this.bannersList = bannersList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        final LayoutInflater inflater = LayoutInflater.from(mContext);
        final ViewGroup view = (ViewGroup) inflater.inflate(R.layout.viewpager_banner_item, container, false);
        final AppCompatTextView bannerText = view.findViewById(R.id.bannerText);
        final AppCompatImageView bannerImage = view.findViewById(R.id.bannerImage);
        bannerText.setText(bannersList.get(position).getTitle());
        new LoadImageFromUrl(bannerImage).execute(bannersList.get(position).getImg());

        view.setOnClickListener(v -> {
            final String title = bannersList.get(position).getTitle();
            ((MainActivityView) mContext).getPresenter().adaptersClickItem(new BlankFragmentView(), title);
        });

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return bannersList.size();
    }
}
