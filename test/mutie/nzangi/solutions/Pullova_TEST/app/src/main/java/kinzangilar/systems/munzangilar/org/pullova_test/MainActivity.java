package kinzangilar.systems.munzangilar.org.pullova_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, "home").commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        boolean walkedThrough = false;
        for (Fragment f : fm.getFragments()) {
            FragmentManager fm2 = f.getActivity().getSupportFragmentManager();
            if (fm2.getBackStackEntryCount() > 0) {
                fm2.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                Toast.makeText(this,R.string.exitMsg,Toast.LENGTH_LONG).show();
                walkedThrough = true;
            }
        }
        if (!walkedThrough && fm.getFragments().size() <= 1) {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
