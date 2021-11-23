package com.ranjutech.barscan.UI.Detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.ranjutech.barscan.Util.Constant;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailsViewModel extends ViewModel {

    private final Sheets sheetsService;
    private final MutableLiveData<String> title= new MutableLiveData<>();
    private final MutableLiveData<String> subTitle= new MutableLiveData<>();
    private final MutableLiveData<String> price= new MutableLiveData<>();
    private final MutableLiveData<String> detail= new MutableLiveData<>();

    public LiveData<String> getTitle(){return title;}
    public LiveData<String> getSubTitle(){return subTitle;}
    public LiveData<String> getPrice(){return price;}
    public LiveData<String> getDetail(){return detail;}

    private  String bcode;

    public DetailsViewModel(String bcode){
        this.bcode=bcode;
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory factory = JacksonFactory.getDefaultInstance();
        sheetsService = new Sheets.Builder(transport, factory, null)
                .setApplicationName("BarScan")
                .build();
        getData();
    }

    public void getData(){
        ExecutorService service =  Executors.newSingleThreadExecutor();
        service.submit(new Runnable() {

            @Override
            public void run() {
                String range = "Sheet1";
                ValueRange result = null;
                try {
                    result = sheetsService.spreadsheets().values()
                            .get(Constant.spreadsheet_id, range)
                            .setKey(Constant.google_api_key)
                            .execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                List<List<Object>> val=result.getValues();
                int numRows = val != null ? result.getValues().size() : 0;

                for(int i=0;i<numRows;i++){

                    if(val.get(i).get(0).toString().equals(bcode)){
                            title.postValue(val.get(i).get(1).toString());
                            subTitle.postValue(val.get(i).get(2).toString());
                        price.postValue(val.get(i).get(3).toString());
                        detail.postValue(val.get(i).get(4).toString());
                    }
                }

            }
        });
    }
}
