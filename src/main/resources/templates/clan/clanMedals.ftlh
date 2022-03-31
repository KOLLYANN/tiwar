<#import "../parts/comp.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page>
    <div class="head " style="position:relative;" onclick="location.href='/';"><span class="ttl fl">Клан</span>
        <span class="bl rght nwr"><img src="/img/icon/health.png" alt="hp"> <span class="white">${us.getHealth()}</span>
            | <img src="/img/icon/mana.png" alt="mp"> ${us.getMana()}</span>
        <div class="clr"></div>
    </div>
    <div class="exp_bar">
        <div class="progress" style="width:${exp}%"></div>
    </div>
    <#if clan??>
    <div class="center">
        <div class="block_zero"><img src="/img/icon/trc.png" alt="Трофеи клана"></div>
        <div class="block_zero"><span class="blue bold">Трофеи Клана</span><br>
        </div>
    </div>
    <div class="line"  style="margin-bottom: 10px"></div>
    <div class="menuList">
        <li><img src="/img/cave/1.png" alt="">Циклоп <span class="error">${clan.getCountB1()}</span></li>
        <li><img src="/img/cave/2.png" alt="">Кентавр <span class="error">${clan.getCountB2()}</span></li>
        <li><img src="/img/cave/4.png" alt="">Гаргона <span class="error">${clan.getCountB3()}</span></li>
        <li><img src="/img/cave/3.png" alt="">Минотавр <span class="error">${clan.getCountB4()}</span></li>
        <li><img src="/img/cave/5.png" alt="">Амазонка <span class="error">${clan.getCountB5()}</span></li>
        <li><img src="/img/cave/6.png" alt="">Цербер <span class="error">${clan.getCountB6()}</span></li>
        <li><img src="/img/cave/7.png" alt="">Сирена <span class="error">${clan.getCountB7()}</span></li>
        <li><img src="/img/cave/8.png" alt="">Анубис <span class="error">${clan.getCountB8()}</span></li>
        <li><img src="/img/cave/9.png" alt="">Хаос <span class="error">${clan.getCountB9()}</span></li>
        <li><img src="/img/cave/10.png" alt="">Аид <span class="error">${clan.getCountB10()}</span></li>
    </div>
        <div class="line"  style="margin-top: 10px"></div>
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
        </#if>
</@c.page>