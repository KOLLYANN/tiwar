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
        <div class="block_zero">
            <div class="gerb ibl fl mr5" style="background-image:url(/img/clanAva.png);"><img
                        src="/img/clanAva.jpg" width="54" height="54" alt=""></div>
            <img src="/img/icon/clan/0.png" alt=""> <b>${clan.getTitle()}</b> <br>
            <img src="/img/icon/level.png" alt=""> Уровень: <b>${clan.getLevel()}</b>
            <br>
            <img src="/img/icon/exp.png" alt=""> Опыт: ${clan.getExp()}/${clan.getExpy()} <span
                    class="grey">(${expPercentClan}%)</span><br>
            <div style="clear:both;"></div>
            <div class="stat_bar">
                <div class="progress" style="width: ${expClan}%"></div>
            </div>
        </div>


        <div class="block_zero">
            <a href="/clan/medals/${clan.getId()}">
                <#if clan.countB1 != 0>
                    <img src="/img/cave/1.jpg" width="24" height="24" alt="" style="margin-right:3px;margin-top:3px;">
                </#if>
                <#if clan.countB2 != 0>
                    <img src="/img/cave/2.jpg" width="24" height="24" alt="" style="margin-right:3px;margin-top:3px;">
                </#if>
                <#if clan.countB3 != 0>
                    <img src="/img/cave/4.jpg" width="24" height="24" alt="" style="margin-right:3px;margin-top:3px;">
                </#if>
                <#if clan.countB4 != 0>
                    <img src="/img/cave/3.jpg" width="24" height="24" alt="" style="margin-right:3px;margin-top:3px;">
                </#if>
                <#if clan.countB5 != 0>
                    <img src="/img/cave/5.jpg" width="24" height="24" alt="" style="margin-right:3px;margin-top:3px;">
                </#if>
            </a>
            <div style="clear:both;"></div>
        </div>


        <div>
        <div class="menuList">
                    <li><a style="padding-bottom:2px;padding-top:2px;" href="/profile/${ownerClan.getId()}">
                            <img src="/img/ownerClan.png" width="25px" height="25px"
                                 alt=""> ${ownerClan.getUsername()}<span class="not_here white">'</span>,
                            <span class="white"><span class="green">Лидер клана</span></span><br>
                            <span class="grey"> ${ownerClan.getExpForClan()} опыта</span></a>
                    </li>
            <#list clanGetUsers as usersInclan>
                <#if usersInclan.getId() != clan.getOwnerId()>
                    <li><a style="padding-bottom:2px;padding-top:2px;" href="/profile/${usersInclan.getId()}">
                            <img src="/img/icon/0.png" alt="">, ${usersInclan.getUsername()}
                            <span class="white"><span class="blue">Титан</span></span><br><span
                                    class="grey"> ${usersInclan.getExpForClan()} опыта</span></a></li>
                </#if>
            </#list>
        </div>
        <div class="mini-line"></div>
        <div class="menuList">
            <li>
                <#if us.getClan()?? == false>
                    <form method="post" action="/clan/subscribe">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input type="hidden" name="id" value="${clan.getId()}">
                        <button type="submit">Подать заявку</button>
                    </form>
                </#if>
            </li>
        </div>
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
        <div class="center "><br><span class="grey"><a href="">Форум</a> | <a href="">Чат</a> | <a
                        href="/rating/">Рейтинг</a></span>
            <div class="mb10"></div>
            <span class="small grey"><!-- 100 --> ${aTime?iso_utc}
            <br>vk.com/kolya0598 © 2022, 18+</span>
        </div>


    <#else>
        Not clan
    </#if>
</@c.page>