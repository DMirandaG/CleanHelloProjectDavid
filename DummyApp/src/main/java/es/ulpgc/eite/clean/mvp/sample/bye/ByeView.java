package es.ulpgc.eite.clean.mvp.sample.bye;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;

public class ByeView
        extends GenericActivity<Bye.PresenterToView, Bye.ViewToPresenter, ByePresenter>
        implements Bye.PresenterToView {

    private Toolbar toolbar;
    private Button btnBackToHello, btnSayBye;
    private TextView text;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bye);
        Log.d(TAG, "calling onCreate()");

        text = (TextView) findViewById(R.id.txtBye);
        progressBar = (ProgressBar) findViewById(R.id.pbBye);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnBackToHello = (Button) findViewById(R.id.btnBackToHello);
        btnBackToHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onBackToHelloBtnClicked();
            }
        });


        btnSayBye = (Button) findViewById(R.id.btnSayBye);
        btnSayBye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onSayByeBtnClicked();
            }
        });


    }

    /**
     * Method that initialized MVP objects
     * {@link super#onResume(Class, Object)} should always be called
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onResume() {
        super.onResume(ByePresenter.class, this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "calling onBackPressed()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "calling onDestroy()");
    }


    ///////////////////////////////////////////////////////////////////////////////////
    // Presenter To View /////////////////////////////////////////////////////////////

    @Override
    public void finishScreen() {
        Log.d(TAG, "calling finishScreen()");
        finish();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideToolbar() {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void hideText() {
        text.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showText() {
        text.setVisibility(View.VISIBLE);
    }

    @Override
    public void setText(String txt) {
        text.setText(txt);
    }

    @Override
    public void setSayByeLabel(String txt) {
        btnSayBye.setText(txt);
    }


    @Override
    public void setBackToHelloLabel(String txt) {
        btnBackToHello.setText(txt);
    }
}
