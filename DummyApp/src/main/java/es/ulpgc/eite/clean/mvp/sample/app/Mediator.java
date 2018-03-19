package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.bye.Bye;
import es.ulpgc.eite.clean.mvp.sample.hello.Hello;

/**
 * Created by imac on 23/1/18.
 */

public interface Mediator {

    interface Lifecycle {
        void startingScreen(Bye.HelloToBye presenter);
        void startingScreen(Hello.ToHello presenter);
        void resumingScreen(Hello.ByeToHello presenter);
        void resumingScreen(Bye.ToBye presenter);
    }

    interface Navigation {

        void backToHelloScreen(Bye.ByeToHello presenter);
        void goToByeScreen(Hello.HelloToBye presenter);

    }
}
