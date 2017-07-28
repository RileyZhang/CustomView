package com.example.customview.view;

import java.util.ArrayList;
import java.util.List;

import com.example.customview.R;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class QQHealthView extends View {

	private static final String TAG = QQHealthView.class.getSimpleName();
	//������ɫ,��С,���ߵ���ɫ
	private int highLightTextColor;
	private int commonTextColor;
	private int lineColor;
	
	private int mySize, rank, averageSize;
    private String myaverageTxt;

    //�����Ļ���
    private Paint backgroundPaint;
    //����������
    private int radiusBg, widthBg, heightBg;
    private Path pathBg, linePath;
    //Բ���Ļ���
    private Paint arcPaint;
    private RectF arcRect;
    //���ֵĻ���
    private Paint textPaint;
    private PathEffect effects;

    //���ߵĻ���
    private Paint linePaint;

    //Բ�������ľ���,�߶�,ƽ���߶�
    private float rectSize, rectAgHeight;
    //Բ�������Ļ���
    private Paint rectPaint;
    private Path rectPath;

    //�ײ�����
    private Paint weavPaint;
    private Path weavPath;

    //����ʵ��
    //����Ч�������
    private AnimatorSet animSet;
    private int walkNum, rankNum;
    private float arcNum;

    //ˮ���ƵĶ���ʵ��
    private float weavX,weavY;
    
    private List<Integer> arrayList = new ArrayList<Integer>();

    
    public void setMySize(int mySize) {
        this.mySize = mySize;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setAverageSize(int averageSize) {
        this.averageSize = averageSize;
    }
    
	public QQHealthView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public QQHealthView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}
	
	public QQHealthView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		arrayList.add(1234);
		arrayList.add(2234);
		arrayList.add(4234);
		arrayList.add(6234);
		arrayList.add(3834);
		arrayList.add(7234);
		arrayList.add(5436);
		TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.QQHealthView, defStyleAttr, 0);
		int n = array.length();
		for (int i = 0; i < n; i++) {
			int type = array.getIndex(i);
			switch(type) {
			case R.styleable.QQHealthView_highLightTextColor:
				highLightTextColor = array.getColor(i, Color.WHITE);
				break;
			case R.styleable.QQHealthView_commonTextColor:
				commonTextColor = array.getColor(i, Color.WHITE);
				break;
			case R.styleable.QQHealthView_lineColor:
				lineColor = array.getColor(i, Color.WHITE);
				break;
			default:
				break;
			}
		}
		
		array.recycle();
		initView();
	}
	
	private void initView() {
		pathBg = new Path();
        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePath = new Path();
        effects = new DashPathEffect(new float[]{5, 5}, 1);
        rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPath = new Path();
        weavPaint = new Paint();
        weavPaint.setAntiAlias(true);
        weavPath = new Path();
        animSet = new AnimatorSet();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onMeasure()######");
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int width;
		int height;
		
		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		} else {
			width = widthSize *1/2;
		}
		
		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;
		} else {
			height = heightSize *3/4;
		}
		
		widthBg = width;
		heightBg = height;
		setMeasuredDimension(width, height);
		startAnima();
	}
	
	private void startAnima() {
		Log.i(TAG, "startAnima()########");
		ValueAnimator walkAnimator = ValueAnimator.ofInt(0, mySize);
		walkAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				walkNum = (Integer)animation.getAnimatedValue();
				Log.i(TAG, "walkAnimator walkNum = " + walkNum);
				postInvalidate();
			}
		});
		
		ValueAnimator rankAnimator = ValueAnimator.ofInt(0,rank);
		rankAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				rankNum = (Integer)animation.getAnimatedValue();
				postInvalidate();
			}
		});
		
		double size = mySize;
		double avgSize = averageSize;
		if (size > avgSize) {
			size = avgSize;
		}
		ValueAnimator arcAnimation = ValueAnimator.ofFloat(0, (float)(size/avgSize * 300));
		arcAnimation.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				arcNum = (Float)animation.getAnimatedValue();
				postInvalidate();
			}
		});
		
		ValueAnimator waveXAnimation = ValueAnimator.ofFloat(heightBg*10/12,heightBg *11/12);
		waveXAnimation.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				weavY = (Float)animation.getAnimatedValue();
				postInvalidate();
			}
		});
		
		animSet.setDuration(3000);
		animSet.playTogether(walkAnimator, rankAnimator, arcAnimation, waveXAnimation);
		animSet.start();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onDraw()######");
		super.onDraw(canvas);
		//������
		radiusBg = widthBg/20;
		pathBg.moveTo(0, heightBg);
		pathBg.lineTo(0, radiusBg);
		pathBg.quadTo(0, 0, radiusBg, 0);
		pathBg.lineTo(widthBg - radiusBg, 0);
		pathBg.quadTo(widthBg, 0, widthBg, radiusBg);
		pathBg.lineTo(widthBg, heightBg);
		pathBg.lineTo(0, heightBg);
		backgroundPaint.setColor(Color.WHITE);
		canvas.drawPath(pathBg, backgroundPaint);
		
		//����Բ��
		arcPaint.setStrokeWidth(widthBg/20);
		arcPaint.setStyle(Paint.Style.STROKE);
		arcPaint.setDither(true);
		arcPaint.setStrokeJoin(Paint.Join.ROUND);
		arcPaint.setStrokeCap(Paint.Cap.ROUND);
		arcPaint.setColor(commonTextColor);
		arcRect = new RectF(widthBg * 1/4, widthBg * 1/4, widthBg *3/4, widthBg *3/4);
		canvas.drawArc(arcRect, 120, 300, false, arcPaint);
		arcPaint.setColor(highLightTextColor);
		canvas.drawArc(arcRect, 120, arcNum, false, arcPaint);
		
		//����Բ���ڵ���
		textPaint.setColor(highLightTextColor);
		textPaint.setTextSize(widthBg/10);
		canvas.drawText(String.valueOf(walkNum), widthBg *3/8, widthBg *1/2 + 20, textPaint);
		textPaint.setTextSize(widthBg/15);
		canvas.drawText(String.valueOf(rankNum), widthBg *1/2 - 15, widthBg *3/4 + 10, textPaint);
		
		textPaint.setColor(commonTextColor);
		textPaint.setTextSize(widthBg/25);
		canvas.drawText("��ֹ13:45����", widthBg *3/8 - 10, widthBg *5/12 - 10, textPaint);
		canvas.drawText("����ƽ��2781��", widthBg *3/8 - 10, widthBg * 2 / 3 - 20, textPaint);
		canvas.drawText("��", widthBg *1/2 - 50, widthBg * 3 / 4 + 10, textPaint);
		canvas.drawText("��", widthBg *1/2 + 30, widthBg * 3 / 4 + 10, textPaint);
		
		canvas.drawText("���7��", widthBg * 1 / 15, widthBg, textPaint);
        myaverageTxt = String.valueOf(averageSize);
        canvas.drawText("ƽ��", widthBg * 10 / 15 - 15, widthBg, textPaint);
        canvas.drawText(myaverageTxt, widthBg * 11 / 15, widthBg, textPaint);
        canvas.drawText("��/��", widthBg * 12 / 15 + 20, widthBg, textPaint);
        
        //��������
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(2);
        linePaint.setStyle(Paint.Style.STROKE);
        linePath.moveTo(widthBg * 1/15, widthBg + 80);
        linePath.lineTo(widthBg * 14/15, widthBg + 80);
        linePaint.setPathEffect(effects);
        canvas.drawPath(linePath, linePaint);
        
        //���Ʒֲ���
        rectSize = widthBg / 12;
        rectAgHeight = widthBg / 10;
        int count = arrayList.size();
        for (int i = 0; i < count; i++) {
        	rectPaint.setStyle(Paint.Style.STROKE);
        	rectPaint.setStrokeWidth(widthBg/25);
        	rectPaint.setStrokeCap(Paint.Cap.ROUND);
        	rectPaint.setStrokeJoin(Paint.Join.ROUND);
        	float startHeight = widthBg + 90 + rectAgHeight;
        	rectPath.moveTo(rectSize, startHeight);
        	double percentage = Double.valueOf(arrayList.get(i)/Double.valueOf(averageSize));
        	double height = percentage * rectAgHeight;
        	rectPath.lineTo(rectSize, (float)(startHeight - height));
        	rectPaint.setColor(commonTextColor);
        	canvas.drawPath(rectPath, rectPaint);
        	textPaint.setColor(commonTextColor);
        	canvas.drawText("0" + (i + 1) + "��", rectSize - 25, startHeight + 50, textPaint);
        	rectSize += widthBg / 7;
        }
        
        //���Ƶײ�����
        weavPaint.setColor(highLightTextColor);
        weavPath.reset();
        weavPath.moveTo(0, heightBg);
        weavPath.lineTo(0, heightBg *10/12);
        weavPath.cubicTo(weavX, weavY, widthBg *3/10, heightBg *11/12, widthBg, heightBg *10/12);
        weavPath.lineTo(widthBg, heightBg);
        weavPath.lineTo(0, heightBg);
        canvas.drawPath(weavPath, weavPaint);
        
        //���Ƶײ�����
        weavPaint.setColor(Color.WHITE);
        weavPaint.setTextSize(widthBg/20);
        canvas.drawText("�ɼ��������Ŭ��Ŷ", widthBg *1/10 -20, heightBg *11/12 +50, weavPaint);
	}
	
	public void reSet(int mysize, int myrank, int myaverageSize) {
		walkNum = 0;
		arcNum = 0;
		rankNum = 0;
		mySize = mysize;
		rank = myrank;
		averageSize = myaverageSize;
		startAnima();
	}
}
