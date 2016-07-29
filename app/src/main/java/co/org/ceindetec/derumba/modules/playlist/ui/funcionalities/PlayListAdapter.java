package co.org.ceindetec.derumba.modules.playlist.ui.funcionalities;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.org.ceindetec.derumba.R;
import co.org.ceindetec.derumba.entities.PlayList;

/**
 * Created by Ceindetec02 on 19/07/2016.
 */
public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.ViewHolder> {

    //Declaracion de la variable contactList para almacenar un List de tipo User
    private List<PlayList> playListSongs;

    //Instanciamiento de la interfaz OnItemClickListener
    private OnItemClickListener onItemClickListener;


    /**
     * Constructor de la clase principal
     *
     * @param playListSongs
     * @param onItemClickListener
     */
    public PlayListAdapter(List<PlayList> playListSongs, OnItemClickListener onItemClickListener) {
        this.playListSongs = playListSongs;
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * Metodo implementado de la clase RecyclerView que se ejecuta en la creacion del objeto
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_play_list_song, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Metodo implementado de la clase RecyclerView para el bindeo de los datos del objeto
     *
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        int color;
        PlayList playList = playListSongs.get(position);
        viewHolder.setClickListener(playList, onItemClickListener);

        int posicionCancion = playList.getPosicionCancionPlayList();
        String nombreCancion = playList.getNombreCancionPlayList();

        if (posicionCancion == 1) {
            color = Color.rgb(207, 181, 59);
        } else if (posicionCancion == 2) {
            color = Color.rgb(192, 192, 192);
        } else if (posicionCancion == 3) {
            color = Color.rgb(218, 165, 32);
        } else {
            color = Color.BLACK;
        }

        viewHolder.txvPosicionCancion.setText(Integer.toString(posicionCancion));
        viewHolder.txvPosicionCancion.setTextColor(color);
        viewHolder.txvNombreCancion.setText(nombreCancion);


    }

    /**
     * Metodo implementado de la clase RecyclerView que devuelve el conteo de los datos dentro del objeto
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return playListSongs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.txvPosicionCancion)
        TextView txvPosicionCancion;
        @Bind(R.id.txvNombreCancion)
        TextView txvNombreCancion;

        //Declaracion de una variable de tipo vista para manejar los datos del RecyclerView
        private View view;

        /**
         * Contructor de la clase
         *
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);

            //Bindeo de los controles mediante ButterKnife de la vista que esta en ViewHolder
            ButterKnife.bind(this, itemView);

            this.view = itemView;

        }

        /**
         * Metodo que asigna los escuchadores a la vista
         *
         * @param playListItem
         * @param listener
         */
        private void setClickListener(final PlayList playListItem, final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {

                /**
                 * Metodo implementado de la clase OnClickListener que escucha el click sobre un objeto
                 *
                 * @param v
                 */
                @Override
                public void onClick(View v) {
                    listener.onItemClick(playListItem);
                }
            });
        }
    }
}
