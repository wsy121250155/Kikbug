package com.func.kikbug;

import com.squareup.picasso.Picasso;
import com.view.util.DisplayUtil;
import com.view.util.SysCall;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ReportDetailFragment extends Fragment {
	private View rootView;
	private LinearLayout gallery;
	private View backView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		if (null == rootView) {
			rootView = inflater.inflate(R.layout.fragment_report, container,
					false);
			initBackView(rootView);
			initGallery(rootView);
		}
		return rootView;
	}

	private void initBackView(View view) {
		backView = view.findViewById(R.id.backView);
		backView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SysCall.clickBack();
			}
		});
	}

	private void initGallery(View view) {
		gallery = (LinearLayout) view.findViewById(R.id.gallery);
		gallery = (LinearLayout) view.findViewById(R.id.gallery);
		LayoutInflater flater = LayoutInflater.from(getActivity());
		ImageView pic;
		for (int i = 0; i < 10; i++) {
			pic = (ImageView) flater.inflate(R.layout.app_pic, null);
			Picasso.with(getActivity())
					.load(R.drawable.today_background)
					.resize(DisplayUtil.dipTopx(480, 1),
							DisplayUtil.dipTopx(800, 1)).into(pic);
			gallery.addView(pic);
		}
	}
}
