<#import "parts/comp.ftlh" as c>
<@c.page>

    <button type="button" class="btn btn-primary">
        Сообщения <span class="badge badge-light">${count}</span>
    </button>
    <div class="mt-2">
    <form method="post" action="/message">
        <input type="text" name="text" id="text" placeholder="Enter text">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Add</button>
    </form>
    </div>
    <div class="mt-2">
    <form method="post" action="/message/filter">
        <input type="text" name="filter" placeholder="Filter" value="${filter!""}">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Filter</button>
    </form>
    </div>
    <div class="alert alert-primary mt-2" role="alert">
        <#list messages as message>
            <h4 class="alert-heading mt-2">${message.author.username}</h4>
            <p class="mb-0">${message.text}</p>
            <hr>
        <#else>
            <p>No message</p>
        </#list>
    </div>
</@c.page>