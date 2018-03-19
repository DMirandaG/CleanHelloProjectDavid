package es.ulpgc.eite.clean.mvp.sample.hello;

import android.content.Context;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface Hello {


    ///////////////////////////////////////////////////////////////////////////////////
    // State /////////////////////////////////////////////////////////////////////////

    interface State {
        void setToolbarVisibility(boolean visible);
        void setTextVisibility(boolean visible);
        void setProgressBarVisibility(boolean visible);
    }

    interface ToHello extends State {
        void onScreenStarted();
        void setButtonClicked(boolean clicked);

    }

    interface ByeToHello extends ToHello {
        void setByeTextVisibility(boolean visible);
        void setByeText(String text);
        void onScreenResumed();

    }

    interface HelloToBye extends State {
        Context getManagedContext();
        void destroyView();
        boolean isToolbarVisible();
        boolean isTextVisible();
        String getText();
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // Screen ////////////////////////////////////////////////////////////////////////

    /**
     * Methods offered to VIEW to communicate with PRESENTER
     */
    interface ViewToPresenter extends Presenter<PresenterToView> {

        void onSayHelloBtnClicked();
        void onGoToByeBtnClicked();
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
        void setSayHelloLabel(String txt);
        void setGoToByeLabel(String txt);
    }

    /**
     * Methods offered to MODEL to communicate with PRESENTER
     */
    interface PresenterToModel extends Model<ModelToPresenter> {
        void resetGetMessageTask();
        void startGetMessageTask();
        boolean isTaskRunning();
        boolean isTaskFinished();
        String getText();
        String getSayHelloLabel();
        String getGoToByeLabel();
    }

    /**
     * Required PRESENTER methods available to MODEL
     */
    interface ModelToPresenter {

        void onGetMessageTaskFinished(String text);
    }

}
