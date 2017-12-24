package team.nure.maria.courseworkproject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by dr0gi on 25.12.2017.
 */

public class ServerController {
    private static ServerController instance;
    private Retrofit retrofit;
    private String url;
    private String token;
    private ServerQuery serverQuery;


    private ServerController(){
        url = "http://localhost:0000/"; //TODO: Вписать свой порт
        retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        serverQuery = retrofit.create(ServerQuery.class);
    }

    public static ServerController Create() {
        if(instance == null) {
            instance = new ServerController();
        }
        return  instance;
    }

    public ServerQuery getServerQuery() {
        return serverQuery;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public interface ServerQuery{
        @FormUrlEncoded
        @POST("/api/Users")
        Call<Void> registration(@Field("UserId") Integer UserId,
                                @Field("Login") String Login,
                                @Field("Password") String Password,
                                @Field("FullName") String FullName,
                                @Field("Email") String Email,
                                @Field("Role") String Role);

    }
}