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

    <div class="block_zero"><img src="/img/meadow.jpg" width="100%" alt=""></div>
    <div class="dot-line"></div>
    <div class="block_zero center"><span class="blue">На своем пути ты встретишь множество опасных существ!</span></div>
    <div class="dot-line"></div>
    <#if us.getCampaign()??>
        <div class="block_zero center">
            <div class="block_zero center"><img src="/images/icon/2hit.png" alt=""> Вы придете из похода через:
                <#if time??>
                <#--                    <span id="time_${time}" style="color: white"></span>-->
                    <span class="bold white" name="timer">00:${time}</span>

                </#if>
            </div>
        </div>
        </div>
    <#else>
        <div class="block_zero center"><img src="/img/icon/2hit.png" alt="">
            <div class="countdown">
                <span class="seconds"></span></div>
        </div>
        <div class="block_zero center">
            <form method="post" action="/campaign/start">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <span class="end">
                    <button type="submit" class="label">Отправиться в поход</button>
                </span>
            </form>
        </div>
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
    <script src="/static/timer.js" type="text/javascript"></script>
</@c.page>