package ru.aeroidea.sitnikovtesttask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ru.aeroidea.sitnikovtesttask.Activity.MainActivityView;
import ru.aeroidea.sitnikovtesttask.Data.CollectionsData;
import ru.aeroidea.sitnikovtesttask.Fragment.BlankFragmentView;
import ru.aeroidea.sitnikovtesttask.R;
import ru.aeroidea.sitnikovtesttask.Utils.Network.LoadImageFromUrl;

public class CollectionsRecyclerViewAdapter extends RecyclerView.Adapter<CollectionsRecyclerViewAdapter.CollectionsViewHolder> {

    private List<CollectionsData> collectionsList;
    private Context mContext;

    static class CollectionsViewHolder extends RecyclerView.ViewHolder {

        private AppCompatImageView backgroundImage;
        private AppCompatTextView nameTextView;
        private AppCompatTextView productsCountTextView;
        private CardView cardView;

        CollectionsViewHolder(@NonNull View itemView, Context mContext, List<CollectionsData> collectionsList) {
            super(itemView);

            backgroundImage = itemView.findViewById(R.id.collections_image);
            nameTextView = itemView.findViewById(R.id.collections_name);
            productsCountTextView = itemView.findViewById(R.id.collections_count);
            cardView = itemView.findViewById(R.id.collections_item);
            cardView.setOnClickListener(view -> {
                final String title = collectionsList.get(getAdapterPosition()).getName();
                ((MainActivityView) mContext).getPresenter().adaptersClickItem(new BlankFragmentView(), title);
            });
        }
    }

    public CollectionsRecyclerViewAdapter(List<CollectionsData> collectionsList) {
        this.collectionsList = collectionsList;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mContext = recyclerView.getContext();
    }

    @NonNull
    @Override
    public CollectionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_collections_item, parent, false);
        return new CollectionsViewHolder(mView, mContext, collectionsList);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionsViewHolder holder, int position) {
        holder.nameTextView.setText(collectionsList.get(position).getName());
        final String productsText = mContext.getString(R.string.collectionsProductText, collectionsList.get(position).getProductsCount());
        holder.productsCountTextView.setText(productsText);
        new LoadImageFromUrl(holder.backgroundImage, ((MainActivityView) mContext).getDatabase()).execute(collectionsList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return collectionsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
