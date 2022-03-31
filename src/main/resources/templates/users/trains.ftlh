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
    <div class="block_zero center blue">Улучшай параметры своего героя!</div>
    <div class="center">
        <div class="block_zero"><img src="/img/icon/train.jpg" width="100%" alt=""></div>
    </div>
    <#if notSilver??>
        <#if notSilver == 0>
    <div class="block_zero center red">Недостаточно серебра
        <br>
        <a href="/arena">На арену</a>
    </div>
            <div class="line"></div>
        </#if>
    </#if>
    <div class="block_zero"><img src="/img/icon/str.png" alt=""> Сила: ${us.getPower()}
        <span class="grey">(урон 50)</span><br>
        <span class="small">+10 к силе</span><br>
        <span class="small">
            <div class="stat_bar" style="margin:2px 0px 2px 0px">
                <div class="progress" style="padding:0px;height:4px;background-color: #3C3;width:${us.getSkillPower()}%">
                    <div class="progress" style="padding:0px;height:4px;width:99%"></div>
                </div>
            </div>Чем больше сила, тем больше урона нанесёшь врагу!</span>
        <div class="center">
            <form class="button" method="post" action="/train/addPower">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" style="background: white; color: black; border: none">Улучшить за <img src="/img/icon/silver.png"> ${us.getPricePower()}</button>
            </form>
        </div>
        <div class="line"/>
    </div>
    <div class="block_zero"><img src="/img/icon/health.png" alt=""> Здоровье: ${us.getMaxHealth()} <br>
        <span class="small">+50 к здоровью</span><br>
        <span class="small">
            <div class="stat_bar" style="margin:2px 0px 2px 0px">
                <div class="progress" style="padding:0px;height:4px;background-color: #3C3;width:${us.getSkillHealth()}%">
                    <div class="progress" style="padding:0px;height:4px;width:99%"></div>
                </div>
            </div>Здоровья много не бывает</span>
        <div class="center">
<#--            <a class="btn" href="/train/str?r=659780"><span class="end">-->
<#--                    <span class="label">Улучшить за <img src="/img/icon/gold.png" alt="silver"> 1600</span>-->
<#--                </span>-->
<#--            </a>-->
            <form class="button" method="post" action="/train/addHealth">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" style="background: white; color: black; border: none">Улучшить за <img src="/img/icon/silver.png"> ${us.getPriceHealth()}</button>
            </form>

        </div>
        <div class="line"/>
    </div>

    <div class="block_zero"><img src="/img/icon/mana.png" alt=""> Мана: ${us.getMaxMana()}<br>
        <span class="small">+50 к мане</span><br>
        <span class="small">
            <div class="stat_bar" style="margin:2px 0px 2px 0px">
                <div class="progress" style="padding:0px;height:4px;background-color: #3C3;width:${us.getSkillMana()}%">
                    <div class="progress" style="padding:0px;height:4px;width:99%"></div>
                </div>
            </div>Увеличивает запас энергии</span>
        <div class="center">
            <form class="button" method="post" action="/train/addMana">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" style="background: white; color: black; border: none">Улучшить за <img src="/img/icon/silver.png"> ${us.getPriceMana()}</button>
            </form>
        </div>
    </div>
    <div class="mini-line"/>
    <div class="block_zero"><img src="/img/icon/skill.png" alt=""> Мастерство: <b>${us.getSkill()}</b></div>
    <ul class="hint">
        <li>Прокачивая параметры, вы увеличиваете мастерство</li>
        <li>Чем выше мастерство, тем более крутые вещи доступны в <img src="/images/icon/equip.png" alt=""> <a
                    href="/shop/">Магазине снаряжения</a></li>
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