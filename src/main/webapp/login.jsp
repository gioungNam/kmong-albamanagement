<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>login</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
      }
  
      .login-container {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      }
  
      .login-container label {
        display: block;
        margin-bottom: 8px;
      }
  
      .login-container input {
        width: 100%;
        padding: 8px;
        margin-bottom: 16px;
        box-sizing: border-box;
      }
  
      .login-container button {
        background-color: #4caf50;
        color: #fff;
        padding: 10px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        width: 100%;
      }
  
      .login-container button:hover {
        background-color: #45a049;
      }
    </style>
  </head>
  <body>
  
  <div class="login-container">
    <h2>ログイン</h2>
    <form action="login" method="post">
      <label for="ID">ID</label>
      <input type="text" id="username" name="username" required>
  
      <label for="password">Password</label>
      <input type="password" id="password" name="password" required>
  
      <button type="submit">Login</button>
    </form>
  </div>
  
  </body>
  </html>