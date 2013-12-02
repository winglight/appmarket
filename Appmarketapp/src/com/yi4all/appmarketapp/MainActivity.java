package com.yi4all.appmarketapp;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.android.volley.toolbox.NetworkImageView;
import com.handmark.pulltorefresh.extras.listfragment.PullToRefreshListFragment;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.viewpagerindicator.TabPageIndicator;
import com.yi4all.appmarketapp.db.AppModel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends BaseActivity  implements OnRefreshListener<ListView> {
	
	private String[] pageTitle;
	
	private List<AppModel> mList;
	
	private AppsTab currentTab = AppsTab.HOTS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		pageTitle = getResources().getStringArray(R.array.main_tab_label);
		
        FragmentPagerAdapter adapter = new MarketTabAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
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

        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private PullToRefreshListFragment createAppFragment(AppsTab tab){
		PullToRefreshListFragment fragment = new PullToRefreshListFragment();

		// Get PullToRefreshListView from Fragment
		PullToRefreshListView mPullRefreshListView = fragment.getPullToRefreshListView();

		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView.setOnRefreshListener(this);

		// You can also just use mPullRefreshListFragment.getListView()
		ListView actualListView = mPullRefreshListView.getRefreshableView();
		
		mList = new ArrayList<AppModel>();

		AppAdapter mAdapter = new AppAdapter(this);

		// You can also just use setListAdapter(mAdapter) or
		// mPullRefreshListView.setAdapter(mAdapter)
		actualListView.setAdapter(mAdapter);
		
		actualListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				
			}
		});

		fragment.setListShown(true);
		
		return fragment;
	}
	
	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		//TODO: Do work to refresh the list here.
		;
	}

	class MarketTabAdapter extends FragmentPagerAdapter {
        public MarketTabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return createAppFragment(AppsTab.values()[position]);
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
	
	private class AppAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		public AppAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return mList.size();
		}

		public AppModel getItem(int i) {
			return mList.get(i);
		}

		public long getItemId(int i) {
			return i;
		}

		public View getView(final int position, View convertView, ViewGroup vg) {
			if (mList == null || position < 0 || position > mList.size())
				return null;

			if(convertView == null){
				convertView = mInflater.inflate(R.layout.app_list_item, null);
			}

			ViewHolder holder = (ViewHolder) convertView.getTag();
			if (holder == null) {
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}

			// other normal row
			final AppModel am = mList.get(position);

			// set triangle for the add
			holder.icon.setImageUrl(am.getIconUrl(), ApplicationController.getInstance().getmImageLoader());
			holder.name.setText(am.getAppname());
			holder.price.setText(String.valueOf(am.getPrice()));
			holder.version.setText(am.getAppVersion());

			return (convertView);
		}

	}




	class ViewHolder {
		NetworkImageView icon = null;
		TextView name = null;
		TextView price = null;
		TextView version = null;

		ViewHolder(View base) {
			this.icon = (NetworkImageView) base.findViewById(R.id.iconImg);
			this.name = (TextView) base.findViewById(R.id.row_name);
			this.price = (TextView) base.findViewById(R.id.row_price);
			this.version = (TextView) base.findViewById(R.id.row_version);
		}
	}
}
