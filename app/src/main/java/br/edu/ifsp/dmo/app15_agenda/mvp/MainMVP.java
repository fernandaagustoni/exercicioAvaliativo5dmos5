package br.edu.ifsp.dmo.app15_agenda.mvp;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public interface MainMVP {

    interface View{
        Context getContext();
    }

    interface Presenter{
        void detach();

        void populate(RecyclerView recyclerView, String searchView);

        void startListener();

        void stopListener();
    }
}
