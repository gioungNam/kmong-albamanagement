<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Table with Shift Submit</title>
        <style>
          body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
          }
      
          table {
            border-collapse: collapse;
            margin: auto;
          }
      
          table, th, td {
            border: 1px solid black;
          }
      
          th, td {
            padding: 8px;
            text-align: center;
          }
      
          .button1 {
            position: fixed;
            bottom: 30px;
            right: 30px;
          }

       
        </style>
      </head>
      <body>
      
      <table>
      
        <tr>
          <th></th>
          <th>11/20(月)</a></th>
          <th>11/21(火)</a></th>
          <th>11/22(水)</a></th>
          <th>11/23(木)</a></th>
          <th>11/24(金)</a></th>
          <th>11/25(土)</a></th>
          <th>11/26(日)</a></th>
        </tr>
      
        <tr>
            <td>ヒナタ</td>
            <td>09~23</td>
            <td>休</td>
            <td>休</td>
            <td>休</td>
            <td>休</td>
            <td>09~23</td>
            <td>09~23</td>
          </tr>

          <tr>
            <td>レイ</td>
            <td>09~17</td>
            <td>09~17</td>
            <td>09~17</td>
            <td>09~17</td>
            <td>09~17</td>
            <td>休</td>
            <td>休</td>
          </tr>

          <tr>
            <td>モエナ</td>
            <td>14~21</td>
            <td>14~21</td>
            <td>休</td>
            <td>休</td>
            <td>休</td>
            <td>17~23</td>
            <td>17~23</td>
          </tr>

          <tr>
            <td>サリナ</td>
            <td>休</td>
            <td>休</td>
            <td>休</td>
            <td>休</td>
            <td>休</td>
            <td>09~17</td>
            <td>09~17</td>
          </tr>

          <tr>
            <td>コウセイ</td>
            <td>17~23</td>
            <td>17~23</td>
            <td>17~23</td>
            <td>17~23</td>
            <td>17~23</td>
            <td>休</td>
            <td>休</td>
          </tr>

          <tr>
            <td>ミサ</td>
            <td>休</td>
            <td>17~23</td>
            <td>17~23</td>
            <td>休</td>
            <td>休</td>
            <td>17~23</td>
            <td>17~23</td>
          </tr>

          <tr>
            <td>アヤノ</td>
            <td>9~23</td>
            <td>09~17</td>
            <td>休</td>
            <td>休</td>
            <td>休</td>
            <td>9~23</td>
            <td>9~23</td>
          </tr>

          <tr>
            <td>キムラ</td>
            <td>休</td>
            <td>09~17</td>
            <td>09~17</td>
            <td>09~17</td>
            <td>09~17</td>
            <td>15~23</td>
            <td>15~23</td>
          </tr>

          <tr>
            <td>モエ</td>
            <td>休</td>
            <td>09~17</td>
            <td>09~17</td>
            <td>09~18</td>
            <td>09~18</td>
            <td>09~18</td>
            <td>09~18</td>
          </tr>

          <tr>
            <td>リア</td>
            <td>15~23</td>
            <td>15~23</td>
            <td>15~23</td>
            <td>15~23</td>
            <td>15~23</td>
            <td>15~23</td>
            <td>休</td>
          </tr>

        
      
      </table>
      <form action="ManagerMain.html" method="post">
      <button class="button1">保存</button>
      </form>
      
      </body>
      </html>