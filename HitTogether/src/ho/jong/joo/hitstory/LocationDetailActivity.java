package ho.jong.joo.hitstory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class LocationDetailActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle budle) {
		super.onCreate(budle);
		setContentView(R.layout.location_detail);
		
		Intent intent = getIntent();
		ImageView testView = (ImageView) findViewById(R.id.imageView1);
		TextView title = (TextView) findViewById(R.id.textView1);
		TextView tel = (TextView) findViewById(R.id.textView3);
		
		ImageDownloader imgDown = new ImageDownloader();
		
		title.setText(intent.getExtras().getString("title"));
		imgDown.download(intent.getExtras().getString("imageUrl"), testView);
		tel.setText(intent.getExtras().getString("tel"));
		
	}
}
