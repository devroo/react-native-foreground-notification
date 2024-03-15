package com.supersami.foregroundservice;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.core.content.res.ResourcesCompat;

import static com.supersami.foregroundservice.Constants.*;

class NotificationConfig {

	private static Bundle metadata;
	private Context context;

	public NotificationConfig(Context context) {
		this.context = context;
		if (metadata == null) {
			try {
				ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
				metadata = applicationInfo.metaData;
			} catch (PackageManager.NameNotFoundException e) {
				e.printStackTrace();
				// Log.e(NOTIFICATION_LOG, "Error reading application meta, falling back to defaults");
				metadata = new Bundle();
			}
		}
	}

	public String getChannelName() {
		try {
			return metadata.getString(KEY_CHANNEL_NAME);
		} catch (Exception e) {
			// Log.w(NOTIFICATION_LOG, "Unable to find " + KEY_CHANNEL_NAME + " in manifest. Falling back to default");
		}
		// Default
		return SERVICE_BUNDLE;
	}

	public String getChannelDescription() {
		try {
			return metadata.getString(KEY_CHANNEL_DESCRIPTION);
		} catch (Exception e) {
			// Log.w(NOTIFICATION_LOG, "Unable to find " + KEY_CHANNEL_DESCRIPTION + " in manifest. Falling back to default");
		}
		// Default
		return SERVICE_BUNDLE;
	}

	public int getNotificationColor() {
		try {
			int resourceId = metadata.getInt(KEY_NOTIFICATION_COLOR);
			return ResourcesCompat.getColor(context.getResources(), resourceId, null);
		} catch (Exception e) {
			// Log.w(NOTIFICATION_LOG, "Unable to find " + KEY_NOTIFICATION_COLOR + " in manifest. Falling back to default");
		}
		// Default
		return -1;
	}
}
