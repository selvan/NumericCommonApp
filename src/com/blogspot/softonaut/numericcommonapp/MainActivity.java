package com.blogspot.softonaut.numericcommonapp;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MainActivity extends Activity {

	private enum Pref {
		X_POW_Y, SQUARE, CUBE, RATIO_PERCENT, MULTIPLICATION_TABLE
	}

	class PreferenceStore {

		private SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());

		public String getPref(String pref) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			try {
				switch (Pref.valueOf(pref)) {
				case X_POW_Y:
					map.put("x_pow_y_base", Integer.parseInt(prefs.getString(
							"x_pow_y_base", "2")));
					map.put("x_pow_y_power_start", Integer.parseInt(prefs
							.getString("x_pow_y_power_start", "2")));
					map.put("x_pow_y_power_end", Integer.parseInt(prefs
							.getString("x_pow_y_power_end", "25")));
					break;
				case SQUARE:
					map.put("square_start", Integer.parseInt(prefs.getString(
							"square_start", "2")));
					map.put("square_end", Integer.parseInt(prefs.getString(
							"square_end", "25")));
					break;
				case CUBE:
					map.put("cube_start", Integer.parseInt(prefs.getString(
							"cube_start", "2")));
					map.put("cube_end",
							Integer.parseInt(prefs.getString("cube_end", "25")));
					break;
				case RATIO_PERCENT:
					map.put("percent_numerator", Integer.parseInt(prefs
							.getString("percent_numerator", "1")));
					map.put("percent_denominator_start", Integer.parseInt(prefs
							.getString("percent_denominator_start", "2")));
					map.put("percent_denominator_end", Integer.parseInt(prefs
							.getString("percent_denominator_end", "25")));
					break;
				case MULTIPLICATION_TABLE:
					map.put("multi_table_start", Integer.parseInt(prefs
							.getString("multi_table_start", "2")));
					map.put("multi_table_end", Integer.parseInt(prefs
							.getString("multi_table_end", "25")));
					map.put("multi_table_row_start", Integer.parseInt(prefs
							.getString("multi_table_row_start", "1")));
					map.put("multi_table_row_end", Integer.parseInt(prefs
							.getString("multi_table_row_end", "25")));
					break;
				}
			} catch (Exception e) {
			}
			return new JSONObject(map).toString();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		WebView webView = new WebView(this);
		setContentView(webView);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new PreferenceStore(), "PreferenceStore");
		webView.loadUrl("file:///android_asset/index.html");

		setContentView(webView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(getBaseContext(), Preferences.class);
		startActivityForResult(intent, 0);
		return true;
	}

}
