package id.mahmud.jdwlkuliah.adapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import id.mahmud.jdwlkuliah.Kamis;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseKamis extends SQLiteOpenHelper {

	//Versi database
			private static final int VERSI_DATABASE =1;
			//Deklarasi nama database
			private static final String DATABASE_NAME = "dbkamis";
	public DatabaseKamis(Context context) {
		super(context, DATABASE_NAME, null, VERSI_DATABASE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//Perintah SQL untuk membuat Tabel Jadwal senin
				String CREATE_TABEL_KAMIS = "CREATE TABLE tb_kamis ( " +
				                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
						                    "nm_matkul TEXT, "+
				                            "kelas TEXT, "+
						                    "ruang TEXT, "+
				                            "jam TEXT )";
				//Exesekusi Perintah SQL
				db.execSQL(CREATE_TABEL_KAMIS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//Drop tabel jika ada Versi yang lama
		db.execSQL("DROP TABEL IF EXIST tb_kamis");
		//Buat tabel jadwal Versi baru
		this.onCreate(db);
	}
	/**Proses CRUD (tambah, hapus,edit dan ambil)*/
	//Nama Tabel jadwals di simpan pada Variabel
	private static final String TABEL_KAMIS = "tb_kamis";
	//nama kolom pada tabel jadwal
	private static final String KEY_ID = "id";
	private static final String KEY_NMMATKUL = "nm_matkul";
	private static final String KEY_KELAS = "kelas";
	private static final String KEY_RUANG = "ruang";
	private static final String KEY_JAM = "jam";
	//Simpan nama nama sebuah kolom pada sebuah array
	private static final String[] COLUMNS = {KEY_ID,KEY_NMMATKUL,KEY_KELAS,
		                                    KEY_RUANG,KEY_JAM};
	//method untuk menambahkan data baru pada tabel selasa
	public void addKamis(Kamis kamis){
		//1.Dapatkan Referensi kedatabase
				SQLiteDatabase db = this.getWritableDatabase();
				//2.Buta object content Values untuk menambah kolom atau value
				ContentValues values = new ContentValues();
				values.put(KEY_NMMATKUL, kamis.getNm_matkul());
				values.put(KEY_KELAS, kamis.getKelas());
				values.put(KEY_RUANG, kamis.getRuang());
				values.put(KEY_JAM, kamis.getJam());
				//3. jalan kan perintah insert
				db.insert(TABEL_KAMIS, null, values);
				//pesan LOG untuk menambah data Jadwal
				Log.i("Tambah data jadwal dari method adaJadwal", kamis.toString());
				//4. Tutup database
				db.close();
	}
	//Getjadwal untuk senin
			public Kamis getKamis(int id){
				//1. Dapatkan Referensi kedatabase
				SQLiteDatabase db = this.getReadableDatabase();
				//2. Exsekusi Query database simpan hasil nya ke Object Kursor
				Cursor cursor = 
						db.query(TABEL_KAMIS,
								COLUMNS, 
								"id= ?",
		                        new String[]{String.valueOf(id)},
		                        null,
		                        null,
		                        null,
		                        null);
				//3. Jika dapat hasil nya pindah cursor ke posisi awal
				if(cursor !=null)
					cursor.moveToFirst();
					//4. Buat aobject jadwal
					Kamis kamis = new Kamis();
					kamis.setId(Integer.parseInt(cursor.getString(0)));
					kamis.setNm_matkul(cursor.getString(1));
					kamis.setKelas(cursor.getString(2));
					kamis.setRuang(cursor.getString(3));
					kamis.setJam(cursor.getString(4));
					//pesan LOG mengganil data Jadwal (1 Record)
					Log.i("ambil data jadwal ("+id+") dari method getJadwal : ", kamis.toString());
				
					return kamis;
				
			}
			//ambil semua Jadwal senin
			public ArrayList<Kamis> getAllKamis(){
				//membuat object list jadwal
				ArrayList<Kamis> tb_kamis = new ArrayList<Kamis>();
				//1. Buat Query buku
				String query = "SELECT * FROM " + TABEL_KAMIS;
				//2. Dapatkan referensi kedatabase
				SQLiteDatabase db = this.getWritableDatabase();
				//3. exsekusi perintah query simpan hasi object ke cursor
				Cursor cursor = db.rawQuery(query, null);
				//3. Akses tiap baris buat aobject buku simpan ke dalam listview
				Kamis kamis = null;
				if(cursor.moveToFirst()){
					do{
						kamis = new Kamis();
						kamis.setId(Integer.parseInt(cursor.getString(0)));
						kamis.setNm_matkul(cursor.getString(1));
						kamis.setKelas(cursor.getString(2));
						kamis.setRuang(cursor.getString(3));
						kamis.setJam(cursor.getString(4));
						tb_kamis.add(kamis);
					}while(cursor.moveToNext());
				}
				//pesan LOG memanggil semua data buku
				Log.i("Ambil semua buku dari method getAllBooks", tb_kamis.toString());
				db.close();
				return tb_kamis;
				
			}
			//Ambil semua Jadwal senin
			public List<String> getAllSelasaTittle(){
				List<String> tb_kamis = new LinkedList<String>();
				
				String query = "SELECT * FROM " + TABEL_KAMIS;
				SQLiteDatabase db = this.getWritableDatabase();
				//3. exsekusi perintah query simpan hasi object ke cursor
				Cursor cursor = db.rawQuery(query, null);
				//3. Akses tiap baris buat aobject buku simpan ke dalam listview
				if(cursor.moveToFirst()){
					do{
						tb_kamis.add(cursor.getString(1));
						
					}while(cursor.moveToNext());
				}
				//pesan LOG memanggil semua data buku
						Log.i("Ambil semua buku dari method getAllBooks", tb_kamis.toString());
				return tb_kamis;
				
			}
			//Update data jadwal senin
			public int UpdateKamis(Kamis kamis){
				//1. Dapatkan referensi kedatabase
				SQLiteDatabase db = this.getWritableDatabase();
				//2.Buta object content Values untuk menambah kolom atau value
				ContentValues values = new ContentValues();
				values.put(KEY_NMMATKUL, kamis.getNm_matkul());
				values.put(KEY_KELAS, kamis.getKelas());
				values.put(KEY_RUANG, kamis.getRuang());
				values.put(KEY_JAM, kamis.getJam());
				//3. Update data Jadwal
				int i = db.update(TABEL_KAMIS, values,
					      KEY_ID+"=?",
					      new String[]{String.valueOf(kamis.getId())});
				//Pesan LOG untuk mengupdata data buku
				Log.i("Updata data jadwal",kamis.toString());
				db.close();
				return i;
				
			}
			
			//Method untuk menghapus data jadwal senin
			public void DeleteKamis (Kamis kamis){
				SQLiteDatabase db = this.getWritableDatabase();
				//perintah untuk manghapus
				db.delete(TABEL_KAMIS, KEY_ID + "=?",
						new String[]{String.valueOf(kamis.getId())});
				db.close();
				//pesan LOG untuk manghapus data dari method deletejadwal
				Log.i("Hapus data dari method DeletelJadwal()", kamis.toString());
			}
			
			

}
