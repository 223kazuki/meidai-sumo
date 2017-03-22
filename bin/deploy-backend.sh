#!/bin/bash

lein.bat do clean, uberjar
# aws --profile meidai-sumo lambda create-function \
# --region ap-northeast-1 \
# --function-name meidai-sumo-backend \
# --zip-file fileb://`dirname $0`/../target/om-next-template-v0.0.0-standalone.jar \
# --role arn:aws:iam::963006559076:role/lambda_basic_execution \
# --handler club.meidai-sumo.OmNextHandler \
# --runtime java8 \
# --timeout 15 \
# --memory-size 512
aws --profile meidai-sumo lambda update-function-code \
--function-name meidai-sumo-backend \
--zip-file fileb://`dirname $0`/../target/meidai-sumo.jar
