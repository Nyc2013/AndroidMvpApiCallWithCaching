package app.pmarchini.com.androidmvpapicallwithcaching.ui.list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.pmarchini.com.androidmvpapicallwithcaching.R;
import app.pmarchini.com.androidmvpapicallwithcaching.data.model.Record;

/**
 * Created by Pierre on 25/11/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<Record> mListRecords;

    public ListAdapter (List<Record> recordsList){
        this.mListRecords = recordsList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Record oneRecord = mListRecords.get(position);

        String imageUrl = oneRecord.getThumbnailUrl();
        Picasso.with(holder.itemView.getContext()).load(imageUrl).into((holder).image);

        holder.title.setText(oneRecord.getTitle());
    }

    @Override
    public int getItemCount() {
        return mListRecords.size();
    }

    public void addItems(List<Record> listRecords) {
        Log.d("DEBUG","Adapter/addItems/taille list en param = "+listRecords.size());
        Log.d("DEBUG","Adapter/addItems/taille list de l'adapter = "+mListRecords.size());
        if (!mListRecords.isEmpty()){
           mListRecords.clear();
        }
        Log.d("DEBUG","Adapter/addItems/taille list de l'adapter apres clear() = "+mListRecords.size());
        mListRecords.addAll(listRecords);
        Log.d("DEBUG","Adapter/addItems/taille nlle list adapter = "+mListRecords.size());
        notifyDataSetChanged();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;


        public MyViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.iv_record);
            title = (TextView) itemView.findViewById(R.id.tv_title);


        }
    }
}
