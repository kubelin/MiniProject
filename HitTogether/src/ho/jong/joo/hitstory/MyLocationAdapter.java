package ho.jong.joo.hitstory;

import ho.jong.joo.hitstory.vo.LocationInfoVO;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyLocationAdapter extends ArrayAdapter<LocationInfoVO>{
	Context context;
	List<LocationInfoVO> list;
	
	public MyLocationAdapter(Context context,  int textViewResourceId, List<LocationInfoVO> list) {
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
		 
		LocationInfoVO locVo =  getItem(position);
		Log.i("test", ""+locVo);
		
		TextView title = (TextView) view.findViewById(R.id.location_title);
		TextView addr1  =(TextView) view.findViewById(R.id.location_addr1);
		TextView sigungucode =(TextView) view.findViewById(R.id.location_sigungucode);
		TextView contenttypeid =(TextView) view.findViewById(R.id.location_contenttypeid);
		
		title.setText(locVo.getTitle());
		addr1.setText(locVo.getAddr1());
		sigungucode.setText( String.valueOf(locVo.getSigungucode()));
		contenttypeid.setText( String.valueOf(locVo.getContenttypeid()));
//		TextView tv = (TextView) view.findViewById(R.id.loc_title);
//		tv.setText(locDao.getTitle());
		
		return view;
	}
}
