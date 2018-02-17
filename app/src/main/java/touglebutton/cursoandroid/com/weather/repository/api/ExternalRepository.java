package touglebutton.cursoandroid.com.weather.repository.api;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;

import touglebutton.cursoandroid.com.weather.entities.APIResponse;
import touglebutton.cursoandroid.com.weather.entities.FullParameters;
import touglebutton.cursoandroid.com.weather.infra.InternetNotAvaliableException;
import touglebutton.cursoandroid.com.weather.repository.local.TaskConstants;
import touglebutton.cursoandroid.com.weather.utils.NetworkUtils;

/**
 * Created by hakua on 16/02/2018.
 */

public class ExternalRepository {

    private Context mContext;

    public ExternalRepository(Context context){
        this.mContext = context;
    }

    public APIResponse execute(FullParameters parameters) throws InternetNotAvaliableException {

        if (!NetworkUtils.isConnectionAvaliable(this.mContext))
            throw new InternetNotAvaliableException();

        APIResponse response;
        InputStream inputStream;
        HttpURLConnection conn;

        try {

            URL url;
            if (parameters.method == TaskConstants.OPERATOIN_METHOD.GET) {
                url = new URL(parameters.url + getQuery(parameters.parameters, parameters.method));
            } else {
                url = new URL(parameters.url);
            }

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(100000);
            conn.setConnectTimeout(150000);
            conn.setRequestMethod(parameters.method);
            conn.setRequestProperty("Content-Type", "application/x-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(true);

            if (parameters.headerParameters != null) {
                Iterator it = parameters.headerParameters.entrySet().iterator();
                while (it.hasNext()){
                    Map.Entry pair = (Map.Entry) it.next();
                    conn.setRequestProperty(pair.getKey().toString(), pair.getValue().toString());
                    it.remove();
                }
            }

            if(parameters.method != TaskConstants.OPERATOIN_METHOD.GET){
                String query = getQuery(parameters.parameters, parameters.method);
                byte[] postDataBytes = query.getBytes("UTF-8");
                int postDataBytesLength = postDataBytes.length;

                conn.setRequestProperty("Content-Length", Integer.toString(postDataBytesLength));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);
            }

            conn.connect();
            if (conn.getResponseCode() == TaskConstants.STATUS_CODE.SUCCESS) {
                inputStream = conn.getInputStream();
                response = new APIResponse(getStringFromInputStream(inputStream), conn.getResponseCode());
            } else {
                inputStream = conn.getErrorStream();
                response = new APIResponse(getStringFromInputStream(inputStream), conn.getResponseCode());
            }

            inputStream.close();
            conn.disconnect();


        } catch (Exception e) {

            response = new APIResponse("", TaskConstants.STATUS_CODE.NOT_FOUND);

        }
        return response;

    }

    private String getStringFromInputStream(InputStream inputStream){
        if (inputStream == null){
            return "";
        }

        BufferedReader br;
        StringBuilder builder = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null)
                builder.append(line);

            br.close();
        } catch (IOException e) {
            return "";
        }

        return builder.toString();
    }

    private String getQuery (AbstractMap<String, String> params, String method) throws UnsupportedEncodingException {

        if (params == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, String> e : params.entrySet()){
            if(first){
                if (method == TaskConstants.OPERATOIN_METHOD.GET){
                    builder.append("?");
                }
                first = false;
            } else {
                builder.append("&");
            }

            builder.append(URLEncoder.encode(e.getKey(), "UTF-8"));
            builder.append("=");
            builder.append(URLEncoder.encode(e.getValue(), "UTF-8"));

        }

        return "";
    }
}
