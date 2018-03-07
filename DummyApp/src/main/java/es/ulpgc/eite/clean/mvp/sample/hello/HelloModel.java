package es.ulpgc.eite.clean.mvp.sample.hello;

import android.os.Handler;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class HelloModel
    extends GenericModel<Hello.ModelToPresenter> implements Hello.PresenterToModel {


  //private String dummyText;
  private String sayHelloLabel, goToByeLabel;
  //private int numOfTimes;
  //private int maxNumOfTimes;
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
    //dummyText = "";
    msgText = "Hello World !";
    //maxNumOfTimes = 3;
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
  public void startHelloGetMessageTask() {
    if(isTaskRunning()){
      return;
    }

    if(isTaskFinished()) {
      getPresenter().onHelloGetMessageTaskFinished(msgText);
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
        if(isTaskRunning()) {
          getPresenter().onHelloGetMessageTaskFinished(msgText);
          taskRunning = false;
          taskFinished = true;
        }

      }
    }, 10000);
  }

  @Override
  public boolean isTaskRunning() {
    return taskRunning;
  }

  @Override
  public boolean isTaskFinished() {

    return taskFinished;
  }

  /*
  @Override
  public boolean isNumOfTimesCompleted() {
    if(numOfTimes == maxNumOfTimes) {
      return true;
    }
    return false;
  }

  @Override
  public void changeMsgByBtnClicked() {
    msgText = dummyText;
    if(numOfTimes > 0) {
      msgText += ", " + (numOfTimes + 1) + " times";
    }
    numOfTimes++;
  }


  @Override
  public void resetMsgByBtnClicked() {
    numOfTimes = 1;
    msgText = dummyText;
  }
  */

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
