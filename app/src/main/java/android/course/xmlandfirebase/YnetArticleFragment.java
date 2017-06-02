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
import android.course.xmlandfirebase.dummy.DummyContent;
import android.course.xmlandfirebase.dummy.DummyContent.DummyItem;

import java.util.List;

//TODO: Delete all but onCreateView

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class YnetArticleFragment extends Fragment implements YnetDataSource.onYnetArrivedLIstener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_ynetarticle, container, false);

        YnetDataSource.getYnet(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new YnetRecyclerAdapter(DummyContent.ITEMS));

        return recyclerView;
    }

    @Override
    public void onYnetArrived(List<YnetDataSource.Ynet> data, Exception e) {

    }
}
