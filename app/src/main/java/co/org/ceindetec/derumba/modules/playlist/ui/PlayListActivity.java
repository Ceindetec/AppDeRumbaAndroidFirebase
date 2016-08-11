package co.org.ceindetec.derumba.modules.playlist.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.org.ceindetec.derumba.R;
import co.org.ceindetec.derumba.entities.PlaylistSong;
import co.org.ceindetec.derumba.modules.detailsong.ui.DetailSongFragment;
import co.org.ceindetec.derumba.modules.login.ui.LoginMainActivity;
import co.org.ceindetec.derumba.modules.playlist.PlayListPresenter;
import co.org.ceindetec.derumba.modules.playlist.PlayListPresenterImpl;
import co.org.ceindetec.derumba.modules.playlist.ui.adapters.OnItemClickListener;
import co.org.ceindetec.derumba.modules.playlist.ui.adapters.PlayListAdapter;
import de.hdodenhof.circleimageview.CircleImageView;

public class PlayListActivity extends AppCompatActivity implements PlayListView, OnItemClickListener {

    //Bindeo de los controles de la vista por ButterKnife
    @Bind(R.id.apblPlayList)
    AppBarLayout apblPlayList;
    @Bind(R.id.srvSearchSong)
    SearchView srvSearchSong;
    @Bind(R.id.imgAddSong)
    CircleImageView imgAddSong;
    @Bind(R.id.llyIncContentSearchPlayList)
    LinearLayout llyIncContentSearchPlayList;
    @Bind(R.id.rcvRecyclerPlayListSongs)
    RecyclerView rcvRecyclerPlayListSongs;
    @Bind(R.id.llyIncContentPlayList)
    LinearLayout llyIncContentPlayList;
    @Bind(R.id.imvImageMessage)
    ImageView imvImageMessage;
    @Bind(R.id.txvMessage)
    TextView txvMessage;
    @Bind(R.id.llyIncContentMessage)
    LinearLayout llyIncContentMessage;
    @Bind(R.id.colSearchSong)
    CoordinatorLayout colSearchSong;
    @Bind(R.id.tlbPlayList)
    Toolbar tlbPlayList;

    //Instanciamiento de la interfaz PlayListPresenter
    private PlayListPresenter playListPresenter;

    //Instanciamiento del PlayListAdapter
    private PlayListAdapter playListAdapter;

    //Declaracion variable del establecimiento
    int idEstablecimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play_list);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ButterKnife.bind(this);
        //Dato dummy del id del establecimiento
        idEstablecimiento = 0;

        //Instanciamiento de la interfaz LoginPresenter
        playListPresenter = new PlayListPresenterImpl(this);

        //playListPresenter.datosDummy();

        playListPresenter.onCreate();

        //Llamado al metodo que inicializa el Adaptador
        setupAdapter();

        //Llamado al metodo que inicializa el RecyclerView
        setupRecyclerView();

        //Llamado al metodo que inicializa el ToolBar
        setupToolbar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.men_close_session, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            playListPresenter.signOff();
            Intent intent = new Intent(this, LoginMainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo sobrecargado de la Actividad para eliminar el Presentador
     */
    @Override
    protected void onDestroy() {
        playListPresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Metodo que inicializa y configura el Adaptador
     */
    private void setupAdapter() {
        playListAdapter = new PlayListAdapter(new ArrayList<PlaylistSong>(), this);
    }

    /**
     * Metodo que inicializa y configura el recyclerView
     */
    private void setupRecyclerView() {
        rcvRecyclerPlayListSongs.setLayoutManager(new LinearLayoutManager(this));
        rcvRecyclerPlayListSongs.setAdapter(playListAdapter);
    }

    private void setupToolbar() {
        tlbPlayList.setTitle(playListPresenter.getCurrentUserName());
        setSupportActionBar(tlbPlayList);
    }

    @Override
    public void onSongAdded(PlaylistSong songPlayList) {
        playListAdapter.add(songPlayList);
    }

    @Override
    public void onSongChanged(PlaylistSong songPlayList) {
        playListAdapter.update(songPlayList);
    }

    @Override
    public void onSongRemoved(PlaylistSong songPlayList) {
        playListAdapter.remove(songPlayList);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void messageAdded() {

    }

    @Override
    public void onItemClick(PlaylistSong playListItem) {

        Bundle args = new Bundle();
        args.putString("codigoPlaylist", playListItem.getCodigoPlaylist());
        args.putString("codigoCancion", playListItem.getCodigoCancion());

        DetailSongFragment detailSongFragment = new DetailSongFragment();
        detailSongFragment.setArguments(args);
        detailSongFragment.show(getSupportFragmentManager(), getString(R.string.detailSong_activity_title));

    }

}
