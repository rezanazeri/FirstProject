package ir.co.ts.firstproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ir.co.ts.firstproject.fragments.FirstFragment;
import ir.co.ts.firstproject.fragments.SecondFragment;


public class MainActivity extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener, SecondFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimaryDark), 0);

        if (savedInstanceState == null) {
            changeFragment(this, FirstFragment.newInstance(), false);
        }
    }

    public void changeFragment(FragmentActivity context, Fragment fragment, boolean addToBackStack) {
        if (context != null && fragment != null) {

            if (addToBackStack) {
                context.getSupportFragmentManager().beginTransaction()
//                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                        .replace(R.id.mainContainer, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
            } else {
                context.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, fragment, fragment.getClass().getSimpleName())
                        .commit();
            }
        }
    }

    @Override
    public void onYahooOpen() {
        changeFragment(MainActivity.this, SecondFragment.newInstance(), false);
    }

    @Override
    public void onFirstItemClick() {
        changeFragment(MainActivity.this, FirstFragment.newInstance(), false);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
