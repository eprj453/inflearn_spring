# IntelliJ Community로 Spring Project 생성하기

https://post.naver.com/viewer/postView.nhn?volumeNo=9379003&memberNo=2490752

**1. New Project -> gradle 프로젝트 생성**



**2. GroupId, ArtifactId를 입력한다. GroupId는 대부분 사이트 도메인을 뒤집어 작성하고, ArtifactId는 해당 프로젝트명을 입력한다.**

**예 : GroupId : com.test / ArtifactId : testpsw**



**3. build.gradle의 내용을 작성한다 (spring library를 import하기 위함)**

```xml
plugins {
	id 'groovy'
    id 'org.springframework.boot' version '2.2.5.RELEASE'
    id 'io.spring.dependency-management' version '1.0.9.RELEASE'
    id 'java'

}

group = 'com.test' 
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '8'

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    testImplementation('org.springframework.boot:spring-boot-starter-test') {
        exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
    }
}

test {
    useJUnitPlatform()
}
```

dependencies

