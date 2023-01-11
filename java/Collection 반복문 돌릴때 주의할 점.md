# Collection 반복문 돌릴 때 주의할 점

### 다음 코드의 문제점은???
```java
public class CollectionTest{
  private final Map<String, String> map;
  
  public CollectionTest(Map<String, String> map){
    this.map = map;
  }
  
  public void init(List<SomeObject> list){
    map = new HashMap<>();
    for(SomeObject so : list){
      map.put(so.getId(), so.getContent);
    }
  }
}
```
- 위 코드의 문제점은 컬렉션을 반복을 돌며 map의 데이터를 변경하는 것이다.
- 이는 동시성 문제를 발생시킬 수 있고 반복 도중 에러 발생시 원 데이터의 손실을 야기할수 있다.

### 어떻게 고칠까?
```java
public class CollectionTest{
  private final Map<String, String> map;
  
  public CollectionTest(Map<String, String> map){
    this.map = map;
  }
  
  public void init(List<SomeObject> list){
   Map<String, String> tmp = new HashMap<>();
    for(SomeObject so : list){
      tmp.put(so.getId(), so.getContent);
    }
    map = tmp;
  }
}
```

- 컨커런트 클래스를 사용한다
- 새로운 맵을 만들어서 데이터를 초기화 한후 대입한다.
