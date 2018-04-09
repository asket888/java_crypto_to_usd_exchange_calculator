package api;

import org.apache.http.HttpResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class HttpClientUtil {

    public String getApiResponse(String currencyName) {

        String currencyPrice = null;

        try {

            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpGet request = new HttpGet("https://api.coinmarketcap.com/v1/ticker/" + currencyName);

            HttpResponse response = httpClient
                    .execute(request);

            String json = IOUtils.toString(response.getEntity().getContent());
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                currencyPrice = object.getString("price_usd");
            }
        } catch (Exception e) {
        }
        return String.format("%.5f", Double.parseDouble(currencyPrice));
    }
}
