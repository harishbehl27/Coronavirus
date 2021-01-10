package io.practise.Coronavirus.services;

import io.practise.Coronavirus.models.LocationStats;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class Coronavirusdataservice {

    private List<LocationStats> allstats= new ArrayList<>();

    private static String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    public List<LocationStats> getAllstats() {
        return this.allstats;
    }

    @PostConstruct
    @Scheduled(cron = "* * * * * *")
    public void fetchData() throws IOException, InterruptedException {

         List<LocationStats> newstats= new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request= HttpRequest.newBuilder()
                                  .uri(URI.create(VIRUS_DATA_URL)).build();


        HttpResponse<String> httpResponse= client.send(request,HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(httpResponse.body());

//        System.out.println("http response" + httpResponse.body());


        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {

            LocationStats locationStats = new LocationStats();
            locationStats.setState(record.get("Province/State"));
            locationStats.setCountry(record.get("Country/Region"));
            locationStats.setLatestTotalScore(Integer.parseInt(record.get(record.size() - 1)));
            //System.out.println(locationStats);
            newstats.add(locationStats);

        }

        this.allstats=newstats;


        //System.out.println("Statts are " + this.allstats);


    }
}
