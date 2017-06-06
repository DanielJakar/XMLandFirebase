package android.course.xmlandfirebase;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

//TODO: Set the adapter.

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class YnetArticleFragment extends Fragment implements YnetDataSource.onYnetArrivedLIstener {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_ynetarticle, container, false);

        YnetDataSource.getYnet(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return recyclerView;
    }

    @Override
    public void onYnetArrived(final List<YnetDataSource.Ynet> data, final Exception e) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (e == null){
                    Toast.makeText(getContext(), data.toString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    static class YnetAdapter extends RecyclerView.Adapter<YnetAdapter.YnetViewHolder>{
        //createViewHolders
        //getcount()
        //bind the viewholders to the data
        //Properties:
        private List<YnetDataSource.Ynet> data;
        private Context context;
        private LayoutInflater inflater;
        //Constuctor:


        public YnetAdapter(List<YnetDataSource.Ynet> data, Context context) {
            this.data = data;
            this.context = context;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public YnetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(YnetViewHolder holder, int position) {
            YnetDataSource.Ynet ynet = data.get(position);
            holder.tvTitle.setText(ynet.getTitle());
            holder.tvDescription.setText(ynet.getDescription());
            Picasso.with(context).load(ynet.getImage()).into(holder.ivThumbnail);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        //View Holder: hold the views
        class YnetViewHolder extends RecyclerView.ViewHolder{
            ImageView ivThumbnail;
            TextView tvTitle;
            TextView tvDescription;

            public YnetViewHolder(View v) {
                super(v);
                ivThumbnail = (ImageView) v.findViewById(R.id.ivThumbnail);
                tvTitle = (TextView) v.findViewById(R.id.tvTitle);
                tvDescription = (TextView) v.findViewById(R.id.tvDescription);

            }
        }

    }
}
