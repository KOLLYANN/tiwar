<#import "../parts/comp.ftlh" as p>
<@p.page>
    <h1>Информация о пользователе</h1>
    <img src="/img/${path!""}" width="250px" height="250px" alt="ava">
    <h4>${name} ${lastname}</h4>
    <a href="/people">People</a>
    <a href="/people/${id}/edit">Edit</a>
</@p.page>
