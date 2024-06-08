package configuration;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ElasticsearchMappingService {

    private final RestHighLevelClient client;

    public ElasticsearchMappingService(RestHighLevelClient client) {
        this.client = client;
    }

    public void createIndexMapping(String indexName, String mapping) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);


        // Set the mapping and create the index
        request.source(mapping, XContentType.JSON);
        client.indices().create(request, RequestOptions.DEFAULT);
    }


}

