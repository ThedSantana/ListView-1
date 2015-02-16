package app.com.example.karthik.homework3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Karthik on 2/5/2015.
 */
public class GridAdapter extends BaseAdapter {

    private final Context context;
    private final List<Map<String,?>> movieList;

    public GridAdapter(Context context,List<Map<String,?>> movieList) {
        this.context=context;
        this.movieList=movieList;
    }

    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int pos) {return 0;}

    class ViewHolder{
        TextView name;
        ImageView img;

    }
    public View getView(int position, View view, ViewGroup parent){

        View rowView;//=inflater.inflate(R.layout.listview_movies,parent,false);
        ViewHolder holder=null;
        if(view==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            rowView=inflater.inflate(R.layout.grid_movies,parent,false);
            holder=new ViewHolder();
            holder.name=(TextView)rowView.findViewById(R.id.text100);
            holder.img=(ImageView) rowView.findViewById(R.id.image100);

            rowView.setTag(holder);
        }
        else{
            rowView=view;
            holder=(ViewHolder)view.getTag();
        }

        Map<String,?> entry=movieList.get(position);

        holder.name.setText((String) entry.get("name"));
        holder.img.setImageResource((Integer)entry.get("image"));






        return rowView;
    }

}
