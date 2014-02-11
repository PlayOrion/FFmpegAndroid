package com.ffmpegtest;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.CursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ffmpegtest.adapter.MainAdapter;

import java.io.File;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView mListView;
	private CursorAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		MatrixCursor cursor = new MatrixCursor(MainAdapter.PROJECTION);
		cursor.addRow(new Object[] {
				1,
				"52f8d9b9782967.69577076_eng_dut.m3u8",
				"http://vod.dvdpost.be/52f8d9b9782967.69577076_eng_dut.m3u8",
				null });
        cursor.addRow(new Object[] {
                2,
                "52a97d7686c568.55413551_eng_fre.m3u8",
                "http://vod.dvdpost.be/52a97d7686c568.55413551_eng_fre.m3u8",
                null });

		mAdapter = new MainAdapter(this);
		mAdapter.swapCursor(cursor);

		mListView = (ListView) findViewById(android.R.id.list);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
	}
	
	private static String getSDCardFile(String file) {
		File videoFile = new File(Environment.getExternalStorageDirectory(),
				file);
		return "file://" + videoFile.getAbsolutePath();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_activity, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
		Cursor cursor = (Cursor) mAdapter.getItem(position);
		String url = cursor.getString(MainAdapter.PROJECTION_URL);
		Intent intent = new Intent(AppConstants.VIDEO_PLAY_ACTION);
		intent.putExtra(AppConstants.VIDEO_PLAY_ACTION_EXTRA_URL, url);
		String encryptionKey = cursor.getString(MainAdapter.PROJECTION_ENCRYPTION_KEY);
		if (encryptionKey != null) {
			intent.putExtra(AppConstants.VIDEO_PLAY_ACTION_EXTRA_ENCRYPTION_KEY, encryptionKey);
		}
		startActivity(intent);
	}

}
