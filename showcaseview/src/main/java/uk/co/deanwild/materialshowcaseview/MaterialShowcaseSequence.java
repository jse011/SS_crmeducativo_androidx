package uk.co.deanwild.materialshowcaseview;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import android.view.View;

import java.util.LinkedList;
import java.util.Queue;


public class MaterialShowcaseSequence implements IDetachedListener {

    PrefsManager mPrefsManager;
    Queue<MaterialShowcaseView> mShowcaseQueue;
    private boolean mSingleUse = false;
    Activity mActivity2;
    Fragment fragment;
    private ShowcaseConfig mConfig;
    private int mSequencePosition = 0;

    private OnSequenceItemShownListener mOnItemShownListener = null;
    private OnSequenceItemDismissedListener mOnItemDismissedListener = null;

    public MaterialShowcaseSequence(Activity activity) {
        mActivity2 = activity;
        mShowcaseQueue = new LinkedList<>();
    }
    public MaterialShowcaseSequence(Fragment fragment) {
        this.fragment = fragment;
        mShowcaseQueue = new LinkedList<>();
    }

    public MaterialShowcaseSequence(Activity activity, String sequenceID) {
        this(activity);
        this.singleUse(sequenceID);
    }

    public MaterialShowcaseSequence(Fragment fragment, String sequenceID) {
        this(fragment);
        this.singleUse(sequenceID);
    }

    public MaterialShowcaseSequence addSequenceItem(View targetView, String content, String dismissText) {
        addSequenceItem(targetView,0, "", content, dismissText);
        return this;
    }

    public MaterialShowcaseSequence addSequenceItem(View targetView,int type, String content, String dismissText) {
        addSequenceItem(targetView,type, "", content, dismissText);
        return this;
    }


    public MaterialShowcaseSequence addSequenceItem(View targetView,int type, String title, String content, String dismissText) {

        MaterialShowcaseView sequenceItem;
        if(mActivity2!=null){
            MaterialShowcaseView.Builder builder = new MaterialShowcaseView.Builder(mActivity2)
                    .renderOverNavigationBar()
                    .setTarget(targetView)
                    .setTitleText(title)
                    .setDismissText(dismissText)
                    .setContentText(content)
                    .setSequence(true);
            switch (type){
                case  MaterialShowcaseView.Builder.CIRCLE_SHAPE:
                    builder.withCircleShape();
                    break;
                case  MaterialShowcaseView.Builder.NO_SHAPE:
                    builder.withoutShape();
                    break;
                case  MaterialShowcaseView.Builder.OVAL_SHAPE:
                    builder.withOvalShape();
                    break;
                case  MaterialShowcaseView.Builder.RECTANGLE_SHAPE:
                    builder.withRectangleShape();
                    break;
            }

            sequenceItem = builder.build();
        }else {
            MaterialShowcaseView.Builder builder = new MaterialShowcaseView.Builder(fragment)
                    .renderOverNavigationBar()
                    .setTarget(targetView)
                    .setTitleText(title)
                    .setDismissText(dismissText)
                    .setContentText(content)
                    .setSequence(true);

            switch (type){
                case  MaterialShowcaseView.Builder.CIRCLE_SHAPE:
                    builder.withCircleShape();
                    break;
                case  MaterialShowcaseView.Builder.NO_SHAPE:
                    builder.withoutShape();
                    break;
                case  MaterialShowcaseView.Builder.OVAL_SHAPE:
                    builder.withOvalShape();
                    break;
                case  MaterialShowcaseView.Builder.RECTANGLE_SHAPE:
                    builder.withRectangleShape();
                    break;
            }

            sequenceItem = builder.build();
        }


        if (mConfig != null) {
            sequenceItem.setConfig(mConfig);
        }

        mShowcaseQueue.add(sequenceItem);
        return this;
    }

    public MaterialShowcaseSequence addSequenceItem(MaterialShowcaseView sequenceItem) {

        if (mConfig != null) {
            sequenceItem.setConfig(mConfig);
        }

        mShowcaseQueue.add(sequenceItem);
        return this;
    }

    public MaterialShowcaseSequence singleUse(String sequenceID) {
        mSingleUse = true;
        if(mActivity2!=null){
            mPrefsManager = new PrefsManager(mActivity2, sequenceID);
        }else {
            mPrefsManager = new PrefsManager(fragment.getContext(), sequenceID);
        }

        return this;
    }

    public void setOnItemShownListener(OnSequenceItemShownListener listener) {
        this.mOnItemShownListener = listener;
    }

    public void setOnItemDismissedListener(OnSequenceItemDismissedListener listener) {
        this.mOnItemDismissedListener = listener;
    }

    public boolean hasFired() {

        if (mPrefsManager.getSequenceStatus() == PrefsManager.SEQUENCE_FINISHED) {
            return true;
        }

        return false;
    }

    public void start() {

        /**
         * Check if we've already shot our bolt and bail out if so         *
         */
        if (mSingleUse) {
            if (hasFired()) {
                return;
            }

            /**
             * See if we have started this sequence before, if so then skip to the point we reached before
             * instead of showing the user everything from the start
             */
            mSequencePosition = mPrefsManager.getSequenceStatus();

            if (mSequencePosition > 0) {
                for (int i = 0; i < mSequencePosition; i++) {
                    mShowcaseQueue.poll();
                }
            }
        }


        // do start
        if (mShowcaseQueue.size() > 0)
            showNextItem();
    }

    private void showNextItem() {

        boolean isfinish;
        if(mActivity2!=null){
            isfinish = mActivity2.isFinishing();
        }else {
            isfinish = fragment.isRemoving();
        }

        if (mShowcaseQueue.size() > 0 && !isfinish) {
            MaterialShowcaseView sequenceItem = mShowcaseQueue.remove();
            sequenceItem.setDetachedListener(this);
            if(mActivity2!=null){
                sequenceItem.show(mActivity2);
            }else {
                sequenceItem.show(fragment);
            }
            if (mOnItemShownListener != null) {
                mOnItemShownListener.onShow(sequenceItem, mSequencePosition);
            }
        } else {
            /**
             * We've reached the end of the sequence, save the fired state
             */
            if (mSingleUse) {
                mPrefsManager.setFired();
            }
        }
    }

    private void skipTutorial() {

        mShowcaseQueue.clear();
        boolean isfinish;
        if(mActivity2!=null){
            isfinish = mActivity2.isFinishing();
        }else {
            isfinish = fragment.isRemoving();
        }
        if (mShowcaseQueue.size() > 0 && !isfinish) {
            MaterialShowcaseView sequenceItem = mShowcaseQueue.remove();
            sequenceItem.setDetachedListener(this);
            if(mActivity2!=null){
                sequenceItem.show(mActivity2);
            }else {
                sequenceItem.show(fragment);
            }
            if (mOnItemShownListener != null) {
                mOnItemShownListener.onShow(sequenceItem, mSequencePosition);
            }
        } else {
            /**
             * We've reached the end of the sequence, save the fired state
             */
            if (mSingleUse) {
                mPrefsManager.setFired();
            }
        }
    }


    @Override
    public void onShowcaseDetached(MaterialShowcaseView showcaseView, boolean wasDismissed, boolean wasSkipped) {

        showcaseView.setDetachedListener(null);

        /**
         * We're only interested if the showcase was purposefully dismissed
         */
        if (wasDismissed) {

            if (mOnItemDismissedListener != null) {
                mOnItemDismissedListener.onDismiss(showcaseView, mSequencePosition);
            }

            /**
             * If so, update the prefsManager so we can potentially resume this sequence in the future
             */
            if (mPrefsManager != null) {
                mSequencePosition++;
                mPrefsManager.setSequenceStatus(mSequencePosition);
            }

            showNextItem();
        }

        if(wasSkipped){
            if (mOnItemDismissedListener != null) {
                mOnItemDismissedListener.onDismiss(showcaseView, mSequencePosition);
            }

            /**
             * If so, update the prefsManager so we can potentially resume this sequence in the future
             */
            if (mPrefsManager != null) {
                mSequencePosition++;
                mPrefsManager.setSequenceStatus(mSequencePosition);
            }

            skipTutorial();
        }
    }

    public void setConfig(ShowcaseConfig config) {
        this.mConfig = config;
    }

    public interface OnSequenceItemShownListener {
        void onShow(MaterialShowcaseView itemView, int position);
    }

    public interface OnSequenceItemDismissedListener {
        void onDismiss(MaterialShowcaseView itemView, int position);
    }

}
