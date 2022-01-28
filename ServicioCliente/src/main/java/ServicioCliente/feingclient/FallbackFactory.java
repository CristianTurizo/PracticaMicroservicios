package ServicioCliente.feingclient;

import org.springframework.stereotype.Component;

@Component
public class FallbackFactory implements org.springframework.cloud.openfeign.FallbackFactory<ImagenFeingFallback> {

    @Override
    public ImagenFeingFallback create(Throwable cause) {
        return new ImagenFeingFallback();
    }
}
