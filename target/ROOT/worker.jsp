<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>アルバイト登録</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
    /* 우측 하단 버튼 위치 조정 */
        .position-fixed {
            bottom: 80px; /* 버튼 위치 조정 */
            right: 20px;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header text-center">
                        <h2>アルバイト登録</h2>
                    </div>
                    <div class="card-body">
                        <form action="/worker" method="post" onsubmit="return validateForm()" name="workerForm">
                            <div class="form-group">
                                <label>アイディー</label>
                                <input type="text" name="userId" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>名前</label>
                                <input type="text" name="name" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>パスワード</label>
                                <input type="password" name="password" class="form-control">
                            </div>
                            <div class="text-center">
                                <input type="submit" value="登録" class="btn btn-primary">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 우측 하단 버튼 추가 -->
    <div class="position-fixed" style="bottom: 20px; right: 20px;">
        <a href="/" class="btn btn-info btn-sm mb-2">メインページ</a><br>
    </div>

    <script>
    function validateForm() {
        var userId = document.forms["workerForm"]["userId"].value;
        var password = document.forms["workerForm"]["password"].value;
        var name = document.forms["workerForm"]["name"].value;

         // 아이디 검사
        if (userId.length === 0) {
            alert('ユーザーIDを入力してください。'); // '아이디를 입력해주세요.'
            return false;
        } else if (userId.length > 10) {
            alert('ユーザーIDは10文字以内でなければなりません。'); // '아이디는 10자 이내여야 합니다.'
            return false;
        }

        // 비밀번호 검사
        if (password.length === 0) {
            alert('パスワードを入力してください。'); // '비밀번호를 입력해주세요.'
            return false;
        } else if (password.length < 5 || password.length > 10) {
            alert('パスワードは5文字以上、10文字以内でなければなりません。'); // '비밀번호는 5자 이상 10자 이하여야 합니다.'
            return false;
        }

        // 이름 검사
        if (name.length === 0) {
            alert('名前を入力してください。'); // '이름을 입력해주세요.'
            return false;
        }
        return true;
    }
    </script>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.7.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>