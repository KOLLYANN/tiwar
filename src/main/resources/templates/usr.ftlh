<#import "parts/comp.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <h2>${name}</h2>
    <h2>Ваш профиль ${code}</h2>
    <div class="nav-bar-cell1-rev">
        <form method="post" action="/logout">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Выйти</button>
        </form>
    </div>
</@c.page>
