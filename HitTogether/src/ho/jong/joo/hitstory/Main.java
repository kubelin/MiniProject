package ho.jong.joo.hitstory;

import java.util.TreeMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	
	private Button searchBtn;
	
	private TreeMap<String, Intent> actions = new TreeMap<String, Intent>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		searchBtn = (Button) findViewById(R.id.SearchBtn);
		addActionMap("1", SearchLocationOwnActivity.class);
	}
	
	public void goAction(View view){
		switch (view.getId()) {
			case R.id.SearchBtn:
				startActivity(actions.get("1"));
				break;
			default:
				break;
		}
	}
	
	private void addActionMap(String keyName, Class<?> className){
		actions.put(keyName, new Intent(this, className));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
