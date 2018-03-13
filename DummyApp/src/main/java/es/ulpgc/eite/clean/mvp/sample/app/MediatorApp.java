package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


import es.ulpgc.eite.clean.mvp.sample.dummy.Dummy;
import es.ulpgc.eite.clean.mvp.sample.dummy.DummyView;
import es.ulpgc.eite.clean.mvp.sample.hello.Hello;
import es.ulpgc.eite.clean.mvp.sample.hello.HelloView;


public class MediatorApp extends Application implements Mediator.Lifecycle, Mediator.Navigation {

  protected final String TAG = this.getClass().getSimpleName();

  private DummyState toDummyState, dummyToState;
  private HelloState toHelloState, helloToByeState;
  private ByeState toByeState, byeToState;



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

    Log.d(TAG, "calling creatingInitialByeState()");
    toByeState = new ByeState();
    toByeState.toolbarVisibility = false;
    toByeState.textVisibility = false;
    toByeState.buttonClicked = false;
    toByeState.progressBarVisibility = false;
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Lifecycle /////////////////////////////////////////////////////////////////////


  // Hello Screen

  @Override
  public void startingScreen(Hello.ToDummy presenter){
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
  public void resumingScreen(Hello.DummyTo presenter){
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

  // Bye Screen

  @Override
  public void startingScreen(Bye.ToDummy presenter){
    if(toByeState != null) {
      Log.d(TAG, "calling settingInitialHelloState()");
      presenter.setToolbarVisibility(toByeState.toolbarVisibility);
      presenter.setTextVisibility(toByeState.textVisibility);
      presenter.setProgressBarVisibility(toByeState.progressBarVisibility);
      presenter.setButtonClicked(toByeState.buttonClicked);

      Log.d(TAG, "calling removingInitialHelloState()");
      toByeState = null;
    }

    presenter.onScreenStarted();
  }


  @Override
  public void resumingScreen(Bye.DummyTo presenter){
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


  // Hello Screen

  @Override
  public void backToPreviousScreen(Hello.DummyTo presenter) {
    Log.d(TAG, "calling savingUpdatedState()");
    dummyToState = new DummyState();
    dummyToState.textVisibility = true;
    dummyToState.toolbarVisibility = false;
  }

  @Override
  public void goToNextScreen(Hello.DummyTo presenter) {
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


  @Override
  public void goToByeScreen(Hello.DummyTo presenter) {
    Log.d(TAG, "calling savingUpdatedState()");
    helloToByeState = new HelloState();
    helloToByeState.toolbarVisibility = presenter.isToolbarVisible();
    //helloToByeState.textVisibility = presenter.isTextVisible();
    helloToByeState.textVisibility = false;

    Context view = presenter.getManagedContext();
    if (view != null) {
      Log.d(TAG, "calling startingNextScreen()");
      view.startActivity(new Intent(view, DummyView.class)); // TODO: 7/3/18
      //Log.d(TAG, "calling finishingCurrentScreen()");
      //presenter.destroyView();
    }

  }

  // Bye Screen

  @Override
  public void backToPreviousScreen(Bye.DummyTo presenter) {
    Log.d(TAG, "calling savingUpdatedState()");
    dummyToState = new DummyState();
    dummyToState.textVisibility = true;
    dummyToState.toolbarVisibility = false;
  }

  @Override
  public void goToHelloScreen(Bye.DummyTo presenter) {
    Log.d(TAG, "calling savingUpdatedState()");
    byeToState = new ByeState();
    byeToState.toolbarVisibility = presenter.isToolbarVisible();
    byeToState.textVisibility = presenter.isTextVisible();

    Context view = presenter.getManagedContext();
    if (view != null) {
      Log.d(TAG, "calling startingNextScreen()");
      view.startActivity(new Intent(view, HelloView.class));
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
  }

  private class ByeState {
    boolean toolbarVisibility;
    boolean progressBarVisibility;
    boolean textVisibility;
    boolean buttonClicked;
  }

}
