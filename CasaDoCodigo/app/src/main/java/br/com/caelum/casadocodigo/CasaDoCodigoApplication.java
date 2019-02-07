package br.com.caelum.casadocodigo;

import android.app.Application;

import br.com.caelum.casadocodigo.di.CasaDoCodigoComponent;
import br.com.caelum.casadocodigo.di.DaggerCasaDoCodigoComponent;

public class CasaDoCodigoApplication extends Application {

    private CasaDoCodigoComponent component;
    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerCasaDoCodigoComponent.builder().build();
    }
    public CasaDoCodigoComponent getComponent() {
        return component;
    }
}
