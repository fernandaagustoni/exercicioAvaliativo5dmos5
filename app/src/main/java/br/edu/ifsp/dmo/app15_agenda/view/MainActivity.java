package br.edu.ifsp.dmo.app15_agenda.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ktx.Firebase;

import br.edu.ifsp.dmo.app15_agenda.R;
import br.edu.ifsp.dmo.app15_agenda.mvp.MainMVP;
import br.edu.ifsp.dmo.app15_agenda.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity
        implements MainMVP.View, View.OnClickListener, SearchView.OnQueryTextListener{

    private MainMVP.Presenter presenter;
    private FloatingActionButton mActionButton;
    private RecyclerView mRecyclerView;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findById();
        setListener();
        presenter = new MainPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.populate(mRecyclerView, null);
        presenter.startListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopListener();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if(v == mActionButton){
            Intent intent = new Intent(this, DetalhesActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    private void findById(){
        mActionButton = findViewById(R.id.fab_new_contact);
        mRecyclerView = findViewById(R.id.recyler_view);
        mSearchView = findViewById(R.id.search_view);
        mSearchView.setOnQueryTextListener(this);
    }

    private void setListener(){
        mActionButton.setOnClickListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String search) {
        presenter.populate(mRecyclerView, search);
        presenter.startListener();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String search) {
        presenter.populate(mRecyclerView, search);
        presenter.startListener();
        return false;
    }

}