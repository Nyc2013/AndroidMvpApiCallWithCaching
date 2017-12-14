package app.pmarchini.com.androidmvpapicallwithcaching.ui.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import app.pmarchini.com.androidmvpapicallwithcaching.R;
import app.pmarchini.com.androidmvpapicallwithcaching.data.model.Record;
import app.pmarchini.com.androidmvpapicallwithcaching.ui.base.BaseActivity;

public class ListActivity extends BaseActivity implements ListViewMvp {


     @Inject
     ListPresenterMvp<ListViewMvp> mPresenter;

     @Inject
     ListAdapter mListAdapter;

     @Inject
     LinearLayoutManager mLayoutManager;

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);

        getActivityComponent().inject(this);

        mPresenter.attachViewOnPresenter(this);

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mListAdapter);

    }


     @Override
     protected void onResume(){
        super.onResume();
        if (isConnected()){
            mPresenter.presenterComputationFromApiForView();
            Log.d("DEBUG","ListActivity/test apres apl rx API ");
        }else{
            mPresenter.presenterComputationFromDbForView();
            Log.d("DEBUG","ListActivity/test apres apl rx DB");
        }


     }



     @Override
     protected void onDestroy(){
        if(mPresenter.getApiDisposable()!=null){//isDisposed revoie un booleen mais pour ça il faut avoir le disposable qui lui peut ne pas exister
            mPresenter.getApiDisposable().dispose();
            //mPresenter.getStoreDisposable().dispose();//à vérifier si utile
        }
        if(mPresenter.getDbDisposable()!=null){ //à tester
            mPresenter.getDbDisposable().dispose();
        }

        mPresenter.onDetach();//set la View à null en appelant onDetach() de BasePresenter
        super.onDestroy();
     }


    /**
     * implementation méthode updateList()
     * méthode permettant l'affichage et la mise à jour de la liste en fonction des données renvoyées par
     * le presenter
     */

    @Override
    public void updateList(List<Record> recordsList){
        Log.d("DEBUG", "ListACTIVITY/updateList()/ contenu listRecords: "+recordsList.size());
        mListAdapter.addItems(recordsList);

    }


}
