<%--
  Created by IntelliJ IDEA.
  User: Ariana
  Date: 2017/12/22
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="service.VisitorCounterService" %>
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
    <table id="orderlist">

    </table>
</div>
<div>
    游客在线人数：<%=VisitorCounterService.getOnlineVisitor()%>
    用户在线人数：<%=VisitorCounterService.getOnlineUser()%>
</div>
<input type="button" onclick="logout()" id="logout" value="退出登录"/>
</body>
<script src="jquery-3.2.1.min (1).js"></script>
<script>
    $("#logout").hide();
    var trylogin =<%=session.getAttribute("tryLogin")%>;
    var msg =<%=session.getAttribute("msg")%>;
    if (trylogin === 1 && msg === null) {
        $("#msg").text("加载中");
        $('#login').empty();
    } else if (msg === "用户名或密码错误") {
        $("#msg").text(msg);
        $('#login').append('      <span>用户名</span><input id="id" placeholder="请输入用户名" type="text"/>' +
            ' <span>密码</span><input id="pwd" placeholder="请输入密码" type="password"/>' +
            '<input type="submit" value="确定" onclick="login()">');
    } else {
        $('#login').append('      <span>用户名</span><input id="id" placeholder="请输入用户名" type="text"/>' +
            ' <span>密码</span><input id="pwd" placeholder="请输入密码" type="password"/>' +
            '<input type="submit" value="确定" onclick="login()">');
    }
    console.log(msg);
    console.log("trylogin" + trylogin);
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
        trylogin = 1;
        $("#msg").text("加载中");
    }

    function checkTableExist() {
        console.log(msg);
        console.log(orders);
        if (msg !== null && msg !== "用户名或密码错误") {
            $("#login").empty();
            $("#msg").text(msg);
            $("#logout").show();
            if (orders.length > 0) {
                $("#orderlist").append("<tr><td>时间</td><td>名称</td><td>数量</td><td>价格</td><td>总价</td></tr>");
                orders.forEach(function (value, array, index) {
                    $("#orderlist").append("<tr><td>" + value.time + "</td><td>" + value.name + "</td><td>" + value.number
                        + "</td><td>" + value.price + "</td><td>" + value.totalprice + "</td></tr>");
                });
            }
        } else if (msg === null && trylogin === 1) {
            $("#msg").text("加载中");
            $('#login').empty();
            setTimeout(function () {
                window.location.href = "/index.jsp";
            }, 1000)
        }
    }
</script>
</html>
