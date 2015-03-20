package nsu.ccfit.ru.uilimageplayer.Activity;

import android.support.v4.app.Fragment;

import nsu.ccfit.ru.uilimageplayer.Fragment.GridFragment;
import nsu.ccfit.ru.uilimageplayer.Fragment.PageFragment;

/**
 * Created by Android on 16.03.2015.
 */
public class HostPagerActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return PageFragment.newInstance(getIntent().getIntExtra(GridFragment.TAG_POSITION,0));
    }
}
