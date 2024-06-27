#!/bin/bash

cd $(dirname $(realpath $0))/..

protoc --plugin=java --java_out=src/main/java/ $(find src -name "*.proto")

