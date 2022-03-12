<#assign
know = Session.SPRING_SECURITY_CONTEXT??
>
<#if know>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAdmin = user.isAdmin()
    id = user.getId()
    isLoggedIn = true
    aDateTime = .now
    aTime = aDateTime?time
    >
<#else>
    <#assign
    name = "unknown"
    isLoggedIn = false
    isAdmin = false
    id = 1
    >
</#if>
