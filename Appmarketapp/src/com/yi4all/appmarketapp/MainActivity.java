package com.yi4all.appmarketapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.searchboxsdk.android.StartAppSearch;
import com.viewpagerindicator.TabPageIndicator;
import com.yi4all.appmarketapp.util.Utils;

public class MainActivity extends BaseActivity {

	private final static String LOGTAG = "MainActivity";

	private String[] pageTitle;

	private AppsTab currentTab = AppsTab.HOTS;
	
	private boolean isTwiceQuit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StartAppSearch.showSearchBox(this);

		pageTitle = getResources().getStringArray(R.array.main_tab_label);

		FragmentPagerAdapter adapter = new MarketTabAdapter(
				getSupportFragmentManager());

		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				currentTab = AppsTab.values()[position];
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);
		
		pager.setCurrentItem(2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
//			case R.id.action_days_n_buy:
//				popupBuyWindow();
//				break;
				
			case R.id.action_more:
				airsdk.startAppWall();
				break;
			case R.id.action_share:
				Intent sendIntent = new Intent(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.shareAppMmsSubject));
				sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.shareAppMmsBody));
				sendIntent.setType("text/plain");
				startActivity(sendIntent);
				break;
			case R.id.action_email:
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_EMAIL, "vncntkarl2@gmail.com");
				intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
//				intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

				startActivity(Intent.createChooser(intent, "Send Email"));
				break;	

			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onKeyDown(int keyCoder, KeyEvent event) {
		int keyCode = event.getKeyCode();
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if (isTwiceQuit) {
				startAppAd.onBackPressed();
				
				if (airsdk!=null) {
			        //Use only one from below.
			        airsdk.startAppWall();
			        airsdk.startOverlayAd();
			        airsdk.startVideoAd();
			        airsdk.startLandingPageAd();
			        airsdk.showRichMediaInterstitialAd();
			    }
				
				this.finish();
			} else {
				Utils.toastMsg(this, R.string.sure_quit_app);
				isTwiceQuit = true;

				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						isTwiceQuit = false;

					}
				}, 2000);
			}
			return true;
		default:
			return false;
		}
	}

	class MarketTabAdapter extends FragmentPagerAdapter {
		public MarketTabAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return  MyPullToRefreshListFragment.getMyPullToRefreshListFragment(AppsTab.values()[position]);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return pageTitle[position];
		}

		@Override
		public int getCount() {
			return pageTitle.length;
		}
	}

}
