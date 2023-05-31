# GenPhotoMd5
Java命令行小工具，功能:生成图片的md5值，并将图片的文件名重命名为其md5

# clone

```shell
git clone https://github.com/WeiZhenOoooo/GenPhotoMd5.git
```

## install

```shell
cd GenPhotoMd5
mvn package
```

## run

```shell
java -cp .\target\GenPhotoMd5-1.0-SNAPSHOT-jar-with-dependencies.jar com.xingfu.App --help
Usage: java -cp GenPhotoMd5-1.0-SNAPSHOT-jar-with-dependencies.jar
      com.xingfu.App [options]
  Options:
  * -input
      original photo location:path
  * -output
      md5 picture location:path
    -ext
      md5 picture extension
      Default: jpg
    --help

    --version
      photo-md5 tool version
```

查看参数要求
