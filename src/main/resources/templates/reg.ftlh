<#import "parts/comp.ftlh" as c>
<@c.page>
<form action="/registration" method="post">
    <label><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="username" required>
    <label><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit" class="btn btn-primary">Регистрация</button>
</form>
<a href="/login">Вход</a>
</@c.page>