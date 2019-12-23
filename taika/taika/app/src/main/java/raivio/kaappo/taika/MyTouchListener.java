package raivio.kaappo.taika;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public final class MyTouchListener implements View.OnTouchListener {

    public boolean onTouch (View view, MotionEvent motionEvent) {
        System.out.println("Touch: " + motionEvent);

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                view.setTag(MainActivity.getId((View) view.getParent()));

                ClipData data = ClipData.newPlainText("", "");

                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view) {

                    private static final int SCALING_FACTOR = 2;

                    @Override
                    public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
                        View v = getView();
                        final int width = v.getWidth() * SCALING_FACTOR;
                        final int height = v.getHeight() * SCALING_FACTOR;
                        shadowSize.set(width, height);
                        shadowTouchPoint.set(width / 2, height);
                    }

                    @Override
                    public void onDrawShadow(Canvas canvas) {
                        canvas.scale(SCALING_FACTOR, SCALING_FACTOR);
                        getView().draw(canvas);
                    }
                };

                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;

            case MotionEvent.ACTION_UP:
                view.performClick();
                view.setVisibility(View.VISIBLE);
                return true;
            default:
                return false;
        }
    }
}