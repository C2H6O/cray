package net.doubov.myredditclient;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.doubov.myredditclient.model.DaoSession;
import net.doubov.myredditclient.model.Lease;
import net.doubov.myredditclient.model.LeaseDao;
import net.doubov.myredditclient.model.Person;
import net.doubov.myredditclient.model.PersonDao;
import net.doubov.myredditclient.model.ResultSet;
import net.doubov.myredditclient.model.ResultSetDao;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

  @Bind(R.id.list) RecyclerView mList;

  @Inject DaoSession dbSession;

  private LeaseAdapter adapter;

  public MainActivityFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    App app = (App) getActivity().getApplicationContext();
    app.getAppComponent().inject(this);
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

    insertSampleData(dbSession);

    LeaseDao leaseDao = dbSession.getLeaseDao();
    List<Lease> lease = leaseDao.loadAll();

    adapter = new LeaseAdapter(lease);
    mList.setAdapter(adapter);
    mList.setLayoutManager(new LinearLayoutManager(getActivity()));

  }

  public void insertSampleData(DaoSession daoSession) {
    Person person = new Person();
    person.setName("John Doe");
    PersonDao personDao = daoSession.getPersonDao();
    personDao.insertOrReplace(person);

    Lease lease = new Lease();
    lease.setItem("My Nexus 6");
    lease.setPerson(person);

    Lease another = new Lease();
    another.setItem("USB-C Cable");
    another.setPerson(person);

    LeaseDao leaseDao = daoSession.getLeaseDao();
    leaseDao.insertOrReplace(lease);
    leaseDao.insertOrReplace(another);

    ResultSet resultSet = new ResultSet();
    resultSet.setUid("1234");
    resultSet.setName("Root");

    ResultSetDao resultSetDao = daoSession.getResultSetDao();
    resultSetDao.insertOrReplace(resultSet);

    ResultSet child_1 = new ResultSet();
    child_1.setUid("1234_1");
    child_1.setName("1");
    child_1.setResultSetId(resultSet.getId());
    resultSetDao.insert(child_1);

    ResultSet child_2 = new ResultSet();
    child_2.setUid("1234_2");
    child_2.setName("2");
    child_2.setResultSetId(resultSet.getId());
    resultSetDao.insert(child_2);

    ResultSet child_1_1 = new ResultSet();
    child_1_1.setUid("1234_1_1");
    child_1_1.setName("1_1");
    child_1_1.setResultSetId(child_1.getId());
    resultSetDao.insert(child_1_1);

    ResultSet child_1_2 = new ResultSet();
    child_1_2.setUid("1234_1_2");
    child_1_2.setName("1_2");
    child_1_2.setResultSetId(child_1.getId());
    resultSetDao.insert(child_1_2);

    ResultSet child_2_1 = new ResultSet();
    child_2_1.setUid("1234_2_1");
    child_2_1.setName("2_1");
    child_2_1.setResultSetId(child_2.getId());
    resultSetDao.insert(child_2_1);


  }

}
