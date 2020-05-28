package com.aa183.apriyanto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MusikAdapter extends RecyclerView.Adapter<MusikAdapter.MusikViewHolder> {

    private Context context;
    private ArrayList<Musik> dataMusik;
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");

    public MusikAdapter(Context context, ArrayList<Musik> dataMusik) {
        this.context = context;
        this.dataMusik = dataMusik;
    }

    @NonNull
    @Override
    public MusikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_musik, parent,false);
        return new MusikViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusikViewHolder holder, int position) {
        Musik tempMusik = dataMusik.get(position);
        holder.idMusik= tempMusik.getIdMusik();
        holder.tvJudul.setText(tempMusik.getJudul());
        holder.tvHeadline.setText(tempMusik.getPencipta());
        holder.tanggal = sdFormat.format(tempMusik.getTanggal());
        holder.gambar = tempMusik.getGambar();
        holder.penyanyi = tempMusik.getPenyanyi();

        try {
            File file = new File(holder.gambar);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            holder.imgMusik.setImageBitmap(bitmap);
            holder.imgMusik.setContentDescription(holder.gambar);
        }catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(context, "Gagal mengambil gambar dari media penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return dataMusik.size();
    }

    public class MusikViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private ImageView imgMusik;
        private TextView tvJudul,tvHeadline;
        private int idMusik;
        private String tanggal, gambar, penyanyi;

        public MusikViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMusik = itemView.findViewById(R.id.iv_musik);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvHeadline = itemView.findViewById(R.id.tv_headline);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent bukaMusik = new Intent(context, TampilActivity.class);
            bukaMusik.putExtra("ID", idMusik);
            bukaMusik.putExtra("JUDUL", tvJudul.getText().toString());
            bukaMusik.putExtra("TANGGAL", tanggal);
            bukaMusik.putExtra("GAMBAR", gambar);
            bukaMusik.putExtra("PENCIPTA", tvHeadline.getText().toString());
            bukaMusik.putExtra("PENYANYI", penyanyi);
            context.startActivity(bukaMusik);
        }

        @Override
        public boolean onLongClick(View v) {

            Intent bukaInput = new Intent(context, InputActivity.class);
            bukaInput.putExtra("OPERASI","update");
            bukaInput.putExtra("ID", idMusik);
            bukaInput.putExtra("JUDUL", tvJudul.getText().toString());
            bukaInput.putExtra("TANGGAL", tanggal);
            bukaInput.putExtra("GAMBAR", gambar);
            bukaInput.putExtra("PENCIPTA", tvHeadline.getText().toString());
            bukaInput.putExtra("PENYANYI", penyanyi);
            context.startActivity(bukaInput);

            return true;
        }
    }
}
