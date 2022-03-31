<#import "../parts/comp.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page>
    <div class="head " style="position:relative;" onclick="location.href='/';"><span class="ttl fl">Магазин</span>
        <span class="bl rght nwr"><img src="/img/icon/health.png" alt="hp"> <span
                    class="white">${us.getHealth()}</span>
            | <img src="/img/icon/mana.png" alt="mp"> ${us.getMana()}</span>
        <div class="clr"></div>
    </div>
    <div class="exp_bar">
        <div class="progress" style="width:${exp}%"></div>
    </div>
    <div class="block_zero center blue">Лучшее снаряжение только тут!</div>
    <div class="mini-line"></div>
    <div class="block_zero center red"> <#if success??>
            <span>Куплено</span>
            <div class="mini-line"></div>
        </#if></div>
    <#if complect1??>
        <#list complect1 as complect>
           <div class="block_zero">
        <div class="item50 ibl fl"
             style="background-image:url(/img/thingsuser/${complect.getPathImage()}.png);margin-right:5px;">
            <div class="it_brd ${complect.getBorder()} sh8">
                <div class="it_stat1"></div>
                <div class="clb"></div>
                <div class="it_stat2 cntr">
                    <div class="it_stars"></div>
                </div>
            </div>
        </div>
        ${complect.getTitle()}
        <span class="small"><br><img src="/img/icon/grade/${complect.getMiniGrade()}.png" alt=""> <span
                    class="${complect.getQuality()}">${complect.getGrade()}</span><br>
            <span class="green">+${complect.getParameters()} к параметрам</span></span>
        <div style="clear:both;"></div>
        <div class="center">
            <form method="post" action="/shop/buy/thing">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="idThing" value="${complect.getId()}"/>
                <span class="end">
                    <button type="submit" class="label">Купить за <img src="/img/icon/gold.png" alt="gold"> ${complect.getPrice()} золота</button>
                </span>
            </form>
        </div>
    </div>
            <div class="mini-line"></div>
    </#list>
    </#if>
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