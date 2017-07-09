package com.accmxxgmail.gtfsreader;

import com.accmxxgmail.gtfsreader.DBAdapter.DBHelper;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class StopList extends ListActivity {
    private DBHelper db = new DBHelper();
    private Long rowId;
    private String agency_name;
    private Cursor stopCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        rowId = extras.getLong(DBAdapter.KEY_SAGENCYID);
        /* Need to import agency_name to StopList intent somehow ... thru agencyCursor maybe? */
        agency_name = extras.getString(DBAdapter.KEY_AGENCYNAME);

        DisplayStops(rowId);
//    	registerForContextMenu(getListView());

    }

    @Override
    protected void onStop() {
        stopManagingCursor(stopCursor);
        stopCursor.close();
        super.onStop();
    }

    @Override
    protected void onDestroy () {
        stopManagingCursor(stopCursor);
        stopCursor.close();
        super.onDestroy();
    }

    public void DisplayStops(long rowId) {
        // open db
        setTitle(agency_name);
        db.open();
        // Get all of the rows from the database
        // and create the item list
        stopCursor = db.getAllStops(rowId);
        startManagingCursor(stopCursor);

        // array to specify the fields to display in the list
        String[] from = new String[]{DBAdapter.KEY_STOPNAME, DBAdapter.KEY_STOPID};

        // array of fields to bind those fields to
        int[] to = new int[]{R.id.stop_name, R.id.stop_id};

        // Simple cursor adapter; set to display
        SimpleCursorAdapter stops = new SimpleCursorAdapter(this, R.layout.stop_list_item, stopCursor, from, to);
        setListAdapter(stops);

        // Have to implement filtering using this
/*    	ListView lv = getListView();
    	lv.setTextFilterEnabled(true); */

        // close db
        db.close();
    }
}
