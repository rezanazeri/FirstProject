package ir.co.ts.firstproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        changeFragment(this, FirstFragment.newInstance(), false);
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
