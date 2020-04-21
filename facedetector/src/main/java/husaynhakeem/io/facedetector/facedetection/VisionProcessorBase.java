
package husaynhakeem.io.facedetector.facedetection;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;

import husaynhakeem.io.facedetector.common.VisionImageProcessor;
import husaynhakeem.io.facedetector.models.Frame;

public abstract class VisionProcessorBase<T> implements VisionImageProcessor {

    private static final int RIGHT_ANGLE = 90;

    @GuardedBy("this")
    private Frame latestFrame;
    @GuardedBy("this")
    private Frame processingFrame;

    public VisionProcessorBase() {
    }

    @Override
    public synchronized void process(
            husaynhakeem.io.facedetector.models.Frame frame) {
        //latestImage = data;
        latestFrame = frame;
        if (processingFrame == null) {
            processLatestImage();
        }
    }

    private synchronized void processLatestImage() {
        processingFrame = latestFrame;//processingImage
        latestFrame = null;
        if (processingFrame != null) {
            processImage(processingFrame);
        }
    }

    private synchronized void processImage(husaynhakeem.io.facedetector.models.Frame frame) {
        FirebaseVisionImageMetadata metadata =
        new FirebaseVisionImageMetadata.Builder()
                .setWidth(frame.getSize().getWidth())
                .setHeight(frame.getSize().getHeight())
                .setFormat(frame.getFormat())
                .setRotation(frame.getRotation() / RIGHT_ANGLE)
                .build();
        onProcess(frame);
        detectInVisionImage(FirebaseVisionImage.fromByteArray(frame.getData(), metadata));
    }

    private void detectInVisionImage(FirebaseVisionImage image) {
        detectInImage(image)
                .addOnSuccessListener(new OnSuccessListener<T>() {
                    @Override
                    public void onSuccess(T results) {
                        VisionProcessorBase.this.onSuccess(results);
                    }}).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                        }
                    });
    }

    protected abstract Task<T> detectInImage(FirebaseVisionImage image);

    protected abstract void onProcess(
            @NonNull husaynhakeem.io.facedetector.models.Frame frame);

    protected abstract void onSuccess(
            @NonNull T results);

    protected abstract void onError(
            @NonNull Exception exception);
}

