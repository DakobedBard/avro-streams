FROM confluentinc/cp-kafka-connect-base:5.4.1

RUN   confluent-hub install --no-prompt hpgrahsl/kafka-connect-mongodb:1.1.0 \
   && confluent-hub install --no-prompt microsoft/kafka-connect-iothub:0.6 \
   && confluent-hub install --no-prompt wepay/kafka-connect-bigquery:1.1.0
