# 의존성 역전
A 클래스에서 B 클래스를 호출할 때 우리는 A 클래스는 B 클래스에 의존하고 있다고 말한다.

## 다형성이 등장하기 전의 의존성
다형성을 안전하고 편리하게 적용할 수 있는 매커니즘이 등장하기 전 소프트웨어는 main 함수가 고수준의 함수를 호출하고 고수준 함수는 다시 중간 수준 함수를 호출하며 중간수준은 다시 저수준의 함수를 호출하는 방식을 사용했다. 이러한 호출 트리에서 소스코드 의존성의 방향은 반드시 제어흐름(고->중->저)을 따르게 된다.  
main 함수가 고수준의 함수를 호출하려면 고수준 함수가 포 함된 모듈의 이름을 지정해야한다(#include, import). 이러한 제약조건으로 인해 제어흐름은 시스템의 행위에 따라 결정되며 소스코드 의존성은 제어흐름에 따라 결정된다.

## 다형성이 등장하고 난 뒤의 의존성
위와 같은 제어흐름에 따른 소스코드의 의존성에서 다형성이 끼어들면 무언가 특별한 일이 일어난다.

![KakaoTalk_Photo_2021-10-17-22-43-56](https://user-images.githubusercontent.com/24540286/137629911-d1a3ef1b-536b-4fa3-bd1b-bc6163c680d1.png)

HL1 모듈은 ML1 모듈의 F() 함수를 호출한다. 소스코드에서는 HL1 모듈은 인터페이스를 통해 F() 함수를 호출한다. 이 인터페이스는 런타임에는 존재하지 않는다. HL1은 단순히 ML1의 F()를 호출할 뿐이다.  
그러나 ML1은 I인터페이스를 Implement 받아 구현했기 때문에 소스코드의 의존성(상속관계)이 제어흐름과는 반대이다. 이를 의존석의 역전이라고 부르며 SOLID 정책의 기본이 된다.