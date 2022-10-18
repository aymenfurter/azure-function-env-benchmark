package envtest;

import com.microsoft.azure.functions.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.*;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


public class FunctionTest {
    @Test
    public void testHttpTriggerJava() throws Exception {
        String variableExisting = "VARIABLE_EXISTS";
        String variableNotExisting = "VARIABLE_NOT_EXISTING";

        assertNull(System.getenv(variableNotExisting));
        assertNotNull(System.getenv(variableExisting));
        
        final ExecutionContext context = mock(ExecutionContext.class);
        doReturn(Logger.getGlobal()).when(context).getLogger();

        // Invoke
        final HttpResponseMessage ret = new Function().run(createHttpRequestMessageMock(), context);        

        // Verify
        assertEquals(ret.getStatus(), HttpStatus.OK);
    }

    private HttpRequestMessage<Optional<String>> createHttpRequestMessageMock () {
         @SuppressWarnings("unchecked")
        final HttpRequestMessage<Optional<String>> req = mock(HttpRequestMessage.class);

        doAnswer(new Answer<HttpResponseMessage.Builder>() {
            @Override
            public HttpResponseMessage.Builder answer(InvocationOnMock invocation) {
                HttpStatus status = (HttpStatus) invocation.getArguments()[0];
                return new HttpResponseMessageMock.HttpResponseMessageBuilderMock().status(status);
            }
        }).when(req).createResponseBuilder(any(HttpStatus.class));

        return req;
         }
}
