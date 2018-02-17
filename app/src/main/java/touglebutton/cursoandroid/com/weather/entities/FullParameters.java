package touglebutton.cursoandroid.com.weather.entities;

import java.util.AbstractMap;
import java.util.HashMap;

/**
 * Created by hakua on 16/02/2018.
 */

public class FullParameters {

    public String method;
    public String url;
    public AbstractMap<String,String> headerParameters;
    public AbstractMap<String,String> parameters;

    public FullParameters (String method, String url, HashMap params, HashMap headerParams){
        this.method = method;
        this.url = url;
        this.parameters = params;
        this.headerParameters = headerParams;
    }


}
