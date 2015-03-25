package com.func.kikbug;

import com.net.data.DownTask;
import com.squareup.picasso.Picasso;
import com.view.util.DisplayUtil;
import com.view.util.SysCall;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AllTestDetailFragment extends Fragment {
	private View rootView;
	private LinearLayout gallery;
	private View backView;
	private View bottombar;// for download
	private TextView downText;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		if (null == rootView) {
			rootView = inflater.inflate(R.layout.fragment_alltest, container,
					false);
			initListView(rootView);
			initBackView(rootView);
			initDownView(rootView);
		}
		return rootView;
	}

	private void initDownView(View view) {
		bottombar = view.findViewById(R.id.bottombar);
		downText = (TextView) view.findViewById(R.id.downText);
		final Handler myHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				int fileSize = msg.getData().getInt(DownTask.FILESIZE);
				int bytesDL = msg.getData().getInt(DownTask.DOWNSIZE);
				downText.setText(""
						+ (int) ((bytesDL * 1.0) / (fileSize * 1.0) * 100)
						+ "%");
			}
		};
		bottombar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				downText.setText("正在下载");
				DownTask task = new DownTask(getActivity(), myHandler,
						"HiMarket4.5.3_0226153319_1.apk");
				task.down();
			}
		});
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

	private void initListView(View view) {
		gallery = (LinearLayout) view.findViewById(R.id.gallery);
		LayoutInflater flater = LayoutInflater.from(getActivity());
		ImageView pic;
		for (int i = 0; i < 10; i++) {
			pic = (ImageView) flater.inflate(R.layout.app_pic, null);
			Picasso.with(getActivity())
					.load(R.drawable.today_background)
					// .load(DataUtils.url + "picture/1.jpg")
					.placeholder(R.drawable.today_background)
					.error(R.drawable.today_background)
					.resize(DisplayUtil.dipTopx(480, 1),
							DisplayUtil.dipTopx(800, 1)).into(pic);
			gallery.addView(pic);
		}
	}
}
