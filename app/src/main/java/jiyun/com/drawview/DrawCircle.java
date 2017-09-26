package jiyun.com.drawview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class DrawCircle extends View{
    private Paint paint;

    public DrawCircle(Context context) {
        super(context);
    }

    public DrawCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DrawCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        int min = Math.min(measuredWidth, measuredHeight);
//        把本地资源图片 加载成bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.street);
//        缩放图片，防止以高的一半作半径时，宽度不够
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, min, min, false);
//
        Bitmap bitmap1 = Bitmap.createBitmap(scaledBitmap.getWidth(), scaledBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas1 = new Canvas(bitmap1);
        canvas1.drawCircle(scaledBitmap.getWidth()/2,scaledBitmap.getHeight()/2,scaledBitmap.getWidth()/2+0.1f,paint);
        paint.setAntiAlias(false);
        paint.setDither(false);

//        设置两个图层相交时的模式，就是在画布上遮上图形的信息
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Rect rect = new Rect(0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight());
        canvas.drawBitmap(bitmap1,0,0,null);
        canvas1.drawBitmap(scaledBitmap,rect,rect,paint);

    }
}
