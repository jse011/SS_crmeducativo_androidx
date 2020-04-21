package com.consultoraestrategia.ss_crmeducativo.services.loginService.util;

import android.content.Context;
import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.services.loginService.ui.LoginJobService;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

/**
 * Created by SCIEV on 13/06/2018.
 */

public class LoginImportarService {

    private static final String JOBSERVICE_LOGIN = "jobServiceLogin";

    public static void jobServiceLogin(Context context){

        Bundle jobParameters = new Bundle();
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        Job job = dispatcher.newJobBuilder().
                setService(LoginJobService.class).
                setLifetime(Lifetime.FOREVER).
                setExtras(jobParameters).
                setRecurring(false).
                setTag(JOBSERVICE_LOGIN).
                setRetryStrategy(RetryStrategy.DEFAULT_LINEAR).
                setReplaceCurrent(false)
                .setTrigger (Trigger. executionWindow(2, 5))
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .build();

        dispatcher.mustSchedule(job);
    }

}