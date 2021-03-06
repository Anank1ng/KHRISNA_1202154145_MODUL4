package com.example.android.ANANTYA_1202154145_MODUL4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

public class ImageSearch extends AppCompatActivity {

    ImageView ivImage; //membuat objek
    EditText etImage;
    private String imageUrl;

    // sebelumnya masukkan <uses-permission ke manifest untuk dapat mengakses internet
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);
        ivImage = (ImageView)findViewById(R.id.ivImage); //inisialisasi data berdasarkan layoutnya
        etImage = (EditText)findViewById(R.id.etImage);
    }

    public void imgOnClick(View view) {
        DownloadImageWithURLTask downloadTask = new DownloadImageWithURLTask(ivImage);
        downloadTask.execute(etImage.getText().toString());
    }

    private class DownloadImageWithURLTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageWithURLTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String pathToFile = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(pathToFile).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
