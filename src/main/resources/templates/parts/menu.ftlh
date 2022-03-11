<#include "security.ftlh">
<ul class="nav justify-content-center">
    <li class="nav-item">
        <a class="nav-link active" href="/">Home</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/message">Message</a>
    </li>
    <#if isAdmin>
    <li class="nav-item">
        <a class="nav-link" href="/user">Users</a>
    </li>
        <#else>
    </#if>
    <#if isLoggedIn>
        <div class="nav-bar-cell1-rev">
                <a href="/u/${id}" type="submit" class="btn btn-primary">${name}</a>
            </form>
        </div>
    <#else>
        <li class="nav-item">
            <a href="/login" class="btn btn-primary">Войти</a>
        </li>
    </#if>
</ul>