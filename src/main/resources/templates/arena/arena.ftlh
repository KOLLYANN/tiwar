<#import "../parts/comp.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page>
    <div class="head " style="position:relative;" onclick="location.href='/';"><span class="ttl fl">Арена</span>
        <span class="bl rght nwr"><img src="/img/icon/health.png" alt="hp"> <span class="white">${us.getHealth()}</span>
            | <img src="/img/icon/mana.png" alt="mp"> ${us.getMana()}</span>
        <div class="clr"></div>
    </div>
    <div class="exp_bar">
        <div class="progress" style="width:${exp}%"></div>
    </div>
    <#if newLevel??>
        <#if newLevel == 1>
            <div class="block_outer center" style="margin:7px 4px 7px 4px;"><img src="/img/icon/2hit.png" alt=""> <span
                        class="dgreen bold">Поздравляем!</span> <img src="/img/icon/2hit.png" alt="">
                <div class="separ"></div>
                Ты получил <span class="dgreen">${us.getUserLevel() + 1}</span> уровень!<br><span
                        class="blue">Награда:</span> <img
                        src="/img/icon/gold.png" alt=""> ${us.getUserLevel() * 3} золота
                <div class="separ"></div>
                <div class="mb10"></div>
            </div>
        </#if>
    </#if>
    <div class="block_zero center blue">Побеждай врагов и получай награду!<br></div>
    <div class="mini-line"/>
    <#if notManaOrHealth??>
        <#if notManaOrHealth == 0>
            <div class="block_zero center">
            <span style="padding: 5px" class="dred">Для нападения надо минимум <img src="/img/icon/health.png" alt=""> 75 жизни и
        <img src="/img/icon/mana.png" alt=""> 75 энергии</span>
            </div>
        </#if>
    </#if>
    <#if random2?? && notManaOrHealth != 0>
        <#if random2 == 1 || random2 == 0>
            <div class="center">
                <div class="block_light"><h2 class="dgreen" style="font-weight:bold;"><img
                                src="/img/icon/2hit.png" alt=""> Победа! <img src="/img/icon/2hit.png" alt="">
                    </h2>
                    <div class="separ"></div>
                    <span class="blue">Награда:</span> <img src="/img/icon/silver.png" alt=""> ${addSilver!""}
                    серебра
                    <img src="/img/icon/exp.png" alt="exp"> ${addExp!""} опыта<br>
                    <div class="mb10"></div>
                </div>
            </div>
        <#else>
            <div class="center">
                <div class="block_light"><h2 class="dred" style="font-weight:bold;"><img
                                src="/img/icon/2hit.png" alt=""> Поражение! <img src="/img/icon/2hit.png" alt="">
                    </h2>
                    <div class="mb10"></div>
                </div>
            </div>
        </#if>
    <#else>
    </#if>
    <div class="mini-line"/>
    <div class="block_zero center">
        <div class="float-left"><img src="/images/icon/race/1.png" alt=""> Боец</div>
        <div class="float-right">Мощь: <img src="/images/icon/2hit.png" alt=""> ${powerOpponent}</div>
        <div class="clr mb5"></div>
        <div class="arena_bg center">
            <a href="">
            <form method="post" action="/arena/attack" style="border: none">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" style="border: none; background: none">
                    <div class="p_dummy"
                         style="background-image:url(/img/${boss});"></div>
                </button>

            </form>
            </a>
        </div>
        <div class="mb5"></div>

        <form method="post" action="/arena/attack" style="padding-top: 10px">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button  style="border: none; background: none" type="submit"><img src="/img/atk.png" alt="attack"></button>
        </form>
    </div>
    <div class="mini-line" style="margin-top: 10px"></div>
    <div class="block_zero">Моя мощь: <img src="/img/icon/2hit.png" alt=""> ${us.getPower() + us.getMaxHealth() + us.getMaxMana()}<br></div>
    <div class="mini-line"></div>
    <ul class="hint">
        <li>Чем сильнее противник, тем больше опыта и серебра получишь за победу!</li>
    </ul>
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