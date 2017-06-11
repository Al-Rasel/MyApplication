package test.rasel.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rasel on 6/11/2017.
 */

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.homeRecylerViewHolder> {

    onClick onClick;
    private List<Person> persons = new ArrayList<>();

    public RecylerViewAdapter(List<Person> value) {
        this.persons = value;
    }

    @Override
    public homeRecylerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new homeRecylerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_single_iteam, parent, false));
    }

    @Override
    public void onBindViewHolder(homeRecylerViewHolder holder, int position) {
        holder.textViewId.setText(String.valueOf(persons.get(position).getId()));
        holder.textViewName.setText(persons.get(position).getName());


    }

    public void setOnClick(onClick onClick) {
        this.onClick = onClick;
    }

    public interface onClick {
        void onClickFromMyAdapter(int postion, View v);
        void onClickNameAdapter(int postion, View v);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    class homeRecylerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewName;
        TextView textViewId;

        public homeRecylerViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.tv_name);
            textViewId = (TextView) itemView.findViewById(R.id.tv_id);
            itemView.setOnClickListener(this);
            textViewName.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.tv_name) {
                onClick.onClickNameAdapter(getAdapterPosition(),view);

            } else {
                onClick.onClickFromMyAdapter(getAdapterPosition(), view);

            }

        }
    }
}
