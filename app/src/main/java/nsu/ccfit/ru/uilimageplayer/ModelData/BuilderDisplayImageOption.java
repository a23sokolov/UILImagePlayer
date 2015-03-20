package nsu.ccfit.ru.uilimageplayer.ModelData;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import nsu.ccfit.ru.uilimageplayer.R;

/**
 * Created by Android on 19.03.2015.
 */
public class BuilderDisplayImageOption {
    private DisplayImageOptions displayImageOptions;
    private Context context;
    private static BuilderDisplayImageOption builderDisplayImageOption;


    public DisplayImageOptions getDisplayImageOptions() {
        return displayImageOptions;
    }


    private BuilderDisplayImageOption(Context c)
    {
        context = c.getApplicationContext();
        initDisplayImageOption();
    }

    private void initDisplayImageOption()
    {
        displayImageOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(Constants.getCACHE_IMAGE_CHECKBOX_VALUE())
                .cacheOnDisk(Constants.getCACHE_IMAGE_CHECKBOX_VALUE())
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    public static BuilderDisplayImageOption getBuilderDisplayImageOption(Context c)
    {
        if(builderDisplayImageOption==null)
        {
            builderDisplayImageOption = new BuilderDisplayImageOption(c);
        }
        return builderDisplayImageOption;
    }

    public void setCacheInMemoryDisk()
    {
        displayImageOptions = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.ic_stub)
            .showImageForEmptyUri(R.drawable.ic_empty)
            .showImageOnFail(R.drawable.ic_error)
            .cacheInMemory(Constants.getCACHE_IMAGE_CHECKBOX_VALUE())
            .cacheOnDisk(Constants.getCACHE_IMAGE_CHECKBOX_VALUE())
            .considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();
    }
}
