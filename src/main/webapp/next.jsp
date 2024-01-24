<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>main page</title>
        <!-- Bootstrap CSS CDN -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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

        table {
          
            margin-top: 100px;
            margin-bottom: 100px;
        }

        table, th, td {
            border: 2px solid black;
        }

        th, td {
            padding: 10px; /* 더 좋은 간격을 위해 패딩을 늘립니다. */
            text-align: center;
        }

        td.clickable:hover {
        cursor: pointer; /* 커서 변경 */
        background-color: #e7f3ff; /* 배경색 변경 */
        transition: background-color 0.3s; /* 부드러운 색상 변화 효과 */
    }

        h1 {
            position: fixed;
            top: 10px;
            left: 50%;
            transform: translateX(-50%);
            font-size: 24px; /* 필요에 따라 헤더의 글꼴 크기를 조절합니다. */
            color: #2c3e50;
        }

        .statbar {
            position: absolute; top: 20px; right: 20px;
        }

        .message h2 {
         margin-bottom: 10px; /* 제목 아래에 약간의 여백을 추가합니다. */
        }

        .message p {
        margin-bottom: 8px; /* 각 단락 아래에 약간의 여백을 추가합니다. */
        }

        .highlight {
            background-color: #add8e6; /* 연한 파란색 배경 */
        }

        /* 우측 하단 버튼 위치 조정 */
        .position-fixed {
            bottom: 80px; /* 버튼 위치 조정 */
            right: 20px;
        }

        .my-schedule {
            background-color: #e0f7fa; 
            font-weight: bold; /* 글꼴 굵게 */
        }

    </style>
      </head>
      <body>
      <h1>来週のシフト</h1>
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
            <th></th>
            <c:forEach items="${weekDays}" var="day" varStatus="status">
              <th>
                  <a>${weekDaysJapanese[status.index]}</a>
              </th>
          </c:forEach>
        </tr>
        <c:forEach items="${workers}" var="worker" varStatus="workerStatus">
            <tr class="worker-row" data-user-id="${worker.userId}">
                <td>${worker.nickname}</td>
                <c:forEach items="${weekDays}" var="day" varStatus="status">
                    <td data-work-date="${fullWeekDays[status.index]}">
                        <c:set var="dayScheduled" value="false" />
                        <c:forEach items="${weeklySchedule[worker.userId]}" var="schedule">
                            <c:if test="${fn:startsWith(schedule, day)}">
                                ${fn:split(schedule, ':')[1]}
                                <c:set var="dayScheduled" value="true" />
                            </c:if>
                        </c:forEach>
                        <c:if test="${!dayScheduled}">休</c:if>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
      </table>



    <!-- 우측 하단 버튼 추가 -->
    <div class="position-fixed" style="bottom: 20px; right: 20px;">
        <a href="/" class="btn btn-info btn-sm mb-2">メインページ</a><br>
    </div>

    <!-- 시간 수정 모달 -->
    <div class="modal" tabindex="-1" role="dialog" id="editModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">時間編集</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                    <input type="hidden" id="userId">
                    <input type="hidden" id="workDate">

                    <div class="form-group">
                        <label for="startTime">開始時間</label>
                        <input type="number" class="form-control" id="startTime" min="9" max="22" value="">
                    </div>
                    <div class="form-group">
                        <label for="endTime">終了時間</label>
                        <input type="number" class="form-control" id="endTime" min="10" max="23" value="">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="saveChanges">修正</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">キャンセル</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        // '修正' 버튼 클릭 이벤트
        document.getElementById('saveChanges').addEventListener('click', function() {
            
            var startTime = parseInt(document.getElementById('startTime').value);
            var endTime = parseInt(document.getElementById('endTime').value);
            var userId = document.getElementById('userId').value;
            var workDate = document.getElementById('workDate').value;

            console.log("startTime = "+startTime);
            console.log("endTime = "+endTime);
            console.log("userId = "+userId);
            console.log("workDate = "+workDate);


            if (isNaN(startTime) || isNaN(endTime)) {
                alert('時間は数字でなければなりません');
                return;
            }
            
            // 유효성 검사
            if (startTime < 9 || startTime >= 23 || endTime < 10 || endTime > 23 || startTime >= endTime) {
                alert('入力時間が無効です。');
                return;
            }
            
           $.ajax({
                url: '/schedule',
                type: 'POST',
                dataType: 'json',
                data: {
                    userId: userId,
                    workDate: workDate,
                    startTime: startTime,
                    endTime: endTime
                },
                success: function(response) {
                    console.log(response);
                    console.log(response.status)
                    if (response.status === 'success') {
                        alert("修正完了");
                        location.reload();
                    } else {
                        alert(response.message);
                    }
                },
                error: function() {
                    alert('error'); 
                }
            });


        });

        document.querySelectorAll('.worker-row').forEach(function(row) {
            var user = '${sessionScope.user}';
            var userId = row.getAttribute('data-user-id');

            // 현재 사용자가 행의 사용자와 일치하는 경우 클래스 추가
            if (user === userId) {
                row.classList.add('my-schedule');
            }

            // 각 'td'에 클릭 이벤트 추가
            row.querySelectorAll('td').forEach(function(td, index) {
                if (index === 0) return; // 첫 번째 'td' 건너뛰기

                if (user === 'admin' || user === userId) {
                    td.classList.add('clickable');
                    td.addEventListener('click', function() {
                        var workDate = td.getAttribute('data-work-date');
                        var scheduleText = td.innerText;
                        var scheduleParts = scheduleText.split("~");
                        if (scheduleParts.length === 2) {
                            // 시작 시간과 종료 시간 설정
                            document.getElementById('startTime').value = scheduleParts[0].trim();
                            document.getElementById('endTime').value = scheduleParts[1].trim();
                        } else {
                            // '休' 또는 비어있는 경우 입력 필드 초기화
                            document.getElementById('startTime').value = '';
                            document.getElementById('endTime').value = '';
                        }

                        document.getElementById('userId').value = userId;
                        document.getElementById('workDate').value = workDate;

                        // 모달 창 열기
                        $('#editModal').modal('show');
                        
                    });
                }
                
            });

        });
    



        // 모달 창 닫기 이벤트 (취소 버튼 또는 모달 창 닫기)
        $('#editModal').on('hidden.bs.modal', function () {
            document.getElementById('startTime').value = '';
            document.getElementById('endTime').value = '';
        });
    </script>


        <!-- 부트스트랩 JS 추가 -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
      </body>
      </html>