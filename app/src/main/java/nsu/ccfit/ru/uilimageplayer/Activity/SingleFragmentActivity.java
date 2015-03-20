package nsu.ccfit.ru.uilimageplayer.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import nsu.ccfit.ru.uilimageplayer.ModelData.BuilderDisplayImageOption;
import nsu.ccfit.ru.uilimageplayer.R;

public abstract class SingleFragmentActivity extends FragmentActivity {
    private DisplayImageOptions displayImageOptions;
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        displayImageOptions = BuilderDisplayImageOption.getBuilderDisplayImageOption(SingleFragmentActivity.this).getDisplayImageOptions();
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = createFragment();
            manager.beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
