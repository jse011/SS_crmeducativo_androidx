package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.LifecycleImpl;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewpagerAdapter;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.preferent.ReconocimientoRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.CameraReconocimientoRepository;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.local.CameraReconocimientoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.remote.CameraReconocimientoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.DeleteFotoAlumno;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.FotoPocisionExist;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.GetAlumno;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.GetFotoFirebaseStorage;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.GetFotoPreferents;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.SaveFirebaseStorage;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.SaveFotoPocision;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.SaveFotoPreferents;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment.Inicial;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment.InicialView;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment.Send;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment.SendView;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment.Sentimiento1;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment.SentimientoView;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.utils.UtilsDocente;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fotoapparat.Fotoapparat;
import io.fotoapparat.configuration.CameraConfiguration;
import io.fotoapparat.parameter.ScaleType;
import io.fotoapparat.result.BitmapPhoto;
import io.fotoapparat.selector.FlashSelectorsKt;
import io.fotoapparat.selector.LensPositionSelectorsKt;
import io.fotoapparat.selector.ResolutionSelectorsKt;
import io.fotoapparat.view.CameraView;
import io.fotoapparat.view.FocusView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

import static io.fotoapparat.log.LoggersKt.fileLogger;

public class PreviewActivity extends BaseActivity<PreviewView, PreviewPresenter> implements PreviewView, LifecycleImpl.LifecycleListener {
    int currentPos;

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private ViewpagerAdapter fragmentAdapter;
    private Fotoapparat fotoapparat;
    private float zoom;
    private float dist;
    private int flash;
    private boolean isback;
    private CameraView cameraView2;
    private FocusView focusView;

    @Override
    protected String getTag() {
        return "PreviewActivityTAG";
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected PreviewPresenter getPresenter() {
        CameraReconocimientoRepository repository = new CameraReconocimientoRepository(
                new CameraReconocimientoLocalDataSource(InjectorUtils.providePersonaDao()),
                new CameraReconocimientoRemoteDataSource());
        return new PreviewPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetAlumno(repository),
                new SaveFotoPocision(),
                new FotoPocisionExist(),
                new DeleteFotoAlumno(),
                new SaveFirebaseStorage(),
                new GetFotoFirebaseStorage(),
                new SaveFotoPreferents(new ReconocimientoRepositoryImpl(this)),
                new GetFotoPreferents(new ReconocimientoRepositoryImpl(this)));
    }

    @Override
    protected PreviewView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_reconocimiento);
        ButterKnife.bind(this);
        setupViewPager();
        setupCamera();
    }

    private void setupCamera() {
        cameraView2 = (CameraView)findViewById(R.id.camera_view);
        focusView = (FocusView)findViewById(R.id.focusView);
        fotoapparat = Fotoapparat.with(this)
                .into(cameraView2)
                .focusView(focusView)
                .previewScaleType(ScaleType.CenterCrop)  // we want the preview to fill the view
                 .photoResolution(ResolutionSelectorsKt.highestResolution())   // we want to have the biggest photo possible
                .lensPosition(LensPositionSelectorsKt.back())      // we want back camera
                .build();
        zoom = 0.0f;
        flash = 0;
        isback=false;
        cameraView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getPointerCount() > 1) {

                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_POINTER_DOWN:
                            dist = UtilsDocente.getFingerSpacing(event);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            float maxZoom = 1f;

                            float newDist = UtilsDocente.getFingerSpacing(event);
                            if (newDist > dist) {
                                //zoom in
                                if (zoom < maxZoom)
                                    zoom = zoom + 0.01f;
                            } else if (newDist < dist) {
                                //zoom out
                                if (zoom > 0)
                                    zoom = zoom - 0.01f;
                            }
                            dist = newDist;
                            fotoapparat.setZoom(zoom);
                            break;
                    }
                }
                return true;
            }
        });
        fotoapparat.start();
        fotoapparat.updateConfiguration(CameraConfiguration.builder().flash(FlashSelectorsKt.autoRedEye()).build());

        /*cameraView(Gesture.PINCH, GestureAction.ZOOM); // Pinch to zoom!
        cameraView.mapGesture(Gesture.TAP, GestureAction.FOCUS); // Tap to focus!
        //cameraView.mapGesture(Gesture.LONG_TAP, GestureAction.CAPTURE); // Long tap to shoot!
        cameraView.setJpegQuality(80);
        cameraView.addCameraListener(new CameraListener() {
            private int orientation = 0;

            @Override
            public void onPictureTaken(byte[] jpeg) {
                super.onPictureTaken(jpeg);
                presenter.onPictureTaken(jpeg, orientation);
            }

            @Override
            public void onCameraOpened(CameraOptions options) {
                super.onCameraOpened(options);
                if (cameraView == null) return;
                if (cameraView.getFlash() == Flash.ON) {
                    presenter.flashOn();

                } else if (cameraView.getFlash() == Flash.OFF) {
                    presenter.flashOff();

                } else if (cameraView.getFlash() == Flash.AUTO) {
                    presenter.flashAuto();

                }
            }

            @Override
            public void onOrientationChanged(int orientation) {
                super.onOrientationChanged(orientation);
                this.orientation = orientation;
                Log.d(getTag(), "orientation: " +orientation);
            }
        });*/

    }

    private void setupViewPager() {

     fragmentAdapter = new ViewpagerAdapter(getSupportFragmentManager(), 5, this);
        fragmentAdapter.addFragment(new Inicial(), "Inicial");
        fragmentAdapter.addFragment(new Sentimiento1(), "sentimiento 1");
        fragmentAdapter.addFragment(new Sentimiento1(), "sentimiento 2");
        fragmentAdapter.addFragment(new Sentimiento1(), "sentimiento 3");
        fragmentAdapter.addFragment(new Sentimiento1(), "sentimiento 4");
        fragmentAdapter.addFragment(new Sentimiento1(), "sentimiento 5");
        fragmentAdapter.addFragment(new Send(), "Send");
        viewPager.setOffscreenPageLimit(7);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // This is paused fragment.
                Fragment pausedFragment = fragmentAdapter.getItem(currentPos);
                if(pausedFragment instanceof SentimientoView){
                    ((SentimientoView)pausedFragment).onPauseViewPager();
                }
                // update current position value
                currentPos = position;

                // This is resumed fragment
                Fragment resumedFragment = fragmentAdapter.getItem(currentPos);
                if(resumedFragment instanceof SentimientoView){
                    ((SentimientoView)resumedFragment).onResumeViewPager();
                }

                if(resumedFragment instanceof SentimientoView){
                    presenter.onResumedFragment(((SentimientoView)resumedFragment), position);
                }else if(resumedFragment instanceof SendView){
                    presenter.onResumedSendView(((SendView)resumedFragment));
                }else if(resumedFragment instanceof InicialView){
                    presenter.onResumedInicialView((InicialView)resumedFragment);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setAdapter(fragmentAdapter);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onChildsFragmentViewCreated() {

    }

    @Override
    public void showMessage(CharSequence error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fotoapparat.start();
    }


    @Override
    protected void onPause() {
        fotoapparat.stop();
        super.onPause();
    }

    @Override
    public void onFragmentViewCreated(Fragment f, View view, Bundle savedInstanceState) {

    }

    @Override
    public void onFragmentResumed(Fragment f) {

    }

    @Override
    public void onFragmentViewDestroyed(Fragment f) {

    }

    @Override
    public void onFragmentActivityCreated(Fragment f, Bundle savedInstanceState) {
        if(f instanceof SentimientoView){
            ((SentimientoView)f).setPresentar(presenter);
        }
        if(f instanceof SendView){
            ((SendView)f).setPresentar(presenter);
        }
        if(f instanceof InicialView){
            ((InicialView)f).setPresenter(presenter);
            presenter.setAtachInicialView(((InicialView)f));
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        fotoapparat.start ();
        fotoapparat.stop ();
        fotoapparat.start ();
    }


    @Override
    public void toggleFacing() {
       CameraConfiguration cameraConfiguration = new CameraConfiguration();
        if (isback) {
            isback = false;
            fotoapparat.switchTo(LensPositionSelectorsKt.front(), cameraConfiguration);
        } else {
            isback = true;
            fotoapparat.switchTo(LensPositionSelectorsKt.back(), cameraConfiguration);
        }
    }

    @Override
    public void onViewFlash() {

        if(flash==0){
            flash = 1;
            presenter.flashOn();
            fotoapparat.updateConfiguration(CameraConfiguration.builder().flash(FlashSelectorsKt.on()).build());
        }else if(flash==1){
            flash = 2;
            presenter.flashAuto();
            fotoapparat.updateConfiguration(CameraConfiguration.builder().flash(FlashSelectorsKt.autoRedEye()).build());
        }else if(flash == 2){
            flash=0;
            presenter.flashOff();
            fotoapparat.updateConfiguration(CameraConfiguration.builder().flash(FlashSelectorsKt.off()).build());
        }
    }

    @Override
    public void capturePicture() {
        fotoapparat.takePicture().toBitmap().transform(new Function1<BitmapPhoto, Bitmap>() {
            @Override
            public Bitmap invoke(BitmapPhoto bitmapPhoto) {
                Log.d("takePicture", bitmapPhoto.toString());
                File photo = UtilsDocente.writeImage(bitmapPhoto.bitmap);
                Log.e("my pick saved","    ->  " + photo.length() / 1024);
                presenter.onPictureTaken(photo.getPath(), -bitmapPhoto.rotationDegrees);
                Log.d("takePicture", "Finish");
                return bitmapPhoto.bitmap;
            }
        }).whenAvailable(new Function1<Bitmap, Unit>() {
            @Override
            public Unit invoke(Bitmap bitmap) {
                if (bitmap != null) {
                    Log.d("takePicture", "whenAvailable");
                    synchronized (bitmap) {
                        //Log.e("my pick saved", bitmap.toString() + "    ->  " + photo.length() / 1024);
                        //Bitmap bitmap = UtilsDocente.rotate(bitmapPhoto.bitmap, -bitmapPhoto.rotationDegrees);

                        Log.d("takePicture", "synchronized");
                    }
                }
                return null;
            }
        });
    }

    @Override
    public void changePage(int page) {
        viewPager.setCurrentItem(page);
    }

    @Override
    public void exitActivity() {
        finish();
    }
}
