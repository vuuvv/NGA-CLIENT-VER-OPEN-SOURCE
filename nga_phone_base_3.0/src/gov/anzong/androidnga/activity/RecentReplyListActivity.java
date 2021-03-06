package gov.anzong.androidnga.activity;

import java.util.List;

import com.alibaba.fastjson.JSON;

import sp.phone.bean.PerferenceConstant;
import sp.phone.bean.User;
import sp.phone.fragment.RecentReplyListFragment;
import sp.phone.fragment.TopiclistContainer;
import sp.phone.interfaces.PullToRefreshAttacherOnwer;
import sp.phone.task.CheckReplyNotificationTask;
import sp.phone.utils.ActivityUtil;
import sp.phone.utils.PhoneConfiguration;
import sp.phone.utils.ReflectionUtil;
import sp.phone.utils.StringUtil;
import sp.phone.utils.ThemeManager;
import uk.co.senab.actionbarpulltorefresh.extras.actionbarcompat.PullToRefreshAttacher;
import gov.anzong.androidnga.R;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

public class RecentReplyListActivity extends SwipeBackAppCompatActivity implements
PerferenceConstant,PullToRefreshAttacherOnwer {
	FragmentManager fm;
	Fragment f;
	private PullToRefreshAttacher mPullToRefreshAttacher;
	View v;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		PullToRefreshAttacher.Options options = new PullToRefreshAttacher.Options();
		options.refreshScrollDistance = 0.3f;
		options.refreshOnUp = true;
		mPullToRefreshAttacher = PullToRefreshAttacher.get(this, options);
		v= LayoutInflater.from(this).inflate(R.layout.recentnotifier_activity,null,false);
		this.setContentView(v);
		fm  = this.getSupportFragmentManager();
		f = fm.findFragmentById(R.id.item_list);
		if( f == null)
		{
			f = new RecentReplyListFragment();
			fm.beginTransaction().add(R.id.item_list,f ).commit();
		}
		getSupportActionBar().setTitle("�ҵı���");
		
	}

	int flags = ThemeManager.ACTION_BAR_FLAG;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		ReflectionUtil.actionBar_setDisplayOption(this, flags);
		return false;// super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

	@Override
	protected void onResume() {
		int orentation = ThemeManager.getInstance().screenOrentation;
		if (orentation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
				|| orentation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
			setRequestedOrientation(orentation);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
		}

		if (PhoneConfiguration.getInstance().fullscreen) {
			ActivityUtil.getInstance().setFullScreen(v);
		}
		super.onResume();
	}

	@Override
	public PullToRefreshAttacher getAttacher() {
		// TODO Auto-generated method stub
		return mPullToRefreshAttacher;
	}

}
