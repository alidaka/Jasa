package us.lidaka.jasa;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import java.security.InvalidParameterException;

import us.lidaka.jasa.AssetFragments.FilmFragment;
import us.lidaka.jasa.AssetFragments.PersonFragment;
import us.lidaka.jasa.AssetFragments.PlanetFragment;
import us.lidaka.jasa.AssetFragments.SpeciesFragment;
import us.lidaka.jasa.AssetFragments.StarshipFragment;
import us.lidaka.jasa.AssetFragments.VehicleFragment;
import us.lidaka.jasa.Model.SwapiAsset;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    // TODO: pass AssetType instead, or something
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, AssetListFragment.newInstance(AssetType.fromInt(position)))
                .commit();
    }

    public void onSectionAttached(String title) {
        mTitle = title;
        restoreActionBar();
    }

    public void onAssetSelected(SwapiAsset asset) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        // TODO: this needs more thought
        Fragment fragment;
        switch (asset.assetType) {
            case FILM:
                fragment = FilmFragment.newInstance(asset.url);
                break;
            case PERSON:
                fragment = PersonFragment.newInstance(asset.url);
                break;
            case PLANET:
                fragment = PlanetFragment.newInstance(asset.url);
                break;
            case SPECIES:
                fragment = SpeciesFragment.newInstance(asset.url);
                break;
            case STARSHIP:
                fragment = StarshipFragment.newInstance(asset.url);
                break;
            case VEHICLE:
                fragment = VehicleFragment.newInstance(asset.url);
                break;
            default:
                String message = String.format("Invalid asset type \"%s\"", asset.assetType.toString());
                throw new InvalidParameterException(message);
        }

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(mTitle);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

}
