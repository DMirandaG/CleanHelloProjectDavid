package es.ulpgc.eite.clean.mvp.sample.bye;

import android.os.Handler;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class ByeModel
        extends GenericModel<Bye.ModelToPresenter> implements Bye.PresenterToModel {

    private String sayByeLabel, backToHelloLabel;
    private String msgText;
    private boolean taskRunning = false;
    private boolean taskFinished = false;

    /**
     * Method that recovers a reference to the PRESENTER
     * You must ALWAYS call {@link super#onCreate(Object)} here
     *
     * @param presenter Presenter interface
     */
    @Override
    public void onCreate(Bye.ModelToPresenter presenter) {
        super.onCreate(presenter);
        Log.d(TAG, "calling onCreate()");

        sayByeLabel = "Say Bye";
        backToHelloLabel = "Back To Hello!";
        msgText = "Bye World !";
    }

    /**
     * Called by layer PRESENTER when VIEW pass for a reconstruction/destruction.
     * Usefull for kill/stop activities that could be running on the background Threads
     *
     * @param isChangingConfiguration Informs that a change is occurring on the configuration
     */
    @Override
    public void onDestroy(boolean isChangingConfiguration) {

    }


    ///////////////////////////////////////////////////////////////////////////////////
    // Presenter To Model ////////////////////////////////////////////////////////////


    @Override
    public void resetGetMessageTask() {
        taskFinished = false;
    }

    @Override
    public void startGetMessageTask() {
        if (isTaskRunning()) {
            return;
        }

        if (isTaskFinished()) {
            getPresenter().onGetMessageTaskFinished(msgText);
        } else {
            taskRunning = true;
            startDelayedTask();
        }
    }

    private void startDelayedTask() {
        // Mock Bye: A handler to delay the answer
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isTaskRunning()) {
                    getPresenter().onGetMessageTaskFinished(msgText);
                    taskRunning = false;
                    taskFinished = true;
                }

            }
        }, 5000);
    }

    @Override
    public boolean isTaskRunning() {
        return taskRunning;
    }

    @Override
    public boolean isTaskFinished() {

        return taskFinished;
    }


    @Override
    public String getText() {
        return msgText;
    }


    @Override
    public String getSayByeLabel() {
        return sayByeLabel;
    }


    @Override
    public String getBackToHelloLabel() {
        return backToHelloLabel;
    }

}
