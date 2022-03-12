<#import "../parts/comp.ftlh" as p>
<@p.page>
<h2>People</h2>
<a href="people/new"><img src="/img/addPerson.png" width="35px" height="35px" border="none" alt="add"></a>
    <table class="table">
        <thead class="table-dark">
        <tr>
            <td>Id</td>
            <td>Firstname</td>
            <td>Lastname</td>
            <td>Age</td>
            <td>Edit</td>
            <td>Delete</td>
            <td>Info</td>
        </tr>
        </thead>
        <#if people??>
            <#list people as user>
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getFirstname()}</td>
            <td> ${user.getLastname()}</td>
            <td>${user.getAge()}</td>
            <td><a href="/people/${user.getId()}/edit"><img src="/img/ed.png" width="25px" height="25px" border="none" alt="edit"></a></td>
            <td>
                <form method="post" action="/people/${user.getId()}">
                    <input type="hidden" name="_method" value="delete">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" style="border: none; background-color: white"><img src="/img/del.png" width="20px" height="20px" border="none" alt="drop"/></button>
                </form>
            </td>
            <td>
                <a href="/people/${user.getId()}"><img src="/img/info.png" width="25px" height="25px" border="none" alt="edit"></a>
            </td>
        </tr>
        </#list>
        <#else>
            <p>Empty people</p>
        </#if>
    </table>
</@p.page>
