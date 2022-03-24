<#import "../parts/comp.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page>
    <div class="head " style="position:relative;" onclick="location.href='/';"><span class="ttl fl">Сумка</span>
        <span class="bl rght nwr"><img src="/img/icon/health.png" alt="hp"> <span
                    class="white">${us.getHealth()}</span>
            | <img src="/img/icon/mana.png" alt="mp"> ${us.getMana()}</span>
        <div class="clr"></div>
    </div>
    <div class="exp_bar">
        <div class="progress" style="width:${exp}%"></div>
    </div>
    <div class="block_zero">
        <#list things as thing>
            <#if thing.getState() == 0>
                <div class="item50 ibl fl"
                     style="background-image:url(/img/thingsuser/${thing.getPathImage()}.png);margin-right:5px;">
                    <div class="it_brd q2 sh8">
                        <div class="it_stat1"></div>
                        <div class="clb"></div>
                        <div class="it_stat2 cntr">
                            <div class="it_stars"></div>
                        </div>
                    </div>
                </div>
                ${thing.getTitle()}
                <span class="small"><br>
                <img src="/img/icon/grade/cave.png" alt="">
                <span class="quality-2">${thing.getGrade()}</span>
                 </span>
                <div style="clear:both;"></div>
                <div class="center">
                    <div style="display:inline-block;">
                        <div class="float-left">
                            <#if error??>
                            <form method="post" action="/profile/bag/sell">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <input type="hidden" name="thingId" value="${thing.getId()}"/>
                                <button type="submit">Продать за <img src="/img/icon/gold.png" alt=""> ${thing.getPrice() / 3}</button>
                            </form>
                             <#else>
                                 <form method="post" action="/profile/bag/sell">
                                     <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                     <input type="hidden" name="thingId" value="${thing.getId()}"/>
                                     <button type="submit">Продать за <img src="/img/icon/gold.png" alt=""> ${thing.getPrice() / 3}</button>
                                 </form>

                            <div class="separ"></div>
                            <form method="post" action="/profile/bag/putOn">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <input type="hidden" name="thingId" value="${thing.getId()}"/>
                                <input type="hidden" name="thingPosition" value="${thing.getPosition()}"/>
                                <input type="hidden" name="thingState" value="${thing.getState()}"/>
                                <input type="hidden" name="thingPlace" value="${thing.getPlace()}"/>
                                <button type="submit">Надеть</button>
                            </form>
                            </#if>
                        </div>
                    </div>
                </div>
                <div style="clear:both;"></div>
                <div class="mini-line"></div>
            </#if>
        </#list>
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
        <div class="center "><br><span class="grey"><a href="">Форум</a> | <a href="">Чат</a> | <a
                        href="/rating/">Рейтинг</a></span>
            <div class="mb10"></div>
            <span class="small grey"><!-- 100 --> ${aTime?iso_utc}
            <br>vk.com/kolya0598 © 2022, 18+</span>
        </div>
    </div>
</@c.page>