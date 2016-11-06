/*
 *  Copyright 2016 Piruin Panichphol
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package me.piruin.quickaction;

import android.graphics.*;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;

final class ArrowDrawable extends ColorDrawable {
  static final int ARROW_UP = 1;
  static final int ARROW_DOWN = 2;

  private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
  private final Paint mStrokePaint;
  private final int mBackgroundColor;
  private final int mGravity;

  private Path mPath;

  ArrowDrawable(@ColorInt int foregroundColor, int gravity) {
    mGravity = gravity;
    mBackgroundColor = Color.TRANSPARENT;

    mPaint.setColor(foregroundColor);

    mStrokePaint = new Paint(mPaint);
    mStrokePaint.setStyle(Paint.Style.STROKE);
    mStrokePaint.setStrokeWidth(2);
    mStrokePaint.setARGB(50, 0, 0, 0);
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
    //canvas.drawPath(mPath, mStrokePaint);
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
