package me.piruin.quickaction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.List;
import me.piruin.quickaction.QuickAction.OnActionItemClickListener;

/**
 * @author Blast Piruin Panichphol <piruin.p@gmail.com>
 */
public class QuickIntentAction {
  private static final int SERVICE = 1;
  private static final int ACTIVITY = 0;
  Context mContext;
  Intent mIntent;
  int mOrientation = QuickAction.VERTICAL;
  int mIntentType = ACTIVITY;
  OnActionItemClickListener mOnActionItemClick;
  String mType[] = {"Activity", "Service"};

  public QuickIntentAction(Context context) {
    mContext = context;
  }

  public QuickIntentAction setOrientation(int orientation) {
    mOrientation = orientation;
    return this;
  }

  public QuickIntentAction setServiceIntent(Intent services) {
    mIntent = services;
    mIntentType = SERVICE;
    return this;
  }

  public QuickIntentAction setActivityIntent(Intent activity) {
    mIntent = activity;
    mIntentType = ACTIVITY;
    return this;
  }

  public QuickIntentAction serOnActionItemClickListener(OnActionItemClickListener onClick) {
    mOnActionItemClick = onClick;
    return this;
  }

  public QuickAction create() {
    QuickAction action = new QuickAction(mContext, mOrientation);

    // Add List of Support Activity or Services
    if (mIntent != null) {
      final List<ResolveInfo> lists;
      PackageManager pm = mContext.getPackageManager();

      switch (mIntentType) {
        case SERVICE:
          lists = pm.queryIntentServices(mIntent, 0);
          break;
        case ACTIVITY:
        default:
          lists = pm.queryIntentActivities(mIntent, 0);
          break;
      }

      // Add Action Item of support intent.
      if (lists.size() > 0) {
        int index = 0;
        for (ResolveInfo info : lists) {
          ActionItem item =
            new ActionItem(index++, (String)info.loadLabel(pm), info.getIconResource());
          action.addActionItem(item);
        }
        addOnActionItemClick(action, lists);
      } else {
        ActionItem item = new ActionItem(0, "Not found support any"+mType[mIntentType]+"!");
        action.addActionItem(item);
      }
    }

    return action;
  }

  private void addOnActionItemClick(QuickAction action, final List<ResolveInfo> lists) {
    // If not explicit add then we'll Add Default OnActionItemClick
    if (mOnActionItemClick != null)
      action.setOnActionItemClickListener(mOnActionItemClick);
    else {
      setDefaultOnActionItemClick(action, lists);
    }
  }

  private void setDefaultOnActionItemClick(QuickAction action, final List<ResolveInfo> lists) {
    switch (mIntentType) {
      case SERVICE:
        action.setOnActionItemClickListener(new OnActionItemClickListener() {
          @Override public void onItemClick(ActionItem item) {
            ResolveInfo info = lists.get(item.getActionId());
            String name = info.serviceInfo.name;
            String packageName = info.serviceInfo.packageName;

            Intent service = new Intent(mIntent);
            service.setComponent(new ComponentName(packageName, name));
            mContext.startService(service);
          }
        });
        break;
      case ACTIVITY:
      default:
        action.setOnActionItemClickListener(new OnActionItemClickListener() {
          @Override public void onItemClick(ActionItem item) {
            ResolveInfo info = lists.get(item.getActionId());
            String name = info.activityInfo.name;
            String packageName = info.activityInfo.packageName;

            Intent intent = new Intent(mIntent);
            intent.setComponent(new ComponentName(packageName, name));
            mContext.startActivity(intent);
          }
        });
        break;
    }
  }
}
