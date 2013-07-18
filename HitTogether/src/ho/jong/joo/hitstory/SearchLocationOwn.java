package ho.jong.joo.hitstory;

import ho.jong.joo.hitstory.dao.LocationInfoDAO;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

public class SearchLocationOwn extends Activity{
	
	// 위젯들
	private ListView locationList;
	private Button searchBtn;
	private AutoCompleteTextView autoText;
	
	// 리스트
	private ArrayList<LocationInfoDAO> adapterItems;
	private ArrayAdapter<String> editableAdapter;
	
	private String[] list = {"구로구", "개봉동", "강남" , "abc"}; 
	
	@SuppressLint("CutPasteId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_own_layout);
		
		searchBtn = (Button) findViewById(R.id.searchLocationBtn);
		locationList = (ListView) findViewById(R.id.searchLocationList);
		adapterItems = new ArrayList<LocationInfoDAO>();
		
		autoText = (AutoCompleteTextView) findViewById(R.id.autoText); 
		autoText.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list));
		
		// 리스트에 커스텀 아답타 셋팅.
		adapterItems.add(new LocationInfoDAO("테스트"));
		MyLocationAdapter adapter = new MyLocationAdapter(this, R.layout.location_list, adapterItems);
		locationList.setAdapter(adapter);
		
		searchBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				callGetMethod();		
			}
		});
		
	}

	private void callGetMethod() {
		String myKey = "0C1cQGwJCuZ/deraQery3KJQgzS+ETZUKaN24/5k/sMeMdxE6nrx3rTtj9wcBSHiQkdL6PlqN3yOdJC5P7WzuQ==";
		String serviceKey ;
		String url;
		try {
			serviceKey = URLEncoder.encode(myKey, "UTF-8");
			
			Log.i("MyDebug", "서비스 키 "  + serviceKey);
			
			url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey="+serviceKey+"&contentTypeId=&areaCode=1" +
					"&sigunguCode=7&listYN=Y&MobileOS=ETC&MobileApp=TourAPI2.0_Guide&arrange=A&numOfRows=10&pageNo=1";
			
			HttpGet method = new HttpGet(url);
			DefaultHttpClient client = new DefaultHttpClient();
			
			HttpResponse response = client.execute(method);
			
			 int status = response.getStatusLine().getStatusCode();
		        if ( status != HttpStatus.SC_OK )
		            throw new Exception( "" );  //실패
		        //(5)
		        //return EntityUtils.toString( response.getEntity(), "UTF-8" );
		        Log.i("MyDebug", EntityUtils.toString( response.getEntity(), "UTF-8" ));
		        
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
