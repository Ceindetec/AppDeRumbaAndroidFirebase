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
import android.widget.TextView;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.org.ceindetec.derumba.R;
import co.org.ceindetec.derumba.modules.detailsong.DetailSongPresenter;
import co.org.ceindetec.derumba.modules.detailsong.DetailSongPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailSongFragment extends DialogFragment implements DetailSongView, DialogInterface.OnShowListener {


    @Bind(R.id.txvDetailSongTitle)
    TextView txvDetailSongTitle;
    @Bind(R.id.txvDetailSongArtist)
    TextView txvDetailSongArtist;
    @Bind(R.id.txvDetailSongDuration)
    TextView txvDetailSongDuration;
    @Bind(R.id.pgbAddContactProgress)
    ProgressBar pgbAddContactProgress;

    DetailSongPresenter detailSongPresenter;

    public DetailSongFragment() {
        detailSongPresenter = new DetailSongPresenterImpl(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

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
    }

    @Override
    public void showInput() {

    }

    @Override
    public void hideInput() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void songRanked() {

    }

    @Override
    public void SongNoRanked() {

    }

}
