package gov.anzong.androidnga.activity;

import sp.phone.utils.PhoneConfiguration;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by Administrator on 13-9-29.
 */
public class SwipeBackAppCompatActivity extends ActionBarActivity implements
		SwipeBackActivityBase {
	private SwipeBackActivityHelper mHelper;
	final int MY_EDGE_SIZE = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHelper = new SwipeBackActivityHelper(this);
		mHelper.onActivtyCreate();
		if (PhoneConfiguration.getInstance().swipeBack) {
			final float density = getResources().getDisplayMetrics().density;// ��ȡ��Ļ�ܶ�PPI
			getSwipeBackLayout().setEdgeSize(
					(int) (MY_EDGE_SIZE * density + 0.5f));// 10dp
			int pos = SwipeBackLayout.EDGE_ALL;
			switch (PhoneConfiguration.getInstance().swipeenablePosition) {
			case 0:
				pos = SwipeBackLayout.EDGE_LEFT;
				break;
			case 1:
				pos = SwipeBackLayout.EDGE_RIGHT;
				break;
			case 2:
				pos = SwipeBackLayout.EDGE_LEFT | SwipeBackLayout.EDGE_RIGHT;
				break;
			default:
				pos = SwipeBackLayout.EDGE_ALL;
				break;
			}
			getSwipeBackLayout().setEdgeTrackingEnabled(pos);
		} else {
			getSwipeBackLayout().setEdgeSize(0);
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mHelper.onPostCreate();
	}

	@Override
	public View findViewById(int id) {
		View v = super.findViewById(id);
		if (v != null)
			return v;
		return mHelper.findViewById(id);
	}

	@Override
	public SwipeBackLayout getSwipeBackLayout() {
		return mHelper.getSwipeBackLayout();
	}

	@Override
	public void setSwipeBackEnable(boolean enable) {
		getSwipeBackLayout().setEnableGesture(enable);
	}

	@Override
	public void scrollToFinishActivity() {
		getSwipeBackLayout().scrollToFinishActivity();
	}
}
