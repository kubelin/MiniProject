package ho.jong.joo.hitstory;

import java.io.InputStream;
import java.lang.ref.WeakReference;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class ImageDownloader  {
	
	public void download(String url, ImageView imageView){
		BitmapDownloaderTask task = new BitmapDownloaderTask(imageView);
		task.execute(url);
	}

}


class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap>{
	private String url;
	private final WeakReference<ImageView> imageViewReference;
	
	public BitmapDownloaderTask(ImageView imageView) {
		imageViewReference = new WeakReference<ImageView>(imageView);
	}
	
	// 백그라운드 스레드 시작
	@Override
	protected Bitmap doInBackground(String... params) {
		return downloadBitmap(params[0]);
	}
	
	// 태스크가 종료된 시점에 UI 스레드에서 실행
	@Override
	protected void onPostExecute(Bitmap result) {
		if( isCancelled() ){
			result = null;
		}
		if( imageViewReference != null){
			ImageView imageView = imageViewReference.get();
			if( imageView != null){
				imageView.setImageBitmap(result);
			}
		}
	}
	
	// URL 이미지 다운로드 
	static Bitmap downloadBitmap(String url) {
		
		final AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
		final HttpGet getRequest = new HttpGet(url);
		
		try{
			HttpResponse response = client.execute(getRequest);
			final int statusCode = response.getStatusLine().getStatusCode();
			
			if( statusCode != HttpStatus.SC_OK ){
				Log.w("test", "Error" + statusCode);
				return null;
			}
			final HttpEntity entity = response.getEntity();
			if( entity != null){
				InputStream is = null;
				try{
					is = entity.getContent();
					final Bitmap bitmap = BitmapFactory.decodeStream(is);
					return bitmap;
				}finally{
					if( is != null){
						is.close();
					}
					//
					entity.consumeContent();
				}
			}
		}catch(Exception e){
			getRequest.abort();
		}finally{
			if(client!=null){
				client.close();
			}
		}
		return null;
	}
	
}