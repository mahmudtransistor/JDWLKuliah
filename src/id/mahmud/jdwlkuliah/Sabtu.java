package id.mahmud.jdwlkuliah;

public class Sabtu {
	private int id;
	private String nm_matkul,kelas,ruang,jam;
	public Sabtu(){
		super();
	}
	public Sabtu(String nm_matkul, String kelas, String ruang, String jam){
		super();
		this.nm_matkul = nm_matkul;
		this.kelas = kelas;
		this.ruang = ruang;
		this.jam = jam;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNm_matkul() {
		return nm_matkul;
	}
	public void setNm_matkul(String nm_matkul) {
		this.nm_matkul = nm_matkul;
	}
	public String getKelas() {
		return kelas;
	}
	public void setKelas(String kelas) {
		this.kelas = kelas;
	}
	public String getRuang() {
		return ruang;
	}
	public void setRuang(String ruang) {
		this.ruang = ruang;
	}
	public String getJam() {
		return jam;
	}
	public void setJam(String jam) {
		this.jam = jam;
	}
	
}
