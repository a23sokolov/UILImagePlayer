package nsu.ccfit.ru.uilimageplayer.Fragment.Utils;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import nsu.ccfit.ru.uilimageplayer.ModelData.UrlImages;
import nsu.ccfit.ru.uilimageplayer.R;

/**
 * Created by Android on 16.03.2015.
 */
public class ImageAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private UrlImages urlImagesAdapter;
    private DisplayImageOptions displayImageOptions;


    public ImageAdapter(FragmentActivity act,DisplayImageOptions displayImageOptions)
    {
        inflater = LayoutInflater.from(act);
        this.displayImageOptions = displayImageOptions;
        this.urlImagesAdapter = UrlImages.newInstanceSingleton(act);
    }
    @Override
    public int getCount() {
        return urlImagesAdapter.getCountOfUrl();
    }

    @Override
    public Object getItem(int position) {
        return urlImagesAdapter.getUrlByPosition(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View view = convertView;
        if(view == null)
        {
            view = inflater.inflate(R.layout.item_image_grid,parent,false);
            holder = new ViewHolder();
            assert view != null;
            holder.imageView = (ImageView)view.findViewById(R.id.image);
            holder.progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        ImageLoader.getInstance()
                .displayImage(urlImagesAdapter.getUrlByPosition(position), holder.imageView, displayImageOptions, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        holder.progressBar.setProgress(0);
                        holder.progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {
                        holder.progressBar.setProgress(Math.round(100.0f * current / total));
                    }
                });
        return view;
    }
    class ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
    }
}

