package ru.aeroidea.sitnikovtesttask.Utils.Network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import androidx.appcompat.widget.AppCompatImageView;
import java.io.InputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import ru.aeroidea.sitnikovtesttask.Data.ImageData;
import ru.aeroidea.sitnikovtesttask.Database.AppDatabase;
import ru.aeroidea.sitnikovtesttask.Utils.BitmapConverter;

public class LoadImageFromUrl extends AsyncTask<String, Void, Bitmap> {

    private AppCompatImageView imageView;
    private AppDatabase db;

    public LoadImageFromUrl(AppCompatImageView imageView,
                            AppDatabase db) {
        this.imageView = imageView;
        this.db = db;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {

        Bitmap bitmap = null;
        if (!db.imageDao().isExists(urls[0])) {
            try {
                final URL url = new URL(urls[0]);
                final HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
                conn.setHostnameVerifier((hostname, session) -> true);
                final InputStream inputstream = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputstream);

                if (bitmap != null) {
                    db.imageDao().insert(new ImageData(urls[0], BitmapConverter.bmpToByteArray(bitmap)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            bitmap = BitmapConverter.byteArrayToBmp(db.imageDao().getImage(urls[0]));
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
