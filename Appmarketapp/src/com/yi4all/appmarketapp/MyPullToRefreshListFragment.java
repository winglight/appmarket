package com.yi4all.appmarketapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.toolbox.NetworkImageView;
import com.handmark.pulltorefresh.extras.listfragment.PullToRefreshListFragment;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.yi4all.appmarketapp.db.AppModel;
import com.yi4all.appmarketapp.util.Utils;

public class MyPullToRefreshListFragment extends PullToRefreshListFragment{
	
	private final static String LOGTAG = "MyPullToRefreshListFragment";
	
	private AppAdapter mAdapter;
	
	private AppsTab tab;
	
	private ArrayList<AppModel> apps = new ArrayList<AppModel>();
	
	private int currentPage = 1;
	
	public static MyPullToRefreshListFragment getMyPullToRefreshListFragment(final AppsTab tab) {
		MyPullToRefreshListFragment f = new MyPullToRefreshListFragment();
		f.tab = tab;
		
		return f;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// Set a listener to be invoked when the list should be refreshed.
		getPullToRefreshListView()
				.setOnRefreshListener(new OnRefreshListener<ListView>() {

					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {
						if (getPullToRefreshListView().getCurrentMode() == Mode.PULL_FROM_END) {
							loadListByPage(tab, currentPage + 1);
						}else{
							loadListByPage(tab, 1);
						}

					}
				});

		// You can also just use mPullRefreshListFragment.getListView()
		ListView actualListView = getPullToRefreshListView().getRefreshableView();

		mAdapter = new AppAdapter(getActivity());

		// You can also just use setListAdapter(mAdapter) or
		// mPullRefreshListView.setAdapter(mAdapter)
		actualListView.setAdapter(mAdapter);

		actualListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO download and install apk

			}
		});

	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		setListShown(true);
		// TODO:async to fetch apps from service and complete refresh of PTR
				loadListByPage(tab, currentPage);
				
	}
	
	/**
	 * 统一刷新列表数据及加载更多数据两种模式： 1.根据页数查询本地数据 2.如果有本地数据则刷新列表（加载数据到最后并跳转至新数据的第一条记录）
	 * 及获取第一条数据的创建/更新时间（假设取数据时以时间倒序排列） 3.根据页数及第一条数据的创建/更新时间（如果没有数据则NULL）获取远程数据列表
	 * 4.如果有返回数据，则在本地库新增或修改列表记录， 然后将返回数据插入到本地数据之前（如果没有本地数据，则是当前列表之末）并刷新列表
	 * 
	 * @param page
	 */
	private void loadListByPage(final AppsTab tab,
			final int page) {
		Log.d(LOGTAG, "begin loadListByPage:" + page);

		if (page == 1 || page != currentPage) {
			currentPage = page;
			
			final int position = apps.size();

			Date lastUpdateDate = null;
			
			if(position > 0){
				lastUpdateDate = apps.get(0).getCreatedAt();
			}
			
			Log.d(LOGTAG, "begin getAppsByTab:" + page);
			if(position == 0 || page != 1){
				List<AppModel> moreApps = ((BaseActivity)getActivity()).getService().getAppsByTab(tab, page);
				if (moreApps != null && moreApps.size() > 0) {
					Log.d(LOGTAG, "getAppsByTab size:" + moreApps.size());
	
					lastUpdateDate = moreApps.get(0).getCreatedAt();
					// update
					apps.addAll(moreApps);
	
				}
			}

			// TODO:notify updating local db
			Log.d(LOGTAG, "begin getAppsByTabRemote:" + page);
			((BaseActivity)getActivity()).getService().getAppsByTabRemote(new Handler() {
				@Override
				public void handleMessage(Message msg) {
					
					// msg construction:
					// what: tab value, arg1: success flag(0 - success, 1 -
					// fail), arg2: page, obj: data of list
					if (msg.arg1 == 0) {
						// load updated app into list
						List<AppModel> remoteMoreIssues = (List<AppModel>) msg.obj;
						if (remoteMoreIssues != null
								&& remoteMoreIssues.size() > 0) {
							Log.d(LOGTAG, "getAppsByTabRemote size:"
									+ remoteMoreIssues.size());

							apps.addAll(position, remoteMoreIssues);

							mAdapter.notifyDataSetChanged();
						} else {
							if (page == 1) {
								// TODO:notify no updated data
								if (getPullToRefreshListView().isRefreshing()) {
								Utils.toastMsg(getActivity(), R.string.refresh_no_data);
								}
							} else {
								// TODO:notify no more data
								Utils.toastMsg(getActivity(), R.string.load_no_more_data);
							}
						}
					}else{
						Utils.toastMsg(getActivity(), (String)msg.obj);
					}
					
					//complete refreshing
					if (getPullToRefreshListView().isRefreshing()) {
						getPullToRefreshListView().onRefreshComplete();
					}
				}
			}, tab, page, lastUpdateDate);

		}
		
		mAdapter.notifyDataSetChanged();

	}
	
	private class AppAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		public AppAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return apps.size();
		}

		public AppModel getItem(int i) {
			return apps.get(i);
		}

		public long getItemId(int i) {
			return i;
		}

		public View getView(final int position, View convertView, ViewGroup vg) {
			if (apps == null || position < 0 || position > apps.size())
				return null;

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.app_list_item, null);
			}

			ViewHolder holder = (ViewHolder) convertView.getTag();
			if (holder == null) {
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}

			// other normal row
			final AppModel am = apps.get(position);

			// set triangle for the add
			holder.icon.setImageUrl(am.getIconUrl(), ApplicationController
					.getInstance().getmImageLoader());
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
