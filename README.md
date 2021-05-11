# Network 간단한 예제
## 1.구조 설명
통신을 하기 위한 기본 예제를 간단하게 구성.<br/>
기본 구조는 하나의 Activity에 [Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=ko)를 사용해서
[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=ko), Module 을 주입했다.

## 2. 사용 기술
- Hilt
- MVVM
- Kotlin
- Retrofit2
- Rx

## 3. 참고
- [network_security_config](https://github.com/wlgnsdi/NetworkExample/blob/master/app/src/main/res/xml/network_security_config.xml) (경로 res -> xml)
<br/>
: 안드로이드 9.0 부터는 https를 사용하도록 강제 하기 때문에 http를 사용하는 URL 도 허용하기 위해서 추가 - [링크 참고](https://gun0912.tistory.com/80#recentComments)
