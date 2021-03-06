package com.yogidev.android.livingroom;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesManager {

	// Preferences File
	public static final String PREFS_NAME = "LivingRoomPrefsFile";
	// Preferences Mode
	public static final int PREFS_MODE = 0;
	
	// Opacity
	public static final int TRANPARENCY = 200;
	
	// Theme
	public static final int THEME_LIGHT = R.style.AppTheme_Light;
	public static final int THEME_DARK = R.style.AppTheme_Dark;
	
	// Background color
	public static final int BG_COLOR_LIGHT = R.color.actionbar_background_light;
	public static final int BG_COLOR_DARK = R.color.actionbar_background_dark;

	private static final String THEME_INT_KEY = "THEME";
	private static SharedPreferences mPreferences;
	private static PreferencesManager mInstance;
	private static Editor mEditor;

	private PreferencesManager() {
	}

	public static PreferencesManager getInstance() {
		if (mInstance == null) {
			Context context = LivingRoomApplication.mContext;
			mInstance = new PreferencesManager();
			mPreferences = context.getSharedPreferences(PREFS_NAME, PREFS_MODE);
			mEditor = mPreferences.edit();
		}
		return mInstance;
	}

	public void saveThemePref(Integer value) {
		mEditor.putInt(THEME_INT_KEY, value).apply();
	}

	public Integer getThemePref() {
		return mPreferences.getInt(THEME_INT_KEY, THEME_LIGHT);
	}

	/**
	 * Return the background color according to the current theme
	 */
	public int getBackgoundColorPref() {
		return getThemePref()==THEME_LIGHT?BG_COLOR_LIGHT:BG_COLOR_DARK;
	}
	
	/**
	 * Save a shared preference
	 * 
	 * @param themeId
	 * @param apptheme
	 */
//	public void savePref(String themeId, int apptheme) {
//		// We need an Editor object to make preference changes.
//	      // All objects are from android.context.Context
//	      SharedPreferences settings = LivingRoomApplication.mContext.getSharedPreferences(PREFS_NAME, PREFS_MODE);
//	      SharedPreferences.Editor editor = settings.edit();
//	      // Save pref theme
//	      editor.putInt(themeId, apptheme);
//	      // Commit the edits!
//	      editor.commit();
//	}

}
