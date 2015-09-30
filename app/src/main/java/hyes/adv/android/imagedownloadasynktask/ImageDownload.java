//package hyes.adv.android.imagedownloadasynktask;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.http.AndroidHttpClient;
//import android.os.AsyncTask;
//import android.util.Log;
//import android.widget.ImageView;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * Created by hyes on 2015. 9. 30..
// */
//public class ImageDownload {
//    public void download(String url, ImageView imageView) {
//
//        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
//        BitmapDownloaderTask task = new BitmapDownloaderTask(imageView);
//        task.setUrl(url);
//
//        if (currentapiVersion >=
//                android.os.Build.VERSION_CODES.HONEYCOMB) {
//            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        } else {
//            task.execute();
//        }
//
//    }
//}
//
//
//
//class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap> {
//    private String url;
//    private ImageView imageView;
//
//    public BitmapDownloaderTask(ImageView imageView){
//        this.imageView = imageView;
//    }
//
//    Bitmap downloadBitmap(String url) {
//
//        final HttpClient client = AndroidHttpClient.newInstance("Android");
//        final HttpGet getRequest = new HttpGet(url);
//
//        try {
//            HttpResponse response = client.execute(getRequest);
//            final int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode != HttpStatus.SC_OK) {
//                Log.w("ImageDownloader", "Error " + statusCode +
//                        " while retrieving bitmap from " + url);
//                return null;
//            }
//
//            final HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                InputStream inputStream = null;
//                try {
//                    inputStream = entity.getContent();
//                    return BitmapFactory.decodeStream(inputStream);
//                } finally {
//                    if (inputStream != null) {
//                        inputStream.close();
//                    }
//                    entity.consumeContent();
//                }
//            }
//        } catch (IOException e) {
//        } catch (IllegalStateException e) {
//        } catch (Exception e) {
//        } finally {
//        }
//        return null;
//    }
//
//    @Override
//    protected Bitmap doInBackground(String... params) {
//
//        Bitmap bitmap = downloadBitmap(url);
//
//        return bitmap;
//    }
//
//    @Override
//    protected void onPostExecute(Bitmap bitmap) {
//
//        imageView.setImageBitmap(bitmap);
//
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//}