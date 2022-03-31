<#import "../parts/comp.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page>
    <div class="head " style="position:relative;" onclick="location.href='/';"><span class="ttl fl">Снаряжение</span>
        <span class="bl rght nwr"><img src="/img/icon/health.png" alt="hp"> <span
                    class="white">${auth.getHealth()}</span>
            | <img src="/img/icon/mana.png" alt="mp"> ${auth.getMana()}</span>
        <div class="clr"></div>
    </div>
    <div class="exp_bar">
        <div class="progress" style="width:${exp}%"></div>
    </div>
    <div class="block_zero">
        <#list things as thing>
            <#if thing.getState() == 1>
                <div class="item50 ibl fl"
                     style="background-image:url(/img/thingsuser/${thing.getPathImage()}.png);margin-right:5px;">
                    <div class="it_brd ${thing.getBorder()} sh8">
                        <div class="it_stat1"></div>
                        <div class="clb"></div>
                        <div class="it_stat2 cntr">
                            <div class="it_stars"></div>
                            ${thing.getSmith()}/90
                        </div>
                    </div>
                </div>
                ${thing.getTitle()}
                <span class="small"><br>
                <img src="/img/icon/grade/${thing.getMiniGrade()}.png" alt="">
                <span class="${thing.getQuality()}">${thing.getGrade()}</span><br>
                   <img src="/img/icon/str.png" alt="">
                <span class="green">+ ${thing.getParameters()} к параметрам</span><br>
                 </span>
                <div style="clear:both;"></div>
                <div class="mini-line" style="margin-top: 10px"></div>
            </#if>
        </#list>
    </div>


    <div class="line"></div>
    <div class="player menuList">
        <li><a href="/profile/${id}"><img src="/img/icon/user.png" alt="">Мой герой</a></li>
        <li><a href="/clan/"><img src="/img/icon/user.png" alt="">Мой клан<span class="green"> (+)</span></a>
        </li>
        <li class="noBorder"><a href="/"><img src="/img/icon/arrow.png" alt="">На главную</a></li>
    </div>
    <div class="line"></div>
    <div class="foot">
        <div><img src="/img/icon/0.png" alt=""> ${auth.getUsername()}<span class="float-right medium"><a
                        href="#">Настройки</a></span></div>
        <div class="center"><img src="/img/icon/level.png" alt="lvl"> ${auth.getUserLevel()}
            | <img src="/img/icon/gold.png" alt="g"> ${auth.getGold()} |
            <img src="/img/icon/silver.png" alt="e"> ${auth.getSilver()}
        </div>
        <div class="mini-line" style="margin-top: 10px"></div>
        <div class="center "><br><span class="grey"><a href="">Форум</a> | <a href="/chat">Чат</a> | <a
                        href="/rating/">Рейтинг</a></span>
            <div class="mb10"></div>
            <span class="small grey"><!-- 100 --> ${aTime?iso_utc}
            <br>vk.com/kolya0598 © 2022, 18+</span>
        </div>
    </div>
</@c.page>