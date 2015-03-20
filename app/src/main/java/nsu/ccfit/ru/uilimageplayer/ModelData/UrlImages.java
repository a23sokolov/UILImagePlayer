package nsu.ccfit.ru.uilimageplayer.ModelData;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 16.03.2015.
 */
public class UrlImages {
    //Singleton. lifecycle == app lifecycle
    private static UrlImages urlImages = null;
    private static boolean readyToUse = false;
    private List<String> arrayList = null;
    Context context;
    private UrlImages(Context c)
    {
        context = c;
        initArrayList();
    }


    public static UrlImages newInstanceSingleton(Context c)
    {
        if(urlImages ==null)
        {
            urlImages = new UrlImages(c.getApplicationContext());
        }
        return urlImages;
    }

    public String getUrlByPosition(int position)
    {
        return arrayList.get(position);
    }

    private void initArrayList()
    {
        arrayList = new ArrayList<String>();
    }
    public int getCountOfUrl()
    {
        return arrayList.size();
    }
    public void setNewUrls(ArrayList<String> urls)
    {
        arrayList.addAll(urls);
        readyToUse = true;
    }

    public boolean isReadyToUse()
    {
        return  readyToUse;
    }
}
