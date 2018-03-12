package es.ulpgc.eite.clean.mvp.sample.hello;

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

public class HelloView
    extends GenericActivity<Hello.PresenterToView, Hello.ViewToPresenter, HelloPresenter>
    implements Hello.PresenterToView {

  private Toolbar toolbar;
  private Button btnGoToBye, btnSayHello;
  private TextView text;
  private ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hello);
    Log.d(TAG, "calling onCreate()");

    text = (TextView) findViewById(R.id.txtHello);
    progressBar = (ProgressBar) findViewById(R.id.pbHello);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    btnGoToBye = (Button) findViewById(R.id.btnGoToBye);
    btnGoToBye.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onGoToByeBtnClicked();
      }
    });


    btnSayHello = (Button) findViewById(R.id.btnSayHello);
    btnSayHello.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onSayHelloBtnClicked();
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
    super.onResume(HelloPresenter.class, this);
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
  public void setSayHelloLabel(String txt) {
    btnSayHello.setText(txt);
  }


  @Override
  public void setGoToByeLabel(String txt) {
    btnGoToBye.setText(txt);
  }
}
