package nsu.ccfit.ru.uilimageplayer.Fragment;

import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import nsu.ccfit.ru.uilimageplayer.ModelData.BuilderDisplayImageOption;
import nsu.ccfit.ru.uilimageplayer.ModelData.Constants;
import nsu.ccfit.ru.uilimageplayer.R;

/**
 * Created by Android on 19.03.2015.
 */
public abstract class SingleFragment extends Fragment {
    protected DisplayImageOptions displayImageOptions;
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main,menu);
        menu.findItem(R.id.cachePicture).setChecked(Constants.getCACHE_IMAGE_CHECKBOX_VALUE());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.memoryCache:
                ImageLoader.getInstance().clearMemoryCache();
                return true;
            case R.id.diskCache:
                ImageLoader.getInstance().clearDiskCache();
                return true;
            case R.id.cachePicture:
                Constants.setCACHE_IMAGE_CHECKBOX_VALUE(!Constants.getCACHE_IMAGE_CHECKBOX_VALUE());
                item.setChecked(Constants.getCACHE_IMAGE_CHECKBOX_VALUE());
                BuilderDisplayImageOption.getBuilderDisplayImageOption(getActivity()).setCacheInMemoryDisk();
                displayImageOptions = BuilderDisplayImageOption.getBuilderDisplayImageOption(getActivity()).getDisplayImageOptions();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
