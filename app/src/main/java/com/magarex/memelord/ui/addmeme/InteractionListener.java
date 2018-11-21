package com.magarex.memelord.ui.addmeme;

import com.magarex.memelord.data.remote.OperationStatus;

/**
 * Created by sahil on 19/11/18.
 **/
public interface InteractionListener {

    void saveMeme(String finalImage);

    void postMeme(OperationStatus status);

}
