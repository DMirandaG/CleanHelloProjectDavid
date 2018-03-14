package es.ulpgc.eite.clean.mvp.sample.dummy;

import android.util.Log;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class DummyModel
    extends GenericModel<Dummy.ModelToPresenter> implements Dummy.PresenterToModel {


  private String dummyText;
  private String dummyLabel;
  private int numOfTimes;
  private int maxNumOfTimes;
  private String msgText;


  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Dummy.ModelToPresenter presenter) {
    super.onCreate(presenter);
    Log.d(TAG, "calling onCreate()");

    dummyLabel = "Click Me!";
    dummyText = "Bye World!";
    maxNumOfTimes = 3;
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
  public String getText() {
    return msgText;
  }

  @Override
  public String getLabel() {
    return dummyLabel;
  }

  @Override
  public void resetMsgByBtnClicked() {
    numOfTimes = 1;
    msgText = dummyText;
  }

}
