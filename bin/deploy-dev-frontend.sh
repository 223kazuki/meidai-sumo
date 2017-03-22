#!/bin/bash

lein.bat do clean, cljsbuild once advanced
aws --profile=meidai-sumo s3 cp `dirname $0`/../resources/index.html s3://dev.meidai-sumo.club
aws --profile=meidai-sumo s3 sync `dirname $0`/../resources/public s3://dev.meidai-sumo.club