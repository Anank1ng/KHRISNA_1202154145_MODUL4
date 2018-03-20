package com.example.android.ANANTYA_1202154145_MODUL4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListName extends AppCompatActivity {

    ListView lvName;
    ProgressDialog mProgressBar;
    //array yang berisikan nama-nama mahasiswa yang ada
    private String[] names = {
            "Anantya", "Khrisna", "Seta", "Cybusters", "Entasia"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_name);
        lvName = (ListView)findViewById(R.id.lvName);
        //membuat array adapter untuk list nama mahasiswa
        lvName.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
    }

    //Method untuk menjalankan proses asyntask yang ada
    public void startAsync(View view) {
        mProgressBar = new ProgressDialog(ListName.this);
        mProgressBar.setCancelable(true);
        mProgressBar.setTitle("Loading Data");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.setMax(100);
        mProgressBar.setProgress(0);
        mProgressBar.show();
        new myTask().execute();
    }
    //class yang digunakan untuk menghitung progress dari nama-nama mahasiswa yang ada
    class myTask extends AsyncTask<Void, String, Void>{

        private ArrayAdapter<String> adapter;
        int count = 0;
        @Override
        //fungsi ini dipanggil sebelum memulai proses pada Background Thread.
        protected void onPreExecute() {
            adapter=(ArrayAdapter<String>)lvName.getAdapter();
        }

        @Override
        //fungsi ini akan dipanggil untuk memproses kode pada Background Thread.
        protected Void doInBackground(Void... voids) {
            for (String item: names){
                try {
                    publishProgress(item);
                    count += 20;
                    Thread.sleep(1000); // 2 segundos

                } catch(InterruptedException e) {

                }
            }
            return null;
        }

        @Override
        //ketika proses.operasi berjalan setiap perubahan akan ditampilkan
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            int progress = count;
            mProgressBar.setProgress(progress);
            mProgressBar.setMessage(values[0]+" ("+progress+"%)");
        }

        @Override
        //fungsi ini dipanggil ketika proses pada doInBackground sudah memberikan hasil
        protected void onPostExecute(Void aVoid) {
            mProgressBar.cancel();
            Toast.makeText(lvName.getContext(),"Async Selesai",Toast.LENGTH_SHORT).show();
        }
    }
}
