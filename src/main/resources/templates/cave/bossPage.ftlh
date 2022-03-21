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
    <#if newLevel??>
        <#if newLevel == 1>
            <div class="block_outer center" style="margin:7px 4px 7px 4px;"><img src="/img/icon/2hit.png" alt=""> <span
                        class="dgreen bold">Поздравляем!</span> <img src="/img/icon/2hit.png" alt="">
                <div class="separ"></div>
                Ты получил <span class="dgreen">${us.getUserLevel() + 1}</span> уровень!<br><span
                        class="blue">Награда:</span> <img
                        src="/img/icon/gold.png" alt=""> ${us.getUserLevel() + 5} золота
                <div class="separ"></div>
                <div class="mb10"></div>
            </div>
        </#if>
    </#if>

    <div class="menuList">
        <#if boss??>
            <li><a href="/boss/${boss.getId()}"><img src="/img/cave/${boss.getTitleAva()}.jpg" alt="">
                    ${boss.getName()}
                    <table>
                        <tr>
                            <td>
                            <td>
                        </tr>
                        <tr>
                            <td><img src="/img/icon/str.png"></td>
                            <td>${boss.getPower()}<br></td>
                        </tr>
                        <tr>
                            <td><img src="/img/icon/vit.png"></td>
                            <td> ${boss.getHealth()} / ${boss.getMaxHealth()}<br></td>
                        </tr>
                        <br>
                    </table>
                </a>
            </li>
            <div class="exp_bar" style="margin:2px 0px 2px 0px">
                <div class="progress" style="padding:0px;height:4px;background-color: red;width:${bossExpBar!"100"}%">
                </div>
            </div>
        </#if>
    </div>
    <div class="mini-line"></div>
    <#if startAttack??>
        <#if startAttack == 1>
            <div class="menuList">
                <li>
                    <form method="post" action="/boss/attack" style="padding-top: 10px; padding-bottom: 10px">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input type="hidden" name="bossId" value="${boss.getId()}">
                        <button style="border: none; background: red; color: white" type="submit">
                            <span style="color: white">Начать бой</span>
                        </button>
                    </form>
                </li>
            </div>
            <div class="mini-line"></div>
            <ul class="hint">
                <li>Чем больше у вас клан, тем более сильных противников сможете убить</li>
                <li>Чем выше у тебя параметры, тем больше урона нанесешь боссу</li>
                <li>Одновременно один клан, может бить только 1 босса</li>
            </ul>
        </#if>
    <#else>
        <#if us.getIdBossAttack()??>
            <form method="post" action="/boss/attack" style="padding-top: 10px">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="bossId" value="${boss.getId()}">
                <button style="border: none; background: none" type="submit"><img src="/img/atk.png" alt="attack">
                </button>
            </form>
            <#if topDamage??>
                <#list topDamage as userDamage>
                    <div class="block_zero"><img src="/img/icon/0.png" alt="">
                        ${userDamage.getUsername()}<br>
                        <img src="/img/icon/hit.png" alt=""> Нанесено урона: ${userDamage.getBossDamage()}<br>
                    </div>
                    <div class="mini-line"></div>
                </#list>
            <#else>
                Никто не нанес урон
            </#if>
            <div class="center">
            <div class="block_light">
                <div class="separ"></div>
                <span class="blue">За победу над этим боссом вы получите:</span><br>
                <#if boss.getPower() == 100>
                <img src="/img/icon/silver.png" alt=""> 50 серебра<br>
                <img src="/img/icon/exp.png" alt="exp"> 100 опыта<br>
                    <#elseif boss.getPower() == 500>
                        <img src="/img/icon/silver.png" alt=""> 250 серебра<br>
                        <img src="/img/icon/exp.png" alt="exp"> 500 опыта<br>
                <#elseif boss.getPower() == 1000>
                    <img src="/img/icon/silver.png" alt=""> 5000 серебра<br>
                    <img src="/img/icon/exp.png" alt="exp"> 1000 опыта<br>
                <#elseif boss.getPower() == 3000>
                    <img src="/img/icon/silver.png" alt=""> 15000 серебра<br>
                    <img src="/img/icon/exp.png" alt="exp"> 3000 опыта<br>
                <#elseif boss.getPower() == 5000>
                    <img src="/img/icon/silver.png" alt=""> 25000 серебра<br>
                    <img src="/img/icon/exp.png" alt="exp"> 5000 опыта<br>
                </#if>
                <div class="separ"></div>
            </div>
            </div>
        <#else>
            <form method="post" action="/boss/attack" style="padding-top: 10px">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="bossId" value="${boss.getId()}">
                <button disabled style="border: none; background: none" type="submit"><img src="/img/atk.png"
                                                                                           alt="attack"></button>
            </form>
        </#if>
    </#if>
    <div class="mini-line"></div>
    <div class="menuList">
        <#if us.getClan()??>
            <li><a href="/cave"><img src="/img/icon/cave.png" alt="">Пещера<span class="green"> </span>
                    <span class="grey small"></span>
                </a></li>
        </#if>
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
</@c.page>