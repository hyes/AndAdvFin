package hyes.adv.android.imagedownloadasynktask;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by hyes on 2015. 9. 30..
 */
public class ImageAdapter extends BaseAdapter {

    private Context context;

    public ImageAdapter(Context context){
        this.context = context;
    }
    private static final String[] URLS = {
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcT_edvuw3euXP1rcRu0zRnPDHs4CaTQ0RfdYmNmQI-QHDSXVQGi7Q",
            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQjtwukh7wqdj1OwPS1XxAq83N80fZuc4-fkWG1K_1m3NkKMqMG",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTVzFeLW_Qh165dcSbM7rnSi6hBjbcZjLlnDwOOKrL7dGBnutBlEg",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRiRhNkEKXAfooRKvbZ32P-K99VbxNIJsDdJ3VKsSD3xuDKDx1V",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcToIq9Ki2VElG70HbzWxzjq4z2WB7a8mB7i-_3SWWOXkpHfI0_4",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTb7Gi864q17wihT2VRBACW8LrZHESpw0yopV5sABiXfA2DnzG-Pw",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSr6nNiXUVUIhXDblaFr86WhxGY8PB2WWJOzhBX-pb2gUYq6nuwNQ",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSgbjjcgdkJoXoK5_T-u6yfB0ogsEa-zoVLaGm9-lD1HtojhgO_Dw",
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQrHEaZscZhXM9-EC2wVqVzBvO3sToyAqAoMNRj_F_HSQRylbWCFA",
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRgHegZ87u1WPq9INMN-SEpAXbZkm56UCaVN3FhNXlOLa4gXOn8BQ",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTGvf6W4qwHMHMV9PTBhsYNS0jvAtXF80yMVyJl66VFSMtekYmAHw",
            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQ_DnaeV9gENS4qEN-Yjq9k2PzZwD5jB1Zd9z_KmacGjaYdIKml"

    };

 //   private final ImageDownload imageDownloader = new ImageDownload();
    private final ImageDownloadHandler imageDownloader = new ImageDownloadHandler();

    public int getCount() {
        return URLS.length;
    }

    public String getItem(int position) {
        return URLS[position];
    }

    public long getItemId(int position) {
        return URLS[position].hashCode();
    }

    public View getView(int position, View view, ViewGroup parent) {
        ImageView imageView;

        if (view == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);

        }else{
            imageView = (ImageView)view;
        }

        imageDownloader.download(URLS[position], imageView);
        return imageView;
    }

}