package com.tzj.tzjcustomview.guaguaview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.tzj.tzjcustomview.R;

/**
 * <p>
 * Description：通过BitmapShader绘制圆形图片
 * </p>
 *
 * @author tangzhijie
 */
public class CircleImageByShader extends View {

    //图片
    private Bitmap srcBitmap;

    private Paint paint;

    //用于缩放图片的
    private Matrix matrix;

    //圆的宽度
    private int viewWidth;
    //半径
    private int radius;

    public CircleImageByShader(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        matrix = new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //强制设置控件宽高一样
        viewWidth = Math.min(getMeasuredHeight(), getMeasuredWidth());
        radius = viewWidth / 2;
        setMeasuredDimension(viewWidth, viewWidth);
    }

    private void setShader() {
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic4);

        BitmapShader bitmapShader = new BitmapShader(srcBitmap,
                Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        //计算缩放比例
        int bitMin = Math.min(srcBitmap.getHeight(), srcBitmap.getWidth());

        float scale = viewWidth * 1.0f / bitMin;

        matrix.setScale(scale, scale);

        bitmapShader.setLocalMatrix(matrix);

        paint.setShader(bitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setShader();
        //绘制圆角矩形
        canvas.drawCircle(viewWidth / 2, viewWidth / 2, radius, paint);
    }
}
