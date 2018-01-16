package id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.R;
import id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.kost.Kost;

/**
 * Created by user on 30/03/2017.
 */

public class KostAdapter extends RecyclerView.Adapter<KostAdapter.ViewHolder> {

    static ClickListener clickListener;
    ArrayList<Kost> kostList;

    public KostAdapter(ArrayList<Kost> kostList)
    {
        this.kostList = kostList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Kost kost = kostList.get(position);
        holder.tvJudul.setText(kost.judul);
        holder.ivFoto.setImageDrawable(kost.foto);
    }

    @Override
    public int getItemCount() {
        if (kostList != null)
            return kostList.size();
        return 0;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        KostAdapter.clickListener = clickListener;
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
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onItemLongClick(getAdapterPosition(), view);
            return false;
        }
    }

}
