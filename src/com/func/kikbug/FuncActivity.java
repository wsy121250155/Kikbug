package com.func.kikbug;

import com.func.kikbug.FuncFragment.JumpToDetail;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class FuncActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_func);
		init();
	}

	private void init() {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		FuncFragment fragment = new FuncFragment();
		fragment.setJumpToDetail(new JumpToDetail() {

			@Override
			public void jumpToTest(int test_id) {
				testDetail();
			}

			@Override
			public void jumpToReport(int report_id) {
				reportDetail();
			}
		});
		transaction.replace(R.id.FrameLayout1, fragment);
		transaction.commit();
	}

	private void reportDetail() {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		ReportDetailFragment fragment = new ReportDetailFragment();
		transaction.replace(R.id.FrameLayout1, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	private void testDetail() {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		AllTestDetailFragment fragment = new AllTestDetailFragment();
		transaction.replace(R.id.FrameLayout1, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}
}
