package app.com.example.karthik.homework3;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Karthik on 2/1/2015.
 */
public class MyAdapter extends BaseAdapter {
    private final Context context;
    private final List<Map<String,?>> movieList;
    //private final DataSetObservable mDataSetObservable = new DataSetObservable();

    public MyAdapter(Context context,List<Map<String,?>> movieList){
        this.context=context;
        this.movieList=movieList;
    }
    @Override
    public int getCount() {return movieList.size();}

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }
    @Override
    public long getItemId(int pos) {return 0;}

    class ViewHolder{
        TextView name;
        ImageView img;
        TextView desc;
        CheckBox chkbox;
        RatingBar rating;
    }

    public View getView(int position, View view, ViewGroup parent){


        View rowView;//=inflater.inflate(R.layout.listview_movies,parent,false);
        ViewHolder holder=null;
        if(view==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            rowView=inflater.inflate(R.layout.listview_movies,parent,false);
            holder=new ViewHolder();
            holder.name=(TextView)rowView.findViewById(R.id.movietitle);
            holder.img=(ImageView) rowView.findViewById(R.id.movieimage);
            holder.desc=(TextView)rowView.findViewById(R.id.moviedesc);
            holder.rating=(RatingBar)rowView.findViewById(R.id.rating);
            holder.chkbox=(CheckBox) rowView.findViewById(R.id.checkbox);
            rowView.setTag(holder);
        }
        else{
            rowView=view;
            holder=(ViewHolder)view.getTag();
        }

        Map<String,?> entry=movieList.get(position);

        holder.name.setText((String) entry.get("name"));
        holder.img.setImageResource((Integer)entry.get("image"));
        holder.desc.setText((String)entry.get("description"));

        float rating=Float.parseFloat(String.valueOf(entry.get("rating")));
        holder.rating.setRating(rating/2);

        if(position%2==0){
            holder.name.setTextColor(Color.RED);
        holder.desc.setTextColor(Color.RED);}
        else {holder.name.setTextColor(Color.BLUE);
            holder.desc.setTextColor(Color.BLUE);}

        if (entry.get("selection")!=null)
            holder.chkbox.setChecked((Boolean) entry.get("selection"));

        return rowView;
    }

    public void duplicateItem(int position){
        HashMap<String,?> movie=(HashMap<String,?>) movieList.get(position);
        movieList.add(position,(HashMap<String,?>)movie.clone());
    }

    @Override
    public  boolean isEnabled(int position){
        if(position ==0|position==1)
            return false;
        return true;


    }



}