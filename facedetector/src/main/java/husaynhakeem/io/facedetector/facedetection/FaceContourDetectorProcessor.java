package husaynhakeem.io.facedetector.facedetection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;


import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import husaynhakeem.io.facedetector.FaceBoundsOverlay;
import husaynhakeem.io.facedetector.FaceBoundsOverlayHandler;
import husaynhakeem.io.facedetector.models.FaceBounds;
import husaynhakeem.io.facedetector.models.Facing;
import husaynhakeem.io.facedetector.models.Frame;
import husaynhakeem.io.facedetector.models.Orientation;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;

public class FaceContourDetectorProcessor extends VisionProcessorBase<List<FirebaseVisionFace>> {

    private static final String TAG = "FaceContourDetectorProc";

    private FaceBoundsOverlayHandler faceBoundsOverlayHandler = new FaceBoundsOverlayHandler();

    private final FirebaseVisionFaceDetector detector;

    private FaceBoundsOverlay faceBoundsOverlay;

    private static final float MIN_FACE_SIZE = 0.15f;

    public FaceContourDetectorProcessor(FaceBoundsOverlay faceBoundsOverlay) {
        this.faceBoundsOverlay = faceBoundsOverlay;
        FirebaseVisionFaceDetectorOptions options =
                new FirebaseVisionFaceDetectorOptions.Builder()
                        .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)
                        .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                        .setClassificationMode(FirebaseVisionFaceDetectorOptions.NO_CLASSIFICATIONS)
                        .setMinFaceSize(MIN_FACE_SIZE)
                        .enableTracking()
                        .build();

        detector = FirebaseVision.getInstance().getVisionFaceDetector(options);
    }
    // Se elige con que contornos se debe de correr la deteccion de rostro


    /**
     * Metodo para detener la camara
     */
    @Override
    public void stop() {
         try {
             detector.close();
         } catch (IOException e) {
             Log.e(TAG, "Exception thrown while trying to close Face Contour Detector: " + e);
         }
     }

    protected Task<List<FirebaseVisionFace>> detectInImage(FirebaseVisionImage image) {
        return detector.detectInImage(image);
    }

    @Override
    protected void onProcess(@NonNull Frame frame) {
        updateOverlayAttributes(frame);
    }
    private void updateOverlayAttributes(Frame frame) {
        faceBoundsOverlayHandler.updateOverlayAttributes(frame.getSize().getWidth(),
                frame.getSize().getHeight(),
                frame.getRotation(),
                frame.isCameraFacingBack(),
                new Function4<Float, Float, Orientation, Facing, Unit>() {
                    @Override
                    public Unit invoke(Float newWidth, Float newHeight, Orientation newOrientation, Facing facing) {
                        faceBoundsOverlay.setCameraPreviewWidth(newWidth);
                        faceBoundsOverlay.setCameraPreviewHeight(newHeight);
                        faceBoundsOverlay.setCameraOrientation(newOrientation);
                        faceBoundsOverlay.setCameraFacing(facing);
                        return null;
                    }
                });

    }

    @Override
    protected void onSuccess(@NonNull List<FirebaseVisionFace> results) {
        faceBoundsOverlay.updateFaces(convertToListOfFaceBounds(results));
    }

    @Override
    protected void onError(@NonNull Exception exception) {
        Log.e(TAG, "Face detection failed " + exception);
    }

    private List<FaceBounds>  convertToListOfFaceBounds(List<FirebaseVisionFace> faces){
        List<FaceBounds> faceBoundsList = new ArrayList<>();
        for (FirebaseVisionFace firebaseVisionFace : faces){
            faceBoundsList.add(new FaceBounds(firebaseVisionFace.getTrackingId(),firebaseVisionFace.getBoundingBox()));
        }
        return faceBoundsList;
    }

}