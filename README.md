# 실행 방법

Command Line Interface 사용 시 아래의 명령으로 프로그램을 실행할 수 있습니다.

```shell
# (1) .java 파일들이 위치한 디렉터리로 이동
$ cd src


# (2) Java 프로그램을 컴파일
$ javac Main.java


# (3) sqlite-jdbc를 classpath에 추가하여 Main 클래스 실행

# (3-a) 저는 MacOS를 사용 중이기에 다음의 명령을 통해 Main 클래스를 실행합니다:
$ java -classpath ".:sqlite-jdbc-3.43.2.2.jar:slf4j-api-1.7.36.jar" Main

# (3-b) Windows 사용자는 다음과 같이 실행합니다:
$ java -classpath ".;sqlite-jdbc-3.43.2.2.jar;slf4j-api-1.7.36.jar" Main
```

## 왜 `-classpath` 옵션을 지정해야 하나요?

본 프로그램은 SQLite 데이터베이스를 사용하여 실습환경을 구축하고 있습니다.

SQLite 사용을 위해 다음 저장소에서 SQLite `.jar` 파일을 다운로드하여 사용하고 있습니다.
* <https://github.com/xerial/sqlite-jdbc>

위 저장소의 README.md를 정독해보면 최신 배포판인 `sqlite-jdbc-3.43.2.2.jar`에서는 `slf4j-api-1.7.36.jar` 추가 의존성을 사용해야 한다고 명시되어있습니다.

따라서, 본 프로그램 저장소의 `/src` 디렉터리에는 `sqlite-jdbc`와 `slf4j-api`의 `.jar`파일이 모두 구비되어 있으며, 상기 언급한 실행 방법을 통해서 프로그램을 구동할 수 있습니다.
