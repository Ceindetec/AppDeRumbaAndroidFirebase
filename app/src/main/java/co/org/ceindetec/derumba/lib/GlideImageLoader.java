package co.org.ceindetec.derumba.lib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * Created by Ceindetec02 on 13/07/2016.
 */
public class GlideImageLoader implements ImageLoader {

    //Declaracion de una variable RequestManager propia de la libreria Glide
    private RequestManager requestManager;

    /**
     * Constructor de la clase GlideImageLoader
     *
     * @param context
     */
    public GlideImageLoader(Context context) {
        this.requestManager = Glide.with(context);
    }

    /**
     * Metodo implementado de la interfaz ImageLoader
     *
     * @param imgProfileImage
     * @param url
     */
    @Override
    public void load(ImageView imgProfileImage, String url) {
        requestManager.load(url).into(imgProfileImage);
    }
}
