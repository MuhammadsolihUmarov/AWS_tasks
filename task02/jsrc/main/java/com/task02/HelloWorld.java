package com.task02;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.HashMap;
import java.util.Map;

public class HelloWorld implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
		String path = request.getPath();
		String method = request.getHttpMethod();

		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		response.setHeaders(headers);

		// Handle /hello path with GET method
		if ("/hello".equals(path) && "GET".equalsIgnoreCase(method)) {
			response.setStatusCode(200);
			response.setBody("{\"message\": \"Hello from Lambda\"}");
		}
		// Handle other paths with 400 Bad Request
		else {
			response.setStatusCode(400);
			response.setBody("{\"message\": \"Bad request syntax or unsupported method. " +
					"Request path: " + path + ". HTTP method: " + method + "\"}");
		}

		return response;
	}
}
