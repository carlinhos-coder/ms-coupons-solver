package co.com.bancolombia.consumer.enviroments;

import co.com.bancolombia.enviroments.CouponsEnviroments;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CouponsEnvironmentsImpl implements CouponsEnviroments {
    private final String baseHost;
    private final String baseUri;

    public CouponsEnvironmentsImpl(@Value("${adapter.restconsumer.items.host}") String baseHost,
                                   @Value("${adapter.restconsumer.items.uri}") String baseUri) {
        this.baseHost = baseHost;
        this.baseUri = baseUri;
    }

    @Override
    public String getBaseHost() {
        return baseHost;
    }

    @Override
    public String getBaseUri() {
        return baseUri;
    }
}
