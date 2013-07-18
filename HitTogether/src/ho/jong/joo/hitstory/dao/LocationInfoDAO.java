package ho.jong.joo.hitstory.dao;

public class LocationInfoDAO {
	
	String title;
	String addr1;
	
	public LocationInfoDAO(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	
	@Override
	public String toString() {
		return "LocationInfoDAO [title=" + title + ", addr1=" + addr1 + "]";
	}
}
