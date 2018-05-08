package id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.R;
import id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.kost.Kost;

/**
 * Created by user on 30/03/2017.
 */

public class KostAdapter extends RecyclerView.Adapter<KostAdapter.ViewHolder> implements Filterable {

    static ClickListener clickListener;
    ArrayList<Kost> kostList;
    ArrayList<Kost> kostList_filtered;

    public KostAdapter(ArrayList<Kost> kostList)
    {
        this.kostList = kostList;
        this.kostList_filtered = kostList;
    }

    public ArrayList<Kost> getFilteredList(){
        return kostList_filtered;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Kost kost = kostList.get(position);
        Kost kost = kostList_filtered.get(position);
        holder.tvJudul.setText(kost.judul);
        holder.ivFoto.setImageDrawable(kost.foto);
    }

    @Override
    public int getItemCount() {
        if (kostList_filtered != null)
            return kostList_filtered.size();

        return 0;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        KostAdapter.clickListener = clickListener;
    }

    @Override
    public Filter getFilter() {
        return new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String sequence = charSequence.toString().toLowerCase();
                if (sequence.isEmpty()) kostList_filtered = kostList;
                else {
                    ArrayList<Kost> filteredList = new ArrayList<>();
                    for (Kost kkk : kostList){
                        if (kkk.judul.toLowerCase().contains(sequence) || kkk.lokasi.toLowerCase().contains(sequence)){
                            filteredList.add(kkk);
                        }
                    }
                    kostList_filtered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = kostList_filtered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                kostList_filtered = (ArrayList<Kost>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener  {

        TextView tvJudul;
        ImageView ivFoto;

        public ViewHolder(View itemView)
        {
            super(itemView);
            tvJudul = (TextView) itemView.findViewById(R.id.textViewJudul);
            ivFoto = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {

            clickListener.onItemClick(getAdapterPosition(), view);
            //clickListener.onItemClick(kostList_filtered.get(getAdapterPosition())
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onItemLongClick(getAdapterPosition(), view);
            return false;
        }
    }

}
