# Teensy-weensy-url

Simple URL Shortener in Scala and Play Framework


## Build process:

```shell script
sh build.sh
docker buildx build --platform linux/arm64 -t helkarou/twu --push -f dockerfile.arm64v8 .
```