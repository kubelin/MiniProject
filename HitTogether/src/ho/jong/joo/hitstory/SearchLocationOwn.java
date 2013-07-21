package ho.jong.joo.hitstory;

import ho.jong.joo.hitstory.vo.LocationInfoVO;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml.Encoding;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

public class SearchLocationOwn extends Activity {

	// ������
	private ListView locationList;
	private Button searchBtn;
	private AutoCompleteTextView autoText;
	private MyLocationAdapter adapter ;

	// ����Ʈ
	private ArrayList<LocationInfoVO> adapterItems;
	private ArrayAdapter<String> editableAdapter;

	private String[] list = { "구로구", "강남구", "강동구", "abc" };

	@SuppressLint("CutPasteId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_own_layout);

		searchBtn = (Button) findViewById(R.id.searchLocationBtn);
		locationList = (ListView) findViewById(R.id.searchLocationList);
		adapterItems = new ArrayList<LocationInfoVO>();

		autoText = (AutoCompleteTextView) findViewById(R.id.autoText);
		autoText.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, list));

		adapterItems.add(new LocationInfoVO("테스트"));
		adapter = new MyLocationAdapter(this, R.layout.location_list, adapterItems);
		locationList.setAdapter(adapter);

		searchBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				callGetMethod();
			}
		});

	}

	// API ȣ��
	private void callGetMethod() {
		String serviceKey;
		String tagName = null;
		URL url;
		LocationInfoVO locationVO = null;

		try {
			serviceKey = URLEncoder
					.encode(getString(R.string.tourKey), "UTF-8");

			Log.i("MyDebug", "서비스 키  " + serviceKey);

			url = new URL(
					"http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey="
							+ serviceKey
							+ "&contentTypeId=&areaCode=1"
							+ "&sigunguCode=7&listYN=Y&MobileOS=ETC&MobileApp=TourAPI2.0_Guide&arrange=A&numOfRows=10&pageNo=1");

			ResponseHandler<String> handler = new BasicResponseHandler();

			XmlPullParser xpp = XmlPullParserFactory.newInstance()
					.newPullParser();
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();
			xpp.setInput(is, "UTF-8");
			

			int eventType = xpp.getEventType();
			boolean isItemBegin = false;

			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("item")) {
						locationVO = new LocationInfoVO();
						isItemBegin = true;
					}
				}
				/*
				 * else if( eventType == XmlPullParser.START_TAG){
				 * while(!xpp.getName().equals("title")){ if (eventType ==
				 * XmlPullParser.TEXT) { //items.add(xpp.getText());
				 * Log.i("test", "왜 깨지나요 2" + xpp.getText()); Log.i("test",
				 * "카운트 " + xpp.getName()); xpp.nextToken(); } } }
				 */
				else if (eventType == XmlPullParser.END_TAG) {
					isItemBegin = false;
				}
				if (isItemBegin) {
					// while(!xpp.getName().equals("title")){
					if (eventType == XmlPullParser.TEXT) {
						// items.add(xpp.getText());
						if( tagName.equals("addr1")){
							locationVO.setAddr1(xpp.getText());
						}else if( tagName.equals("sigungucode")){
							locationVO.setSigungucode(Integer.parseInt(xpp.getText()));
						}else if( tagName.equals("contenttypeid")){
							locationVO.setContenttypeid(Integer.parseInt(xpp.getText()));
						}else if( tagName.equals("title")){
							locationVO.setTitle(xpp.getText());
							adapterItems.add(locationVO);
							adapter.notifyDataSetChanged();
						}
						
						/*Log.i("test", "카운트 " + locationVO	 );
						Log.i("test", "카운트 " + tagName );
						Log.i("test", "왜 깨지나요 2" + xpp.getText());*/
						xpp.next();
					}
				}
				tagName = xpp.getName();
				xpp.next();
				eventType = xpp.getEventType();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
