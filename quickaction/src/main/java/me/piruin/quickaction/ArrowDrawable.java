package me.piruin.quickaction;

import android.graphics.*;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;

final class ArrowDrawable extends ColorDrawable {
  static final int ARROW_UP = 1;
  static final int ARROW_DOWN = 2;

  private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
  private final int mBackgroundColor;
  private final int mGravity;

  private Path mPath;

  ArrowDrawable(@ColorInt int foregroundColor, int gravity) {
    mGravity = gravity;
    mBackgroundColor = Color.TRANSPARENT;

    mPaint.setColor(foregroundColor);
  }

  @Override protected void onBoundsChange(Rect bounds) {
    super.onBoundsChange(bounds);
    updatePath(bounds);
  }

  private synchronized void updatePath(Rect bounds) {
    mPath = new Path();
    switch (mGravity) {
      case ARROW_UP:
        mPath.moveTo(0, bounds.height());
        mPath.lineTo(bounds.width()/2, 0);
        mPath.lineTo(bounds.width(), bounds.height());
        mPath.lineTo(0, bounds.height());
        break;
      case ARROW_DOWN:
        mPath.moveTo(0, 0);
        mPath.lineTo(bounds.width()/2, bounds.height());
        mPath.lineTo(bounds.width(), 0);
        mPath.lineTo(0, 0);
        break;
    }
    mPath.close();
  }

  @Override public void draw(Canvas canvas) {
    canvas.drawColor(mBackgroundColor);
    if (mPath == null) {
      updatePath(getBounds());
    }
    canvas.drawPath(mPath, mPaint);
  }

  public void setColor(@ColorInt int color) {
    mPaint.setColor(color);
  }

  @Override public void setColorFilter(ColorFilter colorFilter) {
    mPaint.setColorFilter(colorFilter);
  }

  @Override public int getOpacity() {
    if (mPaint.getColorFilter() != null) {
      return PixelFormat.TRANSLUCENT;
    }

    switch (mPaint.getColor() >>> 24) {
      case 255:
        return PixelFormat.OPAQUE;
      case 0:
        return PixelFormat.TRANSPARENT;
    }
    return PixelFormat.TRANSLUCENT;
  }
}