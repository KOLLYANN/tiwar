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
    <div class="block_zero center blue">Лучшее снаряжение только тут!</div>
    <div class="mini-line"></div>

    <div class="menuList">
        <li class="original"><a class="white" href="/shop/complect/6">
                <img src="/img/icon/grade/6.png" alt="" width="30" height="30"
                     style="float:left;margin-right:3px;margin-top:3px;">
                <img src="/img/icon/grade/66.png" alt=""><span class="quality-6">Божественные вещи</span><br>
                <span class="medium"><img src="/img/icon/skill.png" alt="">Мастерство: 303</span>
                <div style="clear:both;"></div>
            </a></li>
        <li class="original"><a class="white" href="/shop/complect/5">
                <img src="/img/icon/grade/5.png" alt="" width="30" height="30"
                     style="float:left;margin-right:3px;margin-top:3px;">
                <img src="/img/icon/grade/55.png" alt=""><span class="quality-5">Легендарные вещи</span><br>
                <span class="medium"><img src="/img/icon/skill.png" alt="">Мастерство: 300</span>
                <div style="clear:both;"></div>
            </a></li>
        <li class="original"><a class="white" href="/shop/complect/4">
                <img src="/img/icon/grade/4.png" alt="" width="30" height="30"
                     style="float:left;margin-right:3px;margin-top:3px;">
                <img src="/img/icon/grade/44.png" alt=""><span class="quality-4">Эпические вещи</span><br>
                <span class="medium"><img src="/img/icon/skill.png" alt="">Мастерство: 200</span>
                <div style="clear:both;"></div>
            </a></li>
        <li class="original"><a class="white" href="/shop/complect/3">
                <img src="/img/icon/grade/3.png" alt="" width="30" height="30"
                     style="float:left;margin-right:3px;margin-top:3px;">
                <img src="/img/icon/grade/33.png" alt=""><span class="quality-3">Редкие вещи+</span><br>
                <span class="medium"><img src="/img/icon/skill.png" alt="">Мастерство: 100</span>
                <div style="clear:both;"></div>
            </a></li>
        <li class="original"><a class="white" href="/shop/complect/2">
                <img src="/img/icon/grade/3.png" alt="" width="30" height="30"
                     style="float:left;margin-right:3px;margin-top:3px;">
                <img src="/img/icon/grade/33.png" alt=""><span class="quality-3">Редкие вещи</span><br>
                <span class="medium"><img src="/img/icon/skill.png" alt="">Мастерство: 50</span>
                <div style="clear:both;"></div>
            </a></li>
        <li class="original"><a class="white" href="/shop/complect/1">
                <img src="/img/icon/grade/2.png" alt="" width="30" height="30"
                     style="float:left;margin-right:3px;margin-top:3px;">
                <img src="/img/icon/grade/11.png" alt=""><span class="quality-2">Обычные вещи</span><br>
                <span class="medium"><img src="/img/icon/skill.png" alt="">Мастерство: 1</span>
                <div style="clear:both;"></div>
            </a></li>
    </div>

    <div class="mini-line"></div>
    <div class="block_zero"><img src="/img/icon/skill.png" alt=""> Мастерство: <b>${us.getSkill()}</b>
        <ul class="hint">
            <li>Мастерство можно повысить, прокачивая параметры в <img src="/img/icon/train.png" alt=""> <a
                        href="/train/">Тренировке</a></li>
            <li>В магазине снаряжения покупаются новые вещи</li>
        </ul>
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
        <div class="center "><br><span class="grey"><a href="">Форум</a> | <a href="/chat">Чат</a> | <a
                        href="/rating/">Рейтинг</a></span>
            <div class="mb10"></div>
            <span class="small grey"><!-- 100 --> ${aTime?iso_utc}
            <br>vk.com/kolya0598 © 2022, 18+</span>
        </div>
    </div>
</@c.page>