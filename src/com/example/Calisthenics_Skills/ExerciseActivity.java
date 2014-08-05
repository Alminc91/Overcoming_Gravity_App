package com.example.Calisthenics_Skills;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class ExerciseActivity extends ActionBarActivity {

	// Variablen hier definiert, damit Inhalt dynamisch angepasst werden kann
	TextView timerTextView;
	TextView recordView;
	TextView skillnameview;
	TextView skilllevelview;
	ImageView skillimgview;
	long startTime = 0;
	int seconds = 0;
	int minutes = 0;
	int hours = 0;
	long record = 0;
	long millis = 0;
	String skillname;
	int skilllevel;
	int skillimg;
	int skill_id;

	SharedPreferences prefs;
	String prefrec;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercise);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exercise, menu);
		// Gibt mir alle erforderlichen GUI Elemente, welche an den jeweiligen
		// Skill angepasst werden müssen
		timerTextView = (TextView) findViewById(R.id.timerdisplay);
		recordView = (TextView) findViewById(R.id.timerbest);
		skillnameview = (TextView) findViewById(R.id.skillname);
		skilllevelview = (TextView) findViewById(R.id.skilllevel);
		skillimgview = (ImageView) findViewById(R.id.skillimg);
		// Hole die Id des Skills, welche von der MainActivity übergeben wurde
		Intent mIntent = getIntent();
		skill_id = mIntent.getIntExtra("skill_id", 0);
		prefrec = skill_id + "";
		// Speicherung der Bestzeiten
		prefs = getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
		// Die Bestzeit wird bei Öffnen der ExerciseActivity gleich angezeigt
		record = prefs.getLong(prefrec, 0);
		HHMMSS(record, recordView);
		// Je nach Skill-Id wird der GUI View passend aufgebaut, Ressourcen für
		// GUI werden geholt
		switch (skill_id) {
		// misc skills
		case R.id.rtosupport:
			skillname = getResources().getString(R.string.rtosupport);
			skilllevel = R.string.lev1_2;
			skillimg = R.drawable.support_hold;
			break;
		case R.id.germanhang:
			skillname = getResources().getString(R.string.germanhang);
			skilllevel = R.string.lev1_2;
			skillimg = R.drawable.german_hang;
			break;
		// handstand skills
		case R.id.wallhs:
			skillname = getResources().getString(R.string.wallhs);
			skilllevel = R.string.lev1_4;
			skillimg = R.drawable.wallhandstand;
			break;
		case R.id.freehs:
			skillname = getResources().getString(R.string.freehs);
			skilllevel = R.string.lev5;
			skillimg = R.drawable.freehs;
			break;
		case R.id.ringshstand:
			skillname = getResources().getString(R.string.ringshstand);
			skilllevel = R.string.lev5;
			skillimg = R.drawable.ringstand;
			break;
		case R.id.rstraphs:
			skillname = getResources().getString(R.string.rstraphs);
			skilllevel = R.string.lev6;
			skillimg = R.drawable.ringstraphs;
			break;
		case R.id.chs:
			skillname = getResources().getString(R.string.chs);
			skilllevel = R.string.lev6;
			skillimg = R.drawable.chairhs;
			break;
		case R.id.hsff:
			skillname = getResources().getString(R.string.hsff);
			skilllevel = R.string.lev7;
			skillimg = R.drawable.onearmhs;
			break;
		case R.id.rhs:
			skillname = getResources().getString(R.string.rhs);
			skilllevel = R.string.lev7;
			skillimg = R.drawable.ringhs;
			break;
		case R.id.ichs:
			skillname = getResources().getString(R.string.ichs);
			skilllevel = R.string.lev7;
			skillimg = R.drawable.illchairhs;
			break;
		case R.id.hstf:
			skillname = getResources().getString(R.string.hstf);
			skilllevel = R.string.lev7;
			skillimg = R.drawable.onearmhs;
			break;
		case R.id.hstwof:
			skillname = getResources().getString(R.string.hstwof);
			skilllevel = R.string.lev8;
			skillimg = R.drawable.onearmhs;
			break;
		case R.id.hsof:
			skillname = getResources().getString(R.string.hsof);
			skilllevel = R.string.lev9;
			skillimg = R.drawable.onearmhs;
			break;
		case R.id.onearmhs:
			skillname = getResources().getString(R.string.onearmhs);
			skilllevel = R.string.lev10;
			skillimg = R.drawable.onearmhs;
			break;
		// manna skills
		case R.id.tucklsit:
			skillname = getResources().getString(R.string.tucklsit);
			skilllevel = R.string.lev1;
			skillimg = R.drawable.tucklsit;
			break;
		case R.id.oneleglsit:
			skillname = getResources().getString(R.string.oneleglsit);
			skilllevel = R.string.lev2;
			skillimg = R.drawable.oneleglsit;
			break;
		case R.id.lsit:
			skillname = getResources().getString(R.string.lsit);
			skilllevel = R.string.lev3;
			skillimg = R.drawable.lsit;
			break;
		case R.id.slsit:
			skillname = getResources().getString(R.string.slsit);
			skilllevel = R.string.lev4;
			skillimg = R.drawable.slsit;
			break;
		case R.id.rtolsit:
			skillname = getResources().getString(R.string.rtolsit);
			skilllevel = R.string.lev5;
			skillimg = R.drawable.rtolsit;
			break;
		case R.id.vsitone:
			skillname = getResources().getString(R.string.vsitone);
			skilllevel = R.string.lev6;
			skillimg = R.drawable.vsit;
			break;
		case R.id.rtoslsit:
			skillname = getResources().getString(R.string.rtoslsit);
			skilllevel = R.string.lev6;
			skillimg = R.drawable.rtoslsit;
			break;
		case R.id.vsittwo:
			skillname = getResources().getString(R.string.vsittwo);
			skilllevel = R.string.lev7;
			skillimg = R.drawable.vsittwo;
			break;
		case R.id.rvsitone:
			skillname = getResources().getString(R.string.rvsitone);
			skilllevel = R.string.lev7;
			skillimg = R.drawable.rtolsit;
			break;
		case R.id.vsitthree:
			skillname = getResources().getString(R.string.vsitthree);
			skilllevel = R.string.lev8;
			skillimg = R.drawable.vsitthree;
			break;
		case R.id.rvsittwo:
			skillname = getResources().getString(R.string.rvsittwo);
			skilllevel = R.string.lev8;
			skillimg = R.drawable.rtolsit;
			break;
		case R.id.vsitfour:
			skillname = getResources().getString(R.string.vsitfour);
			skilllevel = R.string.lev9;
			skillimg = R.drawable.manna;
			break;
		case R.id.vsitfive:
			skillname = getResources().getString(R.string.vsitfive);
			skilllevel = R.string.lev10;
			skillimg = R.drawable.manna;
			break;
		case R.id.vsitsix:
			skillname = getResources().getString(R.string.vsitsix);
			skilllevel = R.string.lev11;
			skillimg = R.drawable.manna;
			break;
		case R.id.vsitseven:
			skillname = getResources().getString(R.string.vsitseven);
			skilllevel = R.string.lev12;
			skillimg = R.drawable.manna;
			break;
		case R.id.manna:
			skillname = getResources().getString(R.string.manna);
			skilllevel = R.string.lev13;
			skillimg = R.drawable.manna;
			break;
		// back lever skills
		case R.id.tuckbl:
			skillname = getResources().getString(R.string.tuckbl);
			skilllevel = R.string.lev3;
			skillimg = R.drawable.tuckbl;
			break;
		case R.id.advtuckbl:
			skillname = getResources().getString(R.string.advtuckbl);
			skilllevel = R.string.lev4;
			skillimg = R.drawable.advtuckbl;
			break;
		case R.id.straddlebl:
			skillname = getResources().getString(R.string.straddlebl);
			skilllevel = R.string.lev5;
			skillimg = R.drawable.straddlebl;
			break;
		case R.id.halflaybl:
			skillname = getResources().getString(R.string.halflaybl);
			skilllevel = R.string.lev6;
			skillimg = R.drawable.halflaybl;
			break;
		case R.id.fullbl:
			skillname = getResources().getString(R.string.fullbl);
			skilllevel = R.string.lev13;
			skillimg = R.drawable.fullbl;
			break;
		// front lever skills
		case R.id.tuckfl:
			skillname = getResources().getString(R.string.tuckfl);
			skilllevel = R.string.lev4;
			skillimg = R.drawable.tfl;
			break;
		case R.id.advtuckfl:
			skillname = getResources().getString(R.string.advtuckfl);
			skilllevel = R.string.lev5;
			skillimg = R.drawable.advtfl;
			break;
		case R.id.straddlefl:
			skillname = getResources().getString(R.string.straddlefl);
			skilllevel = R.string.lev6;
			skillimg = R.drawable.straddlefl;
			break;
		case R.id.halflayfl:
			skillname = getResources().getString(R.string.halflayfl);
			skilllevel = R.string.lev7;
			skillimg = R.drawable.halflayfl;
			break;
		case R.id.fullfl:
			skillname = getResources().getString(R.string.fullfl);
			skilllevel = R.string.lev8;
			skillimg = R.drawable.fullfl;
			break;
		// Planche Skills
		case R.id.frogstand:
			skillname = getResources().getString(R.string.frogstand);
			skilllevel = R.string.lev3;
			skillimg = R.drawable.frogstand;
			break;
		case R.id.safrogstand:
			skillname = getResources().getString(R.string.safrogstand);
			skilllevel = R.string.lev4;
			skillimg = R.drawable.safrogstand;
			break;
		case R.id.tuckpl:
			skillname = getResources().getString(R.string.tuckpl);
			skilllevel = R.string.lev5;
			skillimg = R.drawable.tuckpl;
			break;
		case R.id.advtuckpl:
			skillname = getResources().getString(R.string.advtuckpl);
			skilllevel = R.string.lev6;
			skillimg = R.drawable.advtuckpl;
			break;
		case R.id.straddlepl:
			skillname = getResources().getString(R.string.straddlepl);
			skilllevel = R.string.lev8;
			skillimg = R.drawable.straddlepl;
			break;
		case R.id.halflaypl:
			skillname = getResources().getString(R.string.halflaypl);
			skilllevel = R.string.lev9;
			skillimg = R.drawable.halflaypl;
			break;
		case R.id.sastrpltohs:
			skillname = getResources().getString(R.string.sastrpltohs);
			skilllevel = R.string.lev10;
			skillimg = R.drawable.sastrpltohs;
			break;
		case R.id.fullpl:
			skillname = getResources().getString(R.string.fullpl);
			skilllevel = R.string.lev11;
			skillimg = R.drawable.fullpl;
			break;
		case R.id.ringsfullpl:
			skillname = getResources().getString(R.string.ringsfullpl);
			skilllevel = R.string.lev14;
			skillimg = R.drawable.ringsfullpl;
			break;
		// elbow levers skills
		case R.id.twoarmel:
			skillname = getResources().getString(R.string.twoarmel);
			skilllevel = R.string.lev5;
			skillimg = R.drawable.twoarmel;
			break;
		case R.id.ringstwoarmel:
			skillname = getResources().getString(R.string.ringstwoarmel);
			skilllevel = R.string.lev6;
			skillimg = R.drawable.ringstwoarmel;
			break;
		case R.id.oastraddleel:
			skillname = getResources().getString(R.string.oastraddleel);
			skilllevel = R.string.lev7;
			skillimg = R.drawable.oastraddleel;
			break;
		case R.id.oasbel:
			skillname = getResources().getString(R.string.oasbel);
			skilllevel = R.string.lev8;
			skillimg = R.drawable.oasbel;
			break;
		}
		// Setzen der Ressourcen
		skillnameview.setText(skillname);
		skilllevelview.setText(skilllevel);
		skillimgview.setImageResource(skillimg);

		Button b = (Button) findViewById(R.id.go);
		b.setText("start");
		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				if (b.getText().equals("stop")) {
					// Bestzeit speichern!!
					if (record < millis) {
						prefs.edit().putLong(prefrec, millis).commit();
						record = millis;
						HHMMSS(record, recordView);
					}
					timerHandler.removeCallbacks(timerRunnable);
					b.setText("start");
				} else {
					startTime = System.currentTimeMillis();
					timerHandler.postDelayed(timerRunnable, 0);
					b.setText("stop");
				}
			}
		});

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_exercise,
					container, false);
			return rootView;
		}
	}

	// runs without a timer by reposting this handler at the end of the runnable
	Handler timerHandler = new Handler();
	Runnable timerRunnable = new Runnable() {

		@Override
		public void run() {
			millis = System.currentTimeMillis() - startTime;
			HHMMSS(millis, timerTextView);

			timerHandler.postDelayed(this, 500);
		}
	};

	@Override
	public void onPause() {
		super.onPause();
		timerHandler.removeCallbacks(timerRunnable);
		Button b = (Button) findViewById(R.id.go);
		b.setText("start");
	}

	// ändert die Millisekunden in das gewünschte 00:00:00 Format um
	public void HHMMSS(long record, TextView recordView) {
		long tmpseconds = (int) (record / 1000);
		long tmpminutes = tmpseconds / 60;
		long tmphours = tmpminutes / 60;
		tmpseconds = tmpseconds % 60;
		tmpminutes = tmpminutes % 60;

		recordView.setText(String.format("%02d:%02d:%02d", tmphours,
				tmpminutes, tmpseconds));
	}

	// Lässt bei Drücken des Exercise Detail Buttons
	// ein Popup Fenster mit der zur Skill-Id passenden Beschreibung erscheinen
	public void desc(View view) {
		ImageView popuptext = new ImageView(this);
		Button popupbutton = new Button(this);
		ScrollView scroll = new ScrollView(this);
		LinearLayout layoutofpopup = new LinearLayout(this);
		layoutofpopup.setOrientation(1);
		final PopupWindow popupmsg = new PopupWindow(layoutofpopup,
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		// popupmsg.showAsDropDown(view, 0, 0, Gravity.CENTER);
		popupmsg.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.roundborder));
		popupmsg.showAtLocation(view, Gravity.CENTER, 0, 0);

		switch (skill_id) {
		// misc skills
		case R.id.rtosupport:
			popuptext.setImageResource(R.drawable.descrtosupport);
			break;
		case R.id.germanhang:
			popuptext.setImageResource(R.drawable.descgermanhang);
			break;
		// handstand skills
		case R.id.wallhs:
			ImageView popuptext2 = new ImageView(this);
			popuptext2.setImageResource(R.drawable.wallhsone);
			layoutofpopup.addView(popuptext2);
			ImageView popuptext3 = new ImageView(this);
			popuptext3.setImageResource(R.drawable.wallhstwo);
			layoutofpopup.addView(popuptext3);
			popuptext.setImageResource(R.drawable.wallhsthree);
			break;
		case R.id.freehs:
			popuptext.setImageResource(R.drawable.descfreehs);
			break;
		case R.id.ringshstand:
			ImageView popuptextsh2 = new ImageView(this);
			popuptextsh2.setImageResource(R.drawable.descringshstand);
			layoutofpopup.addView(popuptextsh2);
			popuptext.setImageResource(R.drawable.ringshstandtwo);
			break;
		case R.id.rstraphs:
			popuptext.setImageResource(R.drawable.descrstraphs);
			break;
		case R.id.chs:
			ImageView popuptextchs2 = new ImageView(this);
			popuptextchs2.setImageResource(R.drawable.descchs);
			layoutofpopup.addView(popuptextchs2);
			ImageView popuptextchs3 = new ImageView(this);
			popuptextchs3.setImageResource(R.drawable.chstwo);
			layoutofpopup.addView(popuptextchs3);
			popuptext.setImageResource(R.drawable.chsthree);
			break;
		case R.id.hsff:
			ImageView popuptexthsff2 = new ImageView(this);
			popuptexthsff2.setImageResource(R.drawable.deschsff);
			layoutofpopup.addView(popuptexthsff2);
			popuptext.setImageResource(R.drawable.hsfftwo);
			break;
		case R.id.rhs:
			popuptext.setImageResource(R.drawable.descrhs);
			break;
		case R.id.ichs:
			ImageView popuptextichs2 = new ImageView(this);
			popuptextichs2.setImageResource(R.drawable.descichs);
			layoutofpopup.addView(popuptextichs2);
			popuptext.setImageResource(R.drawable.ichstwo);
			break;
		case R.id.hstf:
			popuptext.setImageResource(R.drawable.deschsthreefingers);
			break;
		case R.id.hstwof:
			popuptext.setImageResource(R.drawable.deschstwofingers);
			break;
		case R.id.hsof:
			popuptext.setImageResource(R.drawable.deschsonefingers);
			break;
		case R.id.onearmhs:
			popuptext.setImageResource(R.drawable.desconearmhs);
			break;
		// manna skills
		case R.id.tucklsit:
			popuptext.setImageResource(R.drawable.desctucklsit);
			break;
		case R.id.oneleglsit:
			popuptext.setImageResource(R.drawable.desconeleglsit);
			break;
		case R.id.lsit:
			popuptext.setImageResource(R.drawable.desclsit);
			break;
		case R.id.slsit:
			popuptext.setImageResource(R.drawable.descstraddlelsit);
			break;
		case R.id.rtolsit:
			popuptext.setImageResource(R.drawable.descrtolsit);
			break;
		case R.id.vsitone:
			popuptext.setImageResource(R.drawable.descrtolsit);
			break;
		case R.id.rtoslsit:
			popuptext.setImageResource(R.drawable.descrtolsit);
			break;
		case R.id.vsittwo:
			ImageView popuptextvsit2 = new ImageView(this);
			popuptextvsit2.setImageResource(R.drawable.descvsit);
			layoutofpopup.addView(popuptextvsit2);
			popuptext.setImageResource(R.drawable.descvsittwo);
			break;
		case R.id.rvsitone:
			ImageView popuptextvsitt2 = new ImageView(this);
			popuptextvsitt2.setImageResource(R.drawable.descvsit);
			layoutofpopup.addView(popuptextvsitt2);
			popuptext.setImageResource(R.drawable.descvsittwo);
			break;
		case R.id.vsitthree:
			ImageView popuptextvsittt2 = new ImageView(this);
			popuptextvsittt2.setImageResource(R.drawable.descvsitadv);
			layoutofpopup.addView(popuptextvsittt2);
			popuptext.setImageResource(R.drawable.descvsitadvtwo);
			break;
		case R.id.rvsittwo:
			ImageView popuptextvsitttt2 = new ImageView(this);
			popuptextvsitttt2.setImageResource(R.drawable.descvsit);
			layoutofpopup.addView(popuptextvsitttt2);
			popuptext.setImageResource(R.drawable.descvsittwo);
			break;
		case R.id.vsitfour:
			ImageView popuptextvsittttt2 = new ImageView(this);
			popuptextvsittttt2.setImageResource(R.drawable.descvsitadv);
			layoutofpopup.addView(popuptextvsittttt2);
			popuptext.setImageResource(R.drawable.descvsitadvtwo);
			break;
		case R.id.vsitfive:
			ImageView popuptextvsitttttt2 = new ImageView(this);
			popuptextvsitttttt2.setImageResource(R.drawable.descvsitadv);
			layoutofpopup.addView(popuptextvsitttttt2);
			popuptext.setImageResource(R.drawable.descvsitadvtwo);
			break;
		case R.id.vsitsix:
			popuptext.setImageResource(R.drawable.descmanna);
			break;
		case R.id.vsitseven:
			popuptext.setImageResource(R.drawable.descmanna);
			break;
		case R.id.manna:
			popuptext.setImageResource(R.drawable.descmanna);
			break;
		// back lever skills
		case R.id.tuckbl:
			popuptext.setImageResource(R.drawable.desctuckbl);
			break;
		case R.id.advtuckbl:
			popuptext.setImageResource(R.drawable.descadvtuckbl);
			break;
		case R.id.straddlebl:
			popuptext.setImageResource(R.drawable.descstraddlebl);
			break;
		case R.id.halflaybl:
			popuptext.setImageResource(R.drawable.deschalflaybl);
			break;
		case R.id.fullbl:
			popuptext.setImageResource(R.drawable.descfullbl);
			break;
		// front lever skills
		case R.id.tuckfl:
			popuptext.setImageResource(R.drawable.desctuckfl);
			break;
		case R.id.advtuckfl:
			popuptext.setImageResource(R.drawable.descadvtuckfl);
			break;
		case R.id.straddlefl:
			popuptext.setImageResource(R.drawable.descstraddlefl);
			break;
		case R.id.halflayfl:
			popuptext.setImageResource(R.drawable.deschalflayfl);
			break;
		case R.id.fullfl:
			popuptext.setImageResource(R.drawable.descfullfl);
			break;
		case R.id.frogstand:
			popuptext.setImageResource(R.drawable.descffrogstand);
			break;
		case R.id.safrogstand:
			popuptext.setImageResource(R.drawable.safrogstand);
			break;
		case R.id.tuckpl:
			popuptext.setImageResource(R.drawable.desctuckpl);
			break;
		case R.id.advtuckpl:
			popuptext.setImageResource(R.drawable.descadvtuckpl);
			break;
		case R.id.straddlepl:
			popuptext.setImageResource(R.drawable.descstraddlepl);
			break;
		case R.id.halflaypl:
			popuptext.setImageResource(R.drawable.deschalflaypl);
			break;
		case R.id.sastrpltohs:
			popuptext.setImageResource(R.drawable.descsastrpltohs);
			break;
		case R.id.fullpl:
			popuptext.setImageResource(R.drawable.descfullpl);
			break;
		case R.id.ringsfullpl:
			popuptext.setImageResource(R.drawable.descringsfullpl);
			break;
		// elbow levers skills
		case R.id.twoarmel:
			popuptext.setImageResource(R.drawable.desctwoarmel);
			break;
		case R.id.ringstwoarmel:
			popuptext.setImageResource(R.drawable.descringstwoarmel);
			break;
		case R.id.oastraddleel:
			popuptext.setImageResource(R.drawable.descoastraddleel);
			break;
		case R.id.oasbel:
			popuptext.setImageResource(R.drawable.descoasbel);
			break;
		}
		// Setzen der Beschreibung und Realisierung des Close Buttons (Schließen
		// des Popup Fensters)
		layoutofpopup.addView(popuptext);
		popupbutton.setText("close");
		layoutofpopup.addView(popupbutton);
		popupbutton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				popupmsg.dismiss();
			}
		});
	}
}
