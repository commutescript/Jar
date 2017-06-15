package com.yf.jar.network;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.yf.jar.R;
import com.yf.jar.json.*;
import com.yf.jar.json.TestEntity;

//import org.reactivestreams.Subscriber;
//
////import okhttp3.Call;
//import io.reactivex.ObservableEmitter;
//import io.reactivex.ObservableOnSubscribe;
//import io.reactivex.Observer;
//import io.reactivex.Scheduler;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.schedulers.Schedulers;
//import io.reactivex.subjects.Subject;
import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;


public class NetWorkActivity extends Activity {

    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work);
        mQueue= Volley.newRequestQueue(this);

        volleyTest();

    }

    private void volleyTest(){
        StringRequest stringRequest = new StringRequest("", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }

        );
        mQueue.add(stringRequest);

    }

    private void okhttpTest(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(null)
                .cache(null)
                .build();

        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("size", "10")
                .build();
        Request mRequest = new Request.Builder()
                .url("")
                .post(formBody).build();
//        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, okhttp3.Response response) throws IOException {
//
//            }
//        });



    }

    private void retrofitTest(){
//        Retrofit mRetrofit1 = new Retrofit();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ITestService mITestService = mRetrofit.create(ITestService.class);
        final Observable<TestEntity> mTestEntityCall = mITestService.getData(1);
//        final Call<com.yf.jar.json.TestEntity> mcall = mITestService.getData(1);

//        Observable<TestEntity> mObservable =Observable.create(new Observable.OnSubscribe<TestEntity>() {
//            @Override
//            public void call(rx.Subscriber<? super TestEntity> subscriber) {
//
//            }
//        });

        io.reactivex.Observable<TestEntity> mObservable01 = io.reactivex.Observable.create(new ObservableOnSubscribe<TestEntity>() {
            @Override
            public void subscribe(ObservableEmitter<TestEntity> e) throws Exception {

            }
        });
        io.reactivex.Observable<TestEntity> mObservable02= mITestService.getData(1);
        mObservable02
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TestEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TestEntity value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
//        mObservable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<TestEntity>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(TestEntity testEntity) {
//
//                    }
//                });






//        Call<Entity> call = mITestService.getData(1);
//        call.enqueue(new retrofit2.Callback<ResponseBody>() {
//            @Override
//            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
//
//            }
//        });


    }


}
