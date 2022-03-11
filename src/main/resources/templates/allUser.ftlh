<#import "parts/comp.ftlh" as c>
<@c.page>
    <table>
    <tr>
        <td>Id</td>
        <td>Name</td>
    </tr>
    <#list users as user>
    <tr>
        <td>${user.getId()}</td>
        <td>${user.getUsername()}</td>
        <td><a href="/user/${user.getId()}">Edit</a></td>
    </tr>
    </#list>
</table>
</@c.page>