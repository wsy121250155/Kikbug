package com.func.kikbug;

import com.func.adapter.AllTestAdapter;
import com.func.adapter.MyTestAdapter;
import com.func.adapter.ReportAdapter;
import com.view.util.ArcMenu;
import com.view.util.ArcMenu.OnMenuItemClickListener;
import com.view.util.ReFlashListView;
import com.view.util.SysCall;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class FuncFragment extends Fragment {
	private View rootView;
	private TabHost tabhost;
	private ReFlashListView lv1, lv2, lv3;
	private ArcMenu mArcMenu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		if (null == rootView) {
			rootView = inflater.inflate(R.layout.fragment_func, container,
					false);
			initArc(rootView);
			initTabs(rootView);
			initLists(rootView);
		}
		return rootView;
	}

	private void initArc(View view) {
		mArcMenu = (ArcMenu) view.findViewById(R.id.id_menu);
		mArcMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public void onClick(View view, int pos) {
				Toast.makeText(getActivity(), pos + ":" + view.getTag(),
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void initTabs(View view) {
		tabhost = (TabHost) view.findViewById(R.id.tabhost);
		tabhost.setup();
		String[] tabValues = { "所有测试", "我的测试", "测试报告" };
		final String[] tabNames = { "tab1", "tab2", "tab3" };
		int[] res = { R.id.lv1, R.id.lv2, R.id.lv3 };
		final TextView[] tabs = new TextView[3];
		for (int i = 0; i < tabs.length; i++) {
			tabs[i] = (TextView) LayoutInflater.from(getActivity()).inflate(
					R.layout.tab, null);
			tabs[i].setText(tabValues[i]);
			tabhost.addTab(tabhost.newTabSpec(tabNames[i])
					.setIndicator(tabs[i]).setContent(res[i]));
		}
		tabs[0].setBackground(null);
		tabhost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				for (int i = 0; i < tabNames.length; i++) {
					if (tabId.equals(tabNames[i])) {
						tabs[i].setBackground(null);
					} else {
						tabs[i].setBackground(getResources().getDrawable(
								R.drawable.transferd_course));
					}
				}
			}
		});
	}

	private void initLists(View view) {
		lv1 = (ReFlashListView) view.findViewById(R.id.lv1);
		lv2 = (ReFlashListView) view.findViewById(R.id.lv2);
		lv3 = (ReFlashListView) view.findViewById(R.id.lv3);
		lv1.setAdapter(new AllTestAdapter(getActivity()));
		lv2.setAdapter(new MyTestAdapter(getActivity()));
		lv3.setAdapter(new ReportAdapter(getActivity()));
		OnScrollListener scrollListener = new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (mArcMenu.isOpen())
					mArcMenu.toggleMenu(600);
			}
		};
		lv1.setOnScrollListener(scrollListener);
		lv2.setOnScrollListener(scrollListener);
		lv3.setOnScrollListener(scrollListener);
		OnItemClickListener listener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				SysCall.logi("click", "click");
				if (null == jumper)
					return;
				if (parent.equals(lv1)) {
					SysCall.logi("click", "test");
					jumper.jumpToTest(0);
				}
				if (parent.equals(lv3)) {
					SysCall.logi("click", "report");
					jumper.jumpToReport(0);
				}
			}
		};
		lv1.setOnItemClickListener(listener);
		lv3.setOnItemClickListener(listener);
	}

	private JumpToDetail jumper;

	public void setJumpToDetail(JumpToDetail jumper) {
		this.jumper = jumper;
	}

	public interface JumpToDetail {
		public void jumpToTest(int test_id);

		public void jumpToReport(int report_id);
	}
}
