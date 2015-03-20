package nsu.ccfit.ru.uilimageplayer.Activity;

import android.support.v4.app.Fragment;

import nsu.ccfit.ru.uilimageplayer.Fragment.GridFragment;


public class HostMainActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return GridFragment.newInstance();
    }
}
