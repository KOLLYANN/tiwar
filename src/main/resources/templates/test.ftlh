<#import "parts/comp.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>

    <#if employees??>
    <#list employees[0..1] as employee>
       ${employee.getName()}
    </#list>
        <#else>
        Empty
    </#if>



</@c.page>