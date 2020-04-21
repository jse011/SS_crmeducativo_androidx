package husaynhakeem.io.facedetector.common;

import com.google.firebase.ml.common.FirebaseMLException;

import java.nio.ByteBuffer;

public interface VisionImageProcessor {

    /**
     * Para procesar los frame de la camara
     */
    void process(husaynhakeem.io.facedetector.models.Frame frame)
            throws FirebaseMLException;

    /**
     * Para una buen cierre de la camara por parte de la aplicación
     */
    void stop();

}
