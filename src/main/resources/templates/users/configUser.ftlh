<#import "../parts/comp.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page>

    <#if user??>
        <#if id == id_user>
            <form id="demo-form" method="post" action="/profile/deleteAva">

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <button class="btn btn-primary mt-2" type="submit">Удалить</button>
            </form>
            <form id="demo-form" method="post" action="/profile/${id_user}/upload" enctype="multipart/form-data">
                <div class="form-group row">
                    <div class="col-sm-3">
                        <p>Загрузить аватар:</p> <input class="form-control" type="file" name="file" id="name">
                        ${avaNotExists!""}
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <button class="btn btn-primary mt-2" type="submit">Загрузить</button>
            </form>
            <p>Ваш профиль ${code}</p>
        </#if>
    </#if>

</@c.page>