<#import "../parts/comp.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page>
    <div class="head " style="position:relative;" onclick="location.href='/';"><span class="ttl fl">Рейтинг</span>
        <span class="bl rght nwr"><img src="/img/icon/health.png" alt="hp"> <span class="white">${us.getHealth()}</span>
            | <img src="/img/icon/mana.png" alt="mp"> ${us.getMana()}</span>
        <div class="clr"></div>
    </div>
    <div class="exp_bar">
        <div class="progress" style="width:${exp}%"></div>
    </div>
    <div class="menuList">
        <li>Лучшие</li>
    </div>
    <div class="mini-line"/>
    <#list profile as user>
        <div class="block_zero"><span class="white"></span>${user?counter}. <img src="/img/icon/0.png" alt=""> <a
                    href="/profile/${user.getId()}">${user.getUsername()}</a>
            <img src="/img/icon/level.png" alt="str"> ${user.getUserLevel()}
            <br>Мастерство: <img src="/img/icon/str.png" alt="">
            <b>
                ${user.getSkill()}
            </b>
        </div>

    <#else>
        Нет кланов
    </#list>
    <ul class="hint">
        <li>Вы на ${position+1} месте</li>
    </ul>
    <div class="line"></div>
    <div class="menuList">
        <li><a href="/clanrating"><img src="/img/clanAva.png" style="width: 15px; height: 15px" alt="">Рейтинг кланов</a></li>
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
    <div class="center "><br><span class="grey"><a href="">Форум</a> | <a href="/chat">Чат</a> | <a
                    href="/rating/">Рейтинг</a></span>
        <div class="mb10"></div>
        <span class="small grey"><!-- 100 --> ${aTime?iso_utc}
            <br>vk.com/kolya0598 © 2022, 18+</span>
    </div>
</@c.page>