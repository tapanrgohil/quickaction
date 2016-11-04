package me.piruin.quickaction.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import me.piruin.quickaction.ActionItem;
import me.piruin.quickaction.QuickAction;

public class SampleActivity extends Activity {

  private static final int ID_UP = 1;
  private static final int ID_DOWN = 2;
  private static final int ID_SEARCH = 3;
  private static final int ID_INFO = 4;
  private static final int ID_ERASE = 5;
  private static final int ID_OK = 6;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_sample);

    int arrow_up = me.piruin.quickaction.R.id.arrow_up;

    ActionItem nextItem = new ActionItem(ID_DOWN, "Next", R.drawable.menu_down_arrow);
    ActionItem prevItem = new ActionItem(ID_UP, "Prev", R.drawable.menu_up_arrow);
    ActionItem searchItem = new ActionItem(ID_SEARCH, "Find", R.drawable.menu_search);
    ActionItem infoItem = new ActionItem(ID_INFO, "Info", R.drawable.menu_info);
    ActionItem eraseItem = new ActionItem(ID_ERASE, "Clear", R.drawable.menu_eraser);
    ActionItem okItem = new ActionItem(ID_OK, "OK", R.drawable.menu_ok);

    //use setSticky(true) to disable QuickAction dialog being dismissed after an item is clicked
    prevItem.setSticky(true);
    nextItem.setSticky(true);

    //create QuickAction. Use QuickAction.VERTICAL or QuickAction.HORIZONTAL param to define layout
    //orientation
    final QuickAction quickAction = new QuickAction(this, QuickAction.HORIZONTAL);
    quickAction.setTextColor(Color.WHITE);
    quickAction.setColor(Color.GRAY);

    //add action items into QuickAction
    quickAction.addActionItem(nextItem);
    quickAction.addActionItem(prevItem);
    quickAction.addActionItem(searchItem);
    quickAction.addActionItem(infoItem);
    quickAction.addActionItem(eraseItem);
    quickAction.addActionItem(okItem);

    //Set listener for action item clicked
    quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
      @Override public void onItemClick(QuickAction source, int pos, int actionId) {
        //here we can filter which action item was clicked with pos or actionId parameter
        ActionItem actionItem = quickAction.getActionItem(pos);
        String title = actionItem.getTitle();
        Toast.makeText(SampleActivity.this, title+" selected", Toast.LENGTH_SHORT).show();
      }
    });

    //set listnener for on dismiss event, this listener will be called only if QuickAction dialog
    // was dismissed
    //by clicking the area outside the dialog.
    quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
      @Override public void onDismiss() {
        Toast.makeText(SampleActivity.this, "Dismissed", Toast.LENGTH_SHORT).show();
      }
    });

    //show on btn1
    Button btn1 = (Button)this.findViewById(R.id.button1);
    btn1.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        quickAction.show(v);
      }
    });

    Button btn2 = (Button)this.findViewById(R.id.button2);
    btn2.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        quickAction.show(v);
      }
    });

    Button btn3 = (Button)this.findViewById(R.id.button3);
    btn3.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        quickAction.show(v);
        quickAction.setAnimStyle(QuickAction.ANIM_REFLECT);
      }
    });
  }
}
