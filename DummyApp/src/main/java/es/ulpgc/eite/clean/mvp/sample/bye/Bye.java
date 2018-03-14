package es.ulpgc.eite.clean.mvp.sample.bye;

import android.content.Context;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface Bye {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface State {
    void setToolbarVisibility(boolean visible);
    void setTextVisibility(boolean visible);
  }

  interface HelloToBye extends State {
    void onScreenStarted();
    void setProgressBarVisibility(boolean visible);
    void setButtonClicked(boolean clicked);
    void setHelloTextVisibility(boolean visible);
    void setHelloText(String textHello);
  }

  interface ByeToHello extends State{
    Context getManagedContext();
    void destroyView();
    boolean isToolbarVisible();
    boolean isTextVisible();
    void onScreenResumed();

    String getText();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    //void onButtonClicked();
    void onSayByeBtnClicked();
    void onBackToHelloBtnClicked();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
    void hideProgressBar();
    void showProgressBar();
    void hideToolbar();
    void hideText();
    void showText();
    void setText(String txt);
    void setSayByeLabel(String txt);
    void setBackToHelloLabel(String txt);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {
    void startHelloGetMessageTask();

    boolean isTaskRunning();

    boolean isTaskFinished();

    String getText();
    String getSayByeLabel();
    String getBackToHelloLabel();
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

    void onByeGetMessageTaskFinished(String text);
  }

}
