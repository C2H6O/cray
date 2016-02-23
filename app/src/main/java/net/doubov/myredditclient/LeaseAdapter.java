package net.doubov.myredditclient;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import net.doubov.myredditclient.model.Lease;

import java.util.ArrayList;
import java.util.List;

public class LeaseAdapter extends RecyclerView.Adapter<LeaseAdapter.Holder> {

  private List<Lease> items = new ArrayList<>();

  public LeaseAdapter(List<Lease> data) {
    items = data;
  }

  public void setData(List<Lease> data) {
    items.clear();
    items.addAll(data);
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  @Override
  public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v =
        LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.rv_row, parent, false);

    return new Holder(v);
  }

  @Override
  public void onBindViewHolder(Holder holder, int position) {
    Lease lease = items.get(position);
    holder.mItem.setText(lease.getItem());
    holder.mName.setText(lease.getPerson().getName());
  }

  public static class Holder extends RecyclerView.ViewHolder {
    TextView mItem;
    TextView mName;

    public Holder(View itemView) {
      super(itemView);
      mItem = (TextView) itemView.findViewById(R.id.activity_main_item_item);
      mName = (TextView) itemView.findViewById(R.id.activity_main_item_person_name);
    }
  }

}
