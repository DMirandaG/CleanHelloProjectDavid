package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.bye.Bye;
import es.ulpgc.eite.clean.mvp.sample.dummy.Dummy;
import es.ulpgc.eite.clean.mvp.sample.hello.Hello;

/**
 * Created by imac on 23/1/18.
 */

public interface Mediator {

  interface Lifecycle {
    void startingScreen(Hello.ToDummy presenter);
    void resumingScreen(Hello.DummyTo presenter);

    void startingScreen(Bye.ToDummy presenter);
    void resumingScreen(Bye.DummyTo presenter);

    void startingScreen(Dummy.ToDummy presenter);
    void resumingScreen(Dummy.DummyTo presenter);

  }

  interface Navigation {
    void goToNextScreen(Dummy.DummyTo presenter);
    void backToPreviousScreen(Dummy.DummyTo presenter);


    void goToByeScreen(Hello.DummyTo presenter);
    void backToPreviousScreen(Hello.DummyTo presenter);

    void goToHelloScreen(Bye.DummyTo presenter);
    void backToPreviousScreen(Bye.DummyTo presenter);

    //void goToNextScreen(Hello.DummyTo presenter);


  }
}
