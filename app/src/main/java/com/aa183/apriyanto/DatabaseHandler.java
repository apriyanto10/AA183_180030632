package com.aa183.apriyanto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 2;
    private final static String DATABASE_NAME = "db_musik";
    private final static String TABLE_MUSIK = "t_musik";
    private final static String KEY_ID_MUSIK = "ID_Musik";
    private final static String KEY_JUDUL = "Judul";
    private final static String KEY_TGL = "Tanggal";
    private final static String KEY_GAMBAR = "Gambar";
    private final static String KEY_PENCIPTA = "Pencipta";
    private final static String KEY_PENYANYI = "Penyanyi";
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault());
    private Context context;

    public DatabaseHandler(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_MUSIK = "CREATE TABLE " + TABLE_MUSIK
        + "(" + KEY_ID_MUSIK + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + KEY_JUDUL + " TEXT, " + KEY_TGL + " DATE, "
        + KEY_GAMBAR + " TEXT, " + KEY_PENCIPTA + " TEXT, "
        + KEY_PENYANYI + " TEXT);";

        db.execSQL(CREATE_TABLE_MUSIK);
        inisialisasiMusikAwal(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_MUSIK;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void tambahMusik(Musik dataMusik){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataMusik.getJudul());
        cv.put(KEY_TGL, sdFormat.format(dataMusik.getTanggal()));
        cv.put(KEY_GAMBAR, dataMusik.getGambar());
        cv.put(KEY_PENCIPTA, dataMusik.getPencipta());
        cv.put(KEY_PENYANYI, dataMusik.getPenyanyi());
        db.insert(TABLE_MUSIK, null, cv);
        db.close();
    }

    public void tambahMusik(Musik dataMusik, SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataMusik.getJudul());
        cv.put(KEY_TGL, sdFormat.format(dataMusik.getTanggal()));
        cv.put(KEY_GAMBAR, dataMusik.getGambar());
        cv.put(KEY_PENCIPTA, dataMusik.getPencipta());
        cv.put(KEY_PENYANYI, dataMusik.getPenyanyi());
        db.insert(TABLE_MUSIK, null, cv);
    }

    public void editMusik(Musik dataMusik){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataMusik.getJudul());
        cv.put(KEY_TGL, sdFormat.format(dataMusik.getTanggal()));
        cv.put(KEY_GAMBAR, dataMusik.getGambar());
        cv.put(KEY_PENCIPTA, dataMusik.getPencipta());
        cv.put(KEY_PENYANYI, dataMusik.getPenyanyi());

        db.update(TABLE_MUSIK, cv, KEY_ID_MUSIK + "=?", new String[]{String.valueOf(dataMusik.getIdMusik())});

        db.close();
    }

    public void hapusMusik(int idMusik){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_MUSIK, KEY_ID_MUSIK + "=?", new String[]{String.valueOf(idMusik)});
        db.close();
    }

    public ArrayList<Musik> getAllMusik(){
        ArrayList<Musik> dataMusik = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_MUSIK;
        SQLiteDatabase db = getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if (csr.moveToFirst()){
            do {
                Date tempDate = new Date();
                try {
                    tempDate = sdFormat.parse(csr.getString(2));
                }catch (ParseException er){
                    er.printStackTrace();
                }

                Musik tempMusik = new Musik(
                     csr.getInt(0),
                     csr.getString(1),
                     tempDate,
                     csr.getString(3),
                     csr.getString(4),
                     csr.getString(5)
                );
                dataMusik.add(tempMusik);
            }while (csr.moveToNext());
        }

        return dataMusik;
    }

    private String storeImageFile(int id){
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInteralStorage(image, context);
        return location;
    }

    private void inisialisasiMusikAwal(SQLiteDatabase db){
        int idMusik = 0;
        Date tempDate = new Date();

        //Menambah data Musik ke-1
        try {
            tempDate = sdFormat.parse("3/08/2016");
        }catch (ParseException er){
            er.printStackTrace();
        }

        Musik musik1 = new Musik(
                idMusik,
                "LANGIT ABU ABU ",
                tempDate,
                storeImageFile(R.drawable.musik1),
                "Tulus",
                "Tulus"
        );
        tambahMusik(musik1, db);
        idMusik++;

        //Menambah data Musik ke-2
        try {
            tempDate = sdFormat.parse("2/02/2018");
        }catch (ParseException er){
            er.printStackTrace();
        }

        Musik musik2 = new Musik(
                idMusik,
                "ATTENTION",
                tempDate,
                storeImageFile(R.drawable.musik2),
                "Rich Brian",
                "Rich Brian Ft OFFSET "
        );
        tambahMusik(musik2, db);
        idMusik++;

        //Menambah data Musik ke-3
        try {
            tempDate = sdFormat.parse("14/06/2019");
        }catch (ParseException er){
            er.printStackTrace();
        }

        Musik musik3 = new Musik(
                idMusik,
                "WANITAKU",
                tempDate,
                storeImageFile(R.drawable.musik3),
                "Ariel dan Pongki Barata",
                "Ariel_NOAH"
        );
        tambahMusik(musik3, db);
        idMusik++;

        //Menambah data Musik ke-4
        try {
            tempDate = sdFormat.parse("28/08/2019");
        }catch (ParseException er){
            er.printStackTrace();
        }

        Musik musik4 = new Musik(
                idMusik,
                "TITIK DI BALIK HIDUPKU",
                tempDate,
                storeImageFile(R.drawable.musik4),
                "Virgoun",
                "Virgoun _ LastChild"
        );
        tambahMusik(musik4, db);
        idMusik++;
    }
}
