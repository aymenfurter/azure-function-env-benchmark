package envtest;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

public class Function {

    @FunctionName("benchmarkExistingVariable")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
       
        String variableExisting = "VARIABLE_EXISTS";
        String variableNotExisting = "VARIABLE_NOT_EXISTING";

        if (System.getenv(variableExisting) == null || System.getenv(variableNotExisting) != null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Environment variables not configured").build();
        }

        StringBuilder response = new StringBuilder();
        response.append(performLookupBenchmark(variableExisting));
        response.append(performLookupBenchmark(variableNotExisting));

        context.getLogger().info(response.toString());
        System.out.println(response);

        return request.createResponseBuilder(HttpStatus.OK).body(response).build();
    }

    private String performLookupBenchmark (String variable) {
            StringBuffer response = new StringBuffer();

            response.append("Performance Test for " + variable + "\n");
            long start = System.currentTimeMillis();
            
            for (int z = 0; z < 1000000; z++) {
                System.getenv(variable);
            }

            long end = System.currentTimeMillis();
            long duration = end - start;

            response.append("Duration: " + duration + "ms \n");
            return response.toString();
    }
}
