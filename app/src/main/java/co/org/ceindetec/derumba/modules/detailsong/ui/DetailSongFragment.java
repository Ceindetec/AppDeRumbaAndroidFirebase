package co.org.ceindetec.derumba.modules.detailsong.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.org.ceindetec.derumba.R;
import co.org.ceindetec.derumba.entities.Song;
import co.org.ceindetec.derumba.modules.detailsong.DetailSongPresenter;
import co.org.ceindetec.derumba.modules.detailsong.DetailSongPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailSongFragment extends DialogFragment implements DetailSongView, DialogInterface.OnShowListener {

    //Bindedo de la vista
    @Bind(R.id.txvDetailSongNombre)
    TextView txvDetailSongNombre;
    @Bind(R.id.txvDetailSongInterprete)
    TextView txvDetailSongInterprete;
    @Bind(R.id.txvDetailSongGenero)
    TextView txvDetailSongGenero;
    @Bind(R.id.txvDetailSongDuracion)
    TextView txvDetailSongDuration;
    @Bind(R.id.pgbDetailSongProgress)
    ProgressBar pgbDetailSongProgress;
    @Bind(R.id.imvRankUp)
    ImageView imvRankUp;
    @Bind(R.id.imvBackPlaylist)
    ImageView imvBackPlaylist;

    //Declaracion del presentador para el detalle de la cancion
    DetailSongPresenter detailSongPresenter;

    private String codigoCancion;
    private String codigoPlaylist;

    public DetailSongFragment() {
        //Inicializacion del presentador para el detalle de la cancion
        detailSongPresenter = new DetailSongPresenterImpl(this);
    }

    /**
     * Constructor de la clase del Dialogfragment
     *
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        codigoCancion = getArguments().getString("codigoCancion");
        codigoPlaylist = getArguments().getString("codigoPlaylist");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.detailSong_activity_title);

        // Inflate the layout for this fragment
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_detail_song, null);

        ButterKnife.bind(this, view);

        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);

        return dialog;
    }

    /**
     * Metodo sobrecargado del DialogFragment para su destruccion
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * Metodo sobrecargado del DialogFragment cuando es destruido el fragment
     */
    @Override
    public void onDestroy() {
        detailSongPresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Metodo implementado de la interface DialogInterface.OnShowListener para cuando este sea mostrado
     *
     * @param dialog
     */
    @Override
    public void onShow(DialogInterface dialog) {
        detailSongPresenter.onShow();
        detailSongPresenter.getInfoSong(codigoCancion);
        detailSongPresenter.verifyLikeSong(codigoPlaylist, codigoCancion);
    }

    /**
     * Metodo implementado de la interface DetailSongView en el evento de informacion de la cancion obtenida correctamente
     *
     * @param infoSong
     */
    @Override
    public void getInfoSongSuccess(Song infoSong) {
        String txtNombre = String.format(getString(R.string.detailSong_text_songNombre), infoSong.getNombre());
        txvDetailSongNombre.setText(txtNombre);

        String txtInterprete = String.format(getString(R.string.detailSong_text_songInterprete), infoSong.getInterprete());
        txvDetailSongInterprete.setText(txtInterprete);

        String txtGenero = String.format(getString(R.string.detailSong_text_songGenero), infoSong.getGenero());
        txvDetailSongGenero.setText(txtGenero);

        String txtDuracion = String.format(getString(R.string.detailSong_text_songDuracion), Integer.toString(infoSong.getDuracion() / 60));
        txvDetailSongDuration.setText(txtDuracion);
    }

    /**
     * Metodo implementado de la interface DetailSongView en el evento de informacion de la cancion no ha sido obtenida correctamente
     */
    @Override
    public void getInfoSongError() {

        Toast.makeText(getActivity(), this.getString(R.string.detailSong_messageError_songNoInfo), Toast.LENGTH_SHORT).show();

    }

    /**
     * Metodo implementado de la interface DetailSongView en el evento de el ranked se ha realizado correctamente
     */
    @Override
    public void rankSongSuccess() {
        Toast.makeText(getActivity(), this.getString(R.string.detailSong_messageSuccess_songRanked), Toast.LENGTH_SHORT).show();
        backToPlaylist();
    }

    /**
     * Metodo implementado de la interface DetailSongView en el evento de el ranked no se ha realizado correctamente
     */
    @Override
    public void rankSongError() {

        Toast.makeText(getActivity(), this.getString(R.string.detailSong_messageError_songNoRanked), Toast.LENGTH_SHORT).show();

    }

    /**
     * Metodo implementado de la interface DetailSongView para mostrar la barra de progreso
     */
    @Override
    public void showProgress() {

        pgbDetailSongProgress.setVisibility(View.VISIBLE);

    }

    /**
     * Metodo implementado de la interface DetailSongView para ocultar la barra de progreso
     */
    @Override
    public void hideProgress() {

        pgbDetailSongProgress.setVisibility(View.GONE);

    }

    /**
     * * Metodo implementado de la interface DetailSongView para verificar el like del usuario
     *
     * @param likeExist
     */
    @Override
    public void verifyUserLike(boolean likeExist) {
        if (likeExist) {
            imvRankUp.setImageResource(R.drawable.ic_thumb_down_black_48dp);
        } else {
            imvRankUp.setImageResource(R.drawable.ic_thumb_up_black_48dp);
        }
    }

    /**
     * Metodo que ranquea la cancion
     */
    @OnClick(R.id.imvRankUp)
    public void songRankUp() {

        detailSongPresenter.rankSong(codigoPlaylist, codigoCancion);

    }

    /**
     * Metodo para navegar a la Playlist
     */
    @OnClick(R.id.imvBackPlaylist)
    public void backToPlaylist() {

        dismiss();

    }

}
