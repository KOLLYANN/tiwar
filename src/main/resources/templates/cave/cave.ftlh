<#import "../parts/comp.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page>
    <div class="head " style="position:relative;" onclick="location.href='/';"><span class="ttl fl">Олимп</span>
        <span class="bl rght nwr"><img src="/img/icon/health.png" alt="hp"> <span class="white">${us.getHealth()}</span>
            | <img src="/img/icon/mana.png" alt="mp"> ${us.getMana()}</span>
        <div class="clr"></div>
    </div>
    <div class="exp_bar">
        <div class="progress" style="width:${exp}%"></div>
    </div>

    <div class="block_zero center blue">Великие сражения титанов!<br></div>
    <div class="line"></div>
    <div class="menuList">
        <#if us.getIdBossAttack()??>
        <li><a href="/boss/${us.getIdBossAttack()!"1"}"><img src="/img/cave/1.png" alt="">Циклоп<span class="white"></span></a>
        </li>
            <#else>
                <li><a href="/boss/1"><img src="/img/cave/1.png" alt="">Циклоп <span class="white"></span></a>
                </li>
        </#if>
        <li><a href="/boss/2"><img src="/img/cave/2.png" alt="">Кентавр <span class="white"></span></a></li>
        <li><a href="/boss/3"><img src="/img/cave/4.png" alt="">Гаргона</a></li>
        <li><a href="/boss/4"><img src="/img/cave/3.png" alt="">Минотавр</a></li>
        <li><a href="/boss/5"><img src="/img/cave/5.png" alt="">Амазонка</a></li>
        <li><a href="/boss/6"><img src="/img/cave/6.png" alt="">Цербер</a></li>
        <li><a href="/boss/7"><img src="/img/cave/7.png" alt="">Сирена</a></li>
        <li><a href="/boss/8"><img src="/img/cave/8.png" alt="">Анубис</a></li>
        <li><a href="/boss/9"><img src="/img/cave/9.png" alt="">Хаос</a></li>
        <li><a href="/boss/10"><img src="/img/cave/10.png" alt="">Аид</a></li>
    </div>


    <div class="line"></div>
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
    <div class="center "><br><span class="grey"><a href="">Форум</a> | <a href="/chat">Чат</a> | <a
                    href="/rating/">Рейтинг</a></span>
        <div class="mb10"></div>
        <span class="small grey"><!-- 100 --> ${aTime?iso_utc}
            <br>vk.com/kolya0598 © 2022, 18+</span>
    </div>
</@c.page>