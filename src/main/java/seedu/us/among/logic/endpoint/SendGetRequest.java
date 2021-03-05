package seedu.us.among.logic.endpoint;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Response;

/**
 * Contains the logic for sending get requests.
 */
public class SendGetRequest extends SendRequest {

    /**
     * Constructor for SendGetRequest.
     *
     * @param endpoint endpoint to make API call on
     */
    public SendGetRequest(Endpoint endpoint) {
        super(endpoint.getMethod().getMethodType(), endpoint.getAddress().value);
    }

    /**
     * Executes the API call with a get request.
     *
     * @return returns the response from the API call
     */
    @Override
    public Response execute() throws IOException {
        //solution adapted from https://mkyong.com/java/apache-httpclient-examples/
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response;
        String responseEntity = "";

        try {
            HttpGet request = new HttpGet(this.getAddress());
            response = httpClient.execute(request);

            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    //return data as string
                    responseEntity = EntityUtils.toString(entity);
                }

            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }

        return new Response(response.getProtocolVersion().toString(),
                String.valueOf(response.getStatusLine().getStatusCode()),
                response.getStatusLine().getReasonPhrase(),
                response.getStatusLine().toString(), responseEntity);
    }
}
