package com.supersami.foregroundservice;

import android.content.Intent;
import android.os.Bundle;
import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;

import javax.annotation.Nullable;

public class ForegroundServiceTask extends HeadlessJsTaskService {

	@Nullable
	protected HeadlessJsTaskConfig getTaskConfig(Intent intent) {
		Bundle extras = intent.getExtras();
		if (extras != null) {
			return new HeadlessJsTaskConfig(extras.getString("taskName"), Arguments.fromBundle(extras), 5000, true);
		}
		return null;
	}
}
