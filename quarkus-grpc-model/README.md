## Generate model from proto files

Execute proto compiler:
```shell script
protoc -I=$SRC_DIR --java_out=${OUTPUT_DIR} path/to/your/proto/file
```

For this example:
```shell script
protoc --js_out=js --java_out=src/main/java src/main/resources/protos/addressbook.proto
```