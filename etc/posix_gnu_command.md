# POSIX/GNU 명령어 작성 규칙

- -과 함께 사용하는 옵션은 단일 알파벳/숫자 문자 인자는 짧은 옵션이다
- 일부 옵션은 인자를 필요로 한다.
- --과 함께 사용하는 옵션은 알파벳 두 글자 이상으로 구성한 긴 옵션이다.
- -- 이후에 작성하는 인자가 있다면 관련 옵션을 모두 종료한다.

## 명령의 예
> $ kubectl -n default exec mypod -c my-container -- ls /

- **-n default** 짧은 옵셔으로써 긴 옵셥으로 표현하자면 --namespace로 표기함
- **exec mypod** 필수 인자
- **-- ls /** 관련 옵션을 모두 종료한다. -- 이후에 실행할 명령을 설정 할 수 있다.