package com.func.kikbug;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class FirstActivity extends Activity {
	private View loginBu;
	private ImageView background;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		initBackground();
		initLoginBu();
	}

	private void initBackground() {
		background = (ImageView) findViewById(R.id.background);
		Picasso.with(this).load(R.drawable.today_background).resize(480, 800)
				.into(background);
	}

	private void initLoginBu() {
		loginBu = findViewById(R.id.loginBu);
		loginBu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FirstActivity.this,
						FuncActivity.class);
				startActivity(intent);
			}
		});
	}
}
