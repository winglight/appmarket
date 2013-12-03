package com.yi4all.appmarketapp;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.android.volley.toolbox.NetworkImageView;
import com.handmark.pulltorefresh.extras.listfragment.PullToRefreshListFragment;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.viewpagerindicator.TabPageIndicator;
import com.yi4all.appmarketapp.db.AppModel;
import com.yi4all.appmarketapp.db.CategoryModel;
import com.yi4all.appmarketapp.db.CategoryType;

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
import android.util.Log;
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

public class MainActivity extends BaseActivity {
	
	private final static String LOGTAG = "MainActivity";
	
	private String[] pageTitle;
	
	private AppsTab currentTab = AppsTab.HOTS;
	
	private CategoryType currentCatgegoryType = null;

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
				if(currentTab == AppsTab.APP){
					currentCatgegoryType = CategoryType.APP;
				} else if(currentTab == AppsTab.GAME){
					currentCatgegoryType = CategoryType.GAME;
				}else{
					currentCatgegoryType = null;
				}
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
	
	private PullToRefreshListFragment createAppFragment(final AppsTab tab){
		final PullToRefreshListFragment fragment = new PullToRefreshListFragment();
		
		// Get PullToRefreshListView from Fragment
		final PullToRefreshListView mPullRefreshListView = fragment.getPullToRefreshListView();
		mPullRefreshListView.setTag(0);

		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				int page = (Integer) mPullRefreshListView.getTag();
				if(mPullRefreshListView.getCurrentMode() == Mode.PULL_FROM_END){
					page++;
				}
				loadListByPage(mPullRefreshListView, tab, page);
				
			}
		});

		// You can also just use mPullRefreshListFragment.getListView()
		ListView actualListView = mPullRefreshListView.getRefreshableView();
		
		AppAdapter mAdapter = new AppAdapter(this);

		// You can also just use setListAdapter(mAdapter) or
		// mPullRefreshListView.setAdapter(mAdapter)
		actualListView.setAdapter(mAdapter);
		
		actualListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO download and install apk
				
			}
		});
		
		//TODO:async to fetch apps from service and complete refresh of PTR
		loadListByPage(mPullRefreshListView, tab, 1);

		fragment.setListShown(true);
		
		return fragment;
	}
	
	/**
	 * 统一刷新列表数据及加载更多数据两种模式：
	 * 1.根据页数查询本地数据
	 * 2.如果有本地数据则刷新列表（加载数据到最后并跳转至新数据的第一条记录）
	 * 	及获取第一条数据的创建/更新时间（假设取数据时以时间倒序排列）
	 * 3.根据页数及第一条数据的创建/更新时间（如果没有数据则NULL）获取远程数据列表
	 * 4.如果有返回数据，则在本地库新增或修改列表记录，
	 * 	然后将返回数据插入到本地数据之前（如果没有本地数据，则是当前列表之末）并刷新列表
	 * @param page
	 */
	private void loadListByPage(final PullToRefreshListView mPullToRefreshListView, AppsTab tab, final int page){
		Log.d(LOGTAG, "begin loadListByPage:" + page);
		
			final ArrayList<AppModel> apps = new ArrayList<AppModel>();
		final int position = apps.size();

		Date lastUpdateDate = null;
		if(apps.size() == 0 || page > 1){
		Log.d(LOGTAG, "begin getAppsByTab:" + page);
		List<AppModel> moreApps = getService().getAppsByTab(tab, currentCatgegoryType, page);
		if(moreApps != null && moreApps.size() > 0){
			Log.d(LOGTAG, "getAppsByTab size:" + moreApps.size());
			
			lastUpdateDate  = moreApps.get(0).getCreatedAt();
			//update
			apps.addAll(moreApps);
			
			AppAdapter adapter = (AppAdapter) mPullToRefreshListView.getRefreshableView().getAdapter();
			
			adapter.setmList(apps);
		}
		
		if(!mPullToRefreshListView.isRefreshing()){
			mPullToRefreshListView.setRefreshing();
		}
		
					//TODO:notify updating local db
					Log.d(LOGTAG, "begin getAppsByTabRemote:" + page);
					 getService().getAppsByTabRemote(new Handler(){
						 @Override
							public void handleMessage(Message msg) {
							 //msg construction: 
							 //what: tab value, arg1: page, obj: data of list
								if(msg.what == currentTab.value()){
									//load updated app into list
									List<AppModel> remoteMoreIssues = (List<AppModel>) msg.obj;
									if(remoteMoreIssues != null && remoteMoreIssues.size() > 0){
										Log.d(LOGTAG, "getIssueByCategoryRemote size:" + remoteMoreIssues.size());
										
										apps.addAll(position, remoteMoreIssues);
										
										AppAdapter adapter = (AppAdapter) mPullToRefreshListView.getRefreshableView().getAdapter();
										
										adapter.setmList(apps);
									}else{
										if(page == 1){
											//TODO:notify no updated data
										}else{
											//TODO:notify no more data
										}
									}
								}
							}
					},tab, currentCatgegoryType, page, lastUpdateDate);
					 
				}
		
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
		private List<AppModel> mList;

		public AppAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
			mList = new ArrayList<AppModel>();
		}
		
		public List<AppModel> getmList() {
			return mList;
		}

		public void setmList(List<AppModel> mList) {
			this.mList = mList;
			this.notifyDataSetChanged();
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
