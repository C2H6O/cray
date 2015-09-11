package net.doubov.myredditclient;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.model.DaoSession;
import com.example.model.Lease;
import com.example.model.LeaseDao;
import com.example.model.Person;
import com.example.model.PersonDao;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @Bind(R.id.list) RecyclerView mList;

    private LeaseAdapter mAdapter;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        LeaseDao leaseDao = App.getApp().getDbSession().getLeaseDao();
        List<Lease> lease = leaseDao.loadAll();

        mAdapter = new LeaseAdapter(lease);
        mList.setAdapter(mAdapter);
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));

        insertSampleData(App.getApp().getDbSession());

        lease = leaseDao.loadAll();
        mAdapter.setData(lease);

    }

    public void insertSampleData(DaoSession daoSession) {
        Person person = new Person();
        person.setName("John Doe");
        PersonDao personDao = daoSession.getPersonDao();
        personDao.insertOrReplace(person);

        Lease lease = new Lease();
        lease.setItem("My Nexus 6");
        lease.setPerson(person);
        LeaseDao leaseDao = daoSession.getLeaseDao();
        leaseDao.insertOrReplace(lease);
    }

}
