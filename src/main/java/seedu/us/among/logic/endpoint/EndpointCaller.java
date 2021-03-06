package seedu.us.among.logic.endpoint;

import java.io.IOException;

import seedu.us.among.logic.endpoint.exceptions.RequestException;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.MethodType;
import seedu.us.among.model.endpoint.Response;

/**
 * Contains the logic for making API calls.
 */
public class EndpointCaller {
    private final Endpoint endpointToSend;

    /**
     * Constructor for Endpointcaller.
     *
     * @param endpointToSend endpoint to make API call on
     */
    public EndpointCaller(Endpoint endpointToSend) {
        this.endpointToSend = endpointToSend;
    }

    /**
     * Sends the appropriate request based on attributes provided in EndpointCaller.
     *
     * @return response of API call
     */
    public Response callEndpoint() throws IOException, RequestException {

        Response response = new Response();
        MethodType requestMethod = this.endpointToSend.getMethod().getMethodType();

        switch (requestMethod) {
        case GET:
            response = new GetRequest(endpointToSend).send();
            break;
        case POST:
            response = new PostRequest(endpointToSend).send();
            break;
        case PUT:
            //to-do
            break;
        case DELETE:
            //to-do
            break;
        case HEAD:
            //to-do
            break;
        case OPTIONS:
            //to-do
            break;
        case PATCH:
            //to-do
        default:
            break;
        }

        return response;
    }
}
