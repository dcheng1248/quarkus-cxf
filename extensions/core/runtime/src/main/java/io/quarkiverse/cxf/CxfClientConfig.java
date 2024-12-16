package io.quarkiverse.cxf;

import java.util.List;
import java.util.Optional;

import org.apache.cxf.annotations.SchemaValidation.SchemaValidationType;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.transports.http.configuration.ConnectionType;
import org.apache.cxf.transports.http.configuration.ProxyServerType;

import io.quarkiverse.cxf.LoggingConfig.PerClientOrServiceLoggingConfig;
import io.quarkus.runtime.annotations.ConfigDocEnum;
import io.quarkus.runtime.annotations.ConfigGroup;
import io.smallrye.config.WithConverter;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

/**
 * A class that provides configurable options of a CXF client.
 */
@ConfigGroup
public interface CxfClientConfig {

    /**
     * A URL, resource path or local filesystem path pointing to a WSDL document to use when generating the service proxy of
     * this client.
     *
     * @asciidoclet
     * @since 1.0.0
     */
    @WithName("wsdl")
    public Optional<String> wsdlPath();

    /**
     * The URL of the SOAP Binding, should be one of four values:
     *
     * * `+http://schemas.xmlsoap.org/wsdl/soap/http+` for SOAP11HTTP_BINDING
     * * `+http://schemas.xmlsoap.org/wsdl/soap/http?mtom=true+` for SOAP11HTTP_MTOM_BINDING
     * * `+http://www.w3.org/2003/05/soap/bindings/HTTP/+` for SOAP12HTTP_BINDING
     * * `+http://www.w3.org/2003/05/soap/bindings/HTTP/?mtom=true+` for SOAP12HTTP_MTOM_BINDING
     *
     * @asciidoclet
     * @since 1.0.0
     */
    public Optional<String> soapBinding();

    /**
     * The client endpoint URL
     *
     * @asciidoclet
     * @since 1.0.0
     */
    public Optional<String> clientEndpointUrl();

    /**
     * The client endpoint namespace
     *
     * @asciidoclet
     * @since 1.0.0
     */
    public Optional<String> endpointNamespace();

    /**
     * The client endpoint name
     *
     * @asciidoclet
     * @since 1.0.0
     */
    public Optional<String> endpointName();

    /**
     * The username for HTTP Basic authentication
     *
     * @asciidoclet
     * @since 1.0.0
     */
    public Optional<String> username();

    /**
     * The password for HTTP Basic authentication
     *
     * @asciidoclet
     * @since 1.0.0
     */
    public Optional<String> password();

    /**
     * If `true`, then the `Authentication` header will be sent preemptively when requesting the WSDL, as long as the `username`
     * is set; otherwise the WSDL will be requested anonymously.
     *
     * @since 2.7.0
     * @asciidoclet
     */
    @WithDefault("false")
    public boolean secureWsdlAccess();

    /**
     * Logging related configuration
     *
     * @asciidoclet
     */
    PerClientOrServiceLoggingConfig logging();

    /**
     * A comma-separated list of fully qualified CXF Feature class names.
     *
     * Example:
     *
     * [source,properties]
     * ----
     * quarkus.cxf.endpoint."/my-endpoint".features = org.apache.cxf.ext.logging.LoggingFeature
     * ----
     *
     * @asciidoclet
     * @since 1.0.0
     */
    public Optional<List<String>> features();

    /**
     * The comma-separated list of Handler classes
     *
     * @asciidoclet
     * @since 1.0.0
     */
    public Optional<List<String>> handlers();

    /**
     * The comma-separated list of InInterceptor classes
     *
     * @asciidoclet
     * @since 1.0.0
     */
    public Optional<List<String>> inInterceptors();

    /**
     * The comma-separated list of OutInterceptor classes
     *
     * @asciidoclet
     * @since 1.0.0
     */
    public Optional<List<String>> outInterceptors();

    /**
     * The comma-separated list of OutFaultInterceptor classes
     *
     * @asciidoclet
     * @since 1.0.0
     */
    public Optional<List<String>> outFaultInterceptors();

    /**
     * The comma-separated list of InFaultInterceptor classes
     *
     * @asciidoclet
     * @since 1.0.0
     */
    public Optional<List<String>> inFaultInterceptors();

    /* org.apache.cxf.transports.http.configuration.HTTPClientPolicy attributes */
    /**
     * Specifies the amount of time, in milliseconds, that the consumer will attempt to establish a connection before it times
     * out. 0 is infinite.
     *
     * @since 2.2.3
     * @asciidoclet
     */
    @WithDefault("30000")
    public long connectionTimeout();

    /**
     * Specifies the amount of time, in milliseconds, that the consumer will wait for a response before it times out. 0 is
     * infinite.
     *
     * @since 2.2.3
     * @asciidoclet
     */
    @WithDefault("60000")
    public long receiveTimeout();

    /**
     * Specifies the amount of time, in milliseconds, used when requesting a connection from the connection manager(if
     * appliable). 0 is infinite.
     *
     * @since 2.2.3
     * @asciidoclet
     */
    @WithDefault("60000")
    public long connectionRequestTimeout();

    // The formatter breaks the list with long items
    // @formatter:off
    /**
     * If `true` this CXF client will follow HTTP redirects (HTTP status codes 301, 302, 303 and 307 with `Location` response
     * header); otherwise HTTP redirects will not be followed.
     *
     * [WARNING]
     * ====
     * Enabling this option may increase memory requirements of your application substantially as request bodies will
     * have to be cached for retransmission.
     * ====
     *
     * See also:
     *
     * * `xref:reference/extensions/quarkus-cxf.adoc#quarkus-cxf_quarkus-cxf-client-client-name-redirect-relative-uri[quarkus.cxf.client."client-name".redirect-relative-uri]`
     * * `xref:reference/extensions/quarkus-cxf.adoc#quarkus-cxf_quarkus-cxf-client-client-name-max-retransmits[quarkus.cxf.client."client-name".max-retransmits]`
     *
     * @since 2.2.3
     * @asciidoclet
     */
    // @formatter:on
    @WithDefault("false")
    public boolean autoRedirect();

    // The formatter breaks the list with long items
    // @formatter:off
    /**
     * If `true` relative URIs, such as `/folder/service` received via `Location` response header will be honored when
     * redirecting; otherwise only absolute URIs will be honored and an exception will be thrown for relative redirects.
     *
     * This is equivalent to setting `http.redirect.relative.uri` property to `true` on the CXF client request context.
     *
     * See also:
     *
     * * `xref:reference/extensions/quarkus-cxf.adoc#quarkus-cxf_quarkus-cxf-client-client-name-auto-redirect[quarkus.cxf.client."client-name".auto-redirect]`
     *
     * @since 3.17.0
     * @asciidoclet
     */
    // @formatter:on
    @WithDefault("false")
    public boolean redirectRelativeUri();

    // The formatter breaks the list with long items
    // @formatter:off
    /**
     * Specifies the maximum allowed number of retransmits when following redirects.
     * Retransmits for authorization are included in the retransmit count.
     * Each 401 UNAUTHORIZED response may cause a new retransmit.
     * A negative value indicates unlimited retransmits, but even in that case redirect loop protection is provided.
     *
     * See also:
     *
     * * `xref:reference/extensions/quarkus-cxf.adoc#quarkus-cxf_quarkus-cxf-client-client-name-auto-redirect[quarkus.cxf.client."client-name".auto-redirect]`
     * * `xref:reference/extensions/quarkus-cxf.adoc#quarkus-cxf_quarkus-cxf-client-client-name-max-same-uri[quarkus.cxf.client."client-name".max-same-uri]`
     *
     * @since 2.2.3
     * @asciidoclet
     */
    // @formatter:on
    @WithDefault("-1")
    public int maxRetransmits();

    // The formatter breaks the list with long items
    // @formatter:off
    /**
     * Specifies the maximum allowed number of retransmits to the same URI when following HTTP redirects.
     * The default behavior is to allow a no retransmits to the same URI.
     *
     * See also:
     *
     * * `xref:reference/extensions/quarkus-cxf.adoc#quarkus-cxf_quarkus-cxf-client-client-name-auto-redirect[quarkus.cxf.client."client-name".auto-redirect]`
     * * `xref:reference/extensions/quarkus-cxf.adoc#quarkus-cxf_quarkus-cxf-client-client-name-max-retransmits[quarkus.cxf.client."client-name".max-retransmits]`
     *
     * @since 3.18.0
     * @asciidoclet
     */
    // @formatter:on
    @WithDefault("0")
    public int maxSameUri();

    /**
     * If true, the client is free to use chunking streams if it wants, but it is not required to use chunking streams. If
     * false, the client must use regular, non-chunked requests in all cases.
     *
     * @since 2.2.3
     * @asciidoclet
     */
    @WithDefault("true")
    public boolean allowChunking();

    /**
     * If AllowChunking is true, this sets the threshold at which messages start getting chunked. Messages under this limit do
     * not get chunked.
     *
     * @since 2.2.3
     * @asciidoclet
     */
    @WithDefault("4096")
    public int chunkingThreshold();

    /**
     * Specifies the chunk length for a HttpURLConnection. This value is used in
     * java.net.HttpURLConnection.setChunkedStreamingMode(int chunklen). chunklen indicates the number of bytes to write in each
     * chunk. If chunklen is less than or equal to zero, a default value will be used.
     *
     * @since 2.2.3
     * @asciidoclet
     */
    @WithDefault("-1")
    public int chunkLength();

    /**
     * Specifies the MIME types the client is prepared to handle (e.g., HTML, JPEG, GIF, etc.)
     *
     * @since 2.2.3
     * @asciidoclet
     */
    public Optional<String> accept();

    /**
     * Specifies the language the client desires (e.g., English, French, etc.)
     *
     * @asciidoclet
     * @since 2.2.3
     */
    public Optional<String> acceptLanguage();

    /**
     * Specifies the encoding the client is prepared to handle (e.g., gzip)
     *
     * @since 2.2.3
     * @asciidoclet
     */
    public Optional<String> acceptEncoding();

    /**
     * Specifies the content type of the stream being sent in a post request. (this should be text/xml for web services, or can
     * be set to application/x-www-form-urlencoded if the client is sending form data).
     *
     * @since 2.2.3
     * @asciidoclet
     */
    public Optional<String> contentType();

    /**
     * Specifies the Internet host and port number of the resource on which the request is being invoked. This is sent by
     * default based upon the URL. Certain DNS scenarios or application designs may request you to set this, but typically it is
     * not required.
     *
     * @since 2.2.3
     * @asciidoclet
     */
    public Optional<String> host();

    /**
     * The connection disposition. If close the connection to the server is closed after each request/response dialog. If
     * Keep-Alive the client requests the server to keep the connection open, and if the server honors the keep alive request,
     * the connection is reused. Many servers and proxies do not honor keep-alive requests.
     *
     * @since 2.2.3
     * @asciidoclet
     */
    @WithDefault("keep-alive")
    @WithConverter(ConnectionTypeConverter.class)
    @ConfigDocEnum(enforceHyphenateValues = true)
    public ConnectionType connection();

    /**
     * Most commonly used to specify no-cache, however the standard supports a dozen or so caching related directives for
     * requests
     *
     * @since 2.2.3
     * @asciidoclet
     */
    public Optional<String> cacheControl();

    /**
     * HTTP Version used for the connection. The default value `auto` will use whatever the default is for the `HTTPConduit`
     * implementation defined via `quarkus.cxf.client."client-name".http-conduit-factory`. Other possible values: `1.1`, `2`.
     *
     * Some of these values might be unsupported by some `HTTPConduit` implementations.
     *
     * @since 2.2.3
     * @asciidoclet
     */
    @WithDefault("auto")
    public String version();

    /**
     * The value of the `User-Agent` HTTP header.
     *
     * @since 2.2.3
     * @asciidoclet
     */
    public Optional<String> browserType();

    /**
     * An URI path (starting with `/`) or a full URI for the receipt of responses over a separate provider -> consumer
     * connection. If the value starts with `/`, then it is prefixed with the base URI configured via
     * `quarkus.cxf.client."client-name".decoupled-endpoint-base` before being used as a value for the WS-Addressing `ReplyTo`
     * message header.
     *
     * @since 2.2.3
     * @asciidoclet
     */
    Optional<String> decoupledEndpoint();

    /**
     * Specifies the address of proxy server if one is used.
     *
     * @since 2.2.3
     * @asciidoclet
     */
    public Optional<String> proxyServer();

    /**
     * Specifies the port number used by the proxy server.
     *
     * @since 2.2.3
     * @asciidoclet
     */
    public Optional<Integer> proxyServerPort();

    /**
     * Specifies the list of hostnames that will not use the proxy configuration. Examples:
     *
     * - `localhost` - a single hostname
     * - `localhost++\|++www.google.com` - two hostnames that will not use the proxy configuration
     * - `localhost++\|++www.google.++*\|*++.apache.org` - hostname patterns
     *
     * @since 2.2.3
     * @asciidoclet
     */
    public Optional<String> nonProxyHosts();

    /**
     * Specifies the type of the proxy server. Can be either HTTP or SOCKS.
     *
     * @since 2.2.3
     * @asciidoclet
     */
    @WithDefault("HTTP")
    public ProxyServerType proxyServerType();

    /**
     * Username for the proxy authentication
     *
     * @since 2.2.3
     * @asciidoclet
     */
    public Optional<String> proxyUsername();

    /**
     * Password for the proxy authentication
     *
     * @since 2.2.3
     * @asciidoclet
     */
    public Optional<String> proxyPassword();

    /**
     * Select the `HTTPConduitFactory` implementation for this client.
     *
     * - `QuarkusCXFDefault` (default): if `io.quarkiverse.cxf:quarkus-cxf-rt-transports-http-hc5` is present in class path,
     * then its `HTTPConduitFactory` implementation will be used; otherwise, since 3.16.0, this value is equivalent with
     * `VertxHttpClientHTTPConduitFactory`. Before 3.16.0, the effective default was `URLConnectionHTTPConduitFactory`.
     * - `CXFDefault`: the selection of `HTTPConduitFactory` implementation is left to CXF
     * - `VertxHttpClientHTTPConduitFactory`: the `HTTPConduitFactory` for this client will be set to
     * an implementation always returning `io.quarkiverse.cxf.vertx.http.client.VertxHttpClientHTTPConduit`. This will
     * use `io.vertx.core.http.HttpClient` as the underlying HTTP client. Since {quarkus-cxf-project-name} 3.13.0.
     * - `HttpClientHTTPConduitFactory`: the `HTTPConduitFactory` for this client will be set to an implementation always
     * returning `org.apache.cxf.transport.http.HttpClientHTTPConduit`. This will use `java.net.http.HttpClient` as the
     * underlying HTTP client.
     * +
     * *Deprecated since
     * xref:release-notes/3.18.0.adoc#_1633_httpclienthttpconduitfactory_value_of_http_conduit_factory_deprecated[3.18.0]*
     * +++-+++ use other `HTTPConduitFactory` implementations, preferably `QuarkusCXFDefault`.
     * - `URLConnectionHTTPConduitFactory`: the `HTTPConduitFactory` for this client will be set to an implementation always
     * returning `org.apache.cxf.transport.http.URLConnectionHTTPConduit`. This will use `java.net.HttpURLConnection` as the
     * underlying HTTP client.
     *
     * @asciidoclet
     * @since 2.3.0
     */
    public Optional<HTTPConduitImpl> httpConduitFactory();

    /**
     * The name of the TLS configuration to use for setting up trust store and keystore for this SOAP client.
     *
     * If not set and `.trust-store` or `.key-store` is configured then that the configuration from `.trust-store*`
     * and `.key-store*` family of options will be used.
     * If a name is configured, it uses the configuration from `quarkus.tls.<name>.*`
     * If a name is configured, but no TLS configuration is found with that name then an error will be thrown at runtime.
     * Setting `.tls-configuration-name` and any of `.trust-store` or `.key-store` leads to an exception at runtime.
     *
     * @asciidoclet
     * @since 3.15.0
     */
    Optional<String> tlsConfigurationName();

    /**
     * The key store location for this client. The resource is first looked up in the classpath, then in the file system.
     *
     * *Deprecated* - use
     * `xref:reference/extensions/quarkus-cxf.adoc#quarkus-cxf_quarkus-cxf-client-client-name-tls-configuration-name[quarkus.cxf.client."client-name".tls-configuration-name]`
     * instead.
     *
     * @asciidoclet
     * @since 3.8.1
     * @deprecated Use {@link #tlsConfigurationName()}
     */
    @Deprecated
    public Optional<String> keyStore();

    /**
     * The key store password.
     *
     * *Deprecated* - use
     * `xref:reference/extensions/quarkus-cxf.adoc#quarkus-cxf_quarkus-cxf-client-client-name-tls-configuration-name[quarkus.cxf.client."client-name".tls-configuration-name]`
     * instead.
     *
     * @asciidoclet
     * @since 3.8.1
     * @deprecated Use {@link #tlsConfigurationName()}
     */
    @Deprecated
    public Optional<String> keyStorePassword();

    /**
     * The type of the key store.
     *
     * *Deprecated* - use
     * `xref:reference/extensions/quarkus-cxf.adoc#quarkus-cxf_quarkus-cxf-client-client-name-tls-configuration-name[quarkus.cxf.client."client-name".tls-configuration-name]`
     * instead.
     *
     * @asciidoclet
     * @since 3.8.1
     * @deprecated Use {@link #tlsConfigurationName()}
     */
    @WithDefault("JKS")
    @Deprecated
    public String keyStoreType();

    /**
     * The key password.
     *
     * *Deprecated* - use
     * `xref:reference/extensions/quarkus-cxf.adoc#quarkus-cxf_quarkus-cxf-client-client-name-tls-configuration-name[quarkus.cxf.client."client-name".tls-configuration-name]`
     * instead.
     *
     * @asciidoclet
     * @since 3.8.1
     * @deprecated Use {@link #tlsConfigurationName()}
     */
    @Deprecated
    public Optional<String> keyPassword();

    /**
     * The trust store location for this client. The resource is first looked up in the classpath, then in the file system.
     *
     * *Deprecated* - use
     * `xref:reference/extensions/quarkus-cxf.adoc#quarkus-cxf_quarkus-cxf-client-client-name-tls-configuration-name[quarkus.cxf.client."client-name".tls-configuration-name]`
     * instead.
     *
     * @asciidoclet
     * @since 2.5.0
     * @deprecated Use {@link #tlsConfigurationName()}
     */
    @Deprecated
    public Optional<String> trustStore();

    /**
     * The trust store password.
     *
     * *Deprecated* - use
     * `xref:reference/extensions/quarkus-cxf.adoc#quarkus-cxf_quarkus-cxf-client-client-name-tls-configuration-name[quarkus.cxf.client."client-name".tls-configuration-name]`
     * instead.
     *
     * @asciidoclet
     * @since 2.5.0
     * @deprecated Use {@link #tlsConfigurationName()}
     */
    @Deprecated
    public Optional<String> trustStorePassword();

    /**
     * The type of the trust store.
     *
     * *Deprecated* - use
     * `xref:reference/extensions/quarkus-cxf.adoc#quarkus-cxf_quarkus-cxf-client-client-name-tls-configuration-name[quarkus.cxf.client."client-name".tls-configuration-name]`
     * instead.
     *
     * @asciidoclet
     * @since 2.5.0
     * @deprecated Use {@link #tlsConfigurationName()}
     */
    @WithDefault("JKS")
    @Deprecated
    public String trustStoreType();

    /**
     * Can be one of the following:
     *
     * - One of the well known values: `AllowAllHostnameVerifier`, `HttpsURLConnectionDefaultHostnameVerifier`
     * - A fully qualified class name implementing `javax.net.ssl.HostnameVerifier` to look up in the CDI container.
     * - A bean name prefixed with `++#++` that will be looked up in the CDI container; example: `++#++myHostnameVerifier`
     *
     * If not specified, then the creation of the `HostnameVerifier` is delegated to CXF, which boils down to
     * `org.apache.cxf.transport.https.httpclient.DefaultHostnameVerifier` with the default
     * `org.apache.cxf.transport.https.httpclient.PublicSuffixMatcherLoader` as returned from
     * `PublicSuffixMatcherLoader.getDefault()`.
     *
     * [IMPORTANT]
     * ====
     * Setting this option when the conduit factory of this client is set to `VertxHttpClientHTTPConduitFactory`
     * (default since {quarkus-cxf-project-name} 3.16.0) leads to an exception at runtime.
     * The `AllowAllHostnameVerifier` value of this option can be replaced by using a
     * xref:reference/extensions/quarkus-cxf.adoc#quarkus-cxf_quarkus-cxf-client-client-name-tls-configuration-name[named TLS
     * configuration]
     * with
     * `{link-quarkus-docs-base}/tls-registry-reference#trusting-all-certificates-and-hostname-verification[hostname-verification-algorithm]`
     * set to `NONE`. Otherwise, there is no way to implement custom hostname verification for Vert.x HTTP client.
     * ====
     *
     * [IMPORTANT]
     * ====
     * Since {quarkus-cxf-project-name} 3.18.0, setting this option when the conduit factory of this client is set to
     * `HttpClientHTTPConduitFactory` is not supported and leads to an exception at runtime.
     * See https://github.com/quarkiverse/quarkus-cxf/issues/1687[#1687].
     * ====
     *
     * @asciidoclet
     * @since 2.5.0
     */
    public Optional<String> hostnameVerifier();

    /**
     * Select for which messages XML Schema validation should be enabled. If not specified, no XML Schema validation will be
     * enforced unless it is enabled by other means, such as `&#64;org.apache.cxf.annotations.SchemaValidation` or
     * `&#64;org.apache.cxf.annotations.EndpointProperty(key = "schema-validation-enabled", value = "true")` annotations.
     *
     * @since 2.7.0
     * @asciidoclet
     */
    @WithName("schema-validation.enabled-for")
    public Optional<SchemaValidationType> schemaValidationEnabledFor();

    public enum WellKnownHostnameVerifier {

        AllowAllHostnameVerifier {

            @Override
            public void configure(TLSClientParameters params) {
                params.setDisableCNCheck(true);
            }
        },
        HttpsURLConnectionDefaultHostnameVerifier {

            @Override
            public void configure(TLSClientParameters params) {
                params.setUseHttpsURLConnectionDefaultHostnameVerifier(true);
            }
        };

        public abstract void configure(TLSClientParameters params);

        public static Optional<WellKnownHostnameVerifier> of(String name) {
            if (name == null) {
                return Optional.empty();
            }
            for (WellKnownHostnameVerifier item : values()) {
                if (item.name().equals(name)) {
                    return Optional.of(item);
                }
            }
            return Optional.empty();
        }
    }
}
