package tn.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VkApiClientBuilder {
    public static VkApiClient vk() {
        TransportClient transportClient = new HttpTransportClient();
        return new VkApiClient(transportClient);
    }
}
