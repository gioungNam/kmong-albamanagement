<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Week Schedule</title>
    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }

        h1 {
            position: fixed;
            top: 10px;
            left: 50%;
            transform: translateX(-50%);
            font-size: 24px; /* 필요에 따라 헤더의 글꼴 크기를 조절합니다. */
            color: #2c3e50;
        }

        table {
            border-collapse: collapse;
            margin: auto;
            margin-top: 100px;
        }

        table, th, td {
            border: 2px solid black;
        }

        th, td {
            padding: 10px;
            text-align: center;
        }

        .work-hour {
            background-color: lightblue;
        }

        h1 {
            font-size: 24px;
            color: #2c3e50;
        }

        .statbar {
            position: absolute; top: 20px; right: 20px;
        }

        /* 우측 하단 버튼 위치 조정 */
        .position-fixed {
            bottom: 80px; /* 버튼 위치 조정 */
            right: 20px;
        }
    </style>
</head>
<body>
    <p></p>
    <h1>${date}</h1>
    <!-- 로그인 사용자 정보 및 로그아웃 버튼 -->
      <div class="statbar">
          <div class="row">
              <div class="col-md-12 text-right">
                  <span><strong>${sessionScope.userNickname}</strong></span>
                  <a href="/logout" class="btn btn-primary btn-sm">ログアウト</a>
              </div>
          </div>
      </div>
    <table class="table-bordered table-hover">
        <tr>
            <th></th> <!-- 빈칸 -->
            <c:forEach begin="9" end="23" var="hour">
                <th><fmt:formatNumber value="${hour}" pattern="00"/></th>
            </c:forEach>
        </tr>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.nickname}</td>
                <c:forEach begin="9" end="23" var="hour">
                    <c:set var="schedule" value="${schedulesByUser[user.userId]}" />
                    <c:set var="isWorking" value="${schedule != null && hour >= schedule.startTime && hour <= schedule.endTime}" />
                    <td class="${isWorking ? 'work-hour' : ''}"></td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>

    <!-- 우측 하단 버튼 추가 -->
    <div class="position-fixed" style="bottom: 20px; right: 20px;">
        <a href="/" class="btn btn-info btn-sm mb-2">メインページ</a><br>
    </div>


    <!-- 부트스트랩 JS 추가 -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>