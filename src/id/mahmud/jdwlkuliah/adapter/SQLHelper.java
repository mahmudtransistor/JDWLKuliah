package id.mahmud.jdwlkuliah.adapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import id.mahmud.jdwlkuliah.Jadwal;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLHelper extends SQLiteOpenHelper{
	//Versi database
	private static final int VERSI_DATABASE =1;
	//Deklarasi nama database
	private static final String DATABASE_NAME = "JadwalKuliah";
	
	public SQLHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSI_DATABASE);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//Perintah SQL untuk membuat Tabel Jadwal senin
		String CREATE_TABEL_JADWAL = "CREATE TABLE jadwals ( " +
		                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				                    "nm_matkul TEXT, "+
		                            "kelas TEXT, "+
				                    "ruang TEXT, "+
		                            "jam TEXT )";
		//Exesekusi Perintah SQL
		db.execSQL(CREATE_TABEL_JADWAL);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//Drop tabel jika ada Versi yang lama
		db.execSQL("DROP TABEL IF EXIST jadwals");
		//Buat tabel jadwal Versi baru
		this.onCreate(db);
		
	}
	/**Proses CRUD (tambah, hapus,edit dan ambil)*/
	//Nama Tabel jadwals di simpan pada Variabel
	private static final String TABEL_JADWALS = "jadwals";
	//nama kolom pada tabel jadwal
	private static final String KEY_ID = "id";
	private static final String KEY_NMMATKUL = "nm_matkul";
	private static final String KEY_KELAS = "kelas";
	private static final String KEY_RUANG = "ruang";
	private static final String KEY_JAM = "jam";
	//Simpan nama nama sebuah kolom pada sebuah array
	private static final String[] COLUMNS = {KEY_ID,KEY_NMMATKUL,KEY_KELAS,
		                                    KEY_RUANG,KEY_JAM};
	//metod untuk menambah data baru pada tabel jadwal
	public void addJadwal(Jadwal jadwal){
		//1.Dapatkan Referensi kedatabase
		SQLiteDatabase db = this.getWritableDatabase();
		//2.Buta object content Values untuk menambah kolom atau value
		ContentValues values = new ContentValues();
		values.put(KEY_NMMATKUL, jadwal.getNm_matkul());
		values.put(KEY_KELAS, jadwal.getKelas());
		values.put(KEY_RUANG, jadwal.getRuang());
		values.put(KEY_JAM, jadwal.getJam());
		//3. jalan kan perintah insert
		db.insert(TABEL_JADWALS, null, values);
		//pesan LOG untuk menambah data Jadwal
		Log.i("Tambah data jadwal dari method adaJadwal", jadwal.toString());
		//4. Tutup database
		db.close();
	}

	//Getjadwal untuk senin
	public Jadwal getJadwal(int id){
		//1. Dapatkan Referensi kedatabase
		SQLiteDatabase db = this.getReadableDatabase();
		//2. Exsekusi Query database simpan hasil nya ke Object Kursor
		Cursor cursor = 
				db.query(TABEL_JADWALS,
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
			Jadwal jadwal = new Jadwal();
			jadwal.setId(Integer.parseInt(cursor.getString(0)));
			jadwal.setNm_matkul(cursor.getString(1));
			jadwal.setKelas(cursor.getString(2));
			jadwal.setRuang(cursor.getString(3));
			jadwal.setJam(cursor.getString(4));
			//pesan LOG mengganil data Jadwal (1 Record)
			Log.i("ambil data jadwal ("+id+") dari method getJadwal : ", jadwal.toString());
		
			return jadwal;
		
	}
	
	//ambil semua Jadwal senin
	public ArrayList<Jadwal> getAllJadwals(){
		//membuat object list jadwal
		ArrayList<Jadwal> jadwals = new ArrayList<Jadwal>();
		//1. Buat Query buku
		String query = "SELECT * FROM " + TABEL_JADWALS;
		//2. Dapatkan referensi kedatabase
		SQLiteDatabase db = this.getWritableDatabase();
		//3. exsekusi perintah query simpan hasi object ke cursor
		Cursor cursor = db.rawQuery(query, null);
		//3. Akses tiap baris buat aobject buku simpan ke dalam listview
		Jadwal jadwal = null;
		if(cursor.moveToFirst()){
			do{
				jadwal = new Jadwal();
				jadwal.setId(Integer.parseInt(cursor.getString(0)));
				jadwal.setNm_matkul(cursor.getString(1));
				jadwal.setKelas(cursor.getString(2));
				jadwal.setRuang(cursor.getString(3));
				jadwal.setJam(cursor.getString(4));
				jadwals.add(jadwal);
			}while(cursor.moveToNext());
		}
		//pesan LOG memanggil semua data buku
		Log.i("Ambil semua buku dari method getAllBooks", jadwals.toString());
		db.close();
		return jadwals;
		
	}
	
	//Ambil semua Jadwal senin
	public List<String> getAllJadwalsTittle(){
		List<String> jadwals = new LinkedList<String>();
		
		String query = "SELECT * FROM " + TABEL_JADWALS;
		SQLiteDatabase db = this.getWritableDatabase();
		//3. exsekusi perintah query simpan hasi object ke cursor
		Cursor cursor = db.rawQuery(query, null);
		//3. Akses tiap baris buat aobject buku simpan ke dalam listview
		if(cursor.moveToFirst()){
			do{
				jadwals.add(cursor.getString(1));
				
			}while(cursor.moveToNext());
		}
		//pesan LOG memanggil semua data buku
				Log.i("Ambil semua buku dari method getAllBooks", jadwals.toString());
		return jadwals;
		
	}
	
	//Update data jadwal senin
	public int UpdateJadwal(Jadwal jadwal){
		//1. Dapatkan referensi kedatabase
		SQLiteDatabase db = this.getWritableDatabase();
		//2.Buta object content Values untuk menambah kolom atau value
		ContentValues values = new ContentValues();
		values.put(KEY_NMMATKUL, jadwal.getNm_matkul());
		values.put(KEY_KELAS, jadwal.getKelas());
		values.put(KEY_RUANG, jadwal.getRuang());
		values.put(KEY_JAM, jadwal.getJam());
		//3. Update data Jadwal
		int i = db.update(TABEL_JADWALS, values,
			      KEY_ID+"=?",
			      new String[]{String.valueOf(jadwal.getId())});
		//Pesan LOG untuk mengupdata data buku
		Log.i("Updata data jadwal",jadwal.toString());
		db.close();
		return i;
		
	}
	
	//Method untuk menghapus data jadwal senin
	public void DeleteJadwal (Jadwal jadwal){
		SQLiteDatabase db = this.getWritableDatabase();
		//perintah untuk manghapus
		db.delete(TABEL_JADWALS, KEY_ID + "=?",
				new String[]{String.valueOf(jadwal.getId())});
		db.close();
		//pesan LOG untuk manghapus data dari method deletejadwal
		Log.i("Hapus data dari method DeletelJadwal()", jadwal.toString());
	}
	}
