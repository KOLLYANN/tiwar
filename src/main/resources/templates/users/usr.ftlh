<#import "../parts/comp.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page>
<div class="head " style="position:relative;" onclick="location.href='/';"><span class="ttl fl">Профиль</span>
    <span class="bl rght nwr"><img src="/img/icon/health.png" alt="hp"> <span
                class="white">${auth.getHealth()}</span>
            | <img src="/img/icon/mana.png" alt="mp"> ${auth.getMana()}</span>
    <div class="clr"></div>
</div>
<div class="exp_bar">
    <div class="progress" style="width:${exp}%"></div>
</div>
<div class="block_zero center"><img src="/img/icon/0.png" alt=""> <b>${us.getUsername()}</b> <img
            src="/img/icon/level.png" alt=""> ${us.getUserLevel()} ур<br>
    <img src="/img/icon/clanAva.png" style="width: 10px; height: 10px" alt="">
    <#if us.getClan()??>
        <a href="/clan/">Клан: ${us.getClan().getTitle()}</a><br>
    </#if>
    <a class="white" href="">Мощь:
        <img src="/img/icon/2hit.png" alt=""> <span
                class="bold">${us.getMaxMana() + us.getMaxHealth() + us.getPower()} </span></a>
    </div>
<div class="block_zero">
        <table>
            <#if path??>
                <img class="user" src="/img/${path!""}" width="100px" height="100px" alt="ava">
            <#else>
                <img src="/img/ava.png" width="100px" height="100px" alt="ava">
            </#if>
        </table>
</div>
    <div class="mini-line"></div>
    <img src="/img/icon/str.png" alt="str"> Сила: ${us.getPower()}<br>
    <img src="/img/icon/health.png" alt="vit"> Жизнь: ${us.getMaxHealth()}<br>
    <img src="/img/icon/mana.png" alt="mana"> Энергия: ${us.getMaxMana()}<br>
    <div style="clear:both;"></div>
<div style="clear:both;"></div>
<div class="mini-line" style="margin-top: 5px"></div>
<#if user??>
    <#if id == id_user>
        <div class="menuList">
            <li><a href="/train/"><img src="/img/icon/train.png" alt="">Тренировка<span
                            class="green"> (+)</span></a>
            </li>
            <li><a href=""><img src="/img/icon/train.png" alt="">Усиления</a></li>
        </div>
        <div class="mini-line"></div>
        <div class="block_zero">
            <img src="/img/icon/exp.png" alt="exp"> Опыт: ${us.getExp()}/${us.getExpy()} <span
                    class="grey">(${expPercent}%)</span><br>
        </div>
    </#if>
</#if>

<#--    <div class="mini-line"></div>-->
<#--    <div class="block_zero"><img src="/img/icon/exp.png" alt="exp"> Опыт: ${us.getExp()} <span class="grey"></span><br>-->
<#--    </div>-->
<div class="mini-line"></div>
<div class="block_zero">
    <#if us.getSkill() < 100>
    <img src="/img/icon/master.png" alt="" width="30" height="30"
         style="float:left;margin-right:3px;margin-top:3px;">
    <span class="blue">Лига новичков</span><br>Mастерство: <b>${us.getSkill()}</b>
    <div style="clear:both;"></div>

    <#elseif us.getSkill() < 200>
        <img src="/img/icon/l1.png" alt="" width="30" height="30"
             style="float:left;margin-right:3px;margin-top:3px;"><span
            class="blue">Лига опытных</span><br>Mастерство: <b>${us.getSkill()}</b>
        <div style="clear:both;"></div>
    <#elseif us.getSkill() < 300>
    <img src="/img/icon/l2.png" alt="" width="30" height="30"
         style="float:left;margin-right:3px;margin-top:3px;">
    <span class="blue">Лига претендентов</span><br>Mастерство:
    <b>${us.getSkill()}</b>
    <div style="clear:both;"></div>
<#else>
        <img src="/img/icon/l5.png" alt="" width="30" height="30"
             style="float:left;margin-right:3px;margin-top:3px;"><span
                class="blue">Лига избранных</span><br>Mастерство: <b>${us.getSkill()}</b>
        <div style="clear:both;"></div>
</#if>

</div>


<div class="mini-line" style="margin-top: 5px"></div>
<#if user??>
    <#if id == id_user>

        <div class="block_zero">
            <a style="padding: 10px" href="/profile/${id}/config">Настройки</a>
        </div>

    </#if>
</#if>
<div class="line"></div>
<div class="player menuList">
    <li><a href="/profile/${auth.getId()}"><img src="/img/icon/user.png" alt="">Мой герой</a></li>
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
    <div class="center "><br><span class="grey"><a href="">Форум</a> | <a href="">Чат</a> | <a
                    href="/rating/">Рейтинг</a></span>
        <div class="mb10"></div>
        <span class="small grey"><!-- 100 --> ${aTime?iso_utc}
            <br>vk.com/kolya0598 © 2022, 18+</span>
    </div>
</div>

    <#--        <div class="nav-bar-cell1-rev">-->
    <#--            <p>Обновить данные</p>-->
    <#--            <form id="demo-form" method="post" action="/profile/${id_user}/edit">-->
    <#--                <div class="form-group row">-->
    <#--                    <label id="name">Имя:-->
    <#--                        <div class="col-sm-3">-->
    <#--                            <input class="form-control " type="text" name="name" id="name">-->
    <#--                        </div>-->
    <#--                    </label>-->
    <#--                </div>-->
    <#--                <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
    <#--                <div class="form-group row mt-2">-->
    <#--                    <label id="name">Email:-->
    <#--                        <div class="col-sm-3">-->
    <#--                            <input class="form-control" type="email" name="email" id="email">-->
    <#--                        </div>-->
    <#--                    </label>-->
    <#--                </div>-->
    <#--                &lt;#&ndash;        <p>${nameExists}</p>&ndash;&gt;-->
    <#--                <input type="hidden" name="id" placeholder="new id" value="${id}">-->
    <#--                <button class="btn btn-primary mt-2" type="submit">Сохранить</button>-->
    <#--            </form>-->
    <#--        </div>-->

    </@c.page>
