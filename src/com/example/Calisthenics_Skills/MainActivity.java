package com.example.Calisthenics_Skills;

import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

//Hier wird die Funktion der SwipeViews umgesetzt mit Actiontabs
public class MainActivity extends ActionBarActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.handstand_main, menu);
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

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		// Hier werden die einzelnen Swipes und Tabs erzeugt,
		// der Inhalt wird von den einzelnen _main Klassen implementiert
		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).

			switch (position) {
			case 0:
				return new Misc_main();
			case 1:
				return new Handstand_main();
			case 2:
				return new Manna_main();
			case 3:
				return new BackLever_main();
			case 4:
				return new FrontLever_main();
			case 5:
				return new Planche_main();
			case 6:
				return new ElbowLevers_main();
				// case 7:
				// Games fragment activity
				// return new Plank_main();
				// case 8:
				// Games fragment activity
				// return new ElbowLevers_main();
				// case 9:
				// Games fragment activity
				// return new Flags_main();
				// case 10:
				// Games fragment activity
				// return new IronCross_main();
				// case 11:
				// Games fragment activity
				// return new Misc_main();
			}
			return null;
		}

		@Override
		public int getCount() {
			// Show 7 total pages.
			return 7;
		}

		// Hier werden die Titel der einzelnen Tabs festgelegt
		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section12).toUpperCase(l);
			case 1:
				return getString(R.string.title_section1).toUpperCase(l);
				// case 2:
				// return getString(R.string.title_section3).toUpperCase(l);
				// case 3:
				// return getString(R.string.title_section4).toUpperCase(l);
			case 2:
				return getString(R.string.title_section2).toUpperCase(l);
			case 3:
				return getString(R.string.title_section5).toUpperCase(l);
			case 4:
				return getString(R.string.title_section6).toUpperCase(l);
			case 5:
				return getString(R.string.title_section7).toUpperCase(l);
			case 6:
				return getString(R.string.title_section9).toUpperCase(l);
				// case 9:
				// return getString(R.string.title_section10).toUpperCase(l);
				// case 10:
				// return getString(R.string.title_section11).toUpperCase(l);
				// case 7:
				// return getString(R.string.title_section12).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_exercise,
					container, false);
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

	// Die Funktion wird bei Betätigen eines Skills aufgerufen,
	// je nach Id wird dann die entsprechende Skill-View aufgebaut
	public void ex(View view) {
		Intent intent = new Intent(this, ExerciseActivity.class);

		switch (view.getId()) {
		// misc skills
		case R.id.rtosupport:
			intent.putExtra("skill_id", R.id.rtosupport);
			break;
		case R.id.germanhang:
			intent.putExtra("skill_id", R.id.germanhang);
			break;
		// Handstand skills
		case R.id.wallhs:
			intent.putExtra("skill_id", R.id.wallhs);
			break;
		case R.id.freehs:
			intent.putExtra("skill_id", R.id.freehs);
			break;
		case R.id.ringshstand:
			intent.putExtra("skill_id", R.id.ringshstand);
			break;
		case R.id.rstraphs:
			intent.putExtra("skill_id", R.id.rstraphs);
			break;
		case R.id.chs:
			intent.putExtra("skill_id", R.id.chs);
			break;
		case R.id.hsff:
			intent.putExtra("skill_id", R.id.hsff);
			break;
		case R.id.rhs:
			intent.putExtra("skill_id", R.id.rhs);
			break;
		case R.id.ichs:
			intent.putExtra("skill_id", R.id.ichs);
			break;
		case R.id.hstf:
			intent.putExtra("skill_id", R.id.hstf);
			break;
		case R.id.hstwof:
			intent.putExtra("skill_id", R.id.hstwof);
			break;
		case R.id.hsof:
			intent.putExtra("skill_id", R.id.onearmhs);
			break;
		case R.id.onearmhs:
			intent.putExtra("skill_id", R.id.onearmhs);
			break;
		// Lsit Skills
		case R.id.tucklsit:
			intent.putExtra("skill_id", R.id.tucklsit);
			break;
		case R.id.oneleglsit:
			intent.putExtra("skill_id", R.id.oneleglsit);
			break;
		case R.id.lsit:
			intent.putExtra("skill_id", R.id.lsit);
			break;
		case R.id.slsit:
			intent.putExtra("skill_id", R.id.slsit);
			break;
		case R.id.rtolsit:
			intent.putExtra("skill_id", R.id.rtolsit);
			break;
		case R.id.vsitone:
			intent.putExtra("skill_id", R.id.vsitone);
			break;
		case R.id.rtoslsit:
			intent.putExtra("skill_id", R.id.rtoslsit);
			break;
		case R.id.vsittwo:
			intent.putExtra("skill_id", R.id.vsittwo);
			break;
		case R.id.rvsitone:
			intent.putExtra("skill_id", R.id.rvsitone);
			break;
		case R.id.rvsittwo:
			intent.putExtra("skill_id", R.id.rvsittwo);
			break;
		case R.id.vsitthree:
			intent.putExtra("skill_id", R.id.vsitthree);
			break;
		case R.id.vsitfour:
			intent.putExtra("skill_id", R.id.vsitfour);
			break;
		case R.id.vsitfive:
			intent.putExtra("skill_id", R.id.vsitfive);
			break;
		case R.id.vsitsix:
			intent.putExtra("skill_id", R.id.vsitsix);
			break;
		case R.id.vsitseven:
			intent.putExtra("skill_id", R.id.vsitseven);
			break;
		case R.id.manna:
			intent.putExtra("skill_id", R.id.manna);
			break;
		// Back Lever Skills
		case R.id.tuckbl:
			intent.putExtra("skill_id", R.id.tuckbl);
			break;
		case R.id.advtuckbl:
			intent.putExtra("skill_id", R.id.advtuckbl);
			break;
		case R.id.straddlebl:
			intent.putExtra("skill_id", R.id.straddlebl);
			break;
		case R.id.halflaybl:
			intent.putExtra("skill_id", R.id.halflaybl);
			break;
		case R.id.fullbl:
			intent.putExtra("skill_id", R.id.fullbl);
			break;
		// Front Lever Skills
		case R.id.tuckfl:
			intent.putExtra("skill_id", R.id.tuckfl);
			break;
		case R.id.advtuckfl:
			intent.putExtra("skill_id", R.id.advtuckfl);
			break;
		case R.id.straddlefl:
			intent.putExtra("skill_id", R.id.straddlefl);
			break;
		case R.id.halflayfl:
			intent.putExtra("skill_id", R.id.halflayfl);
			break;
		case R.id.fullfl:
			intent.putExtra("skill_id", R.id.fullfl);
			break;
		// Planche Skills
		case R.id.frogstand:
			intent.putExtra("skill_id", R.id.frogstand);
			break;
		case R.id.safrogstand:
			intent.putExtra("skill_id", R.id.safrogstand);
			break;
		case R.id.tuckpl:
			intent.putExtra("skill_id", R.id.tuckpl);
			break;
		case R.id.advtuckpl:
			intent.putExtra("skill_id", R.id.advtuckpl);
			break;
		case R.id.straddlepl:
			intent.putExtra("skill_id", R.id.straddlepl);
			break;
		case R.id.halflaypl:
			intent.putExtra("skill_id", R.id.halflaypl);
			break;
		case R.id.sastrpltohs:
			intent.putExtra("skill_id", R.id.sastrpltohs);
			break;
		case R.id.fullpl:
			intent.putExtra("skill_id", R.id.fullpl);
			break;
		case R.id.ringsfullpl:
			intent.putExtra("skill_id", R.id.ringsfullpl);
			break;
		// Elbow Levers Skills
		case R.id.twoarmel:
			intent.putExtra("skill_id", R.id.twoarmel);
			break;
		case R.id.ringstwoarmel:
			intent.putExtra("skill_id", R.id.ringstwoarmel);
			break;
		case R.id.oastraddleel:
			intent.putExtra("skill_id", R.id.oastraddleel);
			break;
		case R.id.oasbel:
			intent.putExtra("skill_id", R.id.oasbel);
			break;
		}
		startActivity(intent);
	}
}
