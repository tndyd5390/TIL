# SOLID 원칙

- SRP: 단일 책임 원칙  
  콘웨이 법칙에 따른 따름정일: 소프트웨어 시스템이 가질 수 있는 최적의 구조는 시스템을 만드는 조직의 사회적 구조에 커다란 영향을 받는다. 따라서 각 소프트웨어 모듈은 변경의 이유가 하나 단 하나여야만 한다.

- OCP: 개방-폐쇄 원칙  
  기존 코드를 수정하기 보다는 반드시 새로운 코드를 추가하는 방식으로 시스템의 행위를 변경할 수 있도록 설계해야만 소프트웨어 시스템을 쉽게 변경할 수 있다.

- LSP: 리스코프 치환 원칙  
  상호 대체 가능한 구성요소를 이요해 소프트웨어 시스템을 만들 수 있으려면 이들 구성요소는 반드시 서로 치환 가능해야 한다.

- ISP: 인터페이스 분리 원칙  
  이 원칙에 다르면 소프트웨어 설계자는 시용하지 않은 것에 의존하지 않아야 한다.

- DIP: 의존성 역전 원칙  
  고수준 정책을 구현하는 코드는 저수준 세부사항을 구현하는 코드에 절대로 의존해서는 안된다. 대신 세숩사항이 정책에 의존해야한다.