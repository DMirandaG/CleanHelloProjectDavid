package es.ulpgc.eite.clean.mvp.sample.hello;

import android.os.Handler;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class HelloModel
        extends GenericModel<Hello.ModelToPresenter> implements Hello.PresenterToModel {

    private String sayHelloLabel, goToByeLabel;
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
    public void onCreate(Hello.ModelToPresenter presenter) {
        super.onCreate(presenter);
        Log.d(TAG, "calling onCreate()");

        sayHelloLabel = "Say Hello";
        goToByeLabel = "Go To Bye!";
        msgText = "Hello World !";
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
        Log.d(TAG, "calling startGetMessageTask()");

        Log.d(TAG, "taskRunning = " + taskRunning); //false
        Log.d(TAG, "taskFinished = " + taskFinished); // true

        // si ya se ha iniciado la tarea asyn, no hago nada
        if (isTaskRunning()) {
            return;
        }

        // si ya ha finalizado la tarea asyn, lo notifico
        if (isTaskFinished()) {
            getPresenter().onGetMessageTaskFinished(msgText);

        } else { // inicio la tarea asyn
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
                    // si ya ha finalizado la tarea asyn, lo notifico
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
    public String getSayHelloLabel() {
        return sayHelloLabel;
    }


    @Override
    public String getGoToByeLabel() {
        return goToByeLabel;
    }

}
