package com.consultoraestrategia.ss_crmeducativo.syncJobs;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by @stevecampos on 18/12/2017.
 */

public class MyJobService extends JobService {
    private static final String TAG = MyJobService.class.getSimpleName();

    @Override
    public boolean onStartJob(JobParameters job) {
        Log.d(TAG, "onStartJob");
        codeYouWantToRun(job);
        return true; // Answers the question: "Is there still work going on?"
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        Log.d(TAG, "onStopJob");
        return true; // Answers the question: "Should this job be retried?"
    }


    public void codeYouWantToRun(final JobParameters parameters) {
        try {

            Log.d(TAG, "codeYouWantToRun: " + "jobStarted");
            //This task takes 2 seconds to complete.
            Thread.sleep(2000);

            Log.d(TAG, "codeYouWantToRun: " + "jobFinished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //Tell the framework that the job has completed and doesnot needs to be reschedule
            jobFinished(parameters, true);
        }

    }
}