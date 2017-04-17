package sda.code.intermediate.part3.exercises.rx;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import sda.code.intermediate.part2.answers.json.gson.WeatherGson;

public interface WeatherService {
	
		  @GET("data/2.5/weather")
		  Observable<WeatherGson> listRepos(@Query("appid") String apiKey, @Query("q") String query);

		  @GET("data/2.5/weather")
		  Observable<WeatherGson> listRepos(@Query("appid") String apiKey, @Query("lat") String lat, @Query("lon") String lon);
}
