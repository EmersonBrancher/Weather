package touglebutton.cursoandroid.com.weather.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by hakua on 16/02/2018.
 */

public class NetworkUtils {

    public static Boolean isConnectionAvaliable(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}
