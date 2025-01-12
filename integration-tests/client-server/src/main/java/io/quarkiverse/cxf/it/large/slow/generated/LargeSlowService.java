package io.quarkiverse.cxf.it.large.slow.generated;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.AsyncHandler;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.Response;
import jakarta.xml.ws.ResponseWrapper;
import java.util.concurrent.Future;

/**
 * This class was generated by Apache CXF 4.0.5
 * 2024-11-21T16:40:52.615+01:00
 * Generated source version: 4.0.5
 *
 */
@WebService(targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", name = "LargeSlowService")
@XmlSeeAlso({ObjectFactory.class})
public interface LargeSlowService {

    @WebMethod(operationName = "largeSlow")
    @RequestWrapper(localName = "largeSlow", targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", className = "io.quarkiverse.cxf.it.large.slow.generated.LargeSlow")
    @ResponseWrapper(localName = "largeSlowResponse", targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", className = "io.quarkiverse.cxf.it.large.slow.generated.LargeSlowResponse")
    public Response<io.quarkiverse.cxf.it.large.slow.generated.LargeSlowResponse> largeSlowAsync(

        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1
    );

    @WebMethod(operationName = "largeSlow")
    @ResponseWrapper(localName = "largeSlowResponse", targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", className = "io.quarkiverse.cxf.it.large.slow.generated.LargeSlowResponse")
    @RequestWrapper(localName = "largeSlow", targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", className = "io.quarkiverse.cxf.it.large.slow.generated.LargeSlow")
    public Future<?> largeSlowAsync(

        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<io.quarkiverse.cxf.it.large.slow.generated.LargeSlowResponse> asyncHandler
    );

    @WebMethod
    @RequestWrapper(localName = "largeSlow", targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", className = "io.quarkiverse.cxf.it.large.slow.generated.LargeSlow")
    @ResponseWrapper(localName = "largeSlowResponse", targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", className = "io.quarkiverse.cxf.it.large.slow.generated.LargeSlowResponse")
    @WebResult(name = "return", targetNamespace = "")
    public io.quarkiverse.cxf.it.large.slow.generated.LargeSlowOutput largeSlow(

        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1
    );
}
