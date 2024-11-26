package io.quarkiverse.cxf.it.large.slow.generated;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceFeature;
import jakarta.xml.ws.Service;

/**
 * This class was generated by Apache CXF 4.0.5
 * 2024-11-21T16:40:52.628+01:00
 * Generated source version: 4.0.5
 *
 */
@WebServiceClient(name = "LargeSlowService",
                  wsdlLocation = "file:/home/ppalaga/orgs/cxf/qcxf/integration-tests/client-server/src/main/resources/wsdl/LargeSlow.wsdl",
                  targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test")
public class LargeSlowService_Service extends Service {

    public static final URL WSDL_LOCATION;

    public static final QName SERVICE = new QName("https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", "LargeSlowService");
    public static final QName LargeSlowServiceImplPort = new QName("https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", "LargeSlowServiceImplPort");
    static {
        URL url = null;
        try {
            url = URI.create("file:/home/ppalaga/orgs/cxf/qcxf/integration-tests/client-server/src/main/resources/wsdl/LargeSlow.wsdl").toURL();
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(LargeSlowService_Service.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:/home/ppalaga/orgs/cxf/qcxf/integration-tests/client-server/src/main/resources/wsdl/LargeSlow.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public LargeSlowService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public LargeSlowService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LargeSlowService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    public LargeSlowService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public LargeSlowService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public LargeSlowService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns LargeSlowService
     */
    @WebEndpoint(name = "LargeSlowServiceImplPort")
    public LargeSlowService getLargeSlowServiceImplPort() {
        return super.getPort(LargeSlowServiceImplPort, LargeSlowService.class);
    }

    /**
     *
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LargeSlowService
     */
    @WebEndpoint(name = "LargeSlowServiceImplPort")
    public LargeSlowService getLargeSlowServiceImplPort(WebServiceFeature... features) {
        return super.getPort(LargeSlowServiceImplPort, LargeSlowService.class, features);
    }

}