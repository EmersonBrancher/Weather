package touglebutton.cursoandroid.com.weather.repository.local;

/**
 * Created by hakua on 16/02/2018.
 */

public class TaskConstants {

    public static class HEADER{

        public static final String ID_KEY = "id";
        public static final String APPID_KEY = "a0e7b842780708b939313dfa39522b0f";

    }

    public static class ENDPOINT{

        public static final String ROOT = "http://api.openweathermap.org/data/2.5/forecast?";

        public static final String AUTHENTICATION_CREATE = "Authentication/Create";
        public static final String AUTHENTICATION_LOGIN = "Authentication/Login";

    }

    public static class OPERATOIN_METHOD {
        public static final String GET = "GET";

    }

    public static class STATUS_CODE {

        public static final int SUCCESS = 200;
        public static final int FORBIDDEN = 403;
        public static final int NOT_FOUND  = 404;
        public static final int INTERNAL_SERVER_ERROR = 500;
        public static final int INTERNET_NOT_AVALIABE = 901;

    }


}
