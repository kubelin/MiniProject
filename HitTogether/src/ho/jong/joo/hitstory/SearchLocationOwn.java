package ho.jong.joo.hitstory;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class SearchLocationOwn extends Activity{
	
	private ArrayList<String> adapterItems;
	private ArrayAdapter<String> editableAdapter;
	private ListView locationList;
	private Button searchBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_own_layout);
		
		searchBtn = (Button) findViewById(R.id.searchLocationBtn);
		locationList = (ListView) findViewById(R.id.searchLocationList);
		adapterItems = new ArrayList<String>();
		
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
			
			url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?ServiceKey="+serviceKey+"&areaCode=1&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=앱개발";
			
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
