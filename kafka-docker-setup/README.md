Start docker daemon and run the command :

`docker-compose -f kafka-setup.yaml up -d`

This will set up the kafka brokers for you.
Refer to the scripts folder if you want to run any kafka CLI based scripts.

Create topics using :
`kafka-topics --create --bootstrap-server localhost:9093 --topic checkout-topic`

