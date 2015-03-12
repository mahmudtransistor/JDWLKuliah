package id.mahmud.jdwlkuliah.adapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import id.mahmud.jdwlkuliah.Rabu;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseRabu extends SQLiteOpenHelper {
	//Versi database
			private static final int VERSI_DATABASE =1;
			//Deklarasi nama database
			private static final String DATABASE_NAME = "dbrabu";
	public DatabaseRabu(Context context) {
		super(context, DATABASE_NAME, null, VERSI_DATABASE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//Perintah SQL untuk membuat Tabel Jadwal senin
				String CREATE_TABEL_RABU = "CREATE TABLE tb_rabu ( " +
				                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
						                    "nm_matkul TEXT, "+
				                            "kelas TEXT, "+
						                    "ruang TEXT, "+
				                            "jam TEXT )";
				//Exesekusi Perintah SQL
				db.execSQL(CREATE_TABEL_RABU);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//Drop tabel jika ada Versi yang lama
		db.execSQL("DROP TABEL IF EXIST tb_rabu");
		//Buat tabel jadwal Versi baru
		this.onCreate(db);
	}
	/**Proses CRUD (tambah, hapus,edit dan ambil)*/
	//Nama Tabel jadwals di simpan pada Variabel
	private static final String TABEL_RABU = "tb_rabu";
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
		public void addRabu(Rabu rabu){
			//1.Dapatkan Referensi kedatabase
					SQLiteDatabase db = this.getWritableDatabase();
					//2.Buta object content Values untuk menambah kolom atau value
					ContentValues values = new ContentValues();
					values.put(KEY_NMMATKUL, rabu.getNm_matkul());
					values.put(KEY_KELAS, rabu.getKelas());
					values.put(KEY_RUANG, rabu.getRuang());
					values.put(KEY_JAM, rabu.getJam());
					//3. jalan kan perintah insert
					db.insert(TABEL_RABU, null, values);
					//pesan LOG untuk menambah data Jadwal
					Log.i("Tambah data jadwal dari method adaJadwal", rabu.toString());
					//4. Tutup database
					db.close();
		}
		//Getjadwal untuk senin
				public Rabu getRabu(int id){
					//1. Dapatkan Referensi kedatabase
					SQLiteDatabase db = this.getReadableDatabase();
					//2. Exsekusi Query database simpan hasil nya ke Object Kursor
					Cursor cursor = 
							db.query(TABEL_RABU,
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
						Rabu rabu = new Rabu();
						rabu.setId(Integer.parseInt(cursor.getString(0)));
						rabu.setNm_matkul(cursor.getString(1));
						rabu.setKelas(cursor.getString(2));
						rabu.setRuang(cursor.getString(3));
						rabu.setJam(cursor.getString(4));
						//pesan LOG mengganil data Jadwal (1 Record)
						Log.i("ambil data jadwal ("+id+") dari method getJadwal : ", rabu.toString());
					
						return rabu;
					
				}
				//ambil semua Jadwal senin
				public ArrayList<Rabu> getAllRabu(){
					//membuat object list jadwal
					ArrayList<Rabu> tb_rabu = new ArrayList<Rabu>();
					//1. Buat Query buku
					String query = "SELECT * FROM " + TABEL_RABU;
					//2. Dapatkan referensi kedatabase
					SQLiteDatabase db = this.getWritableDatabase();
					//3. exsekusi perintah query simpan hasi object ke cursor
					Cursor cursor = db.rawQuery(query, null);
					//3. Akses tiap baris buat aobject buku simpan ke dalam listview
					Rabu rabu = null;
					if(cursor.moveToFirst()){
						do{
							rabu = new Rabu();
							rabu.setId(Integer.parseInt(cursor.getString(0)));
							rabu.setNm_matkul(cursor.getString(1));
							rabu.setKelas(cursor.getString(2));
							rabu.setRuang(cursor.getString(3));
							rabu.setJam(cursor.getString(4));
							tb_rabu.add(rabu);
						}while(cursor.moveToNext());
					}
					//pesan LOG memanggil semua data buku
					Log.i("Ambil semua buku dari method getAllBooks", tb_rabu.toString());
					db.close();
					return tb_rabu;
					
				}
				//Ambil semua Jadwal senin
				public List<String> getAllSelasaTittle(){
					List<String> tb_rabu = new LinkedList<String>();
					
					String query = "SELECT * FROM " + TABEL_RABU;
					SQLiteDatabase db = this.getWritableDatabase();
					//3. exsekusi perintah query simpan hasi object ke cursor
					Cursor cursor = db.rawQuery(query, null);
					//3. Akses tiap baris buat aobject buku simpan ke dalam listview
					if(cursor.moveToFirst()){
						do{
							tb_rabu.add(cursor.getString(1));
							
						}while(cursor.moveToNext());
					}
					//pesan LOG memanggil semua data buku
							Log.i("Ambil semua buku dari method getAllBooks", tb_rabu.toString());
					return tb_rabu;
					
				}
				//Update data jadwal senin
				public int UpdateRabu(Rabu rabu){
					//1. Dapatkan referensi kedatabase
					SQLiteDatabase db = this.getWritableDatabase();
					//2.Buta object content Values untuk menambah kolom atau value
					ContentValues values = new ContentValues();
					values.put(KEY_NMMATKUL, rabu.getNm_matkul());
					values.put(KEY_KELAS, rabu.getKelas());
					values.put(KEY_RUANG, rabu.getRuang());
					values.put(KEY_JAM, rabu.getJam());
					//3. Update data Jadwal
					int i = db.update(TABEL_RABU, values,
						      KEY_ID+"=?",
						      new String[]{String.valueOf(rabu.getId())});
					//Pesan LOG untuk mengupdata data buku
					Log.i("Updata data jadwal",rabu.toString());
					db.close();
					return i;
					
				}
				
				//Method untuk menghapus data jadwal senin
				public void DeleteRabu (Rabu rabu){
					SQLiteDatabase db = this.getWritableDatabase();
					//perintah untuk manghapus
					db.delete(TABEL_RABU
							, KEY_ID + "=?",
							new String[]{String.valueOf(rabu.getId())});
					db.close();
					//pesan LOG untuk manghapus data dari method deletejadwal
					Log.i("Hapus data dari method DeletelJadwal()", rabu.toString());
				}
				
				

}
