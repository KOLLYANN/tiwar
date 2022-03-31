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
    <div class="center">
    <#if auth.getStartAttackColiseum()??>


        <div class="rght mlr5 mt5">
            <div class="fl left w50">
                <img src="/img/icon/0.png" alt=""> ${auth.getUsername()}
                <span class="nwr">
                    <img src="/img/icon/health.png" alt="hp"> ${auth.getHealth()}
                </span>
            </div>
            <#if usrAttack??>
                <img src="/img/icon/0.png" alt=""> ${usrAttack.getUsername()}
                <span class="nwr">
                <img src="/img/icon/health.png" alt="hp">&nbsp;${usrAttack.getHealth()}
            </span>
            </#if>
            <div class="clr"></div>
        </div>
        <div class="block_fight coliseum">
            <div class="bf_left">
                <div class="bf_right">
                    <div class="unit_slot">
                        <div class="fprg">
                            <div class="rate fl" style="width:${usrHealth}%;"></div>
                        </div>
                    </div>
                    <div class="unit_slot s_enemy">
                        <div class="fprg">
                            <#if usrAttackExp??>
                                <div class="rate fl" style="width:${usrAttackHealth}%;"></div>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="fight_buttons">
            <form method="post" action="/coliseum/atk" style="float: left">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <span class="lbl">
                    <button type="submit" class="nbtn b_red btn-attack-cd"
                            style="background-color: crimson">Атаковать</button>
                </span>
            </form>
            <form method="post" action="/coliseum/atkrnd" style="float: left">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <span class="lbl">
                    <button type="submit" class="nbtn b_red btn-attack-cd" style="background-color: slategrey">Сменить цель</button>
                </span>
            </form>
            <form method="post" action="/coliseum/exit">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <span class="lbl">
                    <button type="submit" class="nbtn b_red btn-attack-cd"
                            style="background-color: slategrey">Выход</button>
                </span>
            </form>
        </div>
        </div>
        <div class="mini-line"></div>
        <div class="block_zero">На арене: ${countUsers!"0"} гладиатор(ов)</div>
        <div class="mini-line"></div>
        <div class="block_zero">
            <#if attackList??>
            <#list attackList as attack>
<#--            <#if victory??>-->
<#--                <div class="error">Вы убили <img src="/img/icon/0.png" alt=""> ${usrAttack.getUsername()}</div>-->
<#--            </#if>-->
            <#if attack.getUser().equals(auth)>
            <span class="white"> Вы ударили <img src="/img/icon/0.png" alt=""> ${usrAttack.getUsername()} на
                ${attack.getDamage()}</span><br>
            <#else>
            <span class="dred"><img src="/img/icon/0.png" alt=""> ${usrAttack.getUsername()} ударил Вас на
                <span class="bold">${attack.getDamage()}</span><br>
                </#if>

                </#list>
                    <br>Сражение началось!<br>
            </#if>
        </div>
    <#else>
        <div class="block_zero"><img src="/img/col.jpg" width="100%" alt=""></div>
        <div class="block_zero"><img src="/img/icon/1.png" alt=""> <span
                    class="dgreen bold">Победи своих врагов</span>
            <img src="/img/icon/1.png" alt=""><br><span class="blue">На арене <span
                        class="bold error">${countUsers!"0"}</span> гладиатор(ов)!</span><br>
            <div class="separ"></div>
            <div class="fight_buttons">
                <form method="post" action="/coliseum/startatk">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <span class="lbl">
                    <button type="submit" class="btn" style="background-color: white">Выйти на арену</button>
                </span>
                </form>
                <br>
            </div>
        </div>
        <div class="dot-line"></div>
        <div class="block_zero center"><img src="/img/icon/2hit.png" alt=""> <span
                    class="dgreen bold">Турнир колизея!</span>
            <img src="/img/icon/2hit.png" alt=""><br>
            <div class="separ"></div>
            <div class="block_zero">Черепа: <img src="/img/icon/skull.png" alt=""> ${auth.getSkull()} из 25<br></div>
            <div class="block_zero medium">Накопи 25 черепов и прими участие в розыгрыше 5000 золота!<br>Если
                накопишь
                125 черепов, то примешь участие в розыгрыше 7500 золота!<br>А если соберёшь 250 черепов, то примешь
                участие в розыгрыше 10000 золота!<br></div>
            <div class="block_zero">До конца турнира осталось <span id="time_198286000">2 дня</span>!</div>
        </div>
        </div>
    </#if>
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