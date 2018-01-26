<%--
  Created by IntelliJ IDEA.
  User: Ariana
  Date: 2017/12/22
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="service.OnlineUserService" %>
<body>
<html>
<head>
    <title>登录</title>
</head>
<body>
<div>
    <div id="msg"></div>
    <form id="login">
    </form>
    <table id="order">
    </table>
</div>
<div>
    游客在线人数：<%=OnlineUserService.getOnlineVisitor()%>
    用户在线人数：<%=OnlineUserService.getOnlineUser()%>
</div>
<input type="button" onclick="logout()" id="logout" value="退出登录"/>
</body>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script>
    $("#logout").hide();
    var loginTimes =<%=session.getAttribute("loginTimes")%>;
    var message =<%=session.getAttribute("message")%>;
    if (loginTimes === 1 && message === null) {
        $("#message").text("请等待~");
        $('#login').empty();
    } else if (message === "用户名或密码错误") {
        $("#message").text(message);
        $('#login').append('      <span>用户名</span><input id="id" placeholder="请输入用户名" type="text"/>' +
            ' <span>密码</span><input id="pwd" placeholder="请输入密码" type="password"/>' +
            '<input type="submit" value="确定" onclick="login()">');
    } else {
        $('#login').append('      <span>用户名</span><input id="id" placeholder="请输入用户名" type="text"/>' +
            ' <span>密码</span><input id="pwd" placeholder="请输入密码" type="password"/>' +
            '<input type="submit" value="确定" onclick="login()">');
    }
    var orders = null;
    orders =<%=session.getAttribute("orders")%>;
    setInterval(checkTableExist(), 1000);
    function logout() {
        $.ajax({
            url: "/logout",
            type: "get",
            success: function () {
                alert("已退出！");
                window.location.href = "/index.jsp";
            }
        });
    }
    function login() {
        $.ajax({
            url: "/login",
            type: "post",
            data: {"id": $("#id").val(), "pwd": $("#pwd").val()},
            success: function (data) {
            }
        });
        loginTimes = 1;
        $("#message").text("请等待~");
    }
    function checkTableExist() {
        console.log(message);
        console.log(orders);
        if (message !== null && message !== "用户名或密码错误！") {
            $("#login").empty();
            $("#message").text(message);
            $("#logout").show();
            if (orders.length > 0) {
                $("#orderlist").append("<tr><td>时间</td><td>名称</td><td>数量</td><td>价格</td><td>总价</td></tr>");
                orders.forEach(function (value, array, index) {
                    $("#orderlist").append("<tr><td>" + value.time + "</td><td>" + value.name + "</td><td>" + value.number
                        + "</td><td>" + value.price + "</td><td>" + value.totalprice + "</td></tr>");
                });
            }
        } else if (message === null && loginTimes === 1) {
            $("#message").text("请等待~");
            $('#login').empty();
            setTimeout(function () {
                window.location.href = "/index.jsp";
            }, 1000)
        }
    }
</script>
</html>
