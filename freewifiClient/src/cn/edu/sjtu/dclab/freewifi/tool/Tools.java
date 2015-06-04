package cn.edu.sjtu.dclab.freewifi.tool;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Tools
 *
 * @author Jian Yang
 * @date 2015/6/4
 */
public class Tools {
    private static final String TAG = "Tools";

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
