package app.com.example.karthik.homework3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, PlaceholderFragment.newInstance(R.id.selection_home))
                    .commit();
        }
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
        int id = item.getItemId();

        getSupportFragmentManager().beginTransaction().
                replace(R.id.container, PlaceholderFragment.newInstance(id)).commit();

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }
         /* Returns a new instance of the fragment for the given id*/

        public static PlaceholderFragment newInstance(int sectionNumber)
        {
            PlaceholderFragment placeholderFragment= new PlaceholderFragment();
            Bundle args= new Bundle();
            args.putInt("section number", sectionNumber);
            placeholderFragment.setArguments(args);
            return placeholderFragment;
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            View rootView=null;//inflater.inflate(R.layout.fragment_main, container, false);
            int options = getArguments().getInt("section number");

            switch (options)
            {
                case R.id.selection_home:
                    rootView = inflater.inflate(R.layout.fragment_home, container, false);
                    rootView.setBackgroundColor(Color.parseColor("#000000"));
                    TextView text=(TextView) rootView.findViewById(R.id.name);
                    text.setTextSize(getResources().getDimension(R.dimen.textsize));
                    text.setTextColor(Color.parseColor("#BF360C"));
                    TextView text2=(TextView) rootView.findViewById(R.id.session);
                    text2.setTextSize(getResources().getDimension(R.dimen.textsize));
                    text2.setTextColor(Color.parseColor("#BF360C"));
                    //return rootView;
                    break;
                case R.id.selection_listview:

                    rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    ListView listView = (ListView) rootView.findViewById(R.id.movielistview);
                    final MovieData movieData = new MovieData();

                    final MyAdapter myAdapter = new MyAdapter(getActivity(), movieData.getMoviesList());

                    listView.setAdapter(myAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                            HashMap<String, ?> itemMap = (HashMap<String, ?>) myAdapter.getItem(position);
                            CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
                            TextView title = (TextView) view.findViewById(R.id.movietitle);
                            String name = title.getText().toString();
                            Toast.makeText(getActivity(), "Selected: " + name, Toast.LENGTH_SHORT).show();
                            HashMap<String, Boolean> itemMap_bool = (HashMap<String, Boolean>) itemMap;
                            itemMap_bool.put("selection", !checkBox.isChecked());
                            checkBox.setChecked(!checkBox.isChecked());
                            myAdapter.notifyDataSetChanged();
                        }

                    });

                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {
                            myAdapter.duplicateItem(position);
                            myAdapter.notifyDataSetChanged();
                            return true;
                        }
                    });

                    Button selectall = (Button) rootView.findViewById(R.id.selectallbutton);
                    selectall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for (int i = 2; i < myAdapter.getCount(); i++) {
                                HashMap<String, Boolean> selectionval = (HashMap<String, Boolean>) myAdapter.getItem(i);
                                selectionval.put("selection", true);
                            }
                            myAdapter.notifyDataSetChanged();
                        }
                    });

                    Button clearall = (Button) rootView.findViewById(R.id.clearallbutton);
                    clearall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for (int i = 2; i < myAdapter.getCount(); i++) {
                                HashMap<String, Boolean> selectionval = (HashMap<String, Boolean>) myAdapter.getItem(i);
                                selectionval.put("selection", false);
                            }
                            myAdapter.notifyDataSetChanged();
                        }
                    });

                    Button deletebutton = (Button) rootView.findViewById(R.id.deletebutton);
                    deletebutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            movieData.deleteMovie();

                            myAdapter.notifyDataSetChanged();
                        }
                    });
                    break;
                case R.id.selection_gridview:
                    rootView = inflater.inflate(R.layout.gridview_layout, container, false);
                    GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
                    final MovieData movieData2 = new MovieData();
                    final GridAdapter gridAdapter=new GridAdapter(getActivity(),movieData2.getMoviesList());
                    gridview.setAdapter(gridAdapter);
                    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                            //HashMap<String, ?> itemMap = (HashMap<String, ?>) gridAdapter.getItem(position);

                           String name=(String)movieData2.getItem(position).get("name");
                            Toast.makeText(getActivity(), "Selected: " + name, Toast.LENGTH_SHORT).show();

                            gridAdapter.notifyDataSetChanged();
                        }

                    });
                    break;

            }
            return rootView;


        }


    }
}
