package com.aa183.apriyanto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class TampilActivity extends AppCompatActivity {

    private ImageView imgMusik;
    private TextView tvJudul, tvTanggal, tvPencipta, tvPenyanyi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        imgMusik = findViewById(R.id.iv_musik);
        tvJudul = findViewById(R.id.tv_judul);
        tvTanggal = findViewById(R.id.tv_tanggal);
        tvPencipta = findViewById(R.id.tv_pencipta);
        tvPenyanyi = findViewById(R.id.tv_penyanyi);

        Intent terimaData = getIntent();
        tvJudul.setText(terimaData.getStringExtra("JUDUL"));
        tvTanggal.setText(terimaData.getStringExtra("TANGGAL"));
        tvPencipta.setText(terimaData.getStringExtra("PENCIPTA"));
        tvPenyanyi.setText(terimaData.getStringExtra("PENYANYI"));
        String imgLocation = terimaData.getStringExtra("GAMBAR");

        try {
            File file = new File(imgLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imgMusik.setImageBitmap(bitmap);
            imgMusik.setContentDescription(imgLocation);
        }catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(this, "Gagal mengambil gambar dari media penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tampil_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.item_bagikan){
            Intent bagikanMusik = new Intent(Intent.ACTION_SEND);
            bagikanMusik.putExtra(Intent.EXTRA_SUBJECT, tvJudul.getText().toString());
            bagikanMusik.setType("text/plain");
            startActivity(Intent.createChooser(bagikanMusik,"Bagikan musik1"));
        }
        return super.onOptionsItemSelected(item);
    }
}
