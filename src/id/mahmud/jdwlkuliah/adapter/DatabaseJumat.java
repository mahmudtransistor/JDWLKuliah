package id.mahmud.jdwlkuliah.adapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import id.mahmud.jdwlkuliah.Jumat;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseJumat extends SQLiteOpenHelper {
	//Versi database
			private static final int VERSI_DATABASE =1;
			//Deklarasi nama database
			private static final String DATABASE_NAME = "dbjumat";
	public DatabaseJumat(Context context) {
		super(context, DATABASE_NAME, null, VERSI_DATABASE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//Perintah SQL untuk membuat Tabel Jadwal senin
				String CREATE_TABEL_JUMAT = "CREATE TABLE tb_jumat ( " +
				                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
						                    "nm_matkul TEXT, "+
				                            "kelas TEXT, "+
						                    "ruang TEXT, "+
				                            "jam TEXT )";
				//Exesekusi Perintah SQL
				db.execSQL(CREATE_TABEL_JUMAT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//Drop tabel jika ada Versi yang lama
		db.execSQL("DROP TABEL IF EXIST tb_jumat");
		//Buat tabel jadwal Versi baru
		this.onCreate(db);
	}
	/**Proses CRUD (tambah, hapus,edit dan ambil)*/
	//Nama Tabel jadwals di simpan pada Variabel
	private static final String TABEL_JUMAT = "tb_jumat";
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
	public void addJumat(Jumat jumat){
		//1.Dapatkan Referensi kedatabase
				SQLiteDatabase db = this.getWritableDatabase();
				//2.Buta object content Values untuk menambah kolom atau value
				ContentValues values = new ContentValues();
				values.put(KEY_NMMATKUL, jumat.getNm_matkul());
				values.put(KEY_KELAS, jumat.getKelas());
				values.put(KEY_RUANG, jumat.getRuang());
				values.put(KEY_JAM, jumat.getJam());
				//3. jalan kan perintah insert
				db.insert(TABEL_JUMAT, null, values);
				//pesan LOG untuk menambah data Jadwal
				Log.i("Tambah data jadwal dari method adaJadwal", jumat.toString());
				//4. Tutup database
				db.close();
	}
	//Getjadwal untuk senin
			public Jumat getJumat(int id){
				//1. Dapatkan Referensi kedatabase
				SQLiteDatabase db = this.getReadableDatabase();
				//2. Exsekusi Query database simpan hasil nya ke Object Kursor
				Cursor cursor = 
						db.query(TABEL_JUMAT,
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
					Jumat jumat = new Jumat();
					jumat.setId(Integer.parseInt(cursor.getString(0)));
					jumat.setNm_matkul(cursor.getString(1));
					jumat.setKelas(cursor.getString(2));
					jumat.setRuang(cursor.getString(3));
					jumat.setJam(cursor.getString(4));
					//pesan LOG mengganil data Jadwal (1 Record)
					Log.i("ambil data jadwal ("+id+") dari method getJadwal : ", jumat.toString());
				
					return jumat;
				
			}
			//ambil semua Jadwal senin
			public ArrayList<Jumat> getAllJumat(){
				//membuat object list jadwal
				ArrayList<Jumat> tb_jumat = new ArrayList<Jumat>();
				//1. Buat Query buku
				String query = "SELECT * FROM " + TABEL_JUMAT;
				//2. Dapatkan referensi kedatabase
				SQLiteDatabase db = this.getWritableDatabase();
				//3. exsekusi perintah query simpan hasi object ke cursor
				Cursor cursor = db.rawQuery(query, null);
				//3. Akses tiap baris buat aobject buku simpan ke dalam listview
				Jumat jumat = null;
				if(cursor.moveToFirst()){
					do{
						jumat = new Jumat();
						jumat.setId(Integer.parseInt(cursor.getString(0)));
						jumat.setNm_matkul(cursor.getString(1));
						jumat.setKelas(cursor.getString(2));
						jumat.setRuang(cursor.getString(3));
						jumat.setJam(cursor.getString(4));
						tb_jumat.add(jumat);
					}while(cursor.moveToNext());
				}
				//pesan LOG memanggil semua data buku
				Log.i("Ambil semua buku dari method getAllBooks", tb_jumat.toString());
				db.close();
				return tb_jumat;
				
			}
			//Ambil semua Jadwal senin
			public List<String> getAllJumatTittle(){
				List<String> tb_jumat = new LinkedList<String>();
				
				String query = "SELECT * FROM " + TABEL_JUMAT;
				SQLiteDatabase db = this.getWritableDatabase();
				//3. exsekusi perintah query simpan hasi object ke cursor
				Cursor cursor = db.rawQuery(query, null);
				//3. Akses tiap baris buat aobject buku simpan ke dalam listview
				if(cursor.moveToFirst()){
					do{
						tb_jumat.add(cursor.getString(1));
						
					}while(cursor.moveToNext());
				}
				//pesan LOG memanggil semua data buku
						Log.i("Ambil semua buku dari method getAllBooks", tb_jumat.toString());
				return tb_jumat;
				
			}
			//Update data jadwal senin
			public int UpdateJumat(Jumat jumat){
				//1. Dapatkan referensi kedatabase
				SQLiteDatabase db = this.getWritableDatabase();
				//2.Buta object content Values untuk menambah kolom atau value
				ContentValues values = new ContentValues();
				values.put(KEY_NMMATKUL, jumat.getNm_matkul());
				values.put(KEY_KELAS, jumat.getKelas());
				values.put(KEY_RUANG, jumat.getRuang());
				values.put(KEY_JAM, jumat.getJam());
				//3. Update data Jadwal
				int i = db.update(TABEL_JUMAT, values,
					      KEY_ID+"=?",
					      new String[]{String.valueOf(jumat.getId())});
				//Pesan LOG untuk mengupdata data buku
				Log.i("Updata data jadwal",jumat.toString());
				db.close();
				return i;
				
			}
			
			//Method untuk menghapus data jadwal senin
			public void DeleteJumat (Jumat jumat){
				SQLiteDatabase db = this.getWritableDatabase();
				//perintah untuk manghapus
				db.delete(TABEL_JUMAT, KEY_ID + "=?",
						new String[]{String.valueOf(jumat.getId())});
				db.close();
				//pesan LOG untuk manghapus data dari method deletejadwal
				Log.i("Hapus data dari method DeletelJadwal()", jumat.toString());
			}
			
			
}
