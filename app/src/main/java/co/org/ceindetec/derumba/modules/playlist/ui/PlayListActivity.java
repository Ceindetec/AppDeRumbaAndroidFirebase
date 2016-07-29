package co.org.ceindetec.derumba.modules.playlist.ui;

import android.content.Intent;
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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.org.ceindetec.derumba.R;
import co.org.ceindetec.derumba.entities.PlayList;
import co.org.ceindetec.derumba.entities.Session;
import co.org.ceindetec.derumba.modules.detailsong.ui.DetailSongFragment;
import co.org.ceindetec.derumba.modules.login.ui.LoginMainActivity;
import co.org.ceindetec.derumba.modules.login.ui.LoginUserDeRumbaActivity;
import co.org.ceindetec.derumba.modules.playlist.PlayListPresenter;
import co.org.ceindetec.derumba.modules.playlist.PlayListPresenterImpl;
import co.org.ceindetec.derumba.modules.playlist.ui.funcionalities.OnItemClickListener;
import co.org.ceindetec.derumba.modules.playlist.ui.funcionalities.PlayListAdapter;
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
        setContentView(R.layout.activity_music_list);
        ButterKnife.bind(this);
        //Dato dummy del id del establecimiento
        idEstablecimiento = 0;

        //Llamado al metodo que inicializa el Adaptador
        setupAdapter();

        //Llamado al metodo que inicializa el RecyclerView
        setupRecyclerView();

        //Instanciamiento de la interfaz LoginPresenter
        playListPresenter = new PlayListPresenterImpl(this);
        playListPresenter.onCreate();
        playListPresenter.loadPlayList(idEstablecimiento);

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
        //playListPresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Metodo que inicializa y configura el Adaptador
     */
    private void setupAdapter() {

        //Dummy de datos
        List<PlayList> song = new ArrayList<PlayList>();
        song.add(new PlayList(idEstablecimiento, 1, "Una Cancion", 100));
        song.add(new PlayList(idEstablecimiento, 2, "La Macarena", 200));
        song.add(new PlayList(idEstablecimiento, 3, "El Ultimo Polvo", 300));
        song.add(new PlayList(idEstablecimiento, 4, "El Baile De Los Que Sobran", 400));
        song.add(new PlayList(idEstablecimiento, 5, "Can't stop the feeling", 400));
        song.add(new PlayList(idEstablecimiento, 6, "One dance", 400));
        song.add(new PlayList(idEstablecimiento, 7, "This girl", 400));
        song.add(new PlayList(idEstablecimiento, 8, "Don't let me down", 400));
        song.add(new PlayList(idEstablecimiento, 9, "Ride", 400));
        song.add(new PlayList(idEstablecimiento, 10, "Gallo negro", 400));
        song.add(new PlayList(idEstablecimiento, 11, "We don't talk anymore", 400));
        song.add(new PlayList(idEstablecimiento, 12, "Cheap thrills", 400));
        song.add(new PlayList(idEstablecimiento, 13, "I took a pill in Ibiza (Explicit version)", 400));
        song.add(new PlayList(idEstablecimiento, 14, "Work from home", 400));
        song.add(new PlayList(idEstablecimiento, 15, "Rumbo al sol", 400));
        song.add(new PlayList(idEstablecimiento, 16, "Don't you need somebody", 400));
        song.add(new PlayList(idEstablecimiento, 17, "Send my love (To your new lover)", 400));
        playListAdapter = new PlayListAdapter(song, this);

        //playListAdapter = new PlayListAdapter(new ArrayList<PlayList>(), this);
    }

    /**
     * Metodo que inicializa y configura el recyclerView
     */
    private void setupRecyclerView() {
        rcvRecyclerPlayListSongs.setLayoutManager(new LinearLayoutManager(this));
        rcvRecyclerPlayListSongs.setAdapter(playListAdapter);
    }

    private void setupToolbar() {
        tlbPlayList.setTitle(Session.getInstance().getNick());
        setSupportActionBar(tlbPlayList);
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
    public void onItemClick(PlayList playListItem) {
        new DetailSongFragment().show(getSupportFragmentManager(), getString(R.string.detailSong_activity_title));
    }

}
