package org.mddarr.inventory;

import org.apache.kafka.streams.kstream.Initializer;
import org.apache.kafka.streams.state.QueryableStoreType;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.kafka.streams.state.QueryableStoreTypes.keyValueStore;

@RestController
public class InventoryController {
    private InteractiveQueryService interactiveQueryService;
    @RequestMapping("/events")
    public String events(){
        Initializer<String> a = String::new;

        final ReadOnlyKeyValueStore<String, String> topFiveStore =
                interactiveQueryService.getQueryableStore("test-events-snapshots",  QueryableStoreTypes.<String, String>keyValueStore());
        return topFiveStore.get("12345");
    }

}
