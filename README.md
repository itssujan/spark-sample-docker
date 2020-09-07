This is the readme

Covid Data : https://github.com/nytimes/covid-19-data

JSON to single lines : jq -c '.[]' us-counties.json > us-counties_1.json

Delete all indices : curl -X DELETE "localhost:9200/st_*?pretty"