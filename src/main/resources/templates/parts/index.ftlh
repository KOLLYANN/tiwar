<#include "security.ftlh">
<#import "../parts/comp.ftlh" as c>
<@c.page>
    <div class="head " style="position:relative;"><span class="ttl fl">Главная</span>
        <span class="bl rght nwr"><img src="/img/icon/health.png" alt="hp"> <span class="white">${us.getHealth()}</span>
            | <img src="/img/icon/mana.png" alt="mp"> ${us.getMana()}</span>
        <div class="clr"></div>
    </div>
    <div class="exp_bar">
        <div class="progress" style="width:${exp}%"></div>
    </div>
    <div class="block_zero center"><img src="/img/icon/main_h.jpg" width="100%" alt=""></div>
    <div class="menuList">
        <li><a href="/arena/"><img src="/img/icon/arena.png" alt="">Арена</a></li>
        <li><a href="/#/"><img src="/img/icon/duel.png" alt="">Карьера<span class="green"></span></a></li>
<#--        <li><a href="/league/"><img src="/images/icon/league.png" alt="">Лига новичков<span-->
<#--                        class="green"> (+)</span></a>-->
<#--        </li>-->
        <#if us.getClan()??>
            <li><a href="/cave"><img src="/img/icon/cave.png" alt="">Пещера<span class="green"> </span>
                    <span class="grey small"></span>
                </a></li>
            <#else>
            <li><a style="pointer-events: none" href="/cave"><img src="/img/icon/cave.png" alt="">Пещера<span class="green"></span>
                    <span class="grey small">Нужен клан</span>
                </a></li>
        </#if>

<#--        <li><a href="/sage/"><img src="/images/icon/quest.png" alt="">Хижина мудреца<span class="green"> (+)</span></a>-->
<#--        </li>-->
<#--        <li><a href="/king/"><img src="/images/icon/king.png" alt="">Король бессмертных <span-->
<#--                        class="grey">(59:34)</span></a></li>-->
        <li><a href="/#/"><img src="/img/icon/arena.png" alt="">Сражения<span class="green"> </span></a></li>
<#--        <li><a href="/coliseum/"><img src="/images/icon/coliseum.png" alt="">Колизей</a></li>-->
        <li><a href="/#/"><img src="/img/icon/farm.png" alt="">Поход<span class="green"> </span></a></li>
    </div>
    <div class="mini-line"/>
    <div class="menuList">
        <li><a href="/shop/"><img src="/img/icon/equip.png" alt="">Магазин снаряжения<span class="green"> </span></a>
        </li>
<#--        <li><a href="/smith/"><img src="/images/icon/smith.png" alt="">Кузница<br><span class="white small">Руны, заточка, бонус, звезды</span></a>-->
<#--        </li>-->
<#--        <li><a href="/lab/"><img src="/images/icon/lab.png" alt="">Лаборатория<br><span class="white small">Усиления, эликсиры, камень и трава</span></a>-->
<#--        </li>-->
        <li><a href="/rating/"><img src="/img/icon/train.png" alt="">Рейтинг</a></li>
    </div>
<div class="line"/>
    <div class="player menuList">
        <li><a href="/profile/${user.getId()}"><img src="/img/icon/user.png" alt="">Мой герой</a></li>
        <li><a href="/clan/"><img src="/img/icon/user.png" alt="">Мой клан<span class="green"> (+)</span></a>
        </li>
        <li class="noBorder"><a href="/"><img src="/img/icon/arrow.png" alt="">На главную</a></li>
    </div>
    <div class="line"></div>
<div class="foot">
<div><img src="/img/icon/0.png" alt=""> ${us.getUsername()}<span class="float-right medium"><a
                href="#">Настройки</a></span></div>
<div class="center"><img src="/img/icon/level.png" alt="lvl"> ${us.getUserLevel()}
    | <img src="/img/icon/gold.png" alt="g"> ${us.getGold()} |
    <img src="/img/icon/silver.png" alt="e"> ${us.getSilver()}
</div>
<div class="mini-line" style="margin-top: 10px"></div>
<div class="center "><br><span class="grey"><a href="">Форум</a> | <a href="">Чат</a> | <a
                href="/rating/">Рейтинг</a></span>
    <div class="mb10"></div>
    <span class="small grey"><!-- 100 --> ${aTime?iso_utc}
            <br>vk.com/kolya0598 © 2022, 18+</span>
</div>

</@c.page>
<#--<ul class="nav justify-content-center"-->
<#--    style="background-color: white; margin-left: -15px; margin-right: -15px; padding: 15px">-->


<#--    <li class="nav-item">-->
<#--        <a class="nav-link active" href="/">{}</a>-->
<#--    </li>-->
<#--    <li class="nav-item">-->
<#--        <a class="nav-link active" href="/">Home</a>-->
<#--    </li>-->
<#--    <li class="nav-item">-->
<#--        <a class="nav-link active" href="/arena">Арена</a>-->
<#--    </li>-->
<#--    &lt;#&ndash;    <li class="nav-item">&ndash;&gt;-->
<#--    &lt;#&ndash;        <a class="nav-link" href="/people">CRUD</a>&ndash;&gt;-->
<#--    &lt;#&ndash;    </li>&ndash;&gt;-->
<#--    <li class="nav-item">-->
<#--        <a class="nav-link" href="/profile">Рейтинг</a>-->
<#--    </li>-->
<#--    <li class="nav-item">-->
<#--        <a class="nav-link" href="/clan">Клан</a>-->
<#--    </li>-->
<#--    <li class="nav-item">-->
<#--        <a class="nav-link" href="/clanrating">Рейтинг кланов</a>-->
<#--    </li>-->
<#--    <#if isAdmin>-->
<#--        <li class="nav-item">-->
<#--            <a class="nav-link" href="/user">Users</a>-->
<#--        </li>-->
<#--    <#else>-->
<#--    </#if>-->
<#--    <#if isLoggedIn>-->
<#--        <div>-->
<#--            <a href="/profile/${id}" type="submit" class="btn btn-primary">${name}</a>-->
<#--        </div>-->
<#--        <form class="ms-2" id="demo-form" method="post" action="/logout">-->
<#--            <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--            <button type="submit" class="btn btn-primary">Выйти</button>-->
<#--        </form>-->
<#--    <#else>-->
<#--        <li class="nav-item">-->
<#--            <a href="/login" class="btn btn-primary">Войти</a>-->
<#--        </li>-->
<#--        <li class="nav-item ms-2">-->
<#--            <a href="/registration" class="btn btn-primary">Регистрация</a>-->
<#--        </li>-->
<#--    </#if>-->
<#--</ul>-->