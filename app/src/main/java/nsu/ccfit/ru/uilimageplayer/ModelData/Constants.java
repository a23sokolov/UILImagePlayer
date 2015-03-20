package nsu.ccfit.ru.uilimageplayer.ModelData;

/**
 * Created by Android on 16.02.2015.
 */
public final class Constants {
    public static final String IMAGE_POSITION = "com.ccfit.ru.IMAGE_POSITION";

    public static boolean getCACHE_IMAGE_CHECKBOX_VALUE() {
        return CACHE_IMAGE_CHECKBOX_VALUE;
    }

    public static void setCACHE_IMAGE_CHECKBOX_VALUE(boolean CACHE_IMAGE_CHECKBOX_VALUE) {
        Constants.CACHE_IMAGE_CHECKBOX_VALUE = CACHE_IMAGE_CHECKBOX_VALUE;
    }

    private static boolean CACHE_IMAGE_CHECKBOX_VALUE = true;

    public static class Config {
        public static final boolean DEVELOPER_MODE = false;
    }
}
