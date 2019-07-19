package ru.aeroidea.sitnikovtesttask.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import androidx.appcompat.widget.AppCompatImageView;
import java.io.InputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class LoadImageFromUrl extends AsyncTask<String, Void, Bitmap> {

    private AppCompatImageView imageView;

    public LoadImageFromUrl(AppCompatImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        Bitmap bitmap = null;
        try {
            final URL url = new URL(urls[0]);
            final HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            conn.setHostnameVerifier((hostname, session) -> true);
            final InputStream inputstream = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputstream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
