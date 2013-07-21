package ho.jong.joo.hitstory.vo;

public class LocationInfoVO {
	
	String title;
	String addr1;
	int sigungucode;
	int contenttypeid;
	
	public LocationInfoVO(String title) {
		this.title = title;
	}
	public LocationInfoVO() {
		// TODO Auto-generated constructor stub
	}
	public int getSigungucode() {
		return sigungucode;
	}
	public void setSigungucode(int sigungucode) {
		this.sigungucode = sigungucode;
	}
	public int getContenttypeid() {
		return contenttypeid;
	}
	public void setContenttypeid(int contenttypeid) {
		this.contenttypeid = contenttypeid;
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
		return "LocationInfoVO [title=" + title + ", addr1=" + addr1
				+ ", sigungucode=" + sigungucode + ", contenttypeid="
				+ contenttypeid + "]";
	}
	
}
