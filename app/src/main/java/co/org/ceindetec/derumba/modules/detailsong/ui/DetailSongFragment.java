package co.org.ceindetec.derumba.modules.detailsong.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    @Bind(R.id.imvRankedUp)
    ImageView imvRankedUp;

    //Declaracion del presentador para el detalle de la cancion
    DetailSongPresenter detailSongPresenter;

    private String codigoCancion;
    private String codigoPlaylist;

    public DetailSongFragment() {
        //Inicializacion del presentador para el detalle de la cancion
        detailSongPresenter = new DetailSongPresenterImpl(this);
    }

    /**
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        codigoCancion = getArguments().getString("codigoCancion");
        codigoPlaylist = getArguments().getString("codigoPlaylist");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.detailSong_activity_title)
                .setPositiveButton(R.string.detailSong_button_exit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

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
        final AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog != null) {
            Button possitiveButton = alertDialog.getButton(Dialog.BUTTON_POSITIVE);
            possitiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }
        detailSongPresenter.onShow();
        detailSongPresenter.getInfoSong(codigoCancion);
    }

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
     *
     */
    @Override
    public void getInfoSongError() {
        //TO DO...
    }

    /**
     *
     */
    @Override
    public void rankSongSuccess() {
        dismiss();
    }

    /**
     *
     */
    @Override
    public void rankSongError() {
        //TO DO...
    }

    /**
     *
     */
    @Override
    public void showProgress() {
        pgbDetailSongProgress.setVisibility(View.VISIBLE);
    }

    /**
     *
     */
    @Override
    public void hideProgress() {
        pgbDetailSongProgress.setVisibility(View.GONE);
    }

    /**
     *
     */
    @OnClick(R.id.imvRankedUp)
    public void songRankUp() {
        detailSongPresenter.rankSong(codigoPlaylist, codigoCancion);
    }

}
