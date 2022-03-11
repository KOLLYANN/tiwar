<#import "parts/comp.ftlh" as c>
<@c.page>
    <p>Пользователь: ${user.getUsername()}</p>
    <form method="post" action="/user">
         <label id="name">Новое имя: </label>
        <input type="text" name="name" placeholder="new name" id="name" value="${user.getUsername()}">
<#--        <p>${nameExists}</p>-->
        <input type="hidden" name="id" placeholder="new id" value="${user.getId()}">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Rename</button>
    </form>
    <a href="/user">Users</a>
</@c.page>