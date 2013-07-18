package ho.jong.joo.hitstory;

import ho.jong.joo.hitstory.dao.LocationInfoDAO;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyLocationAdapter extends ArrayAdapter<LocationInfoDAO>{
	Context context;
	List<LocationInfoDAO> list;
	
	public MyLocationAdapter(Context context,  int textViewResourceId, List<LocationInfoDAO> list) {
		super(context, textViewResourceId, list);
		this.context = context;
		this.list = list;
	}
	
	public MyLocationAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if( view == null){
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.location_list, parent , false);
		}
		 
		LocationInfoDAO locDao =  getItem(position);
		Log.i("MyDebug", ""+locDao);
		TextView tv = (TextView) view.findViewById(R.id.loc_title);
		
		tv.setText(locDao.getTitle());
		
		return view;
	}
}
