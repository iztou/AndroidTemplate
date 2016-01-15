package gml.template.androidtemplate.tools.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import com.google.zxing.WriterException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by gml on 2016/1/15.
 */
public class QRCodeUtil {
    public static final int QRsIZE = 300;

    public static Bitmap CreateTwoDCode(String contentString)
            throws WriterException {
        return EncodingHandler.createQRCode(contentString, QRsIZE);
    }

    public static String saveQrCodePicture(Bitmap qrCodeBitmap) {
        final File qrImage = FileUtils.createPhotoSavedFile();
        if (qrImage == null) {
            return null;
        }

        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(qrImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (qrCodeBitmap == null) {
            return null;
        }

        qrCodeBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            if (fOut != null) {
                fOut.flush();
                fOut.close();
            }

            return qrImage.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (qrCodeBitmap != null) {
                qrCodeBitmap.recycle();
                qrCodeBitmap = null;
            }
        }
        return null;

    }

    public static String getQRcodeFile(String content) {
        try {
            return saveQrCodePicture(CreateTwoDCode(content));
        } catch (WriterException e) {
        }
        return null;
    }

    public static String mergePicByCanvas(View v) {
        int viewWidth = v.getMeasuredWidth();
        int viewHeight = v.getMeasuredHeight();
        if (viewWidth > 0 && viewHeight > 0) {
            Bitmap b = Bitmap.createBitmap(viewWidth, viewHeight,
                    Bitmap.Config.ARGB_8888);
            Canvas cvs = new Canvas(b);
            v.draw(cvs);
            return saveQrCodePicture(b);
        }
        return null;
    }

    public static void layoutView(View v, int width, int height) {
        // validate view.width and view.height
        v.layout(0, 0, width, height);
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width,
                View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height,
                View.MeasureSpec.EXACTLY);
        // validate view.measurewidth and view.measureheight
        v.measure(measuredWidth, measuredHeight);
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
    }
}
