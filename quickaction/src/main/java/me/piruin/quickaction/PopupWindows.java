package me.piruin.quickaction;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

class PopupWindows {
  PopupWindow mWindow;
  private View mRootView;
  private Drawable mBackground = null;
  private Context mContext;

  PopupWindows(Context context) {
    mContext = context;
    mWindow = new PopupWindow(context);
    mWindow.setTouchInterceptor(new View.OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
          mWindow.dismiss();
          return true;
        }
        return false;
      }
    });
  }

  Context getContext() {
    return mContext;
  }

  void preShow() {
    if (mRootView == null)
      throw new IllegalStateException("setContentView was not called with a view to display.");

    if (mBackground == null)
      mWindow.setBackgroundDrawable(new BitmapDrawable());
    else
      mWindow.setBackgroundDrawable(mBackground);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      mWindow.setElevation(10);
    }

    mWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
    mWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
    mWindow.setTouchable(true);
    mWindow.setFocusable(true);
    mWindow.setOutsideTouchable(true);
    mWindow.setContentView(mRootView);
  }

  void setContentView(View root) {
    mRootView = root;

    mWindow.setContentView(root);
  }

  void setOnDismissListener(PopupWindow.OnDismissListener listener) {
    mWindow.setOnDismissListener(listener);
  }

  void dismiss() {
    mWindow.dismiss();
  }
}
