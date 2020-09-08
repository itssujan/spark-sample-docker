#### What does this application do?
Reads from Kafka datasource and does minor modifications to the state name and pushes the data into ElasticSearch

##### Infrastructure
Everything required for running this application has been dockerized.
1. Kafka
2. Spark
3. ElasticSearch
4. Kibana

##### How to run the application

First build the application using `sbt assembly`.
Then submit to Spark using `./bin/run`. 
This should get the spark application running

Now lets push some data to Kafka by running `./bin/start-events`
At this point, Spark should start processing data and publishing it into ElasticSearch

You can look at the data in ElasticSearch using these urls http://localhost:9200/_cat/indices
Statewise data : http://localhost:9200/st_north_carolina/_search

Also, we can link Kibana to ElasticSearch to build various dashboards : http://localhost:5601/

#### Some useful code
If you want to delete all the created indices in ElasticSearch : `curl -X DELETE "localhost:9200/st_*?pretty"`
Conver JSON to single lines : `jq -c '.[]' us-counties.json > us-counties_1.json`

References:
Covid Data : https://github.com/nytimes/covid-19-data
