package configuration;

public class EsMapping {
    public class ElasticsearchMappings {

        // Define Elasticsearch mapping for the "products" index in a single line
        public static final String POST = "{\"mappings\":{\"properties\":{\"id\":{\"type\":\"long\"},\"content\":{\"type\":\"text\"},\"description\":{\"type\":\"text\"},\"title\":{\"type\":\"text\"}}}}";

    }

}
