<#import "../parts/comp.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page>
<div class="head " style="position:relative;" onclick="location.href='/';"><span class="ttl fl">Казна клана</span>
    <span class="bl rght nwr"><img src="/img/icon/health.png" alt="hp"> <span class="white">${us.getHealth()}</span>
            | <img src="/img/icon/mana.png" alt="mp"> ${us.getMana()}</span>
    <div class="clr"></div>
</div>
<div class="exp_bar">
    <div class="progress" style="width:${exp}%"></div>
</div>
<#if clan??>
<div class="center">
    <div class="block_zero"><img src="/img/icon/chest.png" alt="Казна клана"></div>
    <div class="block_zero"><span class="blue bold">Рейтинг пополнений</span><br>
    </div>
</div>
<div class="block_zero">
<#if usersRatingGold??>
    <#list usersRatingGold as user>


            <img src="/img/icon/0.png" alt=""> <a
                    href="/profile/${user.getId()}/">
            ${user.getUsername()}</a> - <img src="/img/icon/gold.png" alt=""><b>${user.getAmountGoldForClan()},
        <img src="/img/icon/silver.png" alt="">${user.getAmountSilverForClan()}

            </b>
        <br>


    </#list>

<#else>
    Нет пополнений
</#if>
</div>

<div class="mini-line"></div>
<div class="menuList">
    <li><a href="/clan/${clan.getId()}/"><img src="/img/icon/arrow.png" alt="">Вернуться в клан</a></li>
</div>
<div class="line"></div>
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
    </#if>

    </@c.page>

