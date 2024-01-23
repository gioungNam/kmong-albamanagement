[실행시 주의사항]
- war 파일은 target 디렉토리 하위인 ROOT.war 를 이용하시면 됩니다.
- 기존 tomcat 디렉토리의 webapps 하위에 'ROOT' 폴더가 존재한다면 'ROOTS'로 이름을 변경 후에 war파일을 이동시켜주세요.
- java는 17버전, tomcat 9버전에서 개발되었으며 servlet 기반으로 개발하였습니다.
- url 매핑관계는 src/main/webapp/WEB-INF/web.xml 을 참고하시면 됩니다.





[작업영역]

(공통)
	- 우측 상단, 닉네임 및 로그아웃 (o)
	- 특정 날짜 근무 상세 페이지 (o)
(어드민)
- 이번주 근무표 (월 ~ 일) (o)
- 근무표 현재날짜에 맞춰서 '이번주' 근무표 출력 (o)
- 일자 버튼 클릭시 해당 일자의 '근무표 상세페이지' DB에서 조회해서 출력 (o)
	- 현재 해당 일자에 근무를 하는 알바생들은 하늘색으로 시간대 표시

- 근무표 수정기능 (페이지로 굳이 만들필요없을것 같고, 메인페이지에서 해당 달력 누르면 바뀌는걸로) - (o)
		- 바꾸고 싶은 일자의 테이블 요소를 클릭시(디자인적으로도 노출) 모달창이 뜹니다.
		- 모달창 내부에서 시간을 입력해서 바꿀수 있습니다.
		- 입력시간 조건 : 9시 이상 23시 미만, 종료시간보다 클 수 없음.
		- 종료시간 조건 : 10시 이상 23시 이하, 입력시간보다 작을수 없음.
		- 수정 완료시 '수정 완료' 메시지와 함께 리로드
		- 메인페이지 버튼

- 다음주 근무표 확인페이지(o)
	- 다음주 근무표 노출, 기능은 근무표 수정기능과 동일
	- 메인페이지 버튼

- 알바생 추가 페이지
	- 입력값 검사
		- 아이디 필수값이며, 10자이하여야 한다.
		- 비밀번호는 필수값이며, 최소 5자 이상 10자 이하 여야 한다.
		- 이름은 필수값이다.
		- 잘못된 값 입력시 관련 알럿메시지 노출
	- 완료페이지

- 공지사항 출력
   - 등록(별도 페이지 파서 하자)/조회(메인페이지)/삭제 

	

(프론트)
	- 메인페이지 (o)
	- 공지사항 페이지 (o)
		- 조회만 가능
	- 다음주 근무표 작성 페이지