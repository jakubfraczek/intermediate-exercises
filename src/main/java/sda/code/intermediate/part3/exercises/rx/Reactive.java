package sda.code.intermediate.part3.exercises.rx;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.poi.util.SystemOutLogger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.netty.util.internal.SystemPropertyUtil;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Observer;
import sda.code.intermediate.part1.answers.patterns.Settings;
import sda.code.intermediate.part2.answers.json.gson.WeatherGson;
import sda.code.intermediate.part2.answers.weather.WeatherClientStrategy;
import sda.code.intermediate.part2.livecoding.weather.api.CityQuery;
import sda.code.intermediate.part2.livecoding.weather.api.Countries;
import sda.code.intermediate.part2.livecoding.weather.api.GeoQuery;

public class Reactive {

	public static void main(String[] args) throws IOException {
		final String baseUrl = Settings.CONFIG.getString("weather.baseurl");
		final String key = Settings.CONFIG.getString("weather.apikey");

		final CityQuery cityQuery = new CityQuery("Lodz", Countries.POLAND);
		final GeoQuery geoQuery = new GeoQuery(43.95, 4.81);

		// 1. Uzyć Retrofita i Gsona, by pobrać prognozę synchronicznie
		// (retrofit, gson, converter-gson)
		// 2. Uzyć Retrofita, by wykonać asynchroniczne zapytanie
		// 3. Włączyć integrację z RxJava (rxjava, adapter-rxjava)
		// 4. Uzyć Schedulera RxJavy, by wykonać asynchroniczne zapytanie

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//				.client(new OkHttpClient.Builder().)
				.build();

		WeatherService service = retrofit.create(WeatherService.class);

		Observable<WeatherGson> weatherCall = service.listRepos(key, cityQuery.toString());

		Observable<WeatherGson> weatherCall2 = service.listRepos(key, geoQuery.getLatitude(), geoQuery.getLongitude());

			weatherResponse(weatherCall);

			weatherResponse(weatherCall2);
			
	}

	private static void weatherResponse(Observable<WeatherGson> weatherCall) {
		weatherCall.subscribe(new Observer<WeatherGson>() {
			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onError(Throwable paramThrowable) {
				System.err.println("Brak odpowiedzi z serwera");				
				
			}

			@Override
			public void onNext(WeatherGson paramT) {
//				if (paramT.code() != 200) {
//					System.err.println(
//							"Niespodziewany kod odpowiedzi " + paramT.code() + ": " + paramT.message());
//				} else {
//					WeatherGson weatherGson = paramT.body();
					showWeather(paramT);
//				}
				
			}
		});
//		enqueue(new Callback<WeatherGson>() {
			/*
			@Override
			public void onResponse(Call<WeatherGson> paramCall, Response<WeatherGson> paramResponse) {
//				paramCall.
			}
			
			@Override
			public void onFailure(Call<WeatherGson> paramCall, Throwable paramThrowable) {
			}
		});
		*/
	}

	private static void showWeather(WeatherGson weatherGson) {
		println(weatherGson.getName());
		if (weatherGson.getWeather() != null && !weatherGson.getWeather().isEmpty()) {
			println(weatherGson.getWeather().get(0).getDescription());
		}
		if (weatherGson.getMain() != null) {
			println(weatherGson.getMain().getTemp());
		}
	}

	// Przydatnik do wyświetlenia komunikatu razem z nazwą wątku
	public static void println(Object obj) {
		if (obj == null) {
			println("null");
		} else {
			println(obj.toString());
		}
	}

	private static void println(String msg) {
		System.err.println(Thread.currentThread().getName() + ": " + msg);
	}
}
