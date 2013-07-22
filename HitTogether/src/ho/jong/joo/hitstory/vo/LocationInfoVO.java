package ho.jong.joo.hitstory.vo;

public class LocationInfoVO {
	
	String title;
	String addr1;
	String firstimage;
	String tel;
	double Mapx;
	double Mapy;
	int sigungucode;
	int contenttypeid;
	int contentid;
	
	
	public String getTel() {
		return tel;
	}
	public void setTel(String string) {
		this.tel = string;
	}
	public double getMapx() {
		return Mapx;
	}
	public void setMapx(double mapx) {
		Mapx = mapx;
	}
	public double getMapy() {
		return Mapy;
	}
	public void setMapy(double mapy) {
		Mapy = mapy;
	}
	public int getContentid() {
		return contentid;
	}
	public void setContentid(int contentid) {
		this.contentid = contentid;
	}
	public String getFirstimage() {
		return firstimage;
	}
	public void setFirstimage(String firstimage) {
		this.firstimage = firstimage;
	}
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
				+ ", firstimage=" + firstimage + ", Mapx=" + Mapx + ", Mapy="
				+ Mapy + ", sigungucode=" + sigungucode + ", contenttypeid="
				+ contenttypeid + ", contentid=" + contentid + ", tel=" + tel
				+ "]";
	}
}
