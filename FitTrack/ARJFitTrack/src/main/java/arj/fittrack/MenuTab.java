package arj.fittrack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */
public class MenuTab extends AppCompatActivity {
    //Declared the Context for Dialog
    final Context context = this;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Add a upbutton to allow the user to go back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //Menu (ToolBar)
        switch (item.getItemId()) {
            case R.id.Help:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder.setTitle("Help");
                // set dialog message
                alertDialogBuilder
                        .setMessage("Help Message: \n" +
                                "How to set goals? \n" +
                                "The user inputs the goals in the notepad. \n" +
                                "\n" +
                                "Where is the goal section? \n" +
                                "The goal section is located in the main screen in the bottom right.  Another way is for the user to click on the set goals button. \n" +
                                "\n" +
                                "How does the distance travel work? \n" +
                                "The distance travelled works by it using a formula based on steps. Where the length of a step is 0.74 cm is multiplied with number of steps.  \n" +
                                "\n" +
                                "Is there a limit on the number of challenges you can set and goals that I can write? \n" +
                                "There is no limit, you can set as many challenges and write as many goals as you want. \n" +
                                "\n" +
                                "How do you save multiple notes using the notepad function? \n" +
                                "The user would click on the save button and that would save it to a file. \n" +
                                "\n" +
                                "How do I track all of my challenges and goals? \n" +
                                "It saves the goals to a database and it retrieves the challenges and tasks from the database so that the users can see it. \n" +
                                "\n" +
                                "How do I access all the different features of this app? \n" +
                                "Clicking on the icons that correspond to the different features of this app. ")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                                //display in short period of time
                                Toast.makeText(getApplicationContext(), "Ok is clicked",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
                break;
            case R.id.About:
                AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder2.setTitle("About");
                // set dialog message
                alertDialogBuilder2
                        .setMessage("About Message: \n," +
                                "Our health and fitness app is designed to help users achieve and maintain a healthy lifestyle. \n" +
                                " This app will help organize our users through the application. \n" +
                                " Our app will try and use a simple user interface so that all age groups will find it easy to use. \n" +
                                " As a result, we are trying to promote healthier life choices. ")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog2 = alertDialogBuilder2.create();
                // show it
                alertDialog2.show();
                break;
            case R.id.Setting:
                break;
            //Links to a discussion board regarding to health issues
            case R.id.Discussion:
                Uri url = Uri.parse("https://patient.info/forums");
                Intent launch = new Intent(Intent.ACTION_VIEW, url);
                startActivity(launch);
                break;
            //Redirects to CBC News
            case R.id.News:
                Uri url2 = Uri.parse("http://www.cbc.ca/news/health");
                Intent launch2 = new Intent(Intent.ACTION_VIEW, url2);
                startActivity(launch2);
                break;

            /*
            Need to add these into MenuTab so user can redirect MenuTab to other activities
            Redirects to the Notepad activity when notepad image is tapped
            */
            case R.id.notepad:
                Intent intentNotepad = new Intent(MenuTab.this, Notepad.class);
                startActivity(intentNotepad);
                break;

            //Redirects to Weight activity when weight image is tapped
            case R.id.weight:
                Intent intentWeight = new Intent(MenuTab.this, WeightLog.class);
                startActivity(intentWeight);
                break;
        }

        return super.onOptionsItemSelected(item);
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

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_menu_tab, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Goal goals = new Goal();
                    return goals;
                case 1:
                    Challenges challenge = new Challenges();
                    return challenge;
            }
            return null;
        }


        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Goals";
                case 1:
                    return "Challenge";
            }
            return null;
        }
    }
}
