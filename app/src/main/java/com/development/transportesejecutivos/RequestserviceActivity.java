package com.development.transportesejecutivos;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.development.transportesejecutivos.fragments.DestinyFragment;
import com.development.transportesejecutivos.fragments.SourceFragment;

public class RequestserviceActivity extends ActivityBase {
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestservice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        validateSession();
        setFragment();
    }

    protected void setFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        DestinyFragment destinyFragment = DestinyFragment.newInstance(getApplicationContext(), this.user, fragmentTransaction);
        fragmentTransaction.add(R.id.fragment_container, destinyFragment, "Service Request Fragment Part 1");
        fragmentTransaction.commit();
    }

    protected static void nextToSource(FragmentTransaction fragmentTransaction) {
        SourceFragment sourceFragment = SourceFragment.newInstance("Param1", "Param2");
        fragmentTransaction.replace(R.id.fragment_container, sourceFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
