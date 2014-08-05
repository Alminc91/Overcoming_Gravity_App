package com.example.Calisthenics_Skills;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Misc_main extends Fragment {

	View rootView;
	TextView timerTextView;
	TextView recordView;
	long startTime = 0;
	int seconds = 0;
	int minutes = 0;
	int hours = 0;
	long record = 0;

	// ShowSettingsActivity settingsmem;
	// SharedPreferences sharedPrefs = settingsmem.getSharedPrefs();
	// SharedPreferences prefs = rootView.getShared

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_misc_main,
				container, false);
		return rootView;
	}
}