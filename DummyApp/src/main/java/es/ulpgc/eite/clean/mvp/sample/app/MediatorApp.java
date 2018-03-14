package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.sample.bye.Bye;
import es.ulpgc.eite.clean.mvp.sample.bye.ByeView;
import es.ulpgc.eite.clean.mvp.sample.dummy.Dummy;
import es.ulpgc.eite.clean.mvp.sample.dummy.DummyView;
import es.ulpgc.eite.clean.mvp.sample.hello.Hello;


public class MediatorApp extends Application implements Mediator.Lifecycle, Mediator.Navigation {

  protected final String TAG = this.getClass().getSimpleName();

  private DummyState toDummyState, dummyToState;
  private HelloState toHelloState, byeToHelloState;
  private ByeState  helloToByeState;

  @Override
  public void onCreate() {
    super.onCreate();
    Log.d(TAG, "calling onCreate()");

    Log.d(TAG, "calling creatingInitialState()");
    toDummyState = new DummyState();
    toDummyState.toolbarVisibility = false;
    toDummyState.textVisibility = false;


    Log.d(TAG, "calling creatingInitialHelloState()");
    toHelloState = new HelloState();
    toHelloState.toolbarVisibility = false;
    toHelloState.textVisibility = false;
    toHelloState.buttonClicked = false;
    toHelloState.progressBarVisibility = false;
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Lifecycle /////////////////////////////////////////////////////////////////////


  // Bye Screen

  @Override
  public void startingScreen(Bye.HelloToBye presenter){
    if(helloToByeState != null) {
      Log.d(TAG, "calling settingInitialByeState()");
      presenter.setToolbarVisibility(helloToByeState.toolbarVisibility);
      //presenter.setTextVisibility(helloToByeState.textVisibility);
      presenter.setHelloTextVisibility(helloToByeState.textHelloVisibility);
      presenter.setTextVisibility(false);
      //presenter.setProgressBarVisibility(helloToByeState.progressBarVisibility);
      presenter.setProgressBarVisibility(false);
      //presenter.setButtonClicked(helloToByeState.buttonClicked);
      presenter.setButtonClicked(false);
      presenter.setHelloText(helloToByeState.textHello);

      Log.d(TAG, "calling removingInitialByeState()");
      helloToByeState = null;
    }

    presenter.onScreenStarted();
  }


  // Hello Screen

  @Override
  public void startingScreen(Hello.ToHello presenter){
    if(toHelloState != null) {
      Log.d(TAG, "calling settingInitialHelloState()");
      presenter.setToolbarVisibility(toHelloState.toolbarVisibility);
      presenter.setTextVisibility(toHelloState.textVisibility);
      presenter.setProgressBarVisibility(toHelloState.progressBarVisibility);
      presenter.setButtonClicked(toHelloState.buttonClicked);

      Log.d(TAG, "calling removingInitialHelloState()");
      toHelloState = null;
    }

    presenter.onScreenStarted();
  }


  @Override
  public void resumingScreen(Hello.HelloToBye presenter){
    if(dummyToState != null) {
      Log.d(TAG, "calling resumingScreen()");
      Log.d(TAG, "calling restoringUpdatedState()");
      presenter.setToolbarVisibility(dummyToState.toolbarVisibility);
      presenter.setTextVisibility(dummyToState.textVisibility);

      Log.d(TAG, "calling removingUpdatedState()");
      dummyToState = null;
    }

    presenter.onScreenResumed();
  }

  // Dummy Screen

  @Override
  public void startingScreen(Dummy.ToDummy presenter){
    if(toDummyState != null) {
      Log.d(TAG, "calling settingInitialState()");
      presenter.setToolbarVisibility(toDummyState.toolbarVisibility);
      presenter.setTextVisibility(toDummyState.textVisibility);

      Log.d(TAG, "calling removingInitialState()");
      toDummyState = null;
    }

    if(dummyToState != null) {
      Log.d(TAG, "calling settingUpdatedState()");
      presenter.setToolbarVisibility(dummyToState.toolbarVisibility);
      presenter.setTextVisibility(dummyToState.textVisibility);

      Log.d(TAG, "calling removingUpdateState()");
      dummyToState = null;
    }

    presenter.onScreenStarted();
  }


  @Override
  public void resumingScreen(Dummy.DummyTo presenter){
    if(dummyToState != null) {
      Log.d(TAG, "calling resumingScreen()");
      Log.d(TAG, "calling restoringUpdatedState()");
      presenter.setToolbarVisibility(dummyToState.toolbarVisibility);
      presenter.setTextVisibility(dummyToState.textVisibility);

      Log.d(TAG, "calling removingUpdatedState()");
      dummyToState = null;
    }

    presenter.onScreenResumed();
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Navigation ////////////////////////////////////////////////////////////////////


  // Bye Screen

  @Override
  public void goToHelloScreen(Bye.ByeToHello presenter) {
    Log.d(TAG, "calling savingUpdatedState()");
    byeToHelloState = new HelloState();
    byeToHelloState.toolbarVisibility = presenter.isToolbarVisible();
    byeToHelloState.textVisibility = presenter.isTextVisible();
    byeToHelloState.textBye = presenter.getText();
    byeToHelloState.textByeVisibility = presenter.isTextVisible();


    Context view = presenter.getManagedContext();
    if (view != null) {
      //Log.d(TAG, "calling startingNextScreen()");
      //view.startActivity(new Intent(view, ByeView.class));
      Log.d(TAG, "calling finishingCurrentScreen()");
      presenter.destroyView();
    }
  }

  // Hello Screen

  @Override
  public void goToByeScreen(Hello.HelloToBye presenter) {
    Log.d(TAG, "calling savingUpdatedState()");
    helloToByeState = new ByeState();
    helloToByeState.toolbarVisibility = presenter.isToolbarVisible();
    helloToByeState.textVisibility = presenter.isTextVisible();
    helloToByeState.textHello = presenter.getText();
    helloToByeState.textHelloVisibility = presenter.isTextVisible();


    Context view = presenter.getManagedContext();
    if (view != null) {
      Log.d(TAG, "calling startingNextScreen()");
      view.startActivity(new Intent(view, ByeView.class));
      //Log.d(TAG, "calling finishingCurrentScreen()");
      //presenter.destroyView();
    }

  }


  // Dummy Screen

  @Override
  public void backToPreviousScreen(Dummy.DummyTo presenter) {
    Log.d(TAG, "calling savingUpdatedState()");
    dummyToState = new DummyState();
    dummyToState.textVisibility = true;
    dummyToState.toolbarVisibility = false;
  }

  @Override
  public void goToNextScreen(Dummy.DummyTo presenter) {
    Log.d(TAG, "calling savingUpdatedState()");
    dummyToState = new DummyState();
    dummyToState.toolbarVisibility = presenter.isToolbarVisible();
    //dummyToState.textVisibility = presenter.isTextVisible();
    dummyToState.textVisibility = false;

    Context view = presenter.getManagedContext();
    if (view != null) {
      Log.d(TAG, "calling startingNextScreen()");
      view.startActivity(new Intent(view, DummyView.class));
      //Log.d(TAG, "calling finishingCurrentScreen()");
      //presenter.destroyView();
    }

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  private class DummyState {
    boolean toolbarVisibility;
    boolean textVisibility;
  }

  private class HelloState {
    boolean toolbarVisibility;
    boolean progressBarVisibility;
    boolean textVisibility;
    boolean buttonClicked;
    public boolean textByeVisibility;
    String textBye;
  }

  private class ByeState extends HelloState {
    boolean textHelloVisibility;
    String textHello;
  }

}
