package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.camera;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.PointF;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.CameraReconocimientoRepository;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.local.CameraReconocimientoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.remote.CameraReconocimientoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.GetPersona;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.SaveImgen;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraOptions;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.Facing;
import com.otaliastudios.cameraview.Flash;
import com.otaliastudios.cameraview.Frame;
import com.otaliastudios.cameraview.FrameProcessor;
import com.otaliastudios.cameraview.Gesture;
import com.otaliastudios.cameraview.GestureAction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import husaynhakeem.io.facedetector.FaceBoundsOverlay;
import husaynhakeem.io.facedetector.FaceDetector;
import husaynhakeem.io.facedetector.models.Size;

public class CameraReconocimientoActivity extends BaseActivity<CameraReconocimientoView, CameraReconocimientoPresenter> implements CameraReconocimientoView {

    @BindView(R.id.cameraView)
    CameraView cameraView;
    @BindView(R.id.facesBoundsOverlay)
    FaceBoundsOverlay facesBoundsOverlay;
    @BindView(R.id.imgFlashOnOff)
    ImageView imgFlashOnOff;
    @BindView(R.id.text_progress)
    TextView textProgress;
    @BindView(R.id.pgr_progress)
    ProgressBar pgrProgress;
    @BindView(R.id.btn_cancelar)
    Button btnCancelar;
    @BindView(R.id.contenEnviar)
    ConstraintLayout contenEnviar;
    @BindView(R.id.rlCaptureOption)
    RelativeLayout rlCaptureOption;

    private int mOrientation = 0;

    private String TAG = "FaceDetection";
    private FaceDetector mInstance;


    private FaceDetector getFaceDetector() {
        if (mInstance == null) {
            mInstance = new FaceDetector(facesBoundsOverlay);
        }
        return mInstance;
    }


    @Override
    protected String getTag() {
        return "CameraReconocimientoActivityTAG";
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected CameraReconocimientoPresenter getPresenter() {
        CameraReconocimientoRepository repository = new CameraReconocimientoRepository(
                new CameraReconocimientoLocalDataSource(InjectorUtils.providePersonaDao()),
                new CameraReconocimientoRemoteDataSource());

        return new CameraReconocimientoImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetPersona(repository),
                new SaveImgen());
    }

    @Override
    protected CameraReconocimientoView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_detection_camera);
        ButterKnife.bind(this);
        setupCamera();
    }

    @Override
    protected ViewGroup getRootLayout() {
        return cameraView;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    long tiempo = 0;
    private void setupCamera() {
        facesBoundsOverlay.clear();
        cameraView.mapGesture(Gesture.PINCH, GestureAction.ZOOM); // Pinch to zoom!
        cameraView.mapGesture(Gesture.TAP, GestureAction.FOCUS); // Tap to focus!
        //cameraView.mapGesture(Gesture.LONG_TAP, GestureAction.CAPTURE); // Long tap to shoot!
        cameraView.setJpegQuality(80);
        cameraView.addFrameProcessor(new FrameProcessor() {
            @Override
            public void process(@NonNull Frame frame) {

                long resta =  frame.getTime()-tiempo;
                Log.d(TAG,"resta: " + resta);
                if(tiempo==0||resta >= 1000){
                    tiempo = frame.getTime();
                    if (frame.getSize() != null) {
                        getFaceDetector().process(new husaynhakeem.io.facedetector.models.Frame(
                                frame.getData(),
                                frame.getRotation(),
                                new Size(frame.getSize().getWidth(), frame.getSize().getHeight()),
                                frame.getFormat(),
                                cameraView.getFacing() == Facing.BACK
                        ));
                    }
                }
            }
        });

        cameraView.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(byte[] jpeg) {
                super.onPictureTaken(jpeg);
                facesBoundsOverlay.clear();
                cameraView.destroy();
                cameraView = null;
                contenEnviar.setVisibility(View.VISIBLE);
                rlCaptureOption.setVisibility(View.GONE);



                presenter.onPictureTaken(jpeg, mOrientation);

                Log.d(TAG, "onPictureTaken");
            }

            @Override
            public void onCameraOpened(CameraOptions options) {
                super.onCameraOpened(options);
                if(cameraView==null)return;
                facesBoundsOverlay.clear();
                if (cameraView.getFlash() == Flash.ON) {
                    imgFlashOnOff.setImageResource(R.drawable.ic_flash_on);
                } else if (cameraView.getFlash() == Flash.OFF) {
                    imgFlashOnOff.setImageResource(R.drawable.ic_flash_off);
                } else if (cameraView.getFlash() == Flash.AUTO) {
                    imgFlashOnOff.setImageResource(R.drawable.ic_flash_auto);
                }
            }

            @Override
            public void onCameraClosed() {
                super.onCameraClosed();
                getFaceDetector().reset();
                facesBoundsOverlay.clear();
            }

            @Override
            public void onOrientationChanged(int orientation) {
                super.onOrientationChanged(orientation);
                mOrientation = orientation;
                facesBoundsOverlay.clear();
                Log.d(TAG, "onOrientationChanged: " + orientation);
            }

            @Override
            public void onFocusStart(PointF point) {
                super.onFocusStart(point);
                facesBoundsOverlay.clear();
            }

            @Override
            public void onFocusEnd(boolean successful, PointF point) {
                super.onFocusEnd(successful, point);
                facesBoundsOverlay.clear();
            }

            @Override
            public void onZoomChanged(float newValue, float[] bounds, PointF[] fingers) {
                super.onZoomChanged(newValue, bounds, fingers);
                facesBoundsOverlay.clear();
            }

            @Override
            public void onExposureCorrectionChanged(float newValue, float[] bounds, PointF[] fingers) {
                super.onExposureCorrectionChanged(newValue, bounds, fingers);
                facesBoundsOverlay.clear();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        facesBoundsOverlay.clear();
        if(cameraView!=null)cameraView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        facesBoundsOverlay.clear();
        if(cameraView!=null)cameraView.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        facesBoundsOverlay.clear();
        if(cameraView!=null)cameraView.destroy();
    }

    @OnClick(R.id.imgChangeCamera)
    public void onViewClicked() {
        if(cameraView!=null)cameraView.toggleFacing();
    }

    @OnClick(R.id.imgFlashOnOff)
    public void onViewFlash() {
        if(cameraView==null)return;
        if (cameraView.getFlash() == Flash.AUTO) {
            cameraView.setFlash(Flash.ON);
            imgFlashOnOff.setImageResource(R.drawable.ic_flash_on);
        } else if (cameraView.getFlash() == Flash.ON) {
            cameraView.setFlash(Flash.OFF);
            imgFlashOnOff.setImageResource(R.drawable.ic_flash_off);
        } else if (cameraView.getFlash() == Flash.OFF) {
            cameraView.setFlash(Flash.AUTO);
            imgFlashOnOff.setImageResource(R.drawable.ic_flash_auto);
        }
    }

    @OnClick(R.id.imgCapture)
    public void onCaptureClicked() {
        if(cameraView!=null)cameraView.capturePicture();
    }

    @Override
    public void showMessage(CharSequence error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCamaraPersona(String nombre) {
        Log.d(getTag(), "nombre " + nombre);
        facesBoundsOverlay.setNameLabel(nombre);
    }

    @Override
    public void showBarProgress(int progress) {
        pgrProgress.setProgress(progress);
        textProgress.setText(String.valueOf(progress));
    }

    @Override
    public void successProgress() {
        finish();
    }

    @Override
    public void errorProgress() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(R.string.msg_confirmacionTitlle);
        builder.setMessage("Fallo el envío de los rasgos faciales");
        builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.onClickReintentarEnvio();
                dialogInterface.cancel();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                finish();
            }
        });
        //Create AdapterExample
        builder.create().show();
    }


}
