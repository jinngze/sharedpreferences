package com.example.dell.hhh.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.dell.hhh.MainActivity;

import static android.support.v4.content.ContextCompat.startActivity;

public class CircleProgressBar extends View {

    private Context context;
    private int height,width;  //自定义控件的宽高
    private float progress;  //进度

    private Paint paint;  //蓝色扇形所需的画笔
    private Paint bkPaint;  //绿色圆形所需的画笔
    private Paint tvPaint;  //圆里面的进度字所需的画笔
    private Rect mBound;  //用于获取字体的大小

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.paint = new Paint();
        this.bkPaint = new Paint();
        this.tvPaint = new Paint();
        this.mBound = new Rect();
        init();
    }

    private void init(){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#50A3FE"));
        paint.setAntiAlias(true);

        bkPaint.setStyle(Paint.Style.FILL);
        bkPaint.setColor(Color.parseColor("#00ff00"));
        bkPaint.setAntiAlias(true);

        tvPaint.setColor(Color.parseColor("#ffffff"));
        tvPaint.setTextSize(50);
    }


//画笔等属性初始化完成后，我们需要获取我们设置的宽高（这个圆形进度条的整体宽高），而我们获取View宽高的地方是重写View的onMeasure方法：
//获取当前控件的高度和宽度，单位是像素
@Override
   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        }
        //获取到了宽高后我们就可以开始画了，在CircleProgressBar 的onDraw方法里面画扇形，圆形，字。
   private float set2Degree(float sendFt){   //将进度的数值变为弧度数值，进度100，弧度有360，所以比例是3.6
        return sendFt*3.6f;
        }

     @Override
   protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(width*height==0){

        return;

        }
        canvas.drawArc(new RectF(0, 0, width, height), 270, set2Degree(progress), true, paint);  //画蓝色扇形
        canvas.drawCircle(width / 2, height / 2, width / 2 - 5, bkPaint);  //画绿色圆形，半径比蓝色扇形的小5px
        if(progress < 100){   //进度没达到100%时显示进度
        String strPro = String.valueOf((int)progress)+"%";
        tvPaint.getTextBounds(strPro,0,strPro.length(),mBound);
        canvas.drawText(strPro,width/2 - mBound.width()/2,height/2 + mBound.height()/2,tvPaint);
        }else{  //达到100%后显示完成
        String text = "完成";
        tvPaint.getTextBounds(text,0,text.length(),mBound);
        canvas.drawText(text,width/2 - mBound.width()/2,height/2 + mBound.height()/2,tvPaint);

        }
        }
        //当然画了后我们要实时更新弧度，所以我们在CircleProgressBar 里面加个public方法来实时更新进度。
      public void setProgress(float progress){
        this.progress = progress;
        postInvalidate();
        }

}
