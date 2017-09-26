package jiyun.com.drawview;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ViewGroupLinearLayout extends ViewGroup {
    public ViewGroupLinearLayout(Context context) {
        super(context);
    }

    public ViewGroupLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int width = 0;
        int height=0;
        for(int i = 0; i <childCount ; i++) {
            View childAt = getChildAt(i);
            int childwidth = childAt.getMeasuredWidth();
            int childheight = childAt.getMeasuredHeight();

//            水平排列 必须的立马考虑屏幕有多宽

//           水平排列的时候每次宽都变化  高 上坐标点为0，下坐标点为上坐标点+测量出来的高
//            水平第一个时，左坐标点为0，右坐标点为 测量出来的宽+左坐标点
//            水平排列之后的，左坐标点为左面的右坐标点，右坐标点为测量出来的宽+左坐标点
            childAt.layout(width,0,childwidth+width,t+childheight);
//            水平排列，下一个  左坐标点是左边的右坐标点，也就是 距离左边又多了左边的宽
            width+=childwidth;


//         垂直排列每次的高都变化，宽左坐标点为0，右坐标点为左坐标点+测量出来的宽，
//             高第一个时  上坐标点为0 下为测量出来的高+上坐标点
//             高后面的每个  上坐标点为上一层的高，下坐标点为测量出来的高+上坐标点
//            childAt.layout(0,height,l+childwidth,height+childheight);
//            垂直排列 下一个 上坐标点是上一个的下坐标点 也就是 距离上方多了上一行的高
//            height+=childheight;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else {
            if (widthmode == MeasureSpec.AT_MOST && heightmode == MeasureSpec.AT_MOST) {

                setMeasuredDimension(getTolWidth(), getMaxHeight());
            } else if (widthmode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(getTolWidth(), heightsize);
            } else if (heightmode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(widthsize, getMaxHeight());
            }
        }

    }

    public int getTolWidth(){
        int tolWidth = 0;
        int childCount = getChildCount();
        for (int i = 0;i<childCount;i++){
            View childAt = getChildAt(i);
            tolWidth += childAt.getMeasuredWidth();
        }
        return tolWidth;
    }

    public int getMaxHeight(){
        int maxHeight = 0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View childAt = getChildAt(i);
            if(childAt.getMeasuredHeight() > maxHeight) {
                maxHeight = childAt.getMeasuredHeight();
            }
        }
        return maxHeight;
    }

    public int getMaxWidth() {
        int max = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getMeasuredWidth() > max) {
                max = childAt.getMeasuredWidth();
            }
        }
        return max;
    }

    public int getTolHeight() {
        int tolHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            tolHeight += childAt.getMeasuredHeight();
        }
        return tolHeight;
    }
}
