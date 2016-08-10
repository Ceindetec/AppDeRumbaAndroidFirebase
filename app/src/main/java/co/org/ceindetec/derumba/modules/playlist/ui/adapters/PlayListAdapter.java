package co.org.ceindetec.derumba.modules.playlist.ui.adapters;

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
import co.org.ceindetec.derumba.entities.PlaylistSong;

/**
 * Created by Ceindetec02 on 19/07/2016.
 */
public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.ViewHolder> {

    //Declaracion de la variable contactList para almacenar un List de tipo User
    private List<PlaylistSong> playlistSongList;

    //Instanciamiento de la interfaz OnItemClickListener
    private OnItemClickListener onItemClickListener;


    /**
     * Constructor de la clase principal
     *
     * @param playlistSongList
     * @param onItemClickListener
     */
    public PlayListAdapter(List<PlaylistSong> playlistSongList, OnItemClickListener onItemClickListener) {
        this.playlistSongList = playlistSongList;
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
        List<PlaylistSong> playlistSongListSort = sortPlaylist(playlistSongList);
        PlaylistSong playlistSong = playlistSongListSort.get(position);
        viewHolder.setClickListener(playlistSong, onItemClickListener);

        String nombreCancion = playlistSong.getNombreCancion();
        String likesCancion = Integer.toString(playlistSong.CountLikes());

        if (position == 0) {
            color = Color.rgb(207, 181, 59);
        } else if (position == 1) {
            color = Color.rgb(192, 192, 192);
        } else if (position == 2) {
            color = Color.rgb(218, 165, 32);
        } else {
            color = Color.BLACK;
        }
        viewHolder.txvPosicionCancion.setText(Integer.toString(position + 1));
        viewHolder.txvPosicionCancion.setTextColor(color);
        viewHolder.txvNombreCancion.setText(nombreCancion);
        viewHolder.txvLikesCancion.setText(likesCancion);


    }

    /**
     * Metodo implementado de la clase RecyclerView que devuelve el conteo de los datos dentro del objeto
     *
     * @return
     */
    @Override
    public int getItemCount() {
        try {
            return playlistSongList.size();
        } catch (Exception e) {
            return 0;
        }

    }

    /**
     * Metodo implementado de la clase RecyclerView para agregar items al Recycler View
     *
     * @param songPlayList
     */
    public void add(PlaylistSong songPlayList) {
        if (!playlistSongList.contains(songPlayList)) {
            playlistSongList.add(songPlayList);
            notifyDataSetChanged();
        }
    }

    /**
     * Metodo implementado de la clase RecyclerView para actualizar items del Recycler View
     *
     * @param songPlayList
     */
    public void update(PlaylistSong songPlayList) {
        int index = playlistSongList.indexOf(foundSongPlaylist(songPlayList));
        if (index != -1) {
            playlistSongList.set(index, songPlayList);
            notifyDataSetChanged();
        } else {
            add(songPlayList);
        }
    }

    /**
     * Metodo implementado de la clase RecyclerView para eñiminar items del Recycler View
     *
     * @param songPlayList
     */
    public void remove(PlaylistSong songPlayList) {
        if (!playlistSongList.contains(songPlayList)) {
            playlistSongList.remove(songPlayList);
            notifyDataSetChanged();
        }
    }

    /**
     * Metodo para encontrar la cancion dentro de la lista de canciones basado en el nombre
     *
     * @param songPlayList
     * @return
     */
    private PlaylistSong foundSongPlaylist(PlaylistSong songPlayList) {
        int i = 0;
        boolean aux = true;

        while (i < playlistSongList.size()) {
            if (playlistSongList.get(i).getCodigoCancion().equals(songPlayList.getCodigoCancion())) {
                break;
            }
            i++;
        }
        return playlistSongList.get(i);
    }

    /**
     * Metodo que ordena las canciones de la playlist de mayor a menor
     *
     * @param playlistSongList
     * @return
     */
    private List<PlaylistSong> sortPlaylist(List<PlaylistSong> playlistSongList) {

        boolean aux = true;
        int j = 0;
        PlaylistSong playlistSongTemp = null;

        while (aux) {
            aux = false;
            j++;
            for (int i = 0; i < playlistSongList.size() - j; i++) {
                if (playlistSongList.get(i).CountLikes() < playlistSongList.get(i + 1).CountLikes()) {
                    playlistSongTemp = playlistSongList.get(i);
                    playlistSongList.set(i, playlistSongList.get(i + 1));
                    playlistSongList.set(i + 1, playlistSongTemp);
                    aux = true;
                }
            }
        }

        return playlistSongList;
    }


    /**
     *
     */
    public class ViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.txvPosicionCancion)
        TextView txvPosicionCancion;
        @Bind(R.id.txvNombreCancion)
        TextView txvNombreCancion;
        @Bind(R.id.txvLikesCancion)
        TextView txvLikesCancion;

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
        private void setClickListener(final PlaylistSong playListItem, final OnItemClickListener listener) {
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
