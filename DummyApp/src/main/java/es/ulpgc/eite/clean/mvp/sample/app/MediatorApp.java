package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.sample.bye.Bye;
import es.ulpgc.eite.clean.mvp.sample.bye.ByeView;
import es.ulpgc.eite.clean.mvp.sample.hello.Hello;


public class MediatorApp extends Application implements Mediator.Lifecycle, Mediator.Navigation {

    protected final String TAG = this.getClass().getSimpleName();

    private HelloState toHelloState, byeToHelloState;
    private ByeState helloToByeState;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "calling onCreate()");

        Log.d(TAG, "calling creatingInitialHelloState()");
        toHelloState = new HelloState();
        toHelloState.toolbarVisibility = false;
        toHelloState.textVisibility = false;
        toHelloState.buttonClicked = false;
        toHelloState.progressBarVisibility = false;
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // Lifecycle /////////////////////////////////////////////////////////////////////


    // Hello Screen

    @Override
    public void startingScreen(Hello.ToHello presenter) {
        if (toHelloState != null) {
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
    public void resumingScreen(Hello.ByeToHello presenter) {

        if (byeToHelloState != null) {
            Log.d(TAG, "calling resumingScreen()");
            Log.d(TAG, "calling restoringUpdatedState()");
            presenter.setToolbarVisibility(byeToHelloState.toolbarVisibility);
            presenter.setTextVisibility(byeToHelloState.textVisibility);
            presenter.setProgressBarVisibility(byeToHelloState.progressBarVisibility);

            presenter.setByeText(byeToHelloState.textBye);
            presenter.setByeTextVisibility(byeToHelloState.textByeVisibility);

            Log.d(TAG, "calling removingUpdatedState()");
            byeToHelloState = null;

        }

        presenter.onScreenResumed();

    }


    // Bye Screen

    @Override
    public void startingScreen(Bye.HelloToBye presenter) {
        if (helloToByeState != null) {
            Log.d(TAG, "calling settingInitialByeState()");
            presenter.setToolbarVisibility(helloToByeState.toolbarVisibility);
            presenter.setTextVisibility(helloToByeState.textVisibility);
            presenter.setProgressBarVisibility(helloToByeState.progressBarVisibility);
            presenter.setButtonClicked(helloToByeState.buttonClicked);

            presenter.setHelloText(helloToByeState.textHello);
            presenter.setHelloTextVisibility(helloToByeState.textHelloVisibility);

            Log.d(TAG, "calling removingInitialByeState()");
            helloToByeState = null;
        }

        presenter.onScreenStarted();
    }



    @Override
    public void resumingScreen(Bye.ToBye presenter) {

        presenter.onScreenResumed();
    }


    ///////////////////////////////////////////////////////////////////////////////////
    // Navigation ////////////////////////////////////////////////////////////////////


    // Bye Screen


    @Override
    public void backToHelloScreen(Bye.ByeToHello presenter) {
        Log.d(TAG, "calling savingUpdatedState()");
        byeToHelloState = new HelloState();
        byeToHelloState.toolbarVisibility = presenter.isToolbarVisible();
        byeToHelloState.textVisibility = false;
        byeToHelloState.progressBarVisibility = false;

        byeToHelloState.textBye = presenter.getText();
        byeToHelloState.textByeVisibility = presenter.isTextVisible();

        Context view = presenter.getManagedContext();
        if (view != null) {
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
        helloToByeState.textVisibility = false;
        helloToByeState.progressBarVisibility = false;
        helloToByeState.buttonClicked = false;

        helloToByeState.textHello = presenter.getText();
        helloToByeState.textHelloVisibility = presenter.isTextVisible();

        Context view = presenter.getManagedContext();
        if (view != null) {
            Log.d(TAG, "calling startingNextScreen()");
            view.startActivity(new Intent(view, ByeView.class));
        }

    }


    ///////////////////////////////////////////////////////////////////////////////////
    // State /////////////////////////////////////////////////////////////////////////

    private class State {
        boolean toolbarVisibility;
        boolean progressBarVisibility;
        boolean textVisibility;
        boolean buttonClicked;
    }

    private class HelloState extends State {
        boolean textByeVisibility;
        String textBye;
    }

    private class ByeState extends State {
        boolean textHelloVisibility;
        String textHello;
    }

}
