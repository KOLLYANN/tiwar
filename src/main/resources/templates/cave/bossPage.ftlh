<#import "../parts/comp.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page>
    <div class="head " style="position:relative;" onclick="location.href='/';"><span class="ttl fl">Пещера</span>
        <span class="bl rght nwr"><img src="/img/icon/health.png" alt="hp"> <span class="white">${us.getHealth()}</span>
            | <img src="/img/icon/mana.png" alt="mp"> ${us.getMana()}</span>
        <div class="clr"></div>
    </div>
    <div class="exp_bar">
        <div class="progress" style="width:${exp}%"></div>
    </div>

    <div class="menuList">
        <#if boss??>
        <li><a href="/boss/${boss.getId()}"><img src="/img/cave/1.jpg" alt="">
                ${boss.getName()}<br>
                ${boss.getPower()}<br>
                ${boss.getHealth()} / ${boss.getMaxHealth()}<br>
                </a>
        </li>
        </#if>
        <div class="stat_bar" style="margin:2px 0px 2px 0px">
            <div class="progress" style="padding:0px;height:4px;color: red;width:${bossExpBar!"100"}%">
                <div class="progressBoss" style="padding:0px;height:4px;width:99%"></div>
            </div>
    </div>
    <div class="mb5"></div>
    </div>

    <form method="post" action="/boss/attack" style="padding-top: 10px">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="hidden" name="bossId" value="${boss.getId()}">
        <button  style="border: none; background: none" type="submit"><img src="/img/atk.png" alt="attack"></button>
    </form>


    <div class="line" style="margin-top: 30px"></div>
    <div class="player menuList">
        <li><a href="/profile/${us.getId()}"><img src="/img/icon/user.png" alt="">Мой герой</a></li>
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