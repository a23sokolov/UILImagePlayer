package nsu.ccfit.ru.uilimageplayer.Fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import nsu.ccfit.ru.uilimageplayer.ModelData.BuilderDisplayImageOption;
import nsu.ccfit.ru.uilimageplayer.ModelData.Constants;
import nsu.ccfit.ru.uilimageplayer.ModelData.UrlImages;
import nsu.ccfit.ru.uilimageplayer.R;

/**
 * Created by Android on 17.02.2015.
 */
public class PageFragment extends SingleFragment {
    public final String myLog = "PageFragmentMyLog";
    private final String TAG = "PageFragment";
    FragmentActivity curContext;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setHasOptionsMenu(true);
        setRetainInstance(true);
        displayImageOptions = BuilderDisplayImageOption.getBuilderDisplayImageOption(getActivity()).getDisplayImageOptions();
    }


    @Override
    public void onAttach(Activity activity) {
        curContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(myLog, "On create VIEW");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            if(NavUtils.getParentActivityIntent(getActivity())!=null){
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        View rootView = inflater.inflate(R.layout.fr_item_pager, container, false);
        ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
        pager.setAdapter(new ImageAdapter());
        pager.setCurrentItem(getArguments().getInt(Constants.IMAGE_POSITION, 0));
        return rootView;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(NavUtils.getParentActivityIntent(getActivity())!=null)
                    NavUtils.navigateUpFromSameTask(getActivity());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static PageFragment newInstance(int position)
    {
        Bundle args = new Bundle();
        args.putInt(Constants.IMAGE_POSITION,position);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }
    private class ImageAdapter extends PagerAdapter {

        private LayoutInflater inflater;
        private UrlImages urlImages;


        ImageAdapter() {
            inflater = LayoutInflater.from(getActivity());
            urlImages = UrlImages.newInstanceSingleton(getActivity());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return urlImages.getCountOfUrl();
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            View imageLayout = inflater.inflate(R.layout.item_pager_image, view, false);
            assert imageLayout != null;
            final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.imageGif);
            final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);

            ImageLoader.getInstance().displayImage(urlImages.getUrlByPosition(position), imageView, displayImageOptions, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    spinner.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    String message = null;
                    switch (failReason.getType()) {
                        case IO_ERROR:
                            message = "Input/Output error";
                            break;
                        case DECODING_ERROR:
                            message = "Image can't be decoded";
                            break;
                        case NETWORK_DENIED:
                            message = "Downloads are denied";
                            break;
                        case OUT_OF_MEMORY:
                            message = "Out Of Memory error";
                            break;
                        case UNKNOWN:
                            message = "Unknown error";
                            break;
                    }
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                    spinner.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    spinner.setVisibility(View.GONE);
                }
            });

            view.addView(imageLayout, 0);
            return imageLayout;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }
}
