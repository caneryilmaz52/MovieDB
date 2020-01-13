package code.challenge.moviedb.api

import code.challenge.moviedb.models.*
import code.challenge.moviedb.util.Constants
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory



interface IApiService {


    @GET("movie/top_rated?api_key=${Constants.API_KEY}")
    fun getTopRatedMovies(): Observable<Movie>

    @GET("movie/popular?api_key=${Constants.API_KEY}")
    fun getPopularMovies(): Observable<Movie>

    @GET("movie/now_playing?api_key=${Constants.API_KEY}")
    fun getNowPlayingMovies(): Observable<Movie>

    @GET("movie/{movie_id}?api_key=${Constants.API_KEY}")
    fun getMovieDetail(@Path("movie_id") id: Int): Observable<MovieDetail>

    @GET("movie/{movie_id}/credits?api_key=${Constants.API_KEY}")
    fun getMovieCredits(@Path("movie_id") id: Int): Observable<Credits>

    @GET("tv/top_rated?api_key=${Constants.API_KEY}")
    fun getTopRatedTV(): Observable<TV>

    @GET("tv/popular?api_key=${Constants.API_KEY}")
    fun getPopularTV(): Observable<TV>

    @GET("tv/{tv_id}?api_key=${Constants.API_KEY}")
    fun getTVDetail(@Path("tv_id") id: Int): Observable<TVDetail>

    @GET("tv/{tv_id}/credits?api_key=${Constants.API_KEY}")
    fun getTVCredits(@Path("tv_id") id: Int): Observable<Credits>

    companion object Factory {
        fun create(): IApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_API_URL)
                .build()

            return retrofit.create(IApiService::class.java)
        }
    }
}