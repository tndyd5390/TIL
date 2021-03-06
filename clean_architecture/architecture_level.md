# 아키텍쳐 수준
'수준(level)'을 엄밀하게 정의하면 **입력과 출력까지의 거리**이다.

다음 그림은 간단한 암호화 프로그램을 그린것으로 입력 장치에서 문자를 읽어서 번역한 후 출력 장치로 기록한다. 데이터의 희름은 굽은 실선 화살표로 표시했다.

![그림6](https://user-images.githubusercontent.com/24540286/140643808-70cce9b7-c16e-4e52-836b-711f809edbc9.png)

- 프로그램을 제대로 설계했다면 소스코드 의존성은 곧은 점선처럼 표시되어야 한다.
- 번역 컴포넌트는 이 시스템에서 최고 수준의 컴포넌트인데 이는 입력과 출력에서 가장 멀기 때문이다.
- 주목할 점은 데이터의 흐름과 소스코드의 의존성이 항상 같은 방향을 가리키지는 않는다.
- **소스코드의 의존성은 그 수준에 따라 결합되어야 하며 데이터 흐름을 기준으로 결합되어서는 안된다.**

위 그림을 기준으로 자칫하면 잘못된 아키텍쳐가 만들어지는데, 예를 들어 암호화 프로그램을 다음처럼 작성한다면 그렇게 된다.

```javascript
function encrypt(){
    while(true){
        writeChar(translate(readChar()));
    }
}
```

이는 잘못된 아키텍쳐이다. 고수준인 encrypt 함수가 저수준인 readChar와 writeChar 함수에 의존하기 때문이다. 

## 암호화 프로그램 개선

다음 그림은 위에서 설계한 암호화 프로그램은 개선한 것이다.

![그림7](https://user-images.githubusercontent.com/24540286/140643810-a88f021b-952b-4624-b763-64061f07cd64.png)

- 주목할 점은 Encrypt 클래스, CharWriter와 CharReader 인터페이스를 둘러싸고 있는 점선으로 된 경계이다. 
- 이 경계를 횡단하는 의존성은 모두 경계 안쪽으로 향한다. 이 경계로 묶인 영역이 최고수준의 영역이다. 

이 구조에서 고수준의 암호화 정책을 저수준의 입력/출력 정책으로부터 분리시킨 방식에 주목하자. **입력과 출력에 변화가 생기더라도 암호화 정책은 거의 영향을 받지 않는다**

- 정책을 컴포넌트로 묶는 기준은 정책이 변경되는 방에 달려있다.
- 단일 책임 원칙과 공통 폐쇄 원칙에 따르면 동일한 이유로 동일한 시점에 변경되는 정책은 같이 묶여야 한다. 
  
위 그림에서 입려고가 출력에서 멀리 떨어진 정책(암호화)는 저수준의 정책(입,출력)에 비해 덜 변경된다.  
이처럼 소스코드 의존성의 방향이 고수준의 정책을 향할 수 있다면 변경의 영향도를 줄일 수 있다. 

