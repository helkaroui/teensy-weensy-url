name="teensy-weensy-url"
organization="charik"
version="1.0.1"

repository="$organization/$name"

echo "#################################################"
echo "### BUILDING images for multiple architecture ###"
echo "#################################################"


for docker_arch in amd64 arm64v8; do
  case ${docker_arch} in
    amd64   ) baseimage_arch="openjdk" ;;
    arm64v8 ) baseimage_arch="arm64v8/openjdk" ;;
  esac

  case ${docker_arch} in
    amd64   ) platform_arch="linux/amd64" ;;
    arm64v8 ) platform_arch="linux/arm64" ;;
  esac

  cp Dockerfile.cross Dockerfile.${docker_arch}
  sed -i -- "s~__BASEIMAGE_ARCH__~$baseimage_arch~" Dockerfile.${docker_arch}

  docker buildx build --platform ${platform_arch} -t ${repository}:${version}-${docker_arch} --push -f Dockerfile.${docker_arch} .
done


rm -r -f ~/.docker/manifests/*

echo "#######################################################"
echo "###         Creating docker manifest version: $version ###"
echo "#######################################################"

manifest_args="$repository:$version"
for docker_arch in amd64 arm64v8; do manifest_args=$manifest_args" $repository:${version}-${docker_arch} "; done;

docker manifest create $manifest_args --amend

for docker_arch in amd64 arm64v8; do
    case ${docker_arch} in
      arm64v8   ) docker manifest annotate ${repository}:${version} ${repository}:${version}-arm64v8 --os linux --arch arm64 --variant v8  ;;
      amd64     ) docker manifest annotate ${repository}:${version} ${repository}:${version}-amd64 --os linux --arch amd64 ;;
    esac
done

docker manifest push ${repository}:${version}




echo "#######################################################"
echo "###         Creating docker manifest latest         ###"
echo "#######################################################"

manifest_args="$repository:latest"
for docker_arch in amd64 arm64v8; do manifest_args=$manifest_args" $repository:${version}-${docker_arch} "; done;

docker manifest create $manifest_args --amend

for docker_arch in amd64 arm64v8; do
    case ${docker_arch} in
      arm64v8   ) docker manifest annotate ${repository}:latest ${repository}:${version}-arm64v8 --os linux --arch arm64 --variant v8  ;;
      amd64     ) docker manifest annotate ${repository}:latest ${repository}:${version}-amd64 --os linux --arch amd64 ;;
    esac
done

docker manifest push ${repository}:latest
