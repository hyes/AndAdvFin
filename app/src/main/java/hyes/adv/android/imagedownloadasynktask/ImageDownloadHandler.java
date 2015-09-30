package hyes.adv.android.imagedownloadasynktask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hyes on 2015. 9. 30..
 */
public class ImageDownloadHandler {

    private HandlerThread handlerThread;


        public void download(final String url, final ImageView imageView) {

            handlerThread = new HandlerThread("HandlerThread");
            handlerThread.start();

            Handler handler = new Handler(handlerThread.getLooper()){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    final Bitmap bitmapImage = downloadBitmap(url);

                    Handler handler = new Handler(Looper.getMainLooper()){
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            imageView.setImageBitmap(bitmapImage);
                        }
                    };
                    handler.sendMessage(new Message());
                }
            };
            handler.sendMessage(new Message());
        }


        Bitmap downloadBitmap(String url) {

            final HttpClient client = AndroidHttpClient.newInstance("Android");
            final HttpGet getRequest = new HttpGet(url);
            try {
                HttpResponse response = client.execute(getRequest);
                final int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode != HttpStatus.SC_OK) {
                    return null;
                }

                final HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream inputStream = null;
                    try {
                        inputStream = entity.getContent();
                        return BitmapFactory.decodeStream(inputStream);
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        entity.consumeContent();
                    }
                }
            } catch (IOException e) {
                getRequest.abort();
            } catch (IllegalStateException e) {
                getRequest.abort();
            } catch (Exception e) {
                getRequest.abort();
            } finally {
            }
            return null;
        }
}