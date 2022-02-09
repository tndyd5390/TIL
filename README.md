# **박수용**  
박수용의 TIL 뿌슝빠슝  

[clean architecture](#clean-architecture)  
[clean code](#clean-code)  
[design pattern](#design-pattern)  
[DevOps](#devops)  
[java](#java)  
[effective java](#effective-java)  
[k8s](#k8s)  
[kafaka](#kafka)  
[msa](#msa)  
[spring](#spring)  
[spring cloud](#spring-cloud)  
[test code](#test-code)  
[etc](#etc)  
[만들면서 배우는 클린아키텍처](#만들면서-배우는-클린-아키텍쳐)
  
### clean architecture  
[design and architecture](./clean_architecture/design_and_architecture.md)  
[encapsulation in c](./clean_architecture/Encapsulation_in_C.md)  
[extend in c](./clean_architecture/extend_in_c.md)  
[dependency inversion](./clean_architecture/dependency_inversion.md)  
[SOLID](./clean_architecture/SOLID.md)  
[SRP](./clean_architecture/SRP.md)  
[stable abstraction](./clean_architecture/stable_abstraction.md)  
[stability](./clean_architecture/stability.md)  
[boundary line](./clean_architecture/boundary_line.md)  
[boundary crossing](./clean_architecture/boundary_crossing.md)  
[architecture level](./clean_architecture/architecture_level.md)  
[business rules and usecase](./clean_architecture/business_rule_and_usecase.md)  
[움퍼스 게임](./clean_architecture/hunt_the_wumpus.md)  
[움퍼스 게임의 클린아키텍쳐 01](./clean_architecture/clean_architecture_with_wumpus01.md)  
[움퍼스 게임의 클린아키텍쳐 02](clean_architecture/clean_architecture_with_wumpus02.md)  
[테스트를 고려한 설계](clean_architecture/Design_with_test.md)  
[데이터베이스는 세부사항이다](clean_architecture/database_is_detail.md)  
[데이터베이스와 관련된 일화](clean_architecture/database_story.md)  
[웹은 세부사항이다](clean_architecture/web_is_the_detail.md)  
[프레임워크는 세부사항이다](clean_architecture/framework_is_the_detail.md)  
  
### clean code  
[meaningful name](./clean_code/meaningful_name.md)  
[wrong variable name](./clean_code/wrong_variable_name.md)  
[meaingful distinction](./clean_code/meaningful_distinction.md)  
[디미터 법칙](./clean_code/law_of_demeter.md)  
[디미터 법칙(기차 충돌)](./clean_code/collision_of_train.md)  
[디미터 법칙(구조체 감추기)](./clean_code/hide_struct.md)  
[자료 전달 객체(DTO)](./clean_code/data_transfer_object.md)  
[인수 객체](clean_code/method/argument_object.md)  
[인수 목록](clean_code/method/argument_list.md)  
[함수의 사이드 이펙트](clean_code/method/side_effect.md)  
[명령과 조회를 분리하라](clean_code/method/separate_command_query.md)  

### 만들면서 배우는 클린 아키텍쳐
[계층형 아키텍쳐의 문제](example_for_clean_architecture/1.%20계층형%20아키텍쳐의%20문제.md)  
[의존성 역전하기](example_for_clean_architecture/2.%20의존성%20역전하기.md)  
[코드 구성하기](example_for_clean_architecture/3.%20코드%20구성하기.md)  
  
### design pattern  
[Bridge Desigh Pattern](./design_pattern/bridge_pattern.md)  
[Chain of Responsibility Pattern](./design_pattern/Chain_of_responsibility.md)  
[Observer Pattern](./design_pattern/observer_pattern.md)  
[Memento Pattern](./design_pattern/Memento_pattern.md)  
[Proxy Pattern](./design_pattern/proxy_pattern.md)  
  
### DevOps  
[gitops with k8s](./DevOps/GitOps_with_k8s.md)  
[argocd simple example](./DevOps/argocd_simple_example.md)  
[argocd workflow](./DevOps/argocd_workflow.md)  
[azure key vault with spring boot](./cloud_provider/azure_key_vault_with_java.md)  
  
### java  
[지난주, 다음주 특정 요일 구하기](java/LocalDate_Next_Previous_day.md)  
  
### effective java  
[2-5 자원을 명시하지 말고 의존성 주입을 사용하라](effective%20java/2-5자원을%20명시하지%20말고%20의존%20객체%20주입을%20사용.md)    
[2-6 불필요한 객체 생성을 피하라](effective%20java/2-6%20불필요한%20객체%20생성을%20피하라.md)  
[2-7 다 쓴 객체 참조를 해제하라](effective%20java/2-7%20다%20쓴%20객체%20참조를%20해제하라.md)  
  
### k8s  
[k8s init container](./k8s/init_container.md)  
[k8s init container 2](./k8s/init_container2.md)  
[pv and pvc](./k8s/pv_pvc.md)  
[helm chart](./k8s/helm.md)  
[multi_container_limit_resource](./k8s/multi_container_limit_range.md)  
[k8s Service Endpoint](./k8s/service_endpoint.md)  
[k8s configmap](./k8s/configmap.md)  
[k8s job](./k8s/job.md)  
[k8s secret](./k8s/secret.md)  
[pod 구성 패턴](./k8s/pod_pattern.md)  
[pod lifecycle](./k8s/pod_lifecycle.md)  
[pod hostname and domain](./k8s/using_domain.md)  

### kafka
[kafka command](./kafka/kafka_command.md)  
[kafka custom partitioner](./kafka/kafka_custom_partitioner.md)  
[kafka lag monitoring](./kafka/kafka_lag_monitoring.md)  

### msa
[msa transaction](./msa/msa_transaction.md)

### spring
[gradle dependency configuration](./spring/gradle_dependency_configuration.md)  
[spring cloud data flow install](./spring_cloud_data_flow/scdf_install.md)  
[spring boot mail test](./spring/spring_mail_test.md)  
[spring OAuth Enable](./spring/spring_OAuth_enable.md)  
[spring Security PasswordEncoder](./spring/passwordEncoder.md)  
[Spring WebClient](./spring/webClient.md)  
[Mono](./spring/mono.md)  
[jwt를 사용한 로그인 기능 구현](spring/jwt를%20사용한%20로그인기능%20구현.md)  

### spring cloud
[spring cloud data flow summary](./spring_cloud_data_flow/summary.md) 
[spring cloud stream example](./spring_cloud_data_flow/simple_stream_example.md) 

### test code
[인수테스트 기본 구조](test/acceptance_test.md)  

### etc
[stackoverflow developer survey](./etc/2021_stackoverflow_developer_survey.md)  
[white domain](./etc/white_domain.md)  
[white domain spf](./etc/white_domain_SPF.md)  
[intellij short chut](./etc/intellij_shortcut.md)  
[deployment method](./etc/deploy_method.md)  
[POSIX/GNU command style](./etc/posix_gnu_command.md)  
[REST API](etc/REST_API.md)  
[젠킨스 jdk 버전 설정](etc/jenkins_jdk_version.md)  
