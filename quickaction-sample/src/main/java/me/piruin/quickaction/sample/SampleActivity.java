package me.piruin.quickaction.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import me.piruin.quickaction.ActionItem;
import me.piruin.quickaction.QuickAction;
import me.piruin.quickaction.QuickIntentAction;

public class SampleActivity extends AppCompatActivity {

  private static final int ID_UP = 1;
  private static final int ID_DOWN = 2;
  private static final int ID_SEARCH = 3;
  private static final int ID_INFO = 4;
  private static final int ID_ERASE = 5;
  private static final int ID_OK = 6;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_sample);

    ActionItem nextItem = new ActionItem(ID_DOWN, "Next", R.drawable.ic_arrow_downward);
    ActionItem prevItem = new ActionItem(ID_UP, "Prev", R.drawable.ic_arrow_upward);
    ActionItem searchItem = new ActionItem(ID_SEARCH, "Find", R.drawable.ic_search);
    ActionItem infoItem = new ActionItem(ID_INFO, "Info", R.drawable.ic_info);
    ActionItem eraseItem = new ActionItem(ID_ERASE, "Clear", R.drawable.ic_clear);
    ActionItem okItem = new ActionItem(ID_OK, "OK", R.drawable.ic_ok);

    //use setSticky(true) to disable QuickAction dialog being dismissed after an item is clicked
    prevItem.setSticky(true);
    nextItem.setSticky(true);

    //create QuickAction. Use QuickAction.VERTICAL or QuickAction.HORIZONTAL param to define layout
    //orientation
    final QuickAction quickAction = new QuickAction(this, QuickAction.VERTICAL);
    quickAction.setColorRes(R.color.pink);
    quickAction.setTextColorRes(R.color.white);

    //add action items into QuickAction
    quickAction.addActionItem(nextItem);
    quickAction.addActionItem(prevItem);
    quickAction.addActionItem(searchItem);
    quickAction.addActionItem(infoItem);
    quickAction.addActionItem(eraseItem);
    quickAction.addActionItem(okItem);

    //Set listener for action item clicked
    quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
      @Override public void onItemClick(ActionItem item) {
        //here we can filter which action item was clicked with pos or actionId parameter
        String title = item.getTitle();
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

    //Quick and Easy intent selector in tooltip styles
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
    sendIntent.setType("text/plain");

    final QuickAction quickIntent = new QuickIntentAction(this, QuickAction.HORIZONTAL)
      .setActivityIntent(sendIntent)
      .create();
    quickIntent.setColorRes(R.color.teal);
    quickIntent.setAnimStyle(QuickAction.Animation.REFLECT);

    Button btn3 = (Button)this.findViewById(R.id.button3);
    btn3.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        quickIntent.show(v);
      }
    });
  }
}
