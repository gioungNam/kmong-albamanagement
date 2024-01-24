# 필독 부탁드립니다

**실행시 주의사항**

- war 파일은 target 디렉토리 하위인 ROOT.war 를 이용하시면 됩니다.
- 기존 tomcat 디렉토리의 webapps 하위에 'ROOT' 폴더가 존재한다면 'ROOTS'로 이름을 변경 후에 war파일을 이동시켜주세요.
- java는 17버전, tomcat 9버전에서 개발되었으며 servlet 기반으로 개발하였습니다.
- url 매핑관계는 src/main/webapp/WEB-INF/web.xml 을 참고하시면 됩니다.
- 종속성 라이브러리는 maven을 이용하였습니다.
- 기본 도메인은 http://localhost:8080/ 이며, 로그인 여부에 따른 filter 가 적용되었습니다.



**데이터베이스 설정 정보**

```java
/**
 * db config 설정
 */
public class DBConfig {
    // 필요시 변경하세요!
    public static final String DB_URL = "jdbc:mysql://localhost:3306/project";
    // 필요시 변경하세요!
    public static final String DB_USER = "root";
    // 필요시 변경하세요!
    public static final String DB_PASSWORD = "";
}
```



**테이블 정보**

```sql
// 공지사항 테이블
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `insert_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


// 알바생 테이블
CREATE TABLE `user` (
  `user_id` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(100) NOT NULL,
  `insert_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


// 스케줄(근무표) 테이블
CREATE TABLE `work_schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `work_date` date NOT NULL,
  `start_time` int NOT NULL,
  `end_time` int NOT NULL,
  `insert_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_work_schedule` (`user_id`,`work_date`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```



**개발 사항**

(공통)

- (로그인 전)로그인 페이지 
- (로그인 후)우측 상단, 닉네임 및 로그아웃
- 메인페이지
  - (근무표 날짜 클릭시) 특정 날짜 근무 상세 페이지로 이동
  - (우측 하단 다음주 근무표 수정 버튼 클릭시) 다음주 근무표 수정 페이지 이동
- 특정 날짜 근무 상세페이지
  - 근무 시간대에 해당하는 부분을 블루라이트 배경으로 표시
- 메인페이지 아닌 부분에서 메인페이지 돌아가기 버튼 우측하단에 표시



(어드민)

- 메인페이지

   - 이번주 근무표
      - 모든 알바생의 근무시간 수정 가능
      - 근무시간 바꾸고 싶은 알바생의 td값을 클릭시 모달창 노출
         - 입력시간 조건 : 9시 이상 23시 미만, 종료시간보다 클 수 없음.
         - 종료시간 조건 : 10시 이상 23시 이하, 입력시간보다 작을수 없음.
         - 시간 입력 유효하지 않을시 알럿메시지 노출
         - 수정 완료시 '수정 완료' 메시지와 함께 리로드

      - (날짜 클릭시) 해당 일자 근무표 페이지 이동

   - 공지사항
      - +버튼 클릭시 공지사항 추가를 위한 모달창 노출
      - 메시지 빈값 입력시 알럿 노출
      - 메시지는 최근에 등록된 메시지가 상단에 위치
      - 메시지 클릭하면 삭제 확인창 노출, 공지사항 삭제 가능

- 다음주 근무표 확인페이지
  - 다음주 근무표 노출, 기능은 근무표 수정기능과 동일

- 알바생 추가 페이지
  - 입력값 검사
  	- 아이디 필수값이며, 10자이하여야 한다.
  	- 비밀번호는 필수값이며, 최소 5자 이상 10자 이하 여야 한다.
  	- 이름은 필수값이다.
  	- 잘못된 값 입력시 관련 알럿메시지 노출
  - 알바생 추가시, 등록 완료페이지로 이동

  

(프론트)
- 메인페이지
  - 공지사항은 조회만 가능
  - 이번주 근무표 수정 불가능
  - 자신의 근무표에 해당하는 부분 청록 배경색으로 표시
- 다음주 근무표 작성 페이지
  - 자신의 근무표만 수정가능
    - 수정 액션은 어드민과 동일
  - 자신의 근무표에 해당하는 부분 청록 배경색으로 표시