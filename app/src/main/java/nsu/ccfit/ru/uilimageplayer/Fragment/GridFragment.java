package nsu.ccfit.ru.uilimageplayer.Fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import nsu.ccfit.ru.uilimageplayer.Activity.HostPagerActivity;
import nsu.ccfit.ru.uilimageplayer.AsyncTask.AsyncTaskGetUrlForImage;
import nsu.ccfit.ru.uilimageplayer.Fragment.Utils.ImageAdapter;
import nsu.ccfit.ru.uilimageplayer.Fragment.Utils.InterfaceForUpdateGridView;
import nsu.ccfit.ru.uilimageplayer.ModelData.BuilderDisplayImageOption;
import nsu.ccfit.ru.uilimageplayer.ModelData.UrlImages;
import nsu.ccfit.ru.uilimageplayer.R;

/**
 * Created by Android on 16.03.2015.
 */
public class GridFragment extends SingleFragment implements InterfaceForUpdateGridView {

    public static final String TAG_POSITION = "position";
    private static final String TAG_OPTIONS = "displayImageOption";
    private AsyncTaskGetUrlForImage asyncTaskGetUrlForImage;
    public ImageAdapter imageAdapter;
    AbsListView listView;
    UrlImages urlImages;
    private final String TAG = "GridFragment";
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        urlImages = UrlImages.newInstanceSingleton(getActivity());
        //uriImages lifecycle = application, if it exist don't do extra work
        Log.d(TAG, "OnCreate");
        if(savedInstanceState==null && !urlImages.isReadyToUse())
        {
            asyncTaskGetUrlForImage = new AsyncTaskGetUrlForImage(this, this.getActivity());
            asyncTaskGetUrlForImage.execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.fragment_image_grid,container,false);
        listView = (GridView) v.findViewById(R.id.grid);
        setAdapter();
        return v;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void setAdapter()
    {
        Log.d(TAG, "setAdapter");
        if(getActivity()==null || listView==null)
            return;
        if(!urlImages.isReadyToUse())
            return;
        displayImageOptions = BuilderDisplayImageOption.getBuilderDisplayImageOption(getActivity()).getDisplayImageOptions();
        imageAdapter = new ImageAdapter(this.getActivity(),displayImageOptions);
        listView.setAdapter(imageAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //showPageImage(position);
                Intent intent = new Intent(getActivity(),HostPagerActivity.class);
                intent.putExtra(TAG_POSITION,position);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView");
        super.onDestroyView();
        if(asyncTaskGetUrlForImage!=null)
            asyncTaskGetUrlForImage.cancel(true);
    }

    public static GridFragment newInstance()
    {
        GridFragment gridFragment = new GridFragment();
        //Bundle bundle = new Bundle();
        //gridFragment.setArguments(bundle);
        return gridFragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean value = super.onOptionsItemSelected(item);
        return value;
    }
}
